package org.example.doorhub.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.book.dto.BookCreateDto;
import org.example.doorhub.book.dto.BookPatchDto;
import org.example.doorhub.book.dto.BookResponseDto;
import org.example.doorhub.book.dto.BookUpdateDto;
import org.example.doorhub.book.entity.Book;
import org.example.doorhub.common.service.GenericCrudService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class BookService extends GenericCrudService<Book, Integer, BookCreateDto, BookUpdateDto, BookPatchDto, BookResponseDto> {

    private final BookRepository repository;
    private final BookMapperDto mapper;
    private final Class<Book> EntityClass = Book.class;

    @Override
    protected Book save(BookCreateDto bookCreateDto) {
        Book booking = mapper.toEntity(bookCreateDto);
        return repository.save(booking);

    }

    @Override
    protected Book updateEntity(BookUpdateDto bookUpdateDto, Book book) {
        mapper.update(bookUpdateDto, book);
        return repository.save(book);
    }
}
