package org.example.doorhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableRedisRepositories
@EnableFeignClients
public class DoorHubApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(DoorHubApplication.class, args);
    }
    
}
