package com.geeksforless.station.service;

import com.geeksforless.station.persistence.entity.user.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers();

    Customer createCustomer(Customer customer);
}
