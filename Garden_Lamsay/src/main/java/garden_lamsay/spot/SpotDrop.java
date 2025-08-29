// src/main/java/garden/spot/SpotDrop.java
package garden_lamsay.spot;

import garden_lamsay.plant.Plant;
import jakarta.persistence.*;
import lombok.*;


// 만약에 해당 스팟에 꽃 두 개 두고 랜덤으로 증정느낌으로 확률을 좀 더 낮추려면
// 암튼 그 때 사용 지금은 아님

@Entity
@Table(name = "spot_drop",
        uniqueConstraints = @UniqueConstraint(columnNames = {"spot_id","item_id"}))
@Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class SpotDrop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "spot_id", nullable = false)
    private Spot spot;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "item_id", nullable = false)
    private Plant plant;

    /** 확률 가중치(정수 가중치 합으로 드랍 테이블 구성) */
    @Column(nullable = false)
    private int weight;
}
