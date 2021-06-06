/**
 * @author: Stepan Pijacek
 * @description: Model for court table
 * */
package com.API.CourtReservation.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Courts {

    /**
     * all variable were marked with JsonProperty annotation to match JSON request body
     * */
    @JsonProperty("ID")
    private int ID;
    @JsonProperty("Surface")
    private String Surface;
    @JsonProperty("PricePerMin")
    private int PricePerMin;

    public Courts(){

    }

    public String getSurface(){ return Surface; }
    public int getPricePerMin(){ return PricePerMin; }
    public int getID(){ return ID; }

    public void setID(int id) { ID = id; }
    public void setSurface(String surface){
        Surface = surface;
    }
    public void setPricePerMin(int price){
        PricePerMin = price;
    }

}
