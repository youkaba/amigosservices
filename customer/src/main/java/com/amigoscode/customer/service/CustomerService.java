package com.amigoscode.customer.service;

import com.amigos.clients.fraud.FraudCheckResponse;
import com.amigos.clients.fraud.FraudClient;
import com.amigos.clients.notification.NotificationClient;
import com.amigos.clients.notification.NotificationRequest;
import com.amigoscode.customer.dto.CustomerRegistrationRequest;
import com.amigoscode.customer.model.Customer;
import com.amigoscode.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        FraudClient fraudClient,
        NotificationClient notificationClient) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // TODO check if email valid
        // TODO check if email not taken
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException(" fraudster");
        }

        // TODO send notification
        notificationClient.sendNotification(
                new NotificationRequest(customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to amigos code...", customer.getLastName()),
                        customer.getFirstName()
                )
        );
    }
}
