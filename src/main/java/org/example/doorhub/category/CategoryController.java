package org.example.doorhub.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.dto.*;
import org.example.doorhub.category.parent.dto.ParentCategoryCreateDto;
import org.example.doorhub.category.parent.dto.ParentCategoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto) {
        CategoryResponseDto categoryResponseDto = service.create(categoryCreateDto);
        return ResponseEntity.ok(categoryResponseDto);
    }


    @GetMapping
    public ResponseEntity<Page<CategoryResponseDto>> getAllCategory(Pageable pageable, @RequestParam(required = false) String predicate) {
        Page<CategoryResponseDto> all = service.getAll(pageable, predicate);
        return ResponseEntity.ok(all);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable Integer id) {
        CategoryResponseDto responseDto = service.getById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Integer id,
                                                              @RequestBody @Valid CategoryUpdateDto updateDto) {
        CategoryResponseDto responseDto = service.update(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> patchCategory(@PathVariable Integer id
            , @RequestBody CategoryPatchDto patchDto) throws NoSuchFieldException, IllegalAccessException {
        CategoryResponseDto responseDto = service.patch(id, patchDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
