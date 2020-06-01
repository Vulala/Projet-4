package com.safetynet.safetynetalerts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.safetynet.safetynetalerts.controller.AppController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EndpointsCRUDTest {

	@LocalServerPort
	private int port; // Inject the RANDOM_PORT into that variable

	@Autowired
	private AppController controller;
	@Autowired
	private TestRestTemplate restTemplate; // Used to perform testing method in the Tests

	@DisplayName("Sanity check test who fail if the application context cannot start")
	@Test
	public void givenTheController_whenItStart_thenItIsNotNull() {
		// Sanity check test that will fail if the application context cannot start
		assertThat(controller).isNotNull();
	}

	@DisplayName("When going on the /person endpoint, we can perform POST action")
	@Test
	public void givenTheAppStart_whenGoingOnThePersonEndpoint_thenICanDoPOSTAction() throws Exception {
		JSONObject personBody = new JSONObject();
		personBody.put("firstName", "AAAA");
		personBody.put("lastName", "BBBB");
		personBody.put("address", "AAAA");
		personBody.put("city", "AAAA");
		personBody.put("zip", "0000"); // String 0 and not int 0 to match the expected String assertion
		personBody.put("phone", "0000");
		personBody.put("email", "AAAA@BBBB.com");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> postBody = new HttpEntity<String>(personBody.toString(), headers);
		// postBody: a JSONObject map containing the informations as String who then
		// call toString to set the map as a String, toString JSONify the strings.
		// headers: define the content type.
		ResponseEntity<String> postRequest = restTemplate.postForEntity("http://localhost:" + port + "/person",
				postBody, String.class);
		// postRequest: Ask the restTemplate to do a POST request (on an URL, with a
		// postBody, of a String type).

		// ASSERT
		Assert.assertEquals(201, postRequest.getStatusCodeValue());
		String expectedJSONToBePOSTed = "{\"firstName\":\"AAAA\",\"lastName\":\"BBBB\",\"address\":\"AAAA\",\"city\":\"AAAA\",\"zip\":\"0000\",\"phone\":\"0000\",\"email\":\"AAAA@BBBB.com\"}";
		JSONAssert.assertEquals(expectedJSONToBePOSTed, postBody.getBody(), true);
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/person/AAAABBBB", String.class))
				.contains("AAAA").contains("BBBB").contains("0").contains("phone").contains("AAAA@BBBB");
	}

	@DisplayName("When going on the /person endpoint, we can perform PUT action")
	@Test
	public void givenTheAppStart_whenGoingOnThePersonEndpoint_thenICanDoPUTAction() throws Exception {
		// POST request, to make the test independent
		JSONObject previousPOSTBody = new JSONObject();
		previousPOSTBody.put("firstName", "AAAA");
		previousPOSTBody.put("lastName", "BBBB");
		previousPOSTBody.put("address", "AAAA");
		previousPOSTBody.put("city", "AAAA");
		previousPOSTBody.put("zip", "0000"); // String 0 and not int 0 to match the expected String assertion
		previousPOSTBody.put("phone", "0000");
		previousPOSTBody.put("email", "AAAA@BBBB.com");
		HttpHeaders postHeader = new HttpHeaders();
		postHeader.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> postBody = new HttpEntity<String>(previousPOSTBody.toString(), postHeader);
		restTemplate.postForEntity("http://localhost:" + port + "/person", postBody, String.class);

		// PUT request
		JSONObject personBody = new JSONObject();
		personBody.put("firstName", "ZZZZ");
		personBody.put("lastName", "YYYY");
		personBody.put("address", "ZZZZ");
		personBody.put("city", "ZZZZ");
		personBody.put("zip", "9999"); // String 9999 and not int 9999 to match the expected String assertion
		personBody.put("phone", "9999");
		personBody.put("email", "ZZZZ@YYYY.com");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> putBody = new HttpEntity<String>(personBody.toString(), headers);
		ResponseEntity<String> putRequest = restTemplate.exchange("http://localhost:" + port + "/person/AAAABBBB",
				HttpMethod.PUT, putBody, String.class); // Need to use a slightly different method to do a PUT request.

		// ASSERT
		Assert.assertEquals(200, putRequest.getStatusCodeValue());
		String expectedJSONToBePUTed = "{\"firstName\":\"ZZZZ\",\"lastName\":\"YYYY\",\"address\":\"ZZZZ\",\"city\":\"ZZZZ\",\"zip\":\"9999\",\"phone\":\"9999\",\"email\":\"ZZZZ@YYYY.com\"}";
		JSONAssert.assertEquals(expectedJSONToBePUTed, putBody.getBody(), true);
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/person/AAAABBBB", String.class))
				.contains("AAAA").contains("ZZZZ").contains("ZZZZ@YYYY.com").contains("9999").contains("city");
	}

	@DisplayName("When going on the /person endpoint, we can perform DELETE action")
	@Test
	public void givenTheAppStart_whenGoingOnThePersonEndpoint_thenICanDoDELETEAction() throws Exception {
		// POST request, to make the test independent
		JSONObject previousPOSTBody = new JSONObject();
		previousPOSTBody.put("firstName", "AAAA");
		previousPOSTBody.put("lastName", "BBBB");
		previousPOSTBody.put("address", "AAAA");
		previousPOSTBody.put("city", "AAAA");
		previousPOSTBody.put("zip", "0000");
		previousPOSTBody.put("phone", "0000");
		previousPOSTBody.put("email", "AAAA@BBBB.com");
		HttpHeaders postHeader = new HttpHeaders();
		postHeader.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> postBody = new HttpEntity<String>(previousPOSTBody.toString(), postHeader);
		restTemplate.postForEntity("http://localhost:" + port + "/person", postBody, String.class);

		// DELETE request
		restTemplate.delete("http://localhost:" + port + "/person/AAAABBBB");
		// Need to use a slightly different method to do a DELETE request.

		// ASSERT
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/person", String.class))
				.doesNotContain("AAAA").doesNotContain("BBBB").doesNotContain("AAAA@BBBB.com").doesNotContain("0000");

	}

	@DisplayName("When going on the /firestation endpoint, we can perform POST action")
	@Test
	public void givenTheAppStart_whenGoingOnTheFirestationEndpoint_thenICanDoPOSTAction() throws Exception {
		JSONObject firestationBody = new JSONObject();
		firestationBody.put("address", "AAAA");
		firestationBody.put("station", "0000"); // String 0000 and not int 0000 to match the expected String assertion
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> postBody = new HttpEntity<String>(firestationBody.toString(), headers);
		ResponseEntity<String> postRequest = restTemplate.postForEntity("http://localhost:" + port + "/firestation",
				postBody, String.class);

		// ASSERT
		Assert.assertEquals(201, postRequest.getStatusCodeValue());
		String expectedJSONToBePOSTed = "{\"address\":\"AAAA\",\"station\":\"0000\"}";
		JSONAssert.assertEquals(expectedJSONToBePOSTed, postBody.getBody(), true);
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/firestation/AAAA", String.class))
				.contains("AAAA");
	}

	@DisplayName("When going on the /firestation endpoint, we can perform PUT action")
	@Test
	public void givenTheAppStart_whenGoingOnTheFirestationEndpoint_thenICanDoPUTAction() throws Exception {
		// POST request, to make the test independent
		JSONObject previousPOSTBody = new JSONObject();
		previousPOSTBody.put("address", "AAAA");
		previousPOSTBody.put("station", "0000");
		HttpHeaders previousPOSTHeaders = new HttpHeaders();
		previousPOSTHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> postBody = new HttpEntity<String>(previousPOSTBody.toString(), previousPOSTHeaders);
		restTemplate.postForEntity("http://localhost:" + port + "/firestation", postBody, String.class);

		// PUT request
		JSONObject firestationBody = new JSONObject();
		firestationBody.put("address", "ZZZZ");
		firestationBody.put("station", "9999");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> putBody = new HttpEntity<String>(firestationBody.toString(), headers);
		ResponseEntity<String> putRequest = restTemplate.exchange("http://localhost:" + port + "/firestation/AAAA",
				HttpMethod.PUT, putBody, String.class); // Need to use a slightly different method to do a PUT request.

		// ASSERT
		Assert.assertEquals(200, putRequest.getStatusCodeValue());
		String expectedJSONToBePUTed = "{\"address\":\"ZZZZ\",\"station\":\"9999\"}";
		JSONAssert.assertEquals(expectedJSONToBePUTed, putBody.getBody(), true);
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/firestation/AAAA", String.class))
				.contains("AAAA").contains("9999");
	}

	@DisplayName("When going on the /firestation endpoint, we can perform DELETE action")
	@Test
	public void givenTheAppStart_whenGoingOnTheFirestationEndpoint_thenICanDoDELETEAction() throws Exception {
		// POST request, to make the test independent
		JSONObject previousPOSTBody = new JSONObject();
		previousPOSTBody.put("address", "AAAA");
		previousPOSTBody.put("station", "0000");
		HttpHeaders previousPOSTHeaders = new HttpHeaders();
		previousPOSTHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> postBody = new HttpEntity<String>(previousPOSTBody.toString(), previousPOSTHeaders);
		restTemplate.postForEntity("http://localhost:" + port + "/firestation", postBody, String.class);

		// DELETE request
		restTemplate.delete("http://localhost:" + port + "/firestation/AAAA");
		// Need to use a slightly different method to do a DELETE request.

		// ASSERT
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/firestation", String.class))
				.doesNotContain("AAAA").doesNotContain("0000");

	}

	@DisplayName("When going on the /medicalRecord endpoint, we can perform POST action")
	@Test
	public void givenTheAppStart_whenGoingOnTheMedicalRecordEndpoint_thenICanDoPOSTAction() throws Exception {
		JSONObject medicalRecordBody = new JSONObject();
		medicalRecordBody.put("firstName", "AAAA");
		medicalRecordBody.put("lastName", "BBBB");
		medicalRecordBody.put("birthdate", "01/01/0001");
		List<String> medications = new ArrayList<String>();
		medications.add("AAAA");
		medications.add("BBBB");
		medicalRecordBody.put("medications", new JSONArray(medications));
		List<String> allergies = new ArrayList<String>();
		allergies.add("AAAA");
		allergies.add("BBBB");
		medicalRecordBody.put("allergies", new JSONArray(allergies));
		// Above lines are used to add an Array to the JSON request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> postBody = new HttpEntity<String>(medicalRecordBody.toString(), headers);
		ResponseEntity<String> postRequest = restTemplate.postForEntity("http://localhost:" + port + "/medicalRecord",
				postBody, String.class);

		// ASSERT
		Assert.assertEquals(201, postRequest.getStatusCodeValue());
		String expectedJSONToBePOSTed = "{\"firstName\":\"AAAA\",\"lastName\":\"BBBB\",\"birthdate\":\"01/01/0001\",\"medications\":[\"AAAA\",\"BBBB\"],\"allergies\":[\"AAAA\",\"BBBB\"]}";
		JSONAssert.assertEquals(expectedJSONToBePOSTed, postBody.getBody(), true);
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/medicalRecord/AAAABBBB", String.class))
				.contains("AAAA").contains("BBBB").contains("01/01/0001").contains("medications").contains("allergies");
	}

	@DisplayName("When going on the /medicalRecord endpoint, we can perform PUT action")
	@Test
	public void givenTheAppStart_whenGoingOnTheMedicalRecordEndpoint_thenICanDoPUTAction() throws Exception {
		// POST request, to make the test independent
		JSONObject previousPOSTBody = new JSONObject();
		previousPOSTBody.put("firstName", "AAAA");
		previousPOSTBody.put("lastName", "BBBB");
		previousPOSTBody.put("birthdate", "01/01/0001");
		List<String> postMedication = new ArrayList<String>();
		postMedication.add("AAAA");
		postMedication.add("BBBB");
		previousPOSTBody.put("medications", new JSONArray(postMedication));
		List<String> postAllergies = new ArrayList<String>();
		postAllergies.add("AAAA");
		postAllergies.add("BBBB");
		previousPOSTBody.put("allergies", new JSONArray(postAllergies));
		// Above lines are used to add an Array to the JSON request
		HttpHeaders postHeader = new HttpHeaders();
		postHeader.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> postBody = new HttpEntity<String>(previousPOSTBody.toString(), postHeader);
		restTemplate.postForEntity("http://localhost:" + port + "/medicalRecord", postBody, String.class);

		// PUT request
		JSONObject medicalRecordBody = new JSONObject();
		medicalRecordBody.put("firstName", "ZZZZ");
		medicalRecordBody.put("lastName", "YYYY");
		medicalRecordBody.put("birthdate", "12/31/9999");
		List<String> medications = new ArrayList<String>();
		medications.add("ZZZZ");
		medications.add("YYYY");
		medicalRecordBody.put("medications", new JSONArray(medications));
		List<String> allergies = new ArrayList<String>();
		allergies.add("ZZZZ");
		allergies.add("YYYY");
		medicalRecordBody.put("allergies", new JSONArray(allergies));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> putBody = new HttpEntity<String>(medicalRecordBody.toString(), headers);
		ResponseEntity<String> putRequest = restTemplate.exchange(
				"http://localhost:" + port + "/medicalRecord/AAAABBBB", HttpMethod.PUT, putBody, String.class);
		// Need to use a slightly different method to do a PUT request.

		// ASSERT
		Assert.assertEquals(200, putRequest.getStatusCodeValue());
		String expectedJSONToBePUTed = "{\"firstName\":\"ZZZZ\",\"lastName\":\"YYYY\",\"birthdate\":\"12/31/9999\",\"medications\":[\"ZZZZ\",\"YYYY\"],\"allergies\":[\"ZZZZ\",\"YYYY\"]}";
		JSONAssert.assertEquals(expectedJSONToBePUTed, putBody.getBody(), true);
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/medicalRecord/AAAABBBB", String.class))
				.contains("AAAA").contains("BBBB").contains("12/31/9999").contains("medications").contains("allergies");
	}

	@DisplayName("When going on the /medicalRecord endpoint, we can perform DELETE action")
	@Test
	public void givenTheAppStart_whenGoingOnTheMedicalRecordEndpoint_thenICanDoDELETEAction() throws Exception {
		// POST request, to make the test independent
		JSONObject previousPOSTBody = new JSONObject();
		previousPOSTBody.put("firstName", "AAAA");
		previousPOSTBody.put("lastName", "BBBB");
		previousPOSTBody.put("birthdate", "01/01/0001");
		List<String> postMedication = new ArrayList<String>();
		postMedication.add("AAAA");
		postMedication.add("BBBB");
		previousPOSTBody.put("medications", new JSONArray(postMedication));
		List<String> postAllergies = new ArrayList<String>();
		postAllergies.add("AAAA");
		postAllergies.add("BBBB");
		previousPOSTBody.put("allergies", new JSONArray(postAllergies));
		// Above lines are used to add an Array to the JSON request
		HttpHeaders postHeader = new HttpHeaders();
		postHeader.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> postBody = new HttpEntity<String>(previousPOSTBody.toString(), postHeader);
		restTemplate.postForEntity("http://localhost:" + port + "/medicalRecord", postBody, String.class);

		// DELETE request
		restTemplate.delete("http://localhost:" + port + "/medicalRecord/AAAABBBB");
		// Need to use a slightly different method to do a DELETE request.

		// ASSERT
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/medicalRecord", String.class))
				.doesNotContain("AAAA").doesNotContain("01/01/0001");
	}
}