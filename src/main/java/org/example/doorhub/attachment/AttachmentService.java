package org.example.doorhub.attachment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.attachment.dto.AttachmentBaseDto;
import org.example.doorhub.attachment.dto.AttachmentResponseDto;
import org.example.doorhub.attachment.dto.AttachmentUpdateDto;
import org.example.doorhub.attachment.entity.Attachment;
import org.example.doorhub.common.service.GenericCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class AttachmentService extends GenericCrudService<Attachment, Integer, AttachmentBaseDto, AttachmentUpdateDto, AttachmentUpdateDto, AttachmentResponseDto> {

    private final AttachmentRepository repository;
    private final AttachmentDtoMapper mapper;
    private final Class<Attachment> entityClass = Attachment.class;
    private final ModelMapper modelMapper;


    @Override
    protected Attachment save(AttachmentBaseDto attachmentBaseDto)
    {
        Attachment attachment = mapper.toEntity(attachmentBaseDto);
        return repository.save(attachment);
    }


    @Override
    protected Attachment updateEntity(AttachmentUpdateDto attachmentUpdateDto, Attachment attachment)
    {
        mapper.update(attachmentUpdateDto, attachment);
        return repository.save(attachment);
    }


}