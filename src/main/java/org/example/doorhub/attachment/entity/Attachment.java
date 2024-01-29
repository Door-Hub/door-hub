package org.example.doorhub.attachment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
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

/*    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;*/

/*    @OneToMany(mappedBy = "attachment_id",fetch = FetchType.EAGER)
    private List<Recipe_attachment>recipeAttachments*/;
}
