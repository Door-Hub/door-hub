package org.example.doorhub.profile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.profile.dto.ProfilePatchDto;
import org.example.doorhub.profile.dto.ProfileResponseDto;
import org.example.doorhub.profile.dto.ProfileUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {


    private final ProfileService service;

    @GetMapping
    public ResponseEntity<Page<ProfileResponseDto>> getAllProfile(Pageable pageable, @RequestParam(required = false) String predicate) {
        Page<ProfileResponseDto> all = service.getAll(pageable, predicate);
        return ResponseEntity.ok(all);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable Integer id) {
        ProfileResponseDto responseDto = service.getById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> updateProfile(@PathVariable Integer id, @RequestBody @Valid ProfilePatchDto patchDto) {
        ProfileResponseDto responseDto = service.update(id, patchDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> patchProfile(@PathVariable Integer id, @RequestBody ProfileUpdateDto profileUpdateDto) throws NoSuchFieldException, IllegalAccessException {
        ProfileResponseDto responseDto = service.patch(id, profileUpdateDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
