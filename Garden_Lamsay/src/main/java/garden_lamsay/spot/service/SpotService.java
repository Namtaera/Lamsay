package garden_lamsay.spot.service;

import garden_lamsay.plant.InventoryPlant;
import garden_lamsay.plant.UnlockGarden;
import garden_lamsay.plant.repository.InventoryPlantRepository;
import garden_lamsay.plant.repository.UnlockGardenRepository;
import garden_lamsay.spot.Spot;
import garden_lamsay.spot.SpotVisit;
import garden_lamsay.spot.dto.SpotDto;
import garden_lamsay.spot.repository.SpotRepository;
import garden_lamsay.spot.repository.SpotVisitRepository;
import garden_lamsay.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import garden_lamsay.plant.Plant;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;
    private final UnlockGardenRepository unlockGardenRepository;
    private final InventoryPlantRepository inventoryPlantRepository;
    private final SpotVisitRepository spotVisitRepository;

    // 1. 전체 스팟 조회
    public List<SpotDto> getAllSpots() {
        return spotRepository.findAll().stream()
                .map(spot -> SpotDto.builder()
                        .id(spot.getId())
                        .name(spot.getName())
                        .category(spot.getCategory())
                        .lat(spot.getLat())
                        .lng(spot.getLng())
                        .naverPlaceId(spot.getNaverPlaceId())
                        .address(spot.getAddress())
                        .description(spot.getDescription())
                        .plantId(spot.getPlant().getId())
                        .plantName(spot.getPlant().getPlantName())
                        .plantImageUrl(spot.getPlant().getPlantImageUrl())
                        .build())
                .toList();
    }

    // 2. 걷는 동안 스팟 리워드 처리
    @Transactional
    public void visitSpot(User user, Long spotId, int distanceToCenterM) {
        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스팟입니다."));

        // 1) 방문 기록 남기기
        SpotVisit visit = SpotVisit.builder()
                .user(user)
                .spot(spot)
                .distanceToCenterM(distanceToCenterM)
                .visitedAt(LocalDateTime.now())
                .build();
        spotVisitRepository.save(visit);

        Plant plant = spot.getPlant();

        // 2) unlock_garden 확인
        unlockGardenRepository.findByUserAndPlant(user, plant)
                .orElseGet(() -> {
                    UnlockGarden unlock = UnlockGarden.builder()
                            .user(user)
                            .plant(plant)
                            .slot(0) // 기본은 미배치
                            .build();
                    return unlockGardenRepository.save(unlock);
                });

        // 3) inventory_plant 확인 및 갱신
        inventoryPlantRepository.findByUserAndPlant(user, plant)
                .ifPresentOrElse(inv -> {
                    // 7시간 지났는지 확인
                    if (inv.getUpdatedAt() == null ||
                            inv.getUpdatedAt().isBefore(LocalDateTime.now().minusHours(7))) {
                        inv.setQuantity(inv.getQuantity() + 1);
                        inventoryPlantRepository.save(inv);
                    }
                }, () -> {
                    // 없으면 새로 생성
                    InventoryPlant inv = InventoryPlant.builder()
                            .user(user)
                            .plant(plant)
                            .quantity(1)
                            .build();
                    inventoryPlantRepository.save(inv);
                });
    }

    // ✅ 방금 걸은 동안 얻은 꽃 조회
    public List<InventoryPlant> getPlantsCollectedDuringWalk(User user,
                                                             LocalDateTime startAt,
                                                             LocalDateTime endAt) {
        return inventoryPlantRepository
                .findByUserAndUpdatedAtBetween(user, startAt, endAt);
    }


}
