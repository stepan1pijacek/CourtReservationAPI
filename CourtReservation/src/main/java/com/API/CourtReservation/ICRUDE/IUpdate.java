package com.API.CourtReservation.ICRUDE;

public interface IUpdate {
    boolean UpdateCourt(int id, String surface, int pricePerMinute);
    boolean UpdateReservation(int phone);
}
