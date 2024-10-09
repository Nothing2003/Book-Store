package com.book.store.services;

import com.book.store.entities.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    public Mono<Book> createBookDetails(Book book);
    public Flux<Book> getAllBookDetails();
    public Mono<Book> getBookDetails(int id);
    public Mono<Book> updateBookDetails(Book book,int id);
    public Mono<Void> deleteBookDetails(int id);
    public Flux<Book> searchBookDetails(String type,String title,String author,String publisher);

}
