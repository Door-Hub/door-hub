package org.example.doorhub.attachment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.user.entity.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentResponseDto {
    private Integer id;

    private String file_name;

    private String fileType;

    private String url;

    private LocalDateTime uploadTime;

    private User user;
}
