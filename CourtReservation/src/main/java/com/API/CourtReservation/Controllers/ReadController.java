/**
 * @author: Stepan Pijacek
 * @description: Read controller for processing READ requests, extends Read class
 * @problem: Unfortunately, this controller has bug that I can't figure out, so if you know how to fix it, feel free to tell me.
 * Once you sen request with Postman it returns list as it should, but that list contains duplicates as seen below.
 *     {
 *         "time": 1,
 *         "courts": 1,
 *         "phoneNumber": 123456789,
 *         "gameType": false,
 *         "surname": "Honza",
 *         "price": 20.0,
 *         "CourtsID": 1,
 *         "TimeInterval": 1,
 *         "GameType": false,
 *         "PhoneNumber": 123456789,
 *         "Surname": "Honza",
 *         "Price": 20.0
 *     },
 * And i have no clue why is it happening. I have tried multiple things to make it work but nothing worked.
 * */
package com.API.CourtReservation.Controllers;

import com.API.CourtReservation.CRUD.Read;
import com.API.CourtReservation.Models.Reservations;
import com.API.CourtReservation.Utils.CheckInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/read", produces = "application/json")
@CrossOrigin
public class ReadController extends Read{


    /**
     * @author: Stepan Pijacek
     * @description: endpoint for reading court list
     * @parameters:
     * @returns: ResponseEntity<any>(courtList)
     * */
    @JsonProperty("ReadCourtList")
    @RequestMapping(value = "/readCourtList", method = RequestMethod.GET)
    public ResponseEntity<?> readCourtList(){
        try{
            List<?> courtList = ReadCourtList();
            return new ResponseEntity<>(courtList, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author: Stepan Pijacek
     * @description: endpoint for reading reservation corresponding to the phone number
     * @parameters: Reservation(PhoneNumber) model
     * @returns: ResponseEntity<any>(phoneReservationList)
     * */
    @JsonProperty("ReadWithPhone")
    @RequestMapping(value = "/readReservationWPhoneNumber", method = RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<?> readReservationByPhoneNumber(@RequestBody Reservations reservations){
        try{
            if (CheckInput.checkPhoneNumber(reservations.getPhoneNumber()) == false){
                return new ResponseEntity<>("Invalid phone number input, provided phone number is incorrect", HttpStatus.NOT_ACCEPTABLE);
            }
            if(phoneNumberExists(reservations.getPhoneNumber()) == false){
                return new ResponseEntity<>("Phone number does not exist", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Reservations>>(ReadReservationPerPhone(reservations.getPhoneNumber()), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author: Stepan Pijacek
     * @description: endpoint for reading reservation corresponding to the court ID
     * @parameters: Reservation(CourtsID) model
     * @returns: ResponseEntity<any>(courtReservationList)
     * */
    @JsonProperty("ReadReservationPerCourt")
    @RequestMapping(value = "/readReservationPerCourt", method = RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<?> readReservationByCourtID(@RequestBody Reservations reservations){
        List<Reservations> courtReservation = null;
        courtReservation.clear();
        try{
            if(CourtExists(reservations.getCourts()) == false){
                return new ResponseEntity<>("Court number does not exist", HttpStatus.NOT_FOUND);
            }
            courtReservation = ReadCourtReservation(reservations.getCourts());
            reservations.setClear();
            return new ResponseEntity<>(courtReservation, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.OK);
        }
    }
}
