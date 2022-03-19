package com.gitir.reading.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLinesResponse {
    private Long id;
    private BookResponse book;
    private BigDecimal amount;
}
