package com.twa.reservations.mapper;

import com.twa.reservations.dto.ReservationDTO;
import com.twa.reservations.model.Reservation;
import org.springframework.core.convert.converter.Converter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends Converter<Reservation, ReservationDTO> {

    @Override
    ReservationDTO convert(Reservation source);

}