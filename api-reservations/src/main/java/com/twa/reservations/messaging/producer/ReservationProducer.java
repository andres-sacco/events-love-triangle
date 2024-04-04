package com.twa.reservations.messaging.producer;

import com.twa.reservations.configuration.EventTopicConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Component
public class ReservationProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationProducer.class);

    SnsClient snsClient;

    EventTopicConfiguration eventTopicConfiguration;

    @Autowired
    public ReservationProducer(SnsClient snsClient, EventTopicConfiguration eventTopicConfiguration) {
        this.snsClient = snsClient;
        this.eventTopicConfiguration = eventTopicConfiguration;
    }

    public void sendMessage(String id) {

        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(eventTopicConfiguration.getReservationConfirmed()).message(id).messageGroupId("reservation")
                .messageDeduplicationId("producer").build();

        LOGGER.info("Publishing event {} to SNS topic {}", publishRequest,
                eventTopicConfiguration.getReservationConfirmed());

        snsClient.publish(publishRequest);
    }
}
