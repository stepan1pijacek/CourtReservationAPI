/**
 * @author: Stepan Pijacek
 * @description: Create controller for processing requests. Extends class Create
 * */
package com.API.CourtReservation.Controllers;

import com.API.CourtReservation.CRUD.Create;
import com.API.CourtReservation.CRUD.Read;
import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;
import com.API.CourtReservation.Utils.CheckInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/create", consumes = "application/json", produces = "application/json")
@CrossOrigin
public class CreateController extends Create{

    //Called when game type is true = quads
    private static final Double PRICE_MULTIPLIER = 1.5;

    private final Read read = new Read();

    //If game type = true, its a quads game, therefore price is multiplied
    /**
     * @author: Stepan Pijacek
     * @description: endpoint for court reservation
     * @parameters: Reservations model
     * @returns: ResponseEntity<any>
     * */
    @RequestMapping(value = "/createReservation", method = RequestMethod.POST)
    public ResponseEntity<?> createReservation(@RequestBody Reservations reservations){
        int priceOfCourt = 0;
        try{
            if(CheckInput.checkTime(reservations.getTime()) == false || CheckInput.checkPhoneNumber(reservations.getPhoneNumber()) == false){
                return new ResponseEntity<>("Invalid time input, you have not inputted time, your time is bigger then our business hours or provided phone number is incorrect", HttpStatus.NOT_ACCEPTABLE);
            }
            if(reservations == null){
                return new ResponseEntity<>("Body of the request is empty", HttpStatus.NO_CONTENT);
            } else {
                priceOfCourt = read.getCourtPrice(reservations.getCourts());
                if(reservations.getGameType() == true){
                    reservations.setPrice(priceOfCourt * reservations.getTime() * PRICE_MULTIPLIER);
                }
                reservations.setPrice(priceOfCourt * reservations.getTime());
                CreateReservation(reservations);
                return new ResponseEntity<>("{\n Status: Reservation created successfully, \n Your price: " +reservations.getPrice() + " \n}", HttpStatus.CREATED);
            }
        } catch(Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author: Stepan Pijacek
     * @description: endpoint for court creation
     * @parameters: Courts model
     * @returns: ResponseEntity<any>
     * */
    @RequestMapping(
            value = "/createCourt", consumes = "application/json", produces = "application/json"
    )
    public ResponseEntity<?> createCourt(@RequestBody final Courts courts) throws Exception  {
        try{
            if(courts == null){
                return new ResponseEntity<>("Body of the request is empty", HttpStatus.NO_CONTENT);
            } else {
                CreateCourt(courts);
                return new ResponseEntity<>("Court has been created", HttpStatus.CREATED);
            }
        } catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
