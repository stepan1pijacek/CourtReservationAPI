/**
 * @author: Stepan Pijacek
 * @description: Update class implementing IUpdate
 * */

package com.API.CourtReservation.CRUD;

import com.API.CourtReservation.Interfaces.IUpdate;
import com.API.CourtReservation.Models.Reservations;

public class Update implements IUpdate {
    @Override
    public boolean UpdateCourt(int id, String surface, int pricePerMinute) {
        return false;
    }

    @Override
    public boolean UpdateReservation(Reservations reservations) {
        return false;
    }
}
