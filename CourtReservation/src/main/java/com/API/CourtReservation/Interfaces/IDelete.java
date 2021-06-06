/**
 * @author: Stepan Pijacek
 * @description: Interface for Delete class
 * */


package com.API.CourtReservation.Interfaces;

public interface IDelete {
    boolean DeleteReservationByPhone(int phoneNumber);
    boolean DeleteReservationByCourt(int courtID);
    boolean DeleteCourt(int courtID);
}
