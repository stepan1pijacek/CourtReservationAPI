package com.API.CourtReservation.Models;

import java.sql.Time;

public class Reservations {

    private int ID;
    private int CourtsID;
    private int TimeInterval;
    private boolean GameType;
    private String PhoneNumber;
    private String Surname;

    public Reservations(int id, int courtsID, int timeInterval, boolean gameType, String phoneNumber, String surname){
        ID = id;
        CourtsID = courtsID;
        TimeInterval = timeInterval;
        GameType = gameType;
        PhoneNumber = phoneNumber;
        Surname = surname;
    }

    public int getID(){ return ID; }
    public int getCourts(){
        return CourtsID;
    }
    public int getTime() { return TimeInterval; }
    public boolean getGameType() { return GameType; }
    public String getPhoneNumber() { return PhoneNumber; }
    public String getSurname() { return Surname; }
}
