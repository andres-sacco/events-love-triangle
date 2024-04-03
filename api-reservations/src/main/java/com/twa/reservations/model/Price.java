package com.twa.reservations.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Objects;

@Document
public class Price {

    @Field(name = "total_price")
    private BigDecimal totalPrice;

    @Field(name = "total_tax")
    private BigDecimal totalTax;

    @Field(name = "base_price")
    private BigDecimal basePrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Price price = (Price) o;
        return Objects.equals(totalPrice, price.totalPrice) && Objects.equals(totalTax, price.totalTax)
                && Objects.equals(basePrice, price.basePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPrice, totalTax, basePrice);
    }

    @Override
    public String toString() {
        return "Price{ totalPrice=" + totalPrice + ", totalTax=" + totalTax + ", basePrice=" + basePrice + '}';
    }
}
