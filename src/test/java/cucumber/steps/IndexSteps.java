package cucumber.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IndexSteps {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Given("The web server start")
    public void the_web_server_start() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().is2xxSuccessful());
    }
    
    @When("I reach the web server with my broswer")
    public void i_reach_the_web_server_with_my_broswer() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }
    
    @Then("I see the index page")
    public void i_see_the_index_page() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.header().stringValues("Index"));
    }
    
}
