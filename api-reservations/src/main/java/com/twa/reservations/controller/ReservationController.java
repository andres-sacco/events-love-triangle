package com.twa.reservations.controller;

import com.twa.reservations.controller.resource.ReservationResource;
import com.twa.reservations.dto.ReservationDTO;
import com.twa.reservations.dto.SearchReservationCriteriaDTO;
import com.twa.reservations.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@Validated
public class ReservationController implements ReservationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations(SearchReservationCriteriaDTO criteria) {
        LOGGER.info("Obtain all the reservations");
        List<ReservationDTO> response = service.getReservations(criteria);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable String id) {
        LOGGER.info("Obtain information from a reservation with {}", id);
        ReservationDTO response = service.getReservationById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> save(@RequestBody ReservationDTO reservation) {
        LOGGER.info("Saving new reservation");
        ReservationDTO response = service.save(reservation);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
