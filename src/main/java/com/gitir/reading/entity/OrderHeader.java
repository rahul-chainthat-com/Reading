package com.gitir.reading.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "order_header")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderHeader {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "header", cascade = CascadeType.ALL)
    private List<OrderItems> lines;

    private BigDecimal totalAmount;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
