package com.twa.reservations.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

public class Itinerary {

    @Valid
    @NotEmpty(message = "You need at least one segment")
    private List<Segment> segment;

    private Price price;

    public List<Segment> getSegment() {
        return segment;
    }

    public void setSegment(List<Segment> segment) {
        this.segment = segment;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Itinerary itinerary = (Itinerary) o;
        return Objects.equals(segment, itinerary.segment) && Objects.equals(price, itinerary.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segment, price);
    }

    @Override
    public String toString() {
        return "Itinerary{ segment=" + segment + ", price=" + price + '}';
    }
}
