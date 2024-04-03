package com.twa.reservations.listener;

import com.twa.reservations.model.Reservation;
import com.twa.reservations.model.Status;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveCallback;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReservationEntityListener implements BeforeSaveCallback<Reservation> {

    @Override
    public Reservation onBeforeSave(Reservation entity, Document document, String collection) {
        document.append("creation_date", LocalDate.now());
        document.append("status", Status.CREATED);

        entity.setCreationDate(LocalDate.now());
        entity.setStatus(Status.CREATED);
        return entity;
    }
}
