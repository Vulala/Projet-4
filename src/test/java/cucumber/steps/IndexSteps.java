package cucumber.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.AppController;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest
@ContextConfiguration(classes = AppController.class)
@AutoConfigureMockMvc
public class IndexSteps {
    
    @Autowired
    MockMvc mockMvc;
    
    @Given("The web server start")
    public void the_web_server_start() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().is2xxSuccessful());
        // Write code here that turns the phrase above into concrete actions
    }
    
    @When("I reach the web server with my broswer")
    public void i_reach_the_web_server_with_my_broswer() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
        // Write code here that turns the phrase above into concrete actions
    }
    
    @Then("I see the index page")
    public void i_see_the_index_page() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().is2xxSuccessful()).andExpect(content().string(""));
        // Write code here that turns the phrase above into concrete actions
    }
    
}
