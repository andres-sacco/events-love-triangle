package com.twa.reservations.repository.query;

import com.twa.reservations.dto.SearchReservationCriteriaDTO;
import com.twa.reservations.model.Itinerary;
import com.twa.reservations.model.Passenger;
import com.twa.reservations.model.Reservation;
import com.twa.reservations.model.Status;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReservationQuery {

    public static Example<Reservation> exampleWithSearchCriteria(SearchReservationCriteriaDTO criteria) {

        ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();

        Reservation entity = new Reservation();

        if (criteria.getReservationDate() != null) {
            entity.setCreationDate(criteria.getReservationDate());
        }

        if (criteria.getStatus() != null) {
            entity.setStatus(Status.valueOf(criteria.getStatus().name()));
        }

        return Example.of(entity, matcher);
    }

    private static void createPassengers(Reservation entity) {
        if (Objects.isNull(entity.getPassengers())) {
            List<Passenger> passengers = new ArrayList<>();
            passengers.add(new Passenger());
            entity.setPassengers(passengers);
        }
    }
}
