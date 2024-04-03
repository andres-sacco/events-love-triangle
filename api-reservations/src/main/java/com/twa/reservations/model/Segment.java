package com.twa.reservations.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document
public class Segment {

    @Field(name = "origin")
    private String origin;

    @Field(name = "destination")
    private String destination;

    @Field(name = "departure")
    private String departure;

    @Field(name = "arrival")
    private String arrival;

    @Field(name = "carrier")
    private String carrier;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Segment segment = (Segment) o;
        return Objects.equals(origin, segment.origin) && Objects.equals(destination, segment.destination)
                && Objects.equals(departure, segment.departure) && Objects.equals(arrival, segment.arrival)
                && Objects.equals(carrier, segment.carrier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, departure, arrival, carrier);
    }

    @Override
    public String toString() {
        return "Segment{ origin='" + origin + '\'' + ", destination='" + destination + '\'' + ", departure='"
                + departure + '\'' + ", arrival='" + arrival + '\'' + ", carrier='" + carrier + '\'' + '}';
    }
}
