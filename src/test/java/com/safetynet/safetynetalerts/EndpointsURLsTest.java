package com.safetynet.safetynetalerts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.safetynet.safetynetalerts.controller.AppController;

@Disabled
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EndpointsURLsTest {
    
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
    
    @DisplayName("The firestation/<station_number> endpoint page contains the informations about the persons deserved by this <station_number>")
    @Test
    public void givenTheAppStart_whenGoingOnTheFirestation_StationEndpoint_thenTheInformationsAboutThePersonsDeservedByTheStationAreShown()
            throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "firestation/<station_number>",
                String.class)).contains("firstName").contains("address");
    }
    
    @DisplayName("The firestation/<station_number> endpoint page contains a count of the numbers of adults and children (-18yo) deserved by the <station_number>")
    @Test
    public void givenTheAppStart_whenGoingOnTheFirestation_StationEndpoint_thenACountOfAdultsAndChildrenDeservedByTheStationAreShown()
            throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "firestation/<station_number>",
                String.class)).contains("adults").contains("children");
    }
    
    @DisplayName("The childAlert/{address} endpoint page contains all the children (-18yo) present at this {address}")
    @Test
    public void givenTheAppStart_whenGoingOnTheChildAlert_AddressEndpoint_thenAllTheChildrenPresentAtThisAddressAreShown()
            throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/childAlert/{address}", String.class))
                .contains("firstName").contains("children");
    }
    
    @DisplayName("The childAlert/{address} endpoint page contains all the adults present at this {address} if atleast one child is present")
    @Test
    public void givenTheAppStart_whenGoingOnTheChildAlert_AddressEndpoint_thenAllTheAdultsPresentAtThisAddressIfThereIsAtleatOneChildAreShown()
            throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/childAlert/{address}", String.class))
                .contains("firstName").contains("adults");
        // The <address> must be an address with atleast one child
    }
    
    @DisplayName("The childAlert/{address} endpoint page return an empty page if there is no child present at this {address}")
    @Test
    public void givenTheAppStart_whenGoingOnTheChildAlert_AddressEndpoint_thenIfThereIsNoChildPresentThenNothingIsShown()
            throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/childAlert/{address}", String.class))
                .isEmpty();
        // The <address> must be an address without child
    }
    
    @DisplayName("The phoneAlert/{firestation} endpoint page return all the phone number of the person deserved by this {firestation}")
    @Test
    public void givenTheAppStart_whenGoingOnThePhoneAlert_FirestationEndpoint_thenThePhoneNumberOfAllThePersonsDeservedByThisFirestationAreShown()
            throws Exception {
        assertThat(
                this.restTemplate.getForObject("http://localhost:" + port + "/phoneAlert/{firestation}", String.class))
                        .contains("phone");
    }
    
    @DisplayName("The fire/{address} endpoint page contains every lastName, phone and age of the persons present at this {address}")
    @Test
    public void givenTheAppStart_whenGoingOnTheFire_AddressEndpoint_thenTheLastnamePhoneNumberAndAgeOfAllThePersonsLeavingAtThisAddressAreShown()
            throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fire/{address}", String.class))
                .contains("lastName").contains("phone").contains("age");
    }
    
    @DisplayName("The fire/{address} endpoint page contains every medicalRecord of the persons present at this {address}")
    @Test
    public void givenTheAppStart_whenGoingOnTheFire_AddressEndpoint_thenTheMedicalRecordsOfAllThePersonsLeavingAtThisAddressAreShown()
            throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fire/{address}", String.class))
                .contains("birthdate").contains("medications").contains("allergies");
    }
    
    @DisplayName("The flood/stations/{station} endpoint page contains every lastName, phone, age and medicalRecord of the persons deserved by this {station}")
    @Test
    public void givenTheAppStart_whenGoingOnTheFlood_Stations_StationEndpoint_thenTheLastnamePhoneNumberAgeAndMedicalRecordsOfAllThePersonsLeavingAtThisAddressAreShown()
            throws Exception {
        assertThat(
                this.restTemplate.getForObject("http://localhost:" + port + "/flood/stations/{station}", String.class))
                        .contains("lastName").contains("phone").contains("age").contains("birthdate")
                        .contains("medications").contains("allergies");
    }
    
    @DisplayName("The personInfo/{firstName}{lastName} endpoint page contains informations about the person refered by this {firstName} and {lastName}")
    @Test
    public void givenTheAppStart_whenGoingOnThePersonInfo_FirstNameLastNameEndpoint_thenInformationsAboutThisPersonAreShown()
            throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/personInfo/{firstName}{lastName}",
                String.class)).contains("lastName").contains("address").contains("age").contains("email")
                        .contains("birthdate").contains("medications").contains("allergies");
    }
    
    @DisplayName("The communityEmail/{city} endpoint page contains all the email of the persons living in this {city}")
    @Test
    public void givenTheAppStart_whenGoingOnTheCommunityEmail_CityEndpoint_thenAllTheEmailsOfThePersonsPresentInThisCityAreShown()
            throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/communityEmail/{city}", String.class))
                .contains("email");
    }
    
}
