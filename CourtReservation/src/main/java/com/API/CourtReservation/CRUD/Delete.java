package com.API.CourtReservation.CRUD;

import com.API.CourtReservation.DB.DbConnection;
import com.API.CourtReservation.Interfaces.IDelete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete implements IDelete {
    //TODO: implement into controller code 204
    @Override
    public boolean DeleteReservationByPhone(int phoneNumber) {
        Connection connection = DbConnection.getDbConnection();
        String delete = "DELETE FROM reservation WHERE PhoneNumber = ?";

        try(PreparedStatement prSmt = connection.prepareStatement(delete)){
            prSmt.setInt(1, phoneNumber);
            prSmt.execute();
            return true;
        } catch (SQLException exception){
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean DeleteReservationByCourt(int courtID) {
        Connection connection = DbConnection.getDbConnection();
        String delete = "DELETE FROM reservation WHERE CourtID = ?";

        try(PreparedStatement prSmt = connection.prepareStatement(delete)){
            prSmt.setInt(1, courtID);
            prSmt.execute();
            return true;
        } catch (SQLException exception){
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean DeleteCourt(int courtID) {
        Connection connection = DbConnection.getDbConnection();
        String delete = "DELETE FROM courts WHERE ID = ?";

        try(PreparedStatement prSmt = connection.prepareStatement(delete)){
            prSmt.setInt(1, courtID);
            prSmt.execute();
            return true;
        } catch (SQLException exception){
            exception.printStackTrace();
            return false;
        }
    }
}
