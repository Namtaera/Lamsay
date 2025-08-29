package garden_lamsay.spot;

import garden_lamsay.common.enums.SpotCategory;
import garden_lamsay.plant.Plant;
import garden_lamsay.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "spot")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Spot extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpotCategory category;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @Column(columnDefinition="TEXT")
    private String naverPlaceId;

    @Column(columnDefinition="TEXT")
    private String address;

    @Column(columnDefinition="TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant;

    @Column(nullable = false)
    private boolean enabled = true;
}
