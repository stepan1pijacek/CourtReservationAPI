package com.API.CourtReservation.ICRUDE;

import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;

public interface ICreate {
    boolean CreateReservation(Reservations reservations);
    boolean CreateCourt(Courts courts);
}
