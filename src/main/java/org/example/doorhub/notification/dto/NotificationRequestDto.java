package org.example.doorhub.notification.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.notification.NotificationType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class NotificationRequestDto {
    @Id
    @NotNull
    private Integer id;
    @NotNull
    private Integer userId;
    @NotBlank
    private String message;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private LocalDateTime timestamp; // notification voxti
    private boolean read; // notification oqilgami yomi
    @NotBlank
    private NotificationType notificationType;

}
