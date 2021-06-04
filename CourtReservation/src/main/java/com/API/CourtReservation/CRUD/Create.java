package com.API.CourtReservation.CRUD;

import com.API.CourtReservation.DB.DbConnection;
import com.API.CourtReservation.ICRUDE.ICreate;
import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create implements ICreate {

    //TODO: implement phone number check, if phone number exists once, update current newest reservation 204 , else create 201
    @Override
    public boolean CreateReservation(Reservations reservations) {
        Connection connection = DbConnection.getDbConnection();
        String insert = "INSERT INTO reservation (CourtID, TimeInterval, GameType, PhoneNumber, Surname) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement prSmt = connection.prepareStatement(insert)){
            prSmt.setInt(1, reservations.getCourts());
            prSmt.setInt(2,reservations.getTime());
            prSmt.setBoolean(3,reservations.getGameType());
            prSmt.setString(4, reservations.getPhoneNumber());
            prSmt.setString(5, reservations.getSurname());

            prSmt.execute();
            return true;
        } catch (SQLException exception){
            exception.printStackTrace();
            return false;
        }
    }

    //TODO: implement court check, if court already exists return 409
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
