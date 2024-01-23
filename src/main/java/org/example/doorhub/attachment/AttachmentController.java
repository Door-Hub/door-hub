package org.example.doorhub.attachment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.attachment.dto.AttachmentBaseDto;
import org.example.doorhub.attachment.dto.AttachmentResponseDto;
import org.example.doorhub.attachment.dto.AttachmentUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController
{
    private final AttachmentService attachmentService;


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
