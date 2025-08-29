package garden_lamsay.garden.repository;

import garden_lamsay.garden.GardenPlacement;
import garden_lamsay.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GardenPlacementRepository extends JpaRepository<GardenPlacement, Long> {
    List<GardenPlacement> findByUser(User user);
}
