/**
 * @author: Stepan Pijacek
 * @description: Create class implementing ICreate
 * */


package com.API.CourtReservation.CRUD;

import com.API.CourtReservation.DB.DbConnection;
import com.API.CourtReservation.Interfaces.ICreate;
import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create implements ICreate {

    /**
     * @author: Stepan Pijacek
     * @description: function for creating reservation
     * @parameters: Reservation model
     * */

    @Override
    public boolean CreateReservation(Reservations reservations) {
        Connection connection = DbConnection.getDbConnection();
        String insert = "INSERT INTO reservation (CourtID, TimeInterval, GameType, PhoneNumber, Surname, Price) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement prSmt = connection.prepareStatement(insert)){
            prSmt.setInt(1, reservations.getCourts());
            prSmt.setInt(2,reservations.getTime());
            prSmt.setBoolean(3,reservations.getGameType());
            prSmt.setInt(4, reservations.getPhoneNumber());
            prSmt.setString(5, reservations.getSurname());
            prSmt.setDouble(6,reservations.getPrice());

            prSmt.execute();
            return true;
        } catch (SQLException exception){
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * @author: Stepan Pijacek
     * @description: function for creating court
     * @parameters: Courts model
     * */

    @Override
    public boolean CreateCourt(Courts courts) {
        Connection connection = DbConnection.getDbConnection();
        String insert = "INSERT INTO courts (surface, perMinutePrice) VALUES (?, ?)";

        try(PreparedStatement prSmt = connection.prepareStatement(insert)){
            prSmt.setString(1, courts.getSurface());
            prSmt.setInt(2, courts.getPricePerMin());

            prSmt.execute();
            return true;
        } catch (SQLException exception){
            exception.printStackTrace();
            return false;
        }
    }
}
