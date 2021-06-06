/**
 * @author: Stepan Pijacek
 * @description: Utility for checking validity of provided inputs
 * */
package com.API.CourtReservation.Utils;

public class CheckInput {
    public static boolean checkTime(int time){
        if(time == 0){
            return false;
        } else if(time < 0){
            return false;
        } else return time <= 480;
    }

    public static boolean checkPhoneNumber(int phone){
        int phoneNumberLength = String.valueOf(phone).length();
        return phoneNumberLength == 9;
    }
}
