package org.example.doorhub.attachment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.doorhub.attachment.dto.AttachmentBaseDto;
import org.example.doorhub.attachment.dto.AttachmentResponseDto;
import org.example.doorhub.attachment.dto.AttachmentUpdateDto;
import org.example.doorhub.attachment.entity.Attachment;
import org.example.doorhub.common.service.GenericCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class AttachmentService extends GenericCrudService<Attachment, Integer, AttachmentBaseDto, AttachmentUpdateDto, AttachmentUpdateDto, AttachmentResponseDto> {

    private final AttachmentRepository repository;
    private final AttachmentDtoMapper mapper;
    private final Class<Attachment> entityClass = Attachment.class;
    private final ModelMapper modelMapper;

    @Value("${upload.dir}")
    private String uploadDir;

    public void processImageUpload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            log.error("Empty file uploaded");
            throw new IllegalArgumentException("Empty file uploaded");
        }


        try {
            File destFile = Paths.get(uploadDir, file.getOriginalFilename()).toFile();
            file.transferTo(destFile);
            log.info("Uploaded: {}", destFile);

            Attachment fileEntity = new Attachment();
            fileEntity.setId(1);
            fileEntity.setFileName(UUID.randomUUID() + file.getOriginalFilename());
            fileEntity.setFileType(file.getContentType());
            fileEntity.setOriginalFileName(file.getOriginalFilename());
            repository.save(fileEntity);
        } catch (IOException e) {
            log.error("Error uploading file: {}", e.getMessage());
            throw new RuntimeException("Error uploading file", e);
        }
    }


    @Override
    protected Attachment save(AttachmentBaseDto attachmentBaseDto) {
        Attachment attachment = mapper.toEntity(attachmentBaseDto);
        return repository.save(attachment);
    }


    @Override
    protected Attachment updateEntity(AttachmentUpdateDto attachmentUpdateDto, Attachment attachment) {
        mapper.update(attachmentUpdateDto, attachment);
        return repository.save(attachment);
    }


}