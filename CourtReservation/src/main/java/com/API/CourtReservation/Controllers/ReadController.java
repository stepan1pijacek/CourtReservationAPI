/**
 * @author: Stepan Pijacek
 * @description: Read controller for processing READ requests, extends Read class
 * */
package com.API.CourtReservation.Controllers;

import com.API.CourtReservation.CRUD.Read;
import com.API.CourtReservation.Models.Reservations;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            if(phoneNumberExists(reservations.getPhoneNumber()) == false){
                return new ResponseEntity<>("Phone number does not exist", HttpStatus.NOT_FOUND);
            }
            List<?> phoneReservation = ReadReservationPerPhone(reservations.getPhoneNumber());
            return new ResponseEntity<>(phoneReservation, HttpStatus.OK);
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
        try{
            if(CourtExists(reservations.getCourts()) == false){
                return new ResponseEntity<>("Court number does not exist", HttpStatus.NOT_FOUND);
            }
            List<?> courtReservation = ReadCourtReservation(reservations.getCourts());
            return new ResponseEntity<>(courtReservation, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.OK);
        }
    }
}
