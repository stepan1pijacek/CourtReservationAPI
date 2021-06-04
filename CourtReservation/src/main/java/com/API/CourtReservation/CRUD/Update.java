package com.API.CourtReservation.CRUD;

import com.API.CourtReservation.ICRUDE.IUpdate;

public class Update implements IUpdate {
    @Override
    public boolean UpdateCourt(int id, String surface, int pricePerMinute) {
        return false;
    }

    @Override
    public boolean UpdateReservation(int phone) {
        return false;
    }
}
