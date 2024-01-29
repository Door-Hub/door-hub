package org.example.doorhub.attachment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentCreateDto {

    private String file_name;

    private String fileType;

    private String url;

    private LocalDateTime uploadTime;

}
