package com.twa.reservations.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Document
public class Reservation extends Base {

    @Valid
    @NotEmpty(message = "You need at least one passenger")
    private List<Passenger> passengers;

    @Valid
    private Itinerary itinerary;

    @Field(name = "creation_date")
    private LocalDate creationDate;

    private Status status;

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Reservation that = (Reservation) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(passengers, that.passengers)
                && Objects.equals(itinerary, that.itinerary) && Objects.equals(creationDate, that.creationDate)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), passengers, itinerary, creationDate, status);
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + getId() + ", creationDate=" + creationDate + ", status=" + status + '}';
    }
}
