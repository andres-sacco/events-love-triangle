package com.twa.reservations.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Objects;

@Document
public class Passenger {

    @Size(min = 1, max = 30)
    @NotBlank(message = "firstName is mandatory")
    @Field(name = "first_name")
    private String firstName;

    @Size(min = 1, max = 30)
    @NotBlank(message = "lastName is mandatory")
    @Field(name = "last_name")
    private String lastName;

    @Field(name = "document_number")
    private String documentNumber;

    @Field(name = "document_type")
    private String documentType;

    @Past(message = "birthday need to be a date in the past")
    @Field(name = "birthday")
    private LocalDate birthday;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(firstName, passenger.firstName) && Objects.equals(lastName, passenger.lastName)
                && Objects.equals(documentNumber, passenger.documentNumber)
                && Objects.equals(documentType, passenger.documentType) && Objects.equals(birthday, passenger.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, documentNumber, documentType, birthday);
    }

    @Override
    public String toString() {
        return "Passenger{ firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", documentNumber='"
                + documentNumber + '\'' + ", documentType='" + documentType + '\'' + ", birthday=" + birthday + '}';
    }
}
