package org.example.doorhub.attachment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.category.entity.Category;
import org.example.doorhub.user.entity.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(EntityListeners.class)
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String file_name;

    @Column(nullable = false)
    private String url;

    private String fileType;

    @CreationTimestamp
    private LocalDateTime uploadTime;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne()
    private User user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne()
    private Category category;

}
