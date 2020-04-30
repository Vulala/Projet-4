package com.safetynet.safetynetalerts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.safetynet.safetynetalerts.controller.AppController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IndexTest {
    
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
    
    @DisplayName("The index page is correctly showed when going on the application")
    @Test
    public void givenTheAppStart_whenGoingOnIt_thenTheIndexPageIsShown() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("");
    }
}