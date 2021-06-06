/**
 * @author: Stepan Pijacek
 * @description: Interface for Create class
 * */


package com.API.CourtReservation.Interfaces;

public interface ICheckForExistance {
    boolean CourtExists(int id);
    boolean phoneNumberExists(int phone);
}
