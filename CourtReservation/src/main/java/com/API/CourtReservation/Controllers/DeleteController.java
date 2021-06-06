/**
 * @author: Stepan Pijacek
 * @description: DELETE controller for processing DELETE requests, extends Delete class
 * */
package com.API.CourtReservation.Controllers;

import com.API.CourtReservation.CRUD.Delete;
import com.API.CourtReservation.CRUD.Read;
import com.API.CourtReservation.Models.Courts;
import com.API.CourtReservation.Models.Reservations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/delete", consumes = "application/json", produces = "application/json")
@CrossOrigin
public class DeleteController extends Delete{

    private final Read read = new Read();

    /**
     * @author: Stepan Pijacek
     * @description: endpoint for deleting reservation corresponding to the phone number
     * @parameters: Reservation model
     * @returns: ResponseEntity<any>
     * */
    @RequestMapping(value = "/deleteByPhone", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReservationByPhone(@RequestBody Reservations reservations){
        try{
            if(read.phoneNumberExists(reservations.getPhoneNumber()) == false){
                return new ResponseEntity<>("Phone number does not exist", HttpStatus.NOT_FOUND);
            }

            DeleteReservationByPhone(reservations.getPhoneNumber());
            return new ResponseEntity<>("Reservation has been deleted", HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author: Stepan Pijacek
     * @description: endpoint for deleting reservation corresponding to the court ID
     * @parameters: Reservation model
     * @returns: ResponseEntity<any>
     * */
    @RequestMapping(value = "/deleteByCourt", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReservationByCourt(@RequestBody Reservations reservations){
        try{
            if(read.CourtExists(reservations.getCourts()) == false){
                return new ResponseEntity<>("Court does not exist", HttpStatus.NOT_FOUND);
            }

            DeleteReservationByCourt(reservations.getCourts());
            return  new ResponseEntity<>("Reservation has been deleted", HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author: Stepan Pijacek
     * @description: endpoint for deleting court corresponding to the court ID
     * @parameters: Courts model
     * @returns: ResponseEntity<any>
     * */
    @RequestMapping(value = "/deleteCourt", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCourt(@RequestBody Courts courts){
        try{
            if(read.CourtExists(courts.getID()) == false){
                return new ResponseEntity<>("Court does not exist", HttpStatus.NOT_FOUND);
            }

            DeleteCourt(courts.getID());
            return new ResponseEntity<>("Court has been deleted", HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
