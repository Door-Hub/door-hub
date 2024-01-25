package org.example.doorhub.attachment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.doorhub.address.dto.AddressBaseDto;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentUpdateDto extends AddressBaseDto
{
    private String fileName;

    private String OriginalFileName;
}
