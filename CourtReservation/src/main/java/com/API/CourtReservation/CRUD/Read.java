/**
 * @author: Stepan Pijacek
 * @description: Read class implementing IRead and ICheckForExistance
 * */


package com.API.CourtReservation.CRUD;

import com.API.CourtReservation.DB.DbConnection;
import com.API.CourtReservation.Interfaces.ICheckForExistance;
import com.API.CourtReservation.Interfaces.IRead;
import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Read implements IRead, ICheckForExistance {
    private List<Reservations> reservationsList = new ArrayList<>();
    private final List<Courts> courtsList = new ArrayList<>();

    /**
     * @author: Stepan Pijacek
     * @description: function getting court list
     * @parameters:
     * @returns: courtsList
     * */

    @Override
    public List<Courts> ReadCourtList() {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT * FROM courts";

        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            ResultSet rs = prSmt.executeQuery();
            while (rs.next()){
                Courts courts = new Courts();
                courts.setID(rs.getInt("ID"));
                courts.setSurface(rs.getString("surface"));
                courts.setPricePerMin(rs.getInt("perMinutePrice"));

                courtsList.add(courts);
            }
            return courtsList;

        } catch (SQLException exception){
            exception.printStackTrace();
            return null;
        }
    }


    /**
     * @author: Stepan Pijacek
     * @description: function getting reservation list corresponding ro provided court ID
     * @parameters: int id
     * @Returns: reservationsList
     * */

    @Override
    public List<Reservations> ReadCourtReservation(int id) {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT * FROM reservation WHERE CourtID = ?";

        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            prSmt.setInt(1, id);
            ResultSet rs = prSmt.executeQuery();
            while(rs.next()){
                Reservations reservations = new Reservations();
                reservations.setCourtsID(id);
                reservations.setTimeInterval(rs.getInt("TimeInterval"));
                reservations.setGameType(rs.getBoolean("GameType"));
                reservations.setPhoneNumber(rs.getInt("PhoneNumber"));
                reservations.setSurname(rs.getString("Surname"));
                reservations.setPrice(rs.getInt("price"));

                reservationsList.add(reservations);
            }
            return reservationsList;
        } catch (SQLException exception){
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * @author: Stepan Pijacek
     * @description: function getting reservation list corresponding to provided phone number
     * @parameters: int phone
     * @Returns: reservationsList
     * */

    @Override
    public List<Reservations> ReadReservationPerPhone(int phone) {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT * FROM reservation WHERE PhoneNumber = ?";

        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            prSmt.setInt(1,phone);
            ResultSet rs = prSmt.executeQuery();
            while(rs.next()){
                Reservations reservations = new Reservations();
                reservations.setCourtsID(rs.getInt("CourtID"));
                reservations.setTimeInterval(rs.getInt("TimeInterval"));
                reservations.setGameType(rs.getBoolean("GameType"));
                reservations.setPhoneNumber(phone);
                reservations.setSurname(rs.getString("Surname"));
                reservations.setPrice(rs.getDouble("Price"));

                reservationsList.add(reservations);
            }
            return reservationsList;
        } catch (SQLException exception){
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * @author: Stepan Pijacek
     * @description: function getting price per minute corresponding to the court with provided ID
     * @parameters: int id
     * @Returns: int
     * */

    @Override
    public int getCourtPrice(int id) {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT perMinutePrice FROM courts WHERE ID = ?";
        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            prSmt.setInt(1,id);
            ResultSet rs = prSmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    /**
     * @author: Stepan Pijacek
     * @description: function checking existance of the court with corresponding to the court with provided ID
     * @parameters: int id
     * @Returns: true when court exists, else false
     * */

    @Override
    public boolean CourtExists(int id) {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT ID FROM courts WHERE ID = ?";
        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            prSmt.setInt(1,id);
            ResultSet rs = prSmt.executeQuery();
            if(rs.next()){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @author: Stepan Pijacek
     * @description: function checking existance of the phone number with corresponding to the court with phone number
     * @parameters: int phone
     * @Returns: int
     * */

    @Override
    public boolean phoneNumberExists(int phone) {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT ID FROM reservation WHERE PhoneNumber = ?";
        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            prSmt.setInt(1,phone);
            ResultSet rs = prSmt.executeQuery();
            if(rs.next()){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
