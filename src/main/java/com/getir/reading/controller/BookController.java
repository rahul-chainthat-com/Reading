package com.getir.reading.controller;

import com.getir.reading.model.request.CreateBookRequest;
import com.getir.reading.model.response.BookResponse;
import com.getir.reading.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Api(tags = {"Book"})
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody CreateBookRequest createBookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(createBookRequest));
    }
}
