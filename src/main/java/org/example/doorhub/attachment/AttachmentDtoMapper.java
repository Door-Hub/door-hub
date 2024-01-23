package org.example.doorhub.attachment;

import lombok.RequiredArgsConstructor;
import org.example.doorhub.attachment.dto.AttachmentBaseDto;
import org.example.doorhub.attachment.dto.AttachmentResponseDto;
import org.example.doorhub.attachment.dto.AttachmentUpdateDto;
import org.example.doorhub.attachment.entity.Attachment;
import org.example.doorhub.common.service.GenericDtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttachmentDtoMapper extends GenericDtoMapper<Attachment, AttachmentBaseDto, AttachmentUpdateDto, AttachmentResponseDto> {
    private final ModelMapper mapper;


    @Override
    public Attachment toEntity(AttachmentBaseDto addressCreateDto) {
        return mapper.map(addressCreateDto, Attachment.class);

    }

    @Override
    public AttachmentResponseDto toResponseDto(Attachment attachment) {
        return mapper.map(attachment, AttachmentResponseDto.class);

    }


    @Override
    public void update(AttachmentUpdateDto attachmentBaseDto, Attachment attachment) {
        mapper.map(attachmentBaseDto, attachment);

    }


    @Override
    public AttachmentBaseDto toCreateDto(Attachment attachment) {
        return mapper.map(attachment, AttachmentBaseDto.class);

    }
}
