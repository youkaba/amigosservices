package com.amigoscode.customer.service;

import com.amigoscode.customer.dto.FraudCheckResponse;
import com.amigoscode.customer.model.Customer;
import com.amigoscode.customer.dto.CustomerRegistrationRequest;
import com.amigoscode.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // TODO check if email valid
        // TODO check if email not taken
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException(" fraudster");
        }

        // TODO send notification
    }
}
