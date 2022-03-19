package com.gitir.reading.model.response;

import com.gitir.reading.entity.OrderHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private Long id;
    private CustomerResponse customer;
    private List<OrderLinesResponse> lines;
    private BigDecimal totalAmount;

    public static OrderResponse of(OrderHeader order) {
        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .customer(CustomerResponse.of(order.getCustomer()))
                .totalAmount(order.getTotalAmount())
                .build();
        List<OrderLinesResponse> orderLinesResponseList = new ArrayList<>();
        order.getLines().forEach(e -> {
            orderLinesResponseList.add(OrderLinesResponse.builder()
                    .amount(e.getAmount())
                    .id(e.getId())
                    .book(BookResponse.builder()
                            .id(e.getBook().getId())
                            .name(e.getBook().getName())
                            .build())
                    .build());
        });
        orderResponse.setLines(orderLinesResponseList);
        return orderResponse;
    }
}
