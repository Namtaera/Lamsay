package garden_lamsay.garden.service;

import garden_lamsay.common.enums.PlantType;
import garden_lamsay.garden.dto.GardenPlantDto;
import garden_lamsay.garden.repository.GardenPlacementRepository;
import garden_lamsay.plant.Plant;
import garden_lamsay.plant.UnlockGarden;
import garden_lamsay.plant.repository.PlantRepository;
import garden_lamsay.plant.repository.UnlockGardenRepository;
import garden_lamsay.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class GardenService {

    private final PlantRepository plantRepository;
    private final UnlockGardenRepository unlockGardenRepository;

    public List<GardenPlantDto> getUserGardenPlants(User user) {
        var allPlants = plantRepository.findAll();

        // 1. 조회 -> 해금된 식물 (plantId → slot 맵핑)
        var unlockedMap = unlockGardenRepository.findByUser(user)
                .stream()
                .collect(Collectors.toMap(
                        u -> u.getPlant().getId(),
                        u -> u.getSlot(),   // ✅ 이렇게 변경
                        (existing, replacement) -> replacement   // 중복 시 최신값 사용
                ));
        return allPlants.stream()
                .map(plant -> {
                    int slot = unlockedMap.getOrDefault(plant.getId(), 0);
                    return GardenPlantDto.builder()
                            .plantId(plant.getId())
                            .plantName(plant.getPlantName())
                            .plantType(plant.getPlantType())
                            .unlocked(unlockedMap.containsKey(plant.getId()))  // ✅ 수정
                            .slot(slot)                                       // ✅ 수정
                            .build();
                })
                .sorted(Comparator
                        .comparingInt((GardenPlantDto dto) -> dto.getSlot() > 0 ? 0 : 1).reversed() // 배치된거 먼저
                        .thenComparing(GardenPlantDto::isUnlocked).reversed()
                        .thenComparing(GardenPlantDto::getPlantType)
                        .thenComparing(GardenPlantDto::getPlantName))
                .toList();
    }

    // 2. 슬롯별 교환 및 배치
    // ✅ 슬롯 규칙 정의
    private static final Map<Integer, PlantType> SLOT_RULES = Map.of(
            1, PlantType.FLOWER,
            2, PlantType.FLOWER,
            3, PlantType.GRASS,
            4, PlantType.GRASS,
            5, PlantType.TREE
    );

    /**
     * 슬롯에 식물 배치/교체하기
     */
    @Transactional
    public void placePlant(User user, Long plantId, int slot) {
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Plant not found"));

        // ✅ 슬롯 규칙 검증
        PlantType requiredType = SLOT_RULES.get(slot);
        if (requiredType == null) {
            throw new IllegalArgumentException("잘못된 슬롯 번호: " + slot);
        }
        if (plant.getPlantType() != requiredType) {
            throw new IllegalArgumentException(
                    "슬롯 " + slot + "에는 " + requiredType + "만 배치할 수 있습니다."
            );
        }

        // ✅ 유저가 해당 식물을 unlock 했는지 확인
        UnlockGarden unlock = unlockGardenRepository.findByUserAndPlant(user, plant)
                .orElseThrow(() -> new IllegalArgumentException("아직 해금되지 않은 식물입니다."));

        // ✅ 슬롯에 기존에 있던 식물 찾기
        unlockGardenRepository.findByUserAndSlot(user, slot).ifPresent(existing -> {
            existing.setSlot(0);  // 기존 식물은 미배치 상태로 되돌림
            unlockGardenRepository.save(existing);
        });

        // ✅ 새 식물 배치
        unlock.setSlot(slot);
        unlockGardenRepository.save(unlock);
    }
}