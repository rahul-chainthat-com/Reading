package com.gitir.reading.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItems {
    @Id
    @GeneratedValue
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "header_id")
    private OrderHeader header;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private BigDecimal amount;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
