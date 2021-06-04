package com.API.CourtReservation.ICRUDE;

import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;

import java.util.List;

public interface IRead {
    List<Courts> ReadCourtList();
    List<Reservations> ReadCourtReservation(int id);
    List<Reservations> ReadReservationPerPhone(String phone);
}
