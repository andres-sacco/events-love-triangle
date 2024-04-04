package com.twa.reservations.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "events.queues")
public class EventQueuesConfiguration {

    private String paymentsInProcess;
    private String paymentsConfirmed;

    public String getPaymentsInProcess() {
        return paymentsInProcess;
    }

    public void setPaymentsInProcess(String paymentsInProcess) {
        this.paymentsInProcess = paymentsInProcess;
    }

    public String getPaymentsConfirmed() {
        return paymentsConfirmed;
    }

    public void setPaymentsConfirmed(String paymentsConfirmed) {
        this.paymentsConfirmed = paymentsConfirmed;
    }
}
