package cucumber.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EndpointsCRUDSteps {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Given("I reach the <\\/person> endpoint")
    public void i_reach_the_person_endpoint() throws Exception {
        mockMvc.perform(get("/person")).andExpect(status().is2xxSuccessful());
    }
    
    @When("I create a new <person>")
    public void i_create_a_new_person() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                // .content(asJsonString(
                // new Person("firstNameA", "lastNameA", "addressA", "cityA", 456456,
                // "phone1", "emailA@A")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    
    @Then("I can see that this <person> have been well created")
    public void i_can_see_that_this_person_have_been_well_created() throws Exception {
        mockMvc.perform(get("/person/{firstName}{lastName}")).andExpect(content().string("firstNameA"))
                .andExpect(content().string("emailA@A"));
    }
    
    @When("I update a <person>")
    public void i_update_a_person() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/person/{firstName}{lastName}")
                // .content(asJsonString(
                // new Person("firstNameB", "lastNameB", "addressB", "cityB", 123123,
                // "phone2", "emailB@B")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    
    @Then("I can see that this <person> have been well updated")
    public void i_can_see_that_this_person_have_been_well_updated() throws Exception {
        mockMvc.perform(get("/person/{firstName}{lastName}")).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("addressB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("cityB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.zip").value("zip2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("phone2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("emailB@B"));
    }
    
    @When("I delete a <person>")
    public void i_delete_a_person() throws Exception {
        mockMvc.perform(delete("/person/{firstName}{lastName}", "AAAA", "BBBB")).andExpect(status().isAccepted());
    }
    
    @Then("I can see that this <person> have been well deleted")
    public void i_can_see_that_this_person_have_been_well_deleted() throws Exception {
        mockMvc.perform(get("/person/{firstName}{lastName}", "AAAA", "BBBB")).andExpect(status().isNotFound());
    }
    
//FIRESTATION    
    @Given("I reach the <\\/firestation> endpoint")
    public void i_reach_the_firestation_endpoint() throws Exception {
        mockMvc.perform(get("/firestation")).andExpect(status().is2xxSuccessful());
    }
    
    @When("I create a new <firestation>")
    public void i_create_a_new_firestation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/firestation").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
    
    @Then("I can see that this <firestation> have been well created")
    public void i_can_see_that_this_firestation_have_been_well_created() throws Exception {
        mockMvc.perform(get("/firestation/{address}")).andExpect(content().string("AAAA"))
                .andExpect(content().string("BBBB"));
    }
    
    @When("I update a <firestation>")
    public void i_update_a_firestation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/firestation/{address}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    
    @Then("I can see that this <firestation> have been well updated")
    public void i_can_see_that_this_firestation_have_been_well_updated() throws Exception {
        mockMvc.perform(get("/firestation/{address}")).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("AAAA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.station").value("BBBB"));
    }
    
    @When("I delete a <firestation>")
    public void i_delete_a_firestation() throws Exception {
        mockMvc.perform(delete("/firestation/{address}", "AAAA")).andExpect(status().isAccepted());
    }
    
    @Then("I can see that this <firestation> have been well deleted")
    public void i_can_see_that_this_firestation_have_been_well_deleted() throws Exception {
        mockMvc.perform(get("/firestation/{address}", "AAAA")).andExpect(status().isNotFound());
    }
    
//MEDICAL RECORD    
    @Given("I reach the <\\/medicalRecord> endpoint")
    public void i_reach_the_medicalRecord_endpoint() throws Exception {
        mockMvc.perform(get("/medicalRecord")).andExpect(status().is2xxSuccessful());
    }
    
    @When("I create a new <medicalRecord>")
    public void i_create_a_new_medicalRecord() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord", "AAAA", "BBBB", "00/00/0000", "", "")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    
    @Then("I can see that this <medicalRecord> have been well created")
    public void i_can_see_that_this_medicalRecord_have_been_well_created() throws Exception {
        mockMvc.perform(get("/medicalRecord/{firstName}{lastName}")).andExpect(content().string("AAAA"))
                .andExpect(content().string("BBBB")).andExpect(content().string("00/00/0000"));
    }
    
    @When("I update a <medicalRecord>")
    public void i_update_a_medicalRecord() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord/{AAAA}{BBBB}", "ZZZZ", "YYYY")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    
    @Then("I can see that this <medicalRecord> have been well updated")
    public void i_can_see_that_this_medicalRecord_have_been_well_updated() throws Exception {
        mockMvc.perform(get("/medicalRecord/{firstName}{lastName}")).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("ZZZZ"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("YYYY"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value("00/00/0000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.medications").value(""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.allergies").value(""));
    }
    
    @When("I delete a <medicalRecord>")
    public void i_delete_a_medicalRecord() throws Exception {
        mockMvc.perform(delete("/medicalRecord/{firstName}{lastName}", "AAAA", "BBBB"))
                .andExpect(status().isAccepted());
    }
    
    @Then("I can see that this <medicalRecord> have been well deleted")
    public void i_can_see_that_this_medicalRecord_have_been_well_deleted() throws Exception {
        mockMvc.perform(get("/medicalRecord/{firstName}{lastName}", "AAAA", "BBBB")).andExpect(status().isNotFound());
    }
    
}
