package com.getir.reading.service;

import com.getir.reading.entity.Book;
import com.getir.reading.repository.BookRepository;
import com.getir.reading.model.request.CreateBookRequest;
import com.getir.reading.model.response.BookResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse createBook(CreateBookRequest createBookRequest) {
        log.debug("createBook called");
        Book book = new Book();
        book.setName(createBookRequest.getName());
        book.setAuthor(createBookRequest.getAuthor());
        book.setQuantity(createBookRequest.getQuantity());
        book.setAmount(createBookRequest.getAmount());
        log.debug("createBook end!!!!");

        return BookResponse.of(bookRepository.save(book));
    }
}
