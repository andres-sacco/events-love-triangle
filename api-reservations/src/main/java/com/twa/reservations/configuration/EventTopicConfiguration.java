package com.twa.reservations.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "events.topics")
public class EventTopicConfiguration {

    private String reservationConfirmed;

    public String getReservationConfirmed() {
        return reservationConfirmed;
    }

    public void setReservationConfirmed(String reservationConfirmed) {
        this.reservationConfirmed = reservationConfirmed;
    }
}
