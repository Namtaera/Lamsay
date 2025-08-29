// src/main/java/garden/shop/InventoryItem.java
package garden_lamsay.plant;

import garden_lamsay.user.User;
import garden_lamsay.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory_plant",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","plant_id"}))
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class InventoryPlant extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user; // 사용자 id

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant; // 아이템 id

    @Column(nullable = false)
    private int quantity; // 컬렉션 해당 꽃 갯수
}
