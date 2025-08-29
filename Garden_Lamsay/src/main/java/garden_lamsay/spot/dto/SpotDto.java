package garden_lamsay.spot.dto;

import garden_lamsay.common.enums.SpotCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotDto {
    private Long id;
    private String name;
    private SpotCategory category;
    private double lat;
    private double lng;
    private String naverPlaceId;
    private String address;
    private String description;

    // 🔽 Plant 정보도 같이 반환
    private Long plantId;
    private String plantName;
    private String plantImageUrl;
}

