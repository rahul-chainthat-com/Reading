package com.getir.reading.service;

import com.getir.reading.entity.Book;
import com.getir.reading.entity.OrderItems;
import com.getir.reading.exception.BookNotFoundException;
import com.getir.reading.exception.OrderNotFoundException;
import com.getir.reading.exception.OutOfStockException;
import com.getir.reading.repository.BookRepository;
import com.getir.reading.repository.OrderHeaderRepository;
import com.getir.reading.entity.OrderHeader;
import com.getir.reading.model.request.BookRequest;
import com.getir.reading.model.request.OrderRequest;
import com.getir.reading.model.response.OrderResponse;
import com.getir.reading.entity.Customer;
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
        log.info("Order Creation started!");
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
        log.info("Order Creation end!!!!");

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
        log.debug("GetOrderById  id===>{}",id);

        OrderHeader oh = orderHeaderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        return OrderResponse.of(oh);
    }
}
