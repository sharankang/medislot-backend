package com.medislot.medislot_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync

public class MedislotBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedislotBackendApplication.class, args);
    }
}
