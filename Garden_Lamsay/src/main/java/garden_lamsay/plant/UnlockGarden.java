package garden_lamsay.plant;

import garden_lamsay.user.User;
import garden_lamsay.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "unlock_garden",
        uniqueConstraints = {
                @UniqueConstraint(columnNames={"user_id","plant_id"})   // 중복 해금 방지
        },
        indexes = {
                @Index(columnList="user_id,slot")                      // 슬롯 조회 최적화
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnlockGarden extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="plant_id", nullable=false)
    private Plant plant;

    /** 0=미배치, 1~3 = 슬롯 번호 */
    @Column(nullable=false)
    private int slot = 0;
}
