/**
 * @author: Stepan Pijacek
 * @description: Model for reservation table
 * */

package com.API.CourtReservation.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Time;

public class Reservations implements Serializable {

    /**
     * all variable were marked with JsonProperty annotation to match JSON request body
     * */
    @JsonProperty("CourtsID")
    private int CourtsID;
    @JsonProperty("TimeInterval")
    private int TimeInterval;

    //If game type is true, then its quad game and price is multiplied by 1.5
    @JsonProperty("GameType")
    private boolean GameType;
    @JsonProperty("PhoneNumber")
    private int PhoneNumber;
    @JsonProperty("Surname")
    private String Surname;
    @JsonProperty("Price")
    private double Price;

    public Reservations(){}

    public int getCourts(){
        return CourtsID;
    }
    public int getTime() { return TimeInterval; }
    public boolean getGameType() { return GameType; }
    public double getPrice(){ return Price;}
    public int getPhoneNumber() { return PhoneNumber; }
    public String getSurname() { return Surname; }

    public void setPrice(double price){ Price = price; }
    public void setCourtsID(int id) { CourtsID = id; }
    public void setTimeInterval(int timeInterval) { TimeInterval = timeInterval; }
    public void setGameType(boolean gameType) { GameType = gameType; }
    public void setPhoneNumber(int phoneNumber) { PhoneNumber = phoneNumber; }
    public void setSurname(String surname) { Surname = surname; }

    public void setClear(){
        Surname = null;
        Price = 0;
        CourtsID = 0;
        TimeInterval = 0;
        GameType = false;
        PhoneNumber = 0;
    }
}
