package cucumber.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EndpointsURLsSteps {
    
    @Autowired
    private MockMvc mockMvc;
    
    @When("I set a <station_number> as parameter")
    public void i_set_a_station_number_as_parameter() throws Exception {
        mockMvc.perform(get("/firestation/<station_number>")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("I see all the persons deserved by this <station_number>, showing me the <firstName>, <lastName>, <address> and <phone> of the concerned people")
    public void i_see_all_the_persons_deserved_by_this_station_showing_me_the_firstName_lastName_address_and_phone_of_the_concerned_people()
            throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/firestation/<station_number>")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("address"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("phone"));
    }
    
    @Then("It show me a count of the numbers of adults and children \\(-18yo) deserved by the <station_number>")
    public void it_show_me_a_count_of_the_numbers_of_adults_and_children_18yo_deserved_by_the_station_number()
            throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/firestation/<station_number>")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.adults").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.children").isNumber());
    }
    
    @Given("I reach the <\\/childAlert> endpoint")
    public void i_reach_the_childAlert_endpoint() throws Exception {
        mockMvc.perform(get("/childAlert")).andExpect(status().is2xxSuccessful());
    }
    
    @When("I set an <address> as parameter")
    public void i_set_an_address_as_parameter() throws Exception {
        mockMvc.perform(get("/childAlert/{address}")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("I see all the children present at this address, showing me the <firstName>, <lastName>, <age> and the <adults> of this address")
    public void i_see_all_the_children_present_at_this_address_showing_me_the_firstName_lastName_age_and_the_adults_of_this_address()
            throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/childAlert/{address}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.children").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.adults").value("firstName"));
    }
    
    @Then("If there is no children, then the url is empty")
    public void if_there_is_no_children_then_the_url_is_empty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/childAlert/{address}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.jsonPath("$.children").isEmpty());
    }
    
    @Given("I reach the <\\/phoneAlert> endpoint")
    public void i_reach_the_phoneAlert_endpoint() throws Exception {
        mockMvc.perform(get("/phoneAlert")).andExpect(status().is2xxSuccessful());
    }
    
    @When("I set a <firestation> as parameter")
    public void i_set_a_firestation_as_parameter() throws Exception {
        mockMvc.perform(get("/phoneAlert/{firestation}")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("I get all the phone numbers of the people deserved by this <firestation>")
    public void i_get_all_the_phone_numbers_of_the_people_deserved_by_this_firestation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert/{firestation}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.jsonPath("$.phone").isNotEmpty());
    }
    
    @Given("I reach the <\\/fire> endpoint")
    public void i_reach_the_fire_endpoint() throws Exception {
        mockMvc.perform(get("/fire")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("I see all the persons living at this <address> with their <lastName>, <phone>, <age>")
    public void i_see_all_the_persons_living_at_this_address_with_their_lastName_phone_age() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fire/{address}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("phone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").isNumber());
    }
    
    @Then("The <medicalRecord> for each persons")
    public void the_medicalRecord_for_each_persons() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fire/{address}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value("birthdate"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.medications").value("medications"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.allergies").value("allergies"));
    }
    
    @Given("I reach the <\\/flood\\/stations> endpoint")
    public void i_reach_the_flood_stations_endpoint() throws Exception {
        mockMvc.perform(get("/flood/stations")).andExpect(status().is2xxSuccessful());
    }
    
    @When("I set a <station> as parameter")
    public void i_set_a_station_as_parameter() throws Exception {
        mockMvc.perform(get("/flood/stations/{station}")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("I see all the persons deserved by this <station> gathered by address, with their <lastName>, <phone> and <age>")
    public void i_see_all_the_persons_deserved_by_this_station_gathered_by_address_with_their_lastName_phone_age()
            throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations/{station}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("phone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").isNumber());;
    }
    
    @Then("I also see the <medicalRecord> of every persons, next to their name")
    public void the_medicalRecord_for_each_persons_next_to_their_name() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations/{station}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value("birthdate"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.medications").value("medications"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.allergies").value("allergies"));
    }
    
    @Given("I reach the <\\/personInfo> endpoint")
    public void i_reach_the_personInfo_endpoint() throws Exception {
        mockMvc.perform(get("/personInfo")).andExpect(status().is2xxSuccessful());
    }
    
    @When("I set a <\\{firstName}\\{lastName}> as parameters")
    public void i_set_a_as_parameters() throws Exception {
        mockMvc.perform(get("/personInfo/{firstName}{lastName}")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("I see all the informations about this person: <lastName>, <address>, <age>, <email>, <medicalRecord>")
    public void i_see_all_the_informations_about_this_person_lastName_address_age_email_medicalRecord()
            throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/personInfo/{firstName}{lastName}")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("address"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value("birthdate"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.medications").value("medications"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.allergies").value("allergies"));
    }
    
    @Then("If there is different persons with the same <lastName>, then nobody is missing")
    public void if_there_is_differents_persons_with_the_same_lastName_then_there_is_everyone() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/personInfo/{firstName}{lastName}")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName"));
    }
    
    @Given("I reach the <\\/communityEmail> endpoint")
    public void i_reach_the_communityEmail_endpoint() throws Exception {
        mockMvc.perform(get("/communityEmail")).andExpect(status().is2xxSuccessful());
    }
    
    @When("I set a <city> as parameter")
    public void i_set_a_city_as_parameter() throws Exception {
        mockMvc.perform(get("/communityEmail/{city}")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("I see all the email of the persons living in that <city>")
    public void i_see_all_the_email_of_the_persons_living_in_that_city() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail/{city}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.jsonPath("$.email").isString());
    }
}
