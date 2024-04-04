package com.twa.reservations.messaging.consumer;

import com.twa.reservations.messaging.producer.ReservationProducer;
import com.twa.reservations.model.Status;
import com.twa.reservations.service.ReservationService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);

    ReservationService service;

    ReservationProducer producer;

    @Autowired
    public PaymentConsumer(ReservationService service, ReservationProducer producer) {
        this.service = service;
        this.producer = producer;
    }

    @SqsListener("${events.queues.payments-in-process}")
    public void processPayment(@Payload String id) {
        LOGGER.info("Received message: {}", id);
        service.changeStatus(id, Status.IN_PROCESS);
    }

    @SqsListener("${events.queues.payments-confirmed}")
    public void confirmPayment(@Payload String id) {
        LOGGER.info("Received message: {}", id);
        service.changeStatus(id, Status.CONFIRMED);
        producer.sendMessage(id);
    }
}
