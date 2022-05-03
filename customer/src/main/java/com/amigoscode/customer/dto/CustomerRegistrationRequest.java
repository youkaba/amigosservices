package com.amigoscode.customer.dto;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {

}
