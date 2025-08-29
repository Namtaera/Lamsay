// src/main/java/garden/notification/NotificationToken.java
package garden_lamsay.notification;

import garden_lamsay.user.User;
import garden_lamsay.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification_token", indexes = @Index(columnList = "user_id"))
@Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class NotificationToken extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 200)
    private String fcmToken;

    @Column(length = 20)
    private String platform; // ANDROID / IOS 등
}
