package com.gitir.reading.controller;

import com.gitir.reading.model.request.CustomerRequest;
import com.gitir.reading.model.response.CustomerResponse;
import com.gitir.reading.model.response.OrderResponse;
import com.gitir.reading.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerRequest));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrdersOfCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getOrdersFromCustomer(id));
    }
}
