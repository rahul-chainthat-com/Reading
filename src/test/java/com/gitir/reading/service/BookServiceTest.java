package com.gitir.reading.service;

import com.gitir.reading.model.request.CreateBookRequest;
import com.gitir.reading.model.response.BookResponse;
import com.gitir.reading.entity.Book;
import com.gitir.reading.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private static CreateBookRequest createBookRequest;

    @BeforeEach
    public void init() {
        createBookRequest = CreateBookRequest.builder()
                .author("author")
                .quantity(10)
                .amount(new BigDecimal("100"))
                .build();
    }


    @Test
    public void createBookSuccessRequest(){
        // GIVEN
        when(bookRepository.save(any(Book.class))).thenReturn(Book.builder()
                .id(500L)
                .name("Book123")
                .author("Book123")
                .quantity(10)
                .amount(new BigDecimal("100"))
                .build());
        // WHEN
        BookResponse bookResponse = bookService.createBook(createBookRequest);
        // THEN
        assertThat(bookResponse.getId()).isEqualTo(500L);
        assertThat(bookResponse.getName()).isEqualTo("Book123");
    }
}
