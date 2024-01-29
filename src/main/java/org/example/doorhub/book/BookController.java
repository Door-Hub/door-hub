package org.example.doorhub.book;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.book.dto.BookCreateDto;
import org.example.doorhub.book.dto.BookResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody @Valid BookCreateDto bookCreateDto) {
        BookResponseDto bookResponseDto = service.create(bookCreateDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<BookResponseDto>> getAllBook(Pageable pageable, @RequestParam(required = false) String predicate) {
        Page<BookResponseDto> all = service.getAll(pageable, predicate);
        return ResponseEntity.ok(all);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable Integer id) {
        BookResponseDto responseDto = service.getById(id);
        return ResponseEntity.ok(responseDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

