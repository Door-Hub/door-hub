package org.example.doorhub.attachment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentCreateDto {

    @NotBlank
    private String file_name;
    @NotBlank
    private String fileType;
    @NotBlank
    private String url;
    @NotNull
    private LocalDateTime uploadTime;

}
