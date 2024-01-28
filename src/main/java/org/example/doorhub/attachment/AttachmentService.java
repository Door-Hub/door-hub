package org.example.doorhub.attachment;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.doorhub.attachment.dto.AttachmentResponseDto;
import org.example.doorhub.attachment.entity.Attachment;
import org.example.doorhub.common.exception.AttachmentNotFound;
import org.example.doorhub.common.exception.FileUploadException;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository repository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Value("${service.upload.dir}")
    private String uploadDir;

    @Transactional
    public AttachmentResponseDto processImageUpload(MultipartFile file, Integer userId){

        extracted(file);
        try {
            return getAttachmentResponseDto(file, userId);
        } catch (IOException e) {
            log.error("Error uploading file: {}", e.getMessage());
            throw new FileUploadException("Error uploading file");
        }
    }

    @Transactional
    public AttachmentResponseDto processImageUpdate(MultipartFile file, Integer userId) {

        extracted(file);

        try {
            return getAttachmentResponseDto(file, userId);

        } catch (IOException e) {
            log.error("Error uploading file: {}", e.getMessage());
            throw new FileUploadException("Error uploading file");
        }
    }

    private static void extracted(MultipartFile file) {
        if (file.isEmpty()) {
            log.error("Empty file uploaded");
            throw new IllegalArgumentException("Empty file uploaded");
        }
    }

    private AttachmentResponseDto getAttachmentResponseDto(MultipartFile file, Integer userId) throws IOException {
        File destFile = Paths.get(uploadDir, file.getOriginalFilename()).toFile();
        file.transferTo(destFile);
        log.info("Uploaded: {}", destFile);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Attachment attachment = user.getAttachments();
        String url = attachment.getUrl();
        deleteFile(url);

        attachment.setId(user.getAttachments().getId());
        attachment.setFile_name(file.getOriginalFilename());
        attachment.setFileType(Objects.requireNonNull(file.getContentType()));
        attachment.setUrl(String.valueOf(Paths.get(uploadDir, file.getOriginalFilename())));
        attachment.setUploadTime(LocalDateTime.now());
        attachment.setUser(user);

        Attachment saved = repository.save(attachment);

        AttachmentResponseDto responseDto = mapper.map(saved, AttachmentResponseDto.class);
        responseDto.setUserId(user.getId());
        return responseDto;
    }

    @Transactional
    protected void deleteFile(String filePath)  {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            log.error("Error deleting file: {}", e.getMessage());
            throw new FileUploadException("Error uploading file");
        }
    }

    @Transactional
    public void deleteAttachment(Integer attachmentId) {
        Attachment attachment = repository.findByUserId(attachmentId).
                orElseThrow(() -> new AttachmentNotFound("Could not find attachment"));
        repository.deleteById(attachment.getId());
        deleteFile(attachment.getUrl());

    }
}
