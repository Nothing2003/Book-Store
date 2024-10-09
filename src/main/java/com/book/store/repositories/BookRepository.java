package com.book.store.repositories;

import com.book.store.entities.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book,Integer> {
    public Flux<Book> findByTitle(String name);
    public Flux<Book> findByAuthor(String author);
    public Flux<Book> findByPublisher(String publisher);
    public Flux<Book> findByTitleAndAuthor(String title, String author);
}
