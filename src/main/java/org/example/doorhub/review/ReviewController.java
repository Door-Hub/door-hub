package org.example.doorhub.review;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.discount.dto.DiscountCreateDto;
import org.example.doorhub.discount.dto.DiscountResponseDto;
import org.example.doorhub.review.dto.ReviewCreateDto;
import org.example.doorhub.review.dto.ReviewResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/stars")
    public ResponseEntity<ReviewResponseDto> create(@PathVariable Principal userId, @RequestBody ReviewCreateDto createDto) {
        ReviewResponseDto responseDto = reviewService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ReviewResponseDto>> getAll(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getAll(id));
    }


    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody @Valid ReviewCreateDto reviewCreateDto) {
        ReviewResponseDto reviewResponseDto = reviewService.create(reviewCreateDto);
        return ResponseEntity.ok(reviewResponseDto);
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
