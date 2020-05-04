package cucumber.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@Ignore
public class EndpointCRUDSteps {
    
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
        mockMvc.perform(get("/person/{firstName&lastName}", "firstNameA&lastNameA"))
                .andExpect(content().string("firstNameA")).andExpect(content().string("emailA@A"));
    }
    
    @When("I update a <person>")
    public void i_update_a_person() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/person/{firstName&lastName}", "firstNameB&lastNameB")
                // .content(asJsonString(
                // new Person("firstNameB", "lastNameB", "addressB", "cityB", 123123,
                // "phone2", "emailB@B")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    
    @Then("I can see that this <person> have been well updated")
    public void i_can_see_that_this_person_have_been_well_updated() throws Exception {
        mockMvc.perform(get("/person/{firstName&lastName}", "firstNameB&lastNameB"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("addressB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("cityB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.zip").value("zip2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("phone2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("emailB@B"));
    }
    
    @When("I delete a <person>")
    public void i_delete_a_person() throws Exception {
        mockMvc.perform(delete("/person/{firstName&lastName}", "firstNameB&lastNameB"))
                .andExpect(status().isAccepted());
    }
    
    @Then("I can see that this <person> have been well deleted")
    public void i_can_see_that_this_person_have_been_well_deleted() throws Exception {
        mockMvc.perform(get("/person/{firstName&lastName}", "firstNameB&lastNameB")).andExpect(status().isNotFound());
    }
}
