package org.example.doorhub.category.parent;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.category.parent.dto.ParentCategoryCreateDto;
import org.example.doorhub.category.parent.dto.ParentCategoryPathDto;
import org.example.doorhub.category.parent.dto.ParentCategoryResponseDto;
import org.example.doorhub.category.parent.dto.ParentCategoryUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parent-categoty")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;


    @PostMapping
    public ResponseEntity<ParentCategoryResponseDto> createCategory(@RequestBody @Valid ParentCategoryCreateDto categoryCreateDto) {
        ParentCategoryResponseDto categoryResponseDto = parentService.create(categoryCreateDto);
        return ResponseEntity.ok(categoryResponseDto);
    }


    @GetMapping
    public ResponseEntity<Page<ParentCategoryResponseDto>> getAllCategory(Pageable pageable,
                                                                          @RequestParam(required = false) String predicate) {
        Page<ParentCategoryResponseDto> all = parentService.getAll(pageable, predicate);
        return ResponseEntity.ok(all);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ParentCategoryResponseDto> getCategory(@PathVariable Integer id) {
        ParentCategoryResponseDto responseDto = parentService.getById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentCategoryResponseDto> updateCategory(@PathVariable Integer id,
                                                              @RequestBody @Valid ParentCategoryUpdateDto updateDto) {
        ParentCategoryResponseDto responseDto = parentService.update(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ParentCategoryResponseDto> patchCategory(@PathVariable Integer id
            , @RequestBody ParentCategoryPathDto patchDto) throws NoSuchFieldException, IllegalAccessException {
        ParentCategoryResponseDto responseDto = parentService.patch(id, patchDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        parentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
