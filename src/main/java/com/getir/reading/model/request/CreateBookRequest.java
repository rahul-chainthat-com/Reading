package com.getir.reading.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookRequest {
    private String name;
    private String author;
    private Integer quantity;
    private BigDecimal amount;
}
