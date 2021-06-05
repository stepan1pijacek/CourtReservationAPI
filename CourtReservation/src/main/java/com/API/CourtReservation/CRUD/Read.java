package com.API.CourtReservation.CRUD;

import com.API.CourtReservation.DB.DbConnection;
import com.API.CourtReservation.Interfaces.IRead;
import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;
import org.springframework.security.access.prepost.PrePostAdviceReactiveMethodInterceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Read implements IRead {
    private List<Reservations> reservationsList = new ArrayList<>();
    private List<Courts> courtsList = new ArrayList<>();
    public Reservations reservations = new Reservations();

    @Override
    public List<Courts> ReadCourtList() {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT * FROM courts";

        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            ResultSet rs = prSmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("ID");
                String surface = rs.getString("surface");
                int perMinutePrice = rs.getInt("perMinutePrice");

                Courts courts = new Courts();
                courts.setID(id);
                courts.setSurface(surface);
                courts.setPricePerMin(perMinutePrice);

                courtsList.add(courts);
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
                int timeInterval = rs.getInt("TimeInterval");
                boolean gameType = rs.getBoolean("GameType");
                int phoneNumber = rs.getInt("PhoneNumber");
                String surname = rs.getString("Surname");
                int price = rs.getInt("price");

                reservations = new Reservations();
                reservations.setCourtsID(id);
                reservations.setTimeInterval(timeInterval);
                reservations.setGameType(gameType);
                reservations.setPhoneNumber(phoneNumber);
                reservations.setSurname(surname);
                reservations.setPrice(price);

                reservationsList.add(reservations);
            }
            return reservationsList;
        } catch (SQLException exception){
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reservations> ReadReservationPerPhone(int phone) {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT * FROM reservation WHERE PhoneNumber = ?";
        reservationsList = new ArrayList<>();


        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            prSmt.setInt(1,phone);
            ResultSet rs = prSmt.executeQuery();
            while(rs.next()){
                reservations = new Reservations();
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

    @Override
    public int getCourtPrice(int id) {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT perMinutePrice FROM courts WHERE ID = ?";
        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            prSmt.setInt(1,id);
            ResultSet rs = prSmt.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    @Override
    public boolean CourtExists(int id) {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT ID FROM courts WHERE ID = ?";
        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            prSmt.setInt(1,id);
            ResultSet rs = prSmt.executeQuery();
            while (rs.next()){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean phoneNumberExists(int phone) {
        Connection connection = DbConnection.getDbConnection();
        String read = "SELECT ID FROM reservation WHERE PhoneNumber = ?";
        try(PreparedStatement prSmt = connection.prepareStatement(read)){
            prSmt.setInt(1,phone);
            ResultSet rs = prSmt.executeQuery();
            while (rs.next()){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
