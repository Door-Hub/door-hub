package org.example.doorhub.attachment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.doorhub.user.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileName;
    private String OriginalFileName;
    private String url;
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
}
