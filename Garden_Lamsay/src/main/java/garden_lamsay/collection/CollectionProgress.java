package garden_lamsay.collection;
// src/main/java/garden/collection/CollectionProgress.java

import garden_lamsay.user.User;
import garden_lamsay.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name = "collection_progress",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","collectionSet_id"}))
@Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class CollectionProgress extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "collectionSet_id", nullable = false)
    private CollectionSet collectionSet;

    @Column(nullable = false)
    private boolean completed;

    private LocalDateTime completedAt;
}
