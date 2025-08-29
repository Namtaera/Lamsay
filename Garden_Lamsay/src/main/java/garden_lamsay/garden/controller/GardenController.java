package garden_lamsay.garden.controller;

import garden_lamsay.garden.dto.GardenPlantDto;
import garden_lamsay.garden.service.GardenService;
import garden_lamsay.user.User;
import garden_lamsay.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/garden")
@RequiredArgsConstructor
public class GardenController {

    private final GardenService gardenService;
    private final UserRepository userRepository;

    // ✅ 2) deviceId 기준 조회 (선택)
    /* @GetMapping("/plants/device/{deviceId}")
    public List<GardenPlantDto> getGardenPlantsByDeviceId(@PathVariable String deviceId) {
        User user = userRepository.findByDeviceId(deviceId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return gardenService.getUserGardenPlants(user);
    }*/

    // 1) 정원 조회
    @GetMapping("/plants")
    public List<GardenPlantDto> getGardenPlants(@RequestHeader("X-Device-Id") String deviceId) {
        User user = userRepository.findByDeviceId(deviceId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return gardenService.getUserGardenPlants(user);
    }

    // 2) 정원 배치 및 교환
    @PostMapping("/place")
    public ResponseEntity<String> placePlant(
            @RequestHeader("X-Device-Id") String deviceId,
            @RequestParam Long plantId,
            @RequestParam int slot
    ) {
        User user = userRepository.findByDeviceId(deviceId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        gardenService.placePlant(user, plantId, slot);
        return ResponseEntity.ok("식물이 슬롯 " + slot + "에 배치되었습니다.");
    }




}
