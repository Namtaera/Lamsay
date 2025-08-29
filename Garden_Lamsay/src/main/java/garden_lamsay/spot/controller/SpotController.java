package garden_lamsay.spot.controller;

import garden_lamsay.spot.dto.SpotDto;
import garden_lamsay.spot.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping
    public List<SpotDto> getAllSpots() {
        return spotService.getAllSpots();
    }
}
