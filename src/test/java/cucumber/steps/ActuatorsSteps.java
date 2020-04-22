package cucumber.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActuatorsSteps {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Given("The server start")
    public void the_server_start() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().is2xxSuccessful()).andExpect(forwardedUrl("index"));
    }
    
    // HEALTH
    @When("I go on the Health path endpoint")
    public void i_go_on_the_Health_path_endpoint() throws Exception {
        mockMvc.perform(get("http://localhost:8080/actuator/health")).andExpect(status().isOk());
    }
    
    @Then("I can see that the Health endpoint is up and working")
    public void i_can_see_that_the_Health_endpoint_is_up_and_working() throws Exception {
        mockMvc.perform(get("/actuator/health")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("Showing proper Health informations")
    public void showing_proper_Health_informations() throws Exception {
        mockMvc.perform(get("/actuator/health")).andExpect(forwardedUrl("actuator/health"));
    }
    
    // INFO
    @When("I go on the Info path endpoint")
    public void i_go_on_the_Info_path_endpoint() throws Exception {
        mockMvc.perform(get("/actuator/info")).andExpect(status().isOk());
    }
    
    @Then("I can see that the Info endpoint is up and working")
    public void i_can_see_that_the_Info_endpoint_is_up_and_working() throws Exception {
        mockMvc.perform(get("/actuator/info")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("Showing proper Info informations")
    public void showing_proper_Info_informations() throws Exception {
        mockMvc.perform(get("/actuator/info")).andExpect(forwardedUrl("actuator/info"));
    }
    
    // METRICS
    @When("I go on the Metrics path endpoint")
    public void i_go_on_the_Metrics_path_endpoint() throws Exception {
        mockMvc.perform(get("/actuator/metrics")).andExpect(status().isOk());
    }
    
    @Then("I can see that the Metrics endpoint is up and working")
    public void i_can_see_that_the_Metrics_endpoint_is_up_and_working() throws Exception {
        mockMvc.perform(get("/actuator/metrics")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("Showing proper Metrics informations")
    public void showing_proper_Metrics_informations() throws Exception {
        mockMvc.perform(get("/actuator/metrics")).andExpect(forwardedUrl("actuator/metrics"));
    }
    
    // HTTPTRACE
    @When("I go on the Httptrace path endpoint")
    public void i_go_on_the_Httptrace_path_endpoint() throws Exception {
        mockMvc.perform(get("/actuator/httptrace")).andExpect(status().isOk());
    }
    
    @Then("I can see that the Httptrace endpoint is up and working")
    public void i_can_see_that_the_Httptrace_endpoint_is_up_and_working() throws Exception {
        mockMvc.perform(get("/actuator/httptrace")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("Showing proper Httptrace informations")
    public void showing_proper_Httptrace_informations() throws Exception {
        mockMvc.perform(get("/actuator/httptrace")).andExpect(forwardedUrl("actuator/httptrace"));
    }
    
    // RELEASE NOTES
    @When("I go on the ReleaseNotes path endpoint")
    public void i_go_on_the_ReleaseNotes_path_endpoint() throws Exception {
        mockMvc.perform(get("/actuator/release-notes")).andExpect(status().isOk());
    }
    
    @Then("I can see that the ReleaseNotes endpoint is up and working")
    public void i_can_see_that_the_ReleaseNotes_endpoint_is_up_and_working() throws Exception {
        mockMvc.perform(get("/actuator/release-notes")).andExpect(status().is2xxSuccessful());
    }
    
    @Then("Showing proper ReleaseNotes informations")
    public void showing_proper_ReleaseNotes_informations() throws Exception {
        mockMvc.perform(get("/actuator/release-notes")).andExpect(forwardedUrl("actuator/release-notes"));
    }
}
