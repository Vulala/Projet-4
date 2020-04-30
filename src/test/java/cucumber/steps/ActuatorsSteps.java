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

public class ActuatorsSteps {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Given("The server start")
    public void the_server_start() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.header().stringValues("Index"));
    }
    
    // HEALTH
    @When("I go on the Health path endpoint")
    public void i_go_on_the_Health_path_endpoint() throws Exception {
        mockMvc.perform(get("http://localhost:8080/actuator/health")).andExpect(status().isOk());
    }
    
    @Then("I can see that the Health endpoint is up and working")
    public void i_can_see_that_the_Health_endpoint_is_up_and_working() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("UP"));
    }
    
    @Then("Showing proper Health informations")
    public void showing_proper_Health_informations() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("UP"));
    }
    
    // INFO
    @When("I go on the Info path endpoint")
    public void i_go_on_the_Info_path_endpoint() throws Exception {
        mockMvc.perform(get("/actuator/info")).andExpect(status().isOk());
    }
    
    @Then("I can see that the Info endpoint is up and working")
    public void i_can_see_that_the_Info_endpoint_is_up_and_working() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/info"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("SafetyNet Alerts"));
    }
    
    @Then("Showing proper Info informations")
    public void showing_proper_Info_informations() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/info")).andExpect(MockMvcResultMatchers
                .jsonPath("$.description").value("WebApplication used to send informations to emergency services"));
    }
    
    // METRICS
    @When("I go on the Metrics path endpoint")
    public void i_go_on_the_Metrics_path_endpoint() throws Exception {
        mockMvc.perform(get("/actuator/metrics")).andExpect(status().isOk());
    }
    
    @Then("I can see that the Metrics endpoint is up and working")
    public void i_can_see_that_the_Metrics_endpoint_is_up_and_working() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/metrics"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.names.[0]").value("jvm.memory.max"));
        // .andExpect(MockMvcResultMatchers.jsonPath("$.names.[7]").value("logback.events"));
    }
    
    @Then("Showing proper Metrics informations")
    public void showing_proper_Metrics_informations() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/metrics/jvm.memory.max"))
                .andExpect(MockMvcResultMatchers.jsonPath("description")
                        .value("The maximum amount of memory in bytes that can be used for memory management"));
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/metrics/logback.events")).andExpect(MockMvcResultMatchers
                .jsonPath("description").value("Number of info level events that made it to the logs"));
    }
    
    // HTTPTRACE
    @When("I go on the Httptrace path endpoint")
    public void i_go_on_the_Httptrace_path_endpoint() throws Exception {
        mockMvc.perform(get("/actuator/httptrace")).andExpect(status().isOk());
    }
    
    @Then("I can see that the Httptrace endpoint is up and working")
    public void i_can_see_that_the_Httptrace_endpoint_is_up_and_working() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/httptrace"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.traces.[0].principal").hasJsonPath());
    }
    
    @Then("Showing proper Httptrace informations")
    public void showing_proper_Httptrace_informations() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/httptrace"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.traces.[0].session").hasJsonPath());
    }
    
    // RELEASE NOTES
    @When("I go on the ReleaseNotes path endpoint")
    public void i_go_on_the_ReleaseNotes_path_endpoint() throws Exception {
        mockMvc.perform(get("/actuator/release-notes")).andExpect(status().isOk());
    }
    
    @Then("I can see that the ReleaseNotes endpoint is up and working")
    public void i_can_see_that_the_ReleaseNotes_endpoint_is_up_and_working() throws Exception {
        mockMvc.perform(get("/actuator/release-notes/1.0")).andExpect(status().isOk());
    }
    
    @Then("Showing proper ReleaseNotes informations")
    public void showing_proper_ReleaseNotes_informations() throws Exception {
        mockMvc.perform(get("/actuator/release-notes/1.2")).andExpect(status().is2xxSuccessful());
    }
}
