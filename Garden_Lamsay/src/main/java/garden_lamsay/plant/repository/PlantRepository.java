package garden_lamsay.plant.repository;

import garden_lamsay.garden.dto.GardenPlantDto;
import garden_lamsay.plant.Plant;
import garden_lamsay.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    @Query("""
    SELECT new garden_lamsay.garden.dto.GardenPlantDto(
           p.id,
           p.plantName,
           p.plantType,
           CASE WHEN ug.id IS NOT NULL THEN true ELSE false END,
           COALESCE(ug.slot, 0)
       )
    FROM Plant p
    LEFT JOIN UnlockGarden ug ON ug.plant = p AND ug.user = :user
    ORDER BY
        CASE WHEN ug.slot > 0 THEN 0 ELSE 1 END,
        CASE WHEN ug.id IS NOT NULL THEN 0 ELSE 1 END,
        p.plantType,
        p.id
    """)
    List<GardenPlantDto> findAllWithOwnership(User user);

}

