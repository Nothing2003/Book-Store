package com.book.store.controller;

import com.book.store.entities.Book;
import com.book.store.repositories.BookRepository;
import com.book.store.services.BookService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public Mono<Book> createBook(@RequestBody Book book) {
      return bookService.createBookDetails(book);
    }
    @GetMapping
    public Flux<Book> getAllBooks() {
        return bookService.getAllBookDetails();
    }
    @GetMapping("/{bookId}")
    public Mono<Book> getSingleBook(@PathVariable("bookId") int bookId) {
        return bookService.getBookDetails(bookId);
    }
    @PutMapping("/{bookId}")
    public Mono<Book> updateBook(@PathVariable("bookId") int bookId, @RequestBody Book book) {
        return bookService.updateBookDetails(book, bookId);
    }
    @DeleteMapping("/{bookId}")
    public Mono<Void> deleteBook(@PathVariable("bookId") int bookId) {
        return bookService.deleteBookDetails(bookId);
    }
    @GetMapping("/search")
    public Flux<Book> searchBook(@RequestParam(required = false,name = "type-of-search") String type,
                                 @RequestParam(required = false,name = "publisher-name") String publisher  ,
                                 @RequestParam(required = false,name = "author-name") String author,
                                 @RequestParam(required = false,name = "title-name") String title ) {
        return bookService.searchBookDetails(type,title,author,publisher);
    }
}
