package org.example.doorhub.notification.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
