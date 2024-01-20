package org.example.doorhub.notification.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.user.entity.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Notification {
    @Id
    private Integer id;
    private Integer userId;
    private String message;
    private LocalDateTime timestamp; // notification voxti
    private boolean read; // notification oqilgami yomi

    @OneToOne(mappedBy = "notification")
    private User user;
}
