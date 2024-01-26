package org.example.doorhub.attachment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.user.entity.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String file_name;

    @Column(nullable = false)
    private String fileType;

    private String url;

    @Column(nullable = false)
    private LocalDateTime uploadTime;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
