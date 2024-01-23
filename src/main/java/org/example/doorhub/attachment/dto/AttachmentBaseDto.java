package org.example.doorhub.attachment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.address.dto.AddressBaseDto;
import org.example.doorhub.attachment.entity.FileType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentBaseDto
{
    private String url;

    private FileType fileType;


}
