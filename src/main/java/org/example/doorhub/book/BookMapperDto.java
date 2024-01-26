package org.example.doorhub.book;

import lombok.RequiredArgsConstructor;
import org.example.doorhub.book.dto.BookCreateDto;
import org.example.doorhub.book.dto.BookResponseDto;
import org.example.doorhub.book.dto.BookUpdateDto;
import org.example.doorhub.book.entity.Book;
import org.example.doorhub.common.service.GenericDtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapperDto extends GenericDtoMapper<Book, BookCreateDto, BookUpdateDto, BookResponseDto> {

    private final ModelMapper mapper;
    //...........
    @Override
    public Book toEntity(BookCreateDto bookCreateDto) {
        return mapper.map(bookCreateDto, Book.class);
    }

    @Override
    public BookResponseDto toResponseDto(Book book) {
        return mapper.map(book, BookResponseDto.class);
    }

    @Override
    public void update(BookUpdateDto bookUpdateDto, Book book) {
        mapper.map(bookUpdateDto, book);
    }

    @Override
    public BookCreateDto toCreateDto(Book book) {
        return mapper.map(book, BookCreateDto.class);
    }
}
