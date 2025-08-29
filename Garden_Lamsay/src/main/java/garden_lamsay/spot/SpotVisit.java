package garden_lamsay.spot;

import garden_lamsay.user.User;
import garden_lamsay.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "spot_visit",
        indexes = { @Index(columnList = "user_id,visitedAt"), @Index(columnList = "spot_id,visitedAt") })
@Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class SpotVisit extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "spot_id", nullable = false)
    private Spot spot;

    private int distanceToCenterM;                         // 로깅용
    private LocalDateTime visitedAt;
}
