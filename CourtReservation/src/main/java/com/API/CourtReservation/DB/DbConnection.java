package com.API.CourtReservation.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection extends MySQLConfig{
    private static volatile Connection dbConnection;

    public DbConnection(){

    }

    public static Connection getDbConnection(){
        if(dbConnection == null){
            synchronized (DbConnection.class){
                if(dbConnection == null){
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        dbConnection = DriverManager.getConnection("jdbc:mysql://"+MYSQL_HOST+"/"+MYSQL_DATABASE+"?user="+MYSQL_USER+"&password="+MYSQL_PWD);
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
