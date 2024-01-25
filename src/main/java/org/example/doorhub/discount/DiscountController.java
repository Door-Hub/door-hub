package org.example.doorhub.discount;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.discount.dto.DiscountCreateDto;
import org.example.doorhub.discount.dto.DiscountPatchDto;
import org.example.doorhub.discount.dto.DiscountResponseDto;
import org.example.doorhub.discount.dto.DiscountUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService service;

    @PostMapping
    public ResponseEntity<DiscountResponseDto> createDiscount(@RequestBody @Valid DiscountCreateDto discountCreateDto) {
        DiscountResponseDto discountResponseDto = service.create(discountCreateDto);
        return ResponseEntity.ok(discountResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<DiscountResponseDto>> getAllDiscount(Pageable pageable, @RequestParam(required = false) String predicate) {
        Page<DiscountResponseDto> all = service.getAll(pageable, predicate);
        return ResponseEntity.ok(all);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DiscountResponseDto> getDiscount(@PathVariable Integer id) {
        DiscountResponseDto responseDto = service.getById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountResponseDto> updateDiscount(@PathVariable Integer id,
                                                              @RequestBody @Valid DiscountUpdateDto updateDto) {
        DiscountResponseDto responseDto = service.update(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DiscountResponseDto> patchDiscount(@PathVariable Integer id
            , @RequestBody DiscountPatchDto patchDto) throws NoSuchFieldException, IllegalAccessException {
        DiscountResponseDto responseDto = service.patch(id, patchDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
