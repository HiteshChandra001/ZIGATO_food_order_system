package com.zigato.service;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        // Custom health check logic
        boolean isHealthy = checkSomeService();
        if (isHealthy) {
            return Health.up().withDetail("CustomHealth", "Service is up and running").build();
        } else {
            return Health.down().withDetail("CustomHealth", "Service is down").build();
        }
    }

    private boolean checkSomeService() {
        // Logic to check the service
        return true;
    }
}

