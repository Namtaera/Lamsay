// src/main/java/garden/collection/CollectionSet.java
package garden_lamsay.collection;

import garden_lamsay.plant.Plant;
import garden_lamsay.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="collection_set")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CollectionSet {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;   // "여름 꽃 세트", "시장 특별 세트" 등
}
