package com.geeksforless.station.persistence.repository.user;

import com.geeksforless.station.persistence.entity.user.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends UserRepository<Customer> {

}