package org.example.doorhub.attachment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doorhub.address.dto.AddressBaseDto;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentResponseDto extends AddressBaseDto
{

    private Integer id;


}
