// src/main/java/garden/shop/Item.java
package garden_lamsay.plant;

import garden_lamsay.common.enums.PlantType;
import garden_lamsay.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

/** 아이템 = 식물(나무/풀/꽃). 필요 시 오브제는 별도 테이블로 분리 권장 */
@Entity
@Table(name = "plant")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Plant extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlantType plantType; // TREE / GRASS / FLOWER

    @Column(nullable = false)
    private String plantName; // 이름

    @Column(columnDefinition="TEXT")
    private String plantExp; // 식물 설명

    private String season;      // SUMMER 등

    @Column(nullable = true)
    private String plantImageUrl;    // 렌더 리소스
}
