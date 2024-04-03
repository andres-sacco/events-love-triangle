package com.twa.reservations.exception;

import com.twa.reservations.enums.APIError;
import org.springframework.http.HttpStatus;

import java.util.List;

public class EdteamException extends RuntimeException {
    private HttpStatus status;
    private String description;
    private List<String> reasons;

    public EdteamException(APIError error) {
        this.status = error.getHttpStatus();
        this.description = error.getMessage();
    }

    public EdteamException(HttpStatus status, String description, List<String> reasons) {
        this.status = status;
        this.description = description;
        this.reasons = reasons;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getReasons() {
        return reasons;
    }

    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
    }
}