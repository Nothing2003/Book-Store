package com.book.store.services.imp;

import com.book.store.entities.Book;
import com.book.store.repositories.BookRepository;
import com.book.store.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Mono<Book> createBookDetails(Book book) {
        return  bookRepository.save(book);
    }

    @Override
    public Flux<Book> getAllBookDetails() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> getBookDetails(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Mono<Book> updateBookDetails(Book book, int id) {
        Mono<Book> oldBook = bookRepository.findById(id);
        return oldBook.flatMap(book1->{
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setPublisher(book.getPublisher());
            book1.setDescription(book.getDescription());
            return bookRepository.save(book1);
        });
    }

    @Override
    public Mono<Void> deleteBookDetails(int id) {
        return bookRepository.deleteById(id);
    }

    @Override
    public Flux<Book> searchBookDetails( String type, String title, String author, String publisher) {
        if (type==null){
            return bookRepository.findAll();
        }
        if (type.equalsIgnoreCase("title")){
            return bookRepository.findByTitle(title);
        }else if (type.equalsIgnoreCase("author")){
            return bookRepository.findByAuthor(author);
        }else if (type.equalsIgnoreCase("publisher")){
            return bookRepository.findByPublisher(publisher);
        }else if (type.equalsIgnoreCase("titleAndAuthor")) {
            return bookRepository.findByTitleAndAuthor(title, author);
        }
        else {
            return bookRepository.findAll();
        }
    }


}
