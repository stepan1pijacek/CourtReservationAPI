/**
 * @author: Stepan Pijacek
 * @description: DB connection class, for establishing and maintaining connection to db. In current mode it is double checked Singleton
 * */
package com.API.CourtReservation.DB;

import com.API.CourtReservation.Config.MySQLConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection extends MySQLConfig {
    private static volatile Connection dbConnection;

    public DbConnection(){

    }

    public static Connection getDbConnection(){
        if(dbConnection == null){
            synchronized (DbConnection.class){
                if(dbConnection == null){
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/courtreservation?user=root&password=");
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dbConnection;
    }

    public static void closeConnection(){
        try{
            dbConnection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
