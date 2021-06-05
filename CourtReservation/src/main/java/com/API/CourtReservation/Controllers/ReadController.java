package com.API.CourtReservation.Controllers;

import com.API.CourtReservation.CRUD.Read;
import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/read", produces = "application/json")
@CrossOrigin
public class ReadController {

    private Read read = new Read();

    @RequestMapping(value = "/readCourtList", method = RequestMethod.GET)
    public ResponseEntity<?> readCourtList(){
        try{
            return new ResponseEntity<>(read.ReadCourtList(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/readReservationWPhoneNumber", method = RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<?> readReservationByPhoneNumber(@RequestBody Reservations reservations){
        try{
            if(read.phoneNumberExists(reservations.getPhoneNumber()) == false){
                return new ResponseEntity<>("Phone number does not exist", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(read.ReadReservationPerPhone(reservations.getPhoneNumber()), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/readReservationPerCourt", method = RequestMethod.GET, consumes = "application/json")
    public ResponseEntity<?> readReservationByCourtID(@RequestBody Reservations reservations){
        try{
            if(read.CourtExists(reservations.getCourts()) == false){
                return new ResponseEntity<>("Court number does not exist", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(read.ReadCourtReservation(reservations.getCourts()), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.OK);
        }
    }
}
