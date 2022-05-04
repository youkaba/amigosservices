package com.amigos.clients.notification;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerEmail,
        String message,
        String name
) {
}