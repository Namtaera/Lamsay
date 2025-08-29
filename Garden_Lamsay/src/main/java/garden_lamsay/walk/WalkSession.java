// src/main/java/garden/walk/WalkSession.java
package garden_lamsay.walk;

import garden_lamsay.user.User;
import garden_lamsay.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "walk_session", indexes = @Index(columnList = "user_id,startAt"))
@Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class WalkSession extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @Column(nullable = false) private int distanceM;   // 이동 거리(m)
    private int steps;                                 // 걸음 수(옵션)
    private int carbonSavedG;                          // 계산된 탄소 절감량(g)
}
