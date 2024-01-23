package org.example.doorhub.book;

import org.example.doorhub.book.entity.Book;
import org.example.doorhub.common.repository.GenericSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends GenericSpecificationRepository<Book, Integer> {
}
