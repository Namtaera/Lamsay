// src/main/java/garden/leaderboard/WeeklyAggregate.java
package garden_lamsay.leaderboard;

import garden_lamsay.user.User;
import garden_lamsay.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "weekly_aggregate",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","weekStart"}))
@Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class WeeklyAggregate extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate weekStart;

    private int distanceM;      // 주간 이동 합계
    private int carbonSavedG;   // 주간 탄소 절감 합계
    private int spotVisits;     // 주간 스팟 방문 수(옵션)
}
