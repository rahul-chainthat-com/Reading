package com.gitir.reading.service;

import com.gitir.reading.entity.OrderHeader;
import com.gitir.reading.model.request.BookRequest;
import com.gitir.reading.model.request.OrderRequest;
import com.gitir.reading.model.response.OrderResponse;
import com.gitir.reading.exception.BookNotFoundException;
import com.gitir.reading.exception.OrderNotFoundException;
import com.gitir.reading.exception.OutOfStockException;
import com.gitir.reading.repository.BookRepository;
import com.gitir.reading.repository.OrderHeaderRepository;
import com.gitir.reading.entity.Book;
import com.gitir.reading.entity.Customer;
import com.gitir.reading.entity.OrderItems;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderHeaderRepository orderHeaderRepository;
    private final CustomerService customerService;
    private final BookRepository bookRepository;


    @Transactional(rollbackFor = RuntimeException.class, isolation = Isolation.READ_COMMITTED)
    public OrderResponse createOrder(OrderRequest orderRequest) {
        log.info("OrderHeader Creation Log started!");
        Customer customer = customerService.findCustomerById(orderRequest.getCustomerId());

        List<OrderItems> orderItemsList = new ArrayList<>();
        BigDecimal totalAmount = new BigDecimal(0);
        OrderHeader order = OrderHeader.builder()
                .customer(customer)
                .lines(orderItemsList)
                .createdAt(LocalDateTime.now())
                .build();
        for (BookRequest bt : orderRequest.getBookRequestList()) {
            Book book = bookRepository.findById(bt.getBookId()).orElseThrow(BookNotFoundException::new);
            getAndDeductBookStock(book,bt.getQuantity());
            OrderItems ol = OrderItems.builder()
                    .book(book)
                    .header(order)
                    .amount(book.getAmount())
                    .quantity(bt.getQuantity())
                    .createdAt(LocalDateTime.now())
                    .build();
            totalAmount = totalAmount.add(book.getAmount().multiply(new BigDecimal(bt.getQuantity())));
            orderItemsList.add(ol);
        }
        order.setTotalAmount(totalAmount);
        OrderHeader oh = orderHeaderRepository.save(order);
        return OrderResponse.of(oh);
    }

    public void getAndDeductBookStock(Book book,int quantity) {
        book.setQuantity(book.getQuantity()-quantity);
        if (book.getQuantity() < 1) {
            throw new OutOfStockException(book.getName() + " is out of stock!");
        }
        bookRepository.save(book);
    }

    public OrderResponse getOrderById(Long id) {
        OrderHeader oh = orderHeaderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        return OrderResponse.of(oh);
    }
}
