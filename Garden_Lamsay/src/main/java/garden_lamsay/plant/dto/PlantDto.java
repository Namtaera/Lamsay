package garden_lamsay.plant.dto;

import garden_lamsay.common.enums.PlantType;
import garden_lamsay.plant.Plant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlantDto {
    private Long id;
    private String name;
    private PlantType type;
    private boolean owned;
    private int slot;  // 서비스에서 세팅할 값

    public PlantDto(Plant plant, boolean owned, int slot) {
        this.id = plant.getId();
        this.name = plant.getPlantName();
        this.type = plant.getPlantType();
        this.owned = owned;
        this.slot = slot;   // ✅ UnlockGarden에서 받아서 세팅
    }
}

