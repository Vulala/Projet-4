package com.safetynet.safetynetalerts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
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

@Disabled
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonEndpointTest {
    
    @LocalServerPort
    private int port; // Inject the RANDOM_PORT into that variable
    
    @Autowired
    private AppController controller;
    
    @Autowired
    private TestRestTemplate restTemplate; // Used to perform testing method in the Tests
    private HttpHeaders httpHeader;
    
    @DisplayName("Sanity check test who fail if the application context cannot start")
    @Test
    public void givenTheController_whenItStart_thenItIsNotNull() {
        // Sanity check test that will fail if the application context cannot start
        assertThat(controller).isNotNull();
    }
    
    @DisplayName("When going on the /person endpoint, we can perform POST action")
    @Test
    public void givenTheAppStart_whenGoingOnThePersonEndpoint_thenICanDoPOSTAction() throws Exception {
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("firstName", "AAAA");
        personJsonObject.put("address", "aaaa");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<String>(personJsonObject.toString(), headers);
        // Create an HTTP request, defining body and header
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/person", entity,
                String.class);
        
        // ASSERT
        assertNotNull(response.getBody());
        Assert.assertEquals(201, response.getStatusCodeValue());
        String expected = "{\"firstName\":\"AAAA\",\"address\":\"aaaa\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/person", String.class))
                .contains("AAAA").contains("aaaa");
    }
    
    @DisplayName("When going on the /person endpoint, we can perform PUT action")
    @Test
    public void givenTheAppStart_whenGoingOnThePersonEndpoint_thenICanDoPUTAction() throws Exception {
        // TOINCREASE : Create a previous post request, to make this test single.
        HttpEntity<String> entity = new HttpEntity<String>("{\"firstName\":\"firstNameB\",\"address\":\"addressB\"}",
                httpHeader);
        // Create an HTTP request, defining body and header
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/person", HttpMethod.PUT,
                entity, String.class);
        // Define the rest of the HTTP request, URL, METHOD, ENTITY, RETURN TYPE
        httpHeader.set("@RequestHeader_PUTperson", "true");
        // Parameters defined in the Controller, boolean = strict
        
        // ASSERT
        Assert.assertEquals(201, response.getStatusCodeValue());
        String expected = "{\"firstName\":\"firstNameB\",\"address\":\"addressB\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/person", String.class))
                .contains("firstNameB").contains("addressB");
    }
    
    @DisplayName("When going on the /person endpoint, we can perform DELETE action")
    @Test
    public void givenTheAppStart_whenGoingOnThePersonEndpoint_thenICanDoDELETEAction() throws Exception {
        // TOINCREASE : Create a previous post request, to make this test single.
        HttpEntity<String> entity = new HttpEntity<String>(null, httpHeader);
        // Create an HTTP request, defining body and header
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/person/{firstNameA&lastNameA}", HttpMethod.DELETE, entity, String.class);
        // Define the rest of the HTTP request, URL, METHOD, ENTITY, RETURN TYPE
        httpHeader.set("@RequestHeader_DELETEperson", "true");
        // Parameters defined in the Controller, boolean = strict
        
        // ASSERT
        Assert.assertEquals(201, response.getStatusCodeValue());
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/person", String.class))
                .doesNotContain("firstNameA").doesNotContain("addressA");
    }
}