package garden_lamsay.collection;

import garden_lamsay.plant.Plant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name="collection_requirement",
        uniqueConstraints = @UniqueConstraint(columnNames={"collectionSet_id","plant_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionRequirement {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="collectionSet_id", nullable=false)
    private CollectionSet collectionSet; // 어떤 컬렉션에 포함되는건지

    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="plant_id", nullable=false)
    private Plant plant;

    @Column(nullable=false)
    private int requiredCount;   // 보통 1, 하지만 같은 식물 여러 개 필요하면 2 이상
}
