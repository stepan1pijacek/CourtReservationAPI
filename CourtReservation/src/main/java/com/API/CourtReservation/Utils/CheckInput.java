package com.API.CourtReservation.Utils;

public class CheckInput {
    public static boolean checkTime(int time){
        if(time == 0){
            return false;
        } else if(time < 0){
            return false;
        } else if(time > 480 ){
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkPhoneNumber(int phone){
        int phoneNumberLength = String.valueOf(phone).length();
        if(phoneNumberLength != 9){
            return false;
        } else{
            return true;
        }
    }
}
