package com.API.CourtReservation.ICRUDE;

public interface IDelete {
    boolean DeleteReservationByPhone(String phoneNumber);
    boolean DeleteReservationByCourt(int courtID);
    boolean DeleteCourt(int courtID);
}
