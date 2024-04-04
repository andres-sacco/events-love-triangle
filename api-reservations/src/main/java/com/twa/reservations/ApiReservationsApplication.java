package com.twa.reservations;

import com.twa.reservations.configuration.EventQueuesConfiguration;
import com.twa.reservations.configuration.EventTopicConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ EventQueuesConfiguration.class, EventTopicConfiguration.class })
public class ApiReservationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiReservationsApplication.class, args);
    }

}
