package org.example.doorhub.attachment;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.doorhub.attachment.dto.AttachmentCreateDto;
import org.example.doorhub.attachment.dto.AttachmentResponseDto;
import org.example.doorhub.attachment.entity.Attachment;
import org.example.doorhub.common.exception.AttachmentNotFound;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository repository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Value("${service.upload.dir}")
    private String uploadDir;

    public AttachmentResponseDto processImageUpload(MultipartFile file, Integer userId) {

        if (file.isEmpty()) {
            log.error("Empty file uploaded");
            throw new IllegalArgumentException("Empty file uploaded");
        }

        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User with id = %s not found".formatted(userId)));

            File destFile = Paths.get(uploadDir, file.getOriginalFilename()).toFile();
            file.transferTo(destFile);
            log.info("Uploaded: {}", destFile);

            AttachmentCreateDto attachment = new AttachmentCreateDto();
            attachment.setFile_name(file.getOriginalFilename());
            attachment.setFileType(Objects.requireNonNull(file.getContentType()));
            attachment.setUrl(String.valueOf(Paths.get(uploadDir, file.getOriginalFilename())));

            Attachment map = mapper.map(attachment, Attachment.class);

            Attachment saved = repository.save(map);

            user.setAttachment(saved);
            user.setAvatar(attachment.getUrl());

            userRepository.save(user);

            return mapper.map(saved, AttachmentResponseDto.class);
        } catch (IOException e) {
            log.error("Error uploading file: {}", e.getMessage());
            throw new RuntimeException("Error uploading file", e);
        }
    }

    public AttachmentResponseDto processImageUpdate(MultipartFile file, Integer userId) {

        if (file.isEmpty()) {
            log.error("Empty file uploaded");
            throw new IllegalArgumentException("Empty file uploaded");
        }

        try {
            File destFile = Paths.get(uploadDir, file.getOriginalFilename()).toFile();
            file.transferTo(destFile);
            log.info("Uploaded: {}", destFile);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Attachment attachment = user.getAttachment();
            String url = attachment.getUrl();
            deleteFile(url);

            attachment.setId(user.getAttachment().getId());
            attachment.setFile_name(file.getOriginalFilename());
            attachment.setFileType(Objects.requireNonNull(file.getContentType()));
            attachment.setUrl(String.valueOf(Paths.get( uploadDir , file.getOriginalFilename())));
            attachment.setUploadTime(LocalDateTime.now());

            Attachment saved = repository.save(attachment);

            return mapper.map(saved, AttachmentResponseDto.class);

        } catch (IOException e) {
            log.error("Error uploading file: {}", e.getMessage());
            throw new RuntimeException("Error uploading file", e);
        }
    }

    private void deleteFile(String filePath) {

        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            log.error("Error deleting file: {}", e.getMessage());
            throw new RuntimeException("Error deleting file", e);
        }
    }


    public void deleteAttachment(Integer userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        Attachment attachment = user.getAttachment();

        if (attachment != null) {
            user.setAttachment(null);
            userRepository.save(user);

            Integer attachmentID = attachment.getId();
            repository.deleteById(attachmentID);
            deleteFile(attachment.getUrl());
        } else {
            throw new AttachmentNotFound("Attachment not found for the user");
        }

    }
}
