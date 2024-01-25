package org.example.doorhub.attachment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentBaseDto
{
    private String url;

    private String fileType;


}
