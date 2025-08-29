package garden_lamsay.plant.repository;

import garden_lamsay.plant.InventoryPlant;
import garden_lamsay.plant.Plant;
import garden_lamsay.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InventoryPlantRepository extends JpaRepository<InventoryPlant, Long> {
    Optional<InventoryPlant> findByUserAndPlant(User user, Plant plant);

    List<InventoryPlant> findByUserAndUpdatedAtBetween(User user,
                                                       LocalDateTime start,
                                                       LocalDateTime end);
}