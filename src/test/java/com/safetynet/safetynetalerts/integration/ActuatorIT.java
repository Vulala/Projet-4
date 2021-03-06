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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SafetyNetAlertsApplication.class)
public class ActuatorIT {

	@LocalServerPort
	private int port; // Inject the RANDOM_PORT into that variable

	@Autowired
	private TestRestTemplate restTemplate; // Used to perform testing method in the Tests

	@DisplayName("The Health endpoint page contains Health informations")
	@Test
	public void givenTheAppStart_whenGoingOnTheHealthEndpoint_thenHealthInformationsAreShown() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/actuator/health", String.class))
				.contains("status").contains("UP");
	}

	@DisplayName("The Info endpoint page contains Info informations")
	@Test
	public void givenTheAppStart_whenGoingOnTheInfoEndpoint_thenInfoInformationsAreShown() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/actuator/info", String.class))
				.contains("SafetyNet Alerts").contains("description").contains("name");
	}

	@DisplayName("The Metrics endpoint page contains Metrics informations")
	@Test
	public void givenTheAppStart_whenGoingOnTheMetricsEndpoint_thenMetricsInformationsAreShown() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/actuator/metrics", String.class))
				.contains("jvm.memory.used").contains("http.server.requests");
	}

	@DisplayName("The Httptrace endpoint page contains Httptrace informations")
	@Test
	public void givenTheAppStart_whenGoingOnTheHttptraceEndpoint_thenHttptraceInformationsAreShown() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/actuator/httptrace", String.class))
				.contains("response").contains("200");
	}
}