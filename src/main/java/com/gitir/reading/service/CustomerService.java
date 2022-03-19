package com.gitir.reading.service;

import com.gitir.reading.model.request.CustomerRequest;
import com.gitir.reading.model.response.CustomerResponse;
import com.gitir.reading.model.response.OrderResponse;
import com.gitir.reading.entity.Customer;
import com.gitir.reading.exception.CustomerNotFoundException;
import com.gitir.reading.repository.CustomerRepository;
import com.gitir.reading.repository.OrderHeaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderHeaderRepository orderHeaderRepository;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer=new Customer();
        customer.setName(customerRequest.getName());
        Customer result = customerRepository.save(customer);
        return CustomerResponse.of(result);
    }

    public List<OrderResponse> getOrdersFromCustomer(Long id) {
        Customer customer = findCustomerById(id);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        orderHeaderRepository.findAllByCustomer(customer, Pageable.ofSize(10)).forEach(order ->
                orderResponseList.add(OrderResponse.of(order)));
        return orderResponseList;
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
}
