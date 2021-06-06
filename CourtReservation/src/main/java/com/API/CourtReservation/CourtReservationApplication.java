/**
 * @author: Stepan Pijacek
 * @description: Simple REST API for court reservations,
 * equipped with JWT authentication (this part has been heavily inspired by tutorial/article from this
 * website: https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
 * */
package com.API.CourtReservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourtReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourtReservationApplication.class, args);
	}

}
