package com.API.CourtReservation.Models;


import net.bytebuddy.NamingStrategy;

public class Courts {
    private int ID;
    private String Surface;
    private int PricePerMin;

    public Courts(int id, String surface, int pricePerMin){
        ID = id;
        Surface = surface;
        PricePerMin = pricePerMin;
    }

    public int getId(){ return ID; }
    public String getSurface(){ return Surface; }
    public int getPricePerMin(){ return PricePerMin; }

}
