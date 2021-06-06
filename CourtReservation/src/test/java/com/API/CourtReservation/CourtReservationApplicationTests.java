package com.API.CourtReservation;

import com.API.CourtReservation.Controllers.JwtAuthenticationController;
import com.API.CourtReservation.Models.JwtRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: for creation of reservation, create test suit for every possible time
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourtReservationApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JwtAuthenticationController jwtController;
	private String token;
	//Sanity check
	@Test
	void contextLoads() {
		assertThat(jwtController).isNotNull();
	}

	@Test
	public void getAuthorization() throws Exception{
		JwtRequest request = new JwtRequest("javainuse", "password");
		assertEquals(HttpStatus.OK.value(), "");
	}
}
