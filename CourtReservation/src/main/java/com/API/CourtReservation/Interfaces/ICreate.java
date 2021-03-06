/**
 * @author: Stepan Pijacek
 * @description: Interface for Create class
 * */

package com.API.CourtReservation.Interfaces;

import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;

public interface ICreate {
    boolean CreateReservation(Reservations reservations);
    boolean CreateCourt(Courts courts);
}
