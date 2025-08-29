package garden_lamsay.garden.dto;

import garden_lamsay.common.enums.PlantType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor   // ⚡ builder + allArgsConstructor 같이 쓰면 중복 아님
public class GardenPlantDto {

    private Long plantId;
    private String plantName;
    private PlantType plantType;
    private boolean unlocked;
    private int slot;
}
