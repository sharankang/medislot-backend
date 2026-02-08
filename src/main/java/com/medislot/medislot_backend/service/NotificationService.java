package com.medislot.medislot_backend.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Async
    public void sendBookingNotification(String patientName) {
        try {
            Thread.sleep(3000);
            System.out.println("NOTIFICATION SENT: Appointment confirmed for " + patientName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}