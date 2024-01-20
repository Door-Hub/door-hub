package org.example.doorhub.notification.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.notification.NotificationType;
import org.example.doorhub.user.entity.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class NotificationRequestDto {
    @Id
    private Integer id;
    private Integer userId;
    private String message;
    private String phoneNumber;
    private LocalDateTime timestamp; // notification voxti
    private boolean read; // notification oqilgami yomi
    private NotificationType notificationType;

}
