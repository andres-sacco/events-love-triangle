package com.twa.reservations.repository;

import com.twa.reservations.model.Reservation;
import com.twa.reservations.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

public interface ReservationRepository extends MongoRepository<Reservation, String> {

    @Query("{ '_id' : ?0 }")
    @Update("{ '$set' : {'status': ?1 }}")
    void updateStatusById(String id, Status status);
}