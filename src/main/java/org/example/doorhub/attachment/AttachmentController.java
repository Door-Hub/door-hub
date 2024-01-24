package org.example.doorhub.attachment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.doorhub.attachment.dto.AttachmentBaseDto;
import org.example.doorhub.attachment.dto.AttachmentResponseDto;
import org.example.doorhub.attachment.dto.AttachmentUpdateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
@Slf4j
public class AttachmentController {

    private final AttachmentService attachmentService;


    @PostMapping(name = "/opload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        switch (Objects.requireNonNull(file.getContentType())){
            case MediaType.IMAGE_GIF_VALUE :
            case MediaType.IMAGE_JPEG_VALUE:
            case MediaType.IMAGE_PNG_VALUE: attachmentService.processImageUpload(file); break;
            default:
                log.error("Unsupported filetype: {}", file.getContentType());
                throw new UnsupportedMediaTypeStatusException(
                        String.format("Unsupported filetype: %s", file.getContentType()));

        }

        return ResponseEntity.ok(
                String.format("File uploaded successfully: %s", file.getOriginalFilename()));
    }




    @PostMapping
    public ResponseEntity<AttachmentResponseDto> createAttachment(@RequestBody @Valid AttachmentBaseDto attachmentBaseDto) {
        AttachmentResponseDto attachmentResponseDto = attachmentService.create(attachmentBaseDto);
        return ResponseEntity
                .ok(attachmentResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<AttachmentResponseDto>> getAllAttachment(Pageable pageable, @RequestParam(required = false) String predicate) {
        Page<AttachmentResponseDto> all = attachmentService.getAll(pageable, predicate);
        return ResponseEntity.ok(all);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AttachmentResponseDto> getAttachment(@PathVariable Integer id) {
        AttachmentResponseDto responseDto = attachmentService.getById(id);
        return ResponseEntity.ok(responseDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AttachmentResponseDto> updateAttachment(@PathVariable Integer id, @RequestBody @Valid AttachmentUpdateDto updateDto) {
        AttachmentResponseDto responseDto = attachmentService.update(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AttachmentResponseDto> patchAttachment(@PathVariable Integer id, @RequestBody AttachmentUpdateDto patchDto) throws NoSuchFieldException, IllegalAccessException {
        AttachmentResponseDto responseDto = attachmentService.patch(id, patchDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttachment(@PathVariable Integer id) {
        attachmentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
