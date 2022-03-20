package com.getir.reading.service;

import com.getir.reading.exception.CustomerNotFoundException;
import com.getir.reading.repository.CustomerRepository;
import com.getir.reading.repository.OrderHeaderRepository;
import com.getir.reading.model.request.CustomerRequest;
import com.getir.reading.model.response.CustomerResponse;
import com.getir.reading.model.response.OrderResponse;
import com.getir.reading.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderHeaderRepository orderHeaderRepository;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        log.debug("createCustomer called");
        Customer customer=new Customer();
        customer.setName(customerRequest.getName());
        Customer result = customerRepository.save(customer);
        log.debug("createCustomer end!!!");

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
        log.debug("findCustomerById id ===> {}",id);
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
}
