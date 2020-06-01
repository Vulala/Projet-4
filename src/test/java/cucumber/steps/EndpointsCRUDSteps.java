package cucumber.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EndpointsCRUDSteps {

	@Autowired
	private MockMvc mockMvc;

	// PERSON
	@Given("I reach the <\\/person> endpoint")
	public void i_reach_the_person_endpoint() throws Exception {
		mockMvc.perform(get("/person")).andExpect(status().is2xxSuccessful());
	}

	@When("I create a new <person>")
	public void i_create_a_new_person() throws Exception {
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(
				"{\"firstName\":\"AAAA\",\"lastName\":\"BBBB\",\"address\":\"AAAA\",\"city\":\"BBBB\",\"zip\":\"0000\",\"phone\":\"0000\",\"email\":\"AAAA@BBBB.com\"}"))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isCreated());
	}

	@Then("I can see that this <person> have been well created")
	public void i_can_see_that_this_person_have_been_well_created() throws Exception {
		mockMvc.perform(get("/person/AAAABBBB")).andExpect(content().json("{\"firstName\":\"AAAA\"}"))
				.andExpect(content().json("{\"email\":\"AAAA@BBBB.com\"}"));
	}

	@When("I update a <person>")
	public void i_update_a_person() throws Exception {
		mockMvc.perform(put("/person/AAAABBBB").contentType(MediaType.APPLICATION_JSON).content(
				"{\"firstName\":\"ZZZZ\",\"lastName\":\"YYYY\",\"address\":\"ZZZZ\",\"city\":\"ZZZZ\",\"zip\":\"9999\",\"phone\":\"9999\",\"email\":\"ZZZZ@ZZZZ.com\"}"))
				.andExpect(status().isOk());
	}

	@Then("I can see that this <person> have been well updated")
	public void i_can_see_that_this_person_have_been_well_updated() throws Exception {
		mockMvc.perform(get("/person/AAAABBBB")).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("ZZZZ"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.city").value("ZZZZ"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.zip").value("9999"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("9999"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("ZZZZ@ZZZZ.com"));
	}

	@When("I delete a <person>")
	public void i_delete_a_person() throws Exception {
		mockMvc.perform(delete("/person/AAAABBBB")).andExpect(status().isOk());
	}

	@Then("I can see that this <person> have been well deleted")
	public void i_can_see_that_this_person_have_been_well_deleted() throws Exception {
		mockMvc.perform(get("/person/AAAABBBB"))
				.andExpect(MockMvcResultMatchers.jsonPath("email").doesNotHaveJsonPath());
	}

	// FIRESTATION
	@Given("I reach the <\\/firestation> endpoint")
	public void i_reach_the_firestation_endpoint() throws Exception {
		mockMvc.perform(get("/firestation/")).andExpect(status().is2xxSuccessful());
	}

	@When("I create a new <firestation>")
	public void i_create_a_new_firestation() throws Exception {
		mockMvc.perform(post("/firestation/").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"AAAA\",\"station\":\"1\"}")).andExpect(status().isCreated());
	}

	@Then("I can see that this <firestation> have been well created")
	public void i_can_see_that_this_firestation_have_been_well_created() throws Exception {
		mockMvc.perform(get("/firestation/AAAA")).andExpect(content().json("{\"address\":\"AAAA\"}"));
	}

	@When("I update a <firestation>")
	public void i_update_a_firestation() throws Exception {
		mockMvc.perform(put("/firestation/AAAA").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"ZZZZ\",\"station\":\"9999\"}")).andExpect(status().isOk());
	}

	@Then("I can see that this <firestation> have been well updated")
	public void i_can_see_that_this_firestation_have_been_well_updated() throws Exception {
		mockMvc.perform(get("/firestation/AAAA")).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("AAAA"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.station").value("9999"));
	}

	@When("I delete a <firestation>")
	public void i_delete_a_firestation() throws Exception {
		mockMvc.perform(delete("/firestation/AAAA")).andExpect(status().isOk());
	}

	@Then("I can see that this <firestation> have been well deleted")
	public void i_can_see_that_this_firestation_have_been_well_deleted() throws Exception {
		mockMvc.perform(get("/firestation/AAAA"))
				.andExpect(MockMvcResultMatchers.jsonPath("address").doesNotHaveJsonPath());
	}

	// MEDICAL RECORD
	@Given("I reach the <\\/medicalRecord> endpoint")
	public void i_reach_the_medicalRecord_endpoint() throws Exception {
		mockMvc.perform(get("/medicalRecord")).andExpect(status().is2xxSuccessful());
	}

	@When("I create a new <medicalRecord>")
	public void i_create_a_new_medicalRecord() throws Exception {
		mockMvc.perform(post("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content(
				"{\"firstName\":\"AAAA\",\"lastName\":\"BBBB\",\"birthdate\":\"01/01/0001\",\"medications\":[\"BBBB\"],\"allergies\":[\"AAAA\"]}"))
				.andExpect(status().isCreated());
	}

	@Then("I can see that this <medicalRecord> have been well created")
	public void i_can_see_that_this_medicalRecord_have_been_well_created() throws Exception {
		mockMvc.perform(get("/medicalRecord/AAAABBBB")).andExpect(content().json(
				"{\"firstName\":\"AAAA\",\"lastName\":\"BBBB\",\"birthdate\":\"01/01/0001\",\"medications\":[\"BBBB\"],\"allergies\":[\"AAAA\"]}"));
	}

	@When("I update a <medicalRecord>")
	public void i_update_a_medicalRecord() throws Exception {
		mockMvc.perform(put("/medicalRecord/AAAABBBB").contentType(MediaType.APPLICATION_JSON).content(
				"{\"firstName\":\"ZZZZ\",\"lastName\":\"YYYY\",\"birthdate\":\"12/31/9999\",\"medications\":[\"ZZZZ\"],\"allergies\":[\"YYYY\"]}"))
				.andExpect(status().isOk());
	}

	@Then("I can see that this <medicalRecord> have been well updated")
	public void i_can_see_that_this_medicalRecord_have_been_well_updated() throws Exception {
		mockMvc.perform(get("/medicalRecord/AAAABBBB")).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("AAAA"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("BBBB"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value("12/31/9999"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.medications").value("ZZZZ"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.allergies").value("YYYY"));
	}

	@When("I delete a <medicalRecord>")
	public void i_delete_a_medicalRecord() throws Exception {
		mockMvc.perform(delete("/medicalRecord/AAAABBBB")).andExpect(status().isOk());
	}

	@Then("I can see that this <medicalRecord> have been well deleted")
	public void i_can_see_that_this_medicalRecord_have_been_well_deleted() throws Exception {
		mockMvc.perform(get("/medicalRecord/AAAABBBB"))
				.andExpect(MockMvcResultMatchers.jsonPath("medications").doesNotHaveJsonPath());
	}

}
