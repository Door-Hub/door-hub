package org.example.doorhub.review;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.review.dto.ReviewCreateDto;

import org.example.doorhub.review.dto.ReviewResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody @Valid ReviewCreateDto createDto) {
        ReviewResponseDto reviewResponseDto = reviewService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        reviewService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/review/users")
    public String getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Foydalanuvchi ma'lumotlarini olish
            String username = authentication.getName();
            // Yoki foydalanuvchi obyektini olish
            // User user = (User) authentication.getPrincipal();

            // ... qolgan logika ...
            return "Foydalanuvchi: " + username;
        } else {
            // Foydalanuvchi avtorizatsiyadan o'tmagan
            return "Foydalanuvchi avtorizatsiyadan o'tmagan";
        }
    }
}
