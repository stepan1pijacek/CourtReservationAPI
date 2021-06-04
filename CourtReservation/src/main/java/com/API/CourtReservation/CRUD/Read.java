package com.API.CourtReservation.CRUD;

import com.API.CourtReservation.DB.DbConnection;
import com.API.CourtReservation.ICRUDE.IRead;
import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Read implements IRead {
    private List<Reservations> reservationsList = null;
    private List<Courts> courtsList = null;

    @Override
    public List<Courts> ReadCourtList() {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT * FROM courts";

        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            ResultSet rs = prSmt.executeQuery();
            while (rs.next()){
                courtsList.add((Courts) rs);
            }
            return courtsList;

        } catch (SQLException exception){
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reservations> ReadCourtReservation(int id) {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT * FROM reservation WHERE CourtID = ?";

        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            prSmt.setInt(1, id);
            ResultSet rs = prSmt.executeQuery();
            while(rs.next()){
                reservationsList.add((Reservations) rs);
            }
            return reservationsList;
        } catch (SQLException exception){
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reservations> ReadReservationPerPhone(String phone) {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT * FROM reservation WHERE PhoneNumber = ?";

        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            prSmt.setString(1,phone);
            ResultSet rs = prSmt.executeQuery();
            while(rs.next()){
                reservationsList.add((Reservations) rs);
            }
            return reservationsList;
        } catch (SQLException exception){
            exception.printStackTrace();
            return null;
        }
    }
}
