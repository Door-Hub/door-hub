package org.example.doorhub.book;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.doorhub.book.dto.BookCreateDto;
import org.example.doorhub.book.dto.BookResponseDto;
import org.example.doorhub.book.entity.Book;
import org.example.doorhub.category.parent.ParentRepository;
import org.example.doorhub.common.rsql.SpecificationBuilder;
import org.example.doorhub.common.service.GenericCrudService;
import org.example.doorhub.user.UserRepository;
import org.example.doorhub.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class BookService extends GenericCrudService<Book, Integer, BookCreateDto, BookCreateDto, BookCreateDto, BookResponseDto> {

    private final BookRepository repository;
    private final BookMapperDto mapper;
    private final Class<Book> EntityClass = Book.class;
    private final UserRepository userRepository;


    @Transactional
    public BookResponseDto create(BookCreateDto bookCreateDto) {

        User worker = userRepository.findById(bookCreateDto.getWorker())
                .orElseThrow(() -> new EntityNotFoundException("user not found"));


        Book saved = save(bookCreateDto);

        BookResponseDto bookResponseDto = mapper.bookResponseDto(bookCreateDto);
        bookResponseDto.setId(saved.getId());
        bookResponseDto.setWorker(worker.getId());

        return bookResponseDto;
    }

    @Override
    protected Book save(BookCreateDto bookCreateDto) {
        //        User booker = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Book book = mapper.toEntity(bookCreateDto);

        User worker = userRepository.findById(bookCreateDto.getWorker())
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        book.setWorker(worker);
        book.setBooker(worker);
        worker.setBook(book);

        return repository.save(book);
    }

    @Override
    protected Book updateEntity(BookCreateDto bookCreateDto, Book book) {
        return null;
    }


}
