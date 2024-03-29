package ru.gb.springdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.service.BookService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;


    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable long id){
        Book book = null;
        try {
            book = bookService.getBookById(id);
        }catch (NoSuchElementException e){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book>  delById(@PathVariable long id){
        Book book = null;
        try {
            book = bookService.delBookById(id);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }
    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book book1 = bookService.createBook(book.getName());
        return ResponseEntity.status(HttpStatus.OK).body(book1);
    }

}
