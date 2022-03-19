package com.gitir.reading.service;

import com.gitir.reading.model.request.CreateBookRequest;
import com.gitir.reading.model.response.BookResponse;
import com.gitir.reading.entity.Book;

import com.gitir.reading.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse createBook(CreateBookRequest createBookRequest) {
        Book book = new Book();
        book.setName(createBookRequest.getName());
        book.setAuthor(createBookRequest.getAuthor());
        book.setQuantity(createBookRequest.getQuantity());
        book.setAmount(createBookRequest.getAmount());

        return BookResponse.of(bookRepository.save(book));
    }
}
