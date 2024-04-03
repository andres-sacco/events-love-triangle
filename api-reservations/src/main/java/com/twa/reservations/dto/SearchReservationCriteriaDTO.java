package com.twa.reservations.dto;

import java.time.LocalDate;

public class SearchReservationCriteriaDTO {
    private LocalDate reservationDate;

    private StatusDTO status;
    private Integer pageActual = 0;
    private Integer pageSize = 10;

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Integer getPageActual() {
        return pageActual;
    }

    public void setPageActual(Integer pageActual) {
        this.pageActual = pageActual;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }
}
