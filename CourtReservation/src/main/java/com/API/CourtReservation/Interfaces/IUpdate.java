/**
 * @author: Stepan Pijacek
 * @description: Interface for Update class
 * */

package com.API.CourtReservation.Interfaces;

import com.API.CourtReservation.Models.Reservations;

public interface IUpdate {
    boolean UpdateCourt(int id, String surface, int pricePerMinute);
    boolean UpdateReservation(Reservations reservations);
}
