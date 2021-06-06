/**
 * @author: Stepan Pijacek
 * @description: Interface for Read class
 * */

package com.API.CourtReservation.Interfaces;

import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;

import java.util.List;

public interface IRead {
    List<Courts> ReadCourtList();
    List<Reservations> ReadCourtReservation(int id);
    List<Reservations> ReadReservationPerPhone(int phone);
    int getCourtPrice(int id);
}
