package com.geeksforless.station.service.impl;

import com.geeksforless.station.persistence.entity.user.Customer;
import com.geeksforless.station.persistence.repository.user.CustomerRepository;
import com.geeksforless.station.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
