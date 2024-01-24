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
    private Integer id;
    private String fileName;
    private String OriginalFileName;
    private String url;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
}
