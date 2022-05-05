package com.amigoscode.notification.service;

import com.amigoscode.clients.notification.NotificationRequest;
import com.amigoscode.notification.model.Notification;
import com.amigoscode.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
public record NotificationService(NotificationRepository notificationRepository) {
    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .sender(notificationRequest.name())
                .message(notificationRequest.message())
                .sentAt(now())
                .build());
    }
}
