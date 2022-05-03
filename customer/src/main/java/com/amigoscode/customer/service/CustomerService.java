package com.amigoscode.customer.service;

import com.amigoscode.customer.model.Customer;
import com.amigoscode.customer.dto.CustomerRegistrationRequest;
import com.amigoscode.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.save(customer);
    }
}
