package garden_lamsay.plant.repository;

import garden_lamsay.plant.Plant;
import garden_lamsay.plant.UnlockGarden;
import garden_lamsay.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnlockGardenRepository extends JpaRepository<UnlockGarden, Long> {
    List<UnlockGarden> findByUser(User user);


    Optional<UnlockGarden> findByUserAndPlant(User user, Plant plant);

    Optional<UnlockGarden> findByUserAndSlot(User user, int slot);
}
