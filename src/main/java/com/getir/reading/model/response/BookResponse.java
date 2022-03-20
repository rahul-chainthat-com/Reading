package com.getir.reading.model.response;

import com.getir.reading.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private Long id;
    private String name;

    public static BookResponse of(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .build();
    }
}
