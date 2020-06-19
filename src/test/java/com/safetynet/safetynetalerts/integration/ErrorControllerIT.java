package com.safetynet.safetynetalerts.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.safetynet.safetynetalerts.SafetyNetAlertsApplication;
import com.safetynet.safetynetalerts.controller.CustomErrorController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SafetyNetAlertsApplication.class)
public class ErrorControllerIT {

	@LocalServerPort
	private int port; // Inject the RANDOM_PORT into that variable

	@Autowired
	private CustomErrorController controller;

	@Autowired
	private TestRestTemplate restTemplate; // Used to perform testing method in the Tests

	@DisplayName("Sanity check test who fail if the application context cannot start")
	@Test
	public void givenTheController_whenItStart_thenItIsNotNull() {
		// Sanity check test that will fail if the application context cannot start
		assertThat(controller).isNotNull();
	}

	@DisplayName("The error page is correctly showed when an wrong URL is set")
	@Test
	public void givenTheAppStart_whenGoingOnAWrongURL_thenTheErrorPageIsShown() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/ThisIsAWrongURL", String.class))
				.contains("An error occurred");
	}
}
