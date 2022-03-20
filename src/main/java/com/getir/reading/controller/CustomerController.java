package com.getir.reading.controller;

import com.getir.reading.model.request.CustomerRequest;
import com.getir.reading.model.response.CustomerResponse;
import com.getir.reading.model.response.OrderResponse;
import com.getir.reading.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Api(tags = {"Customer"})
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
