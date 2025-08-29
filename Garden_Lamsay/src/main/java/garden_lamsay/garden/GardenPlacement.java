// src/main/java/garden/garden/GardenPlacement.java
package garden_lamsay.garden;

import garden_lamsay.plant.Plant;
import garden_lamsay.user.User;
import garden_lamsay.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "garden_placement")
@Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class GardenPlacement extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant;

    @Column(nullable = false)
    private int slot;  // ex. 1=꽃 자리, 2=풀 자리, 3=나무 자리 정해진 슬롯에만 배치 가능 로직짜기 위함
}
