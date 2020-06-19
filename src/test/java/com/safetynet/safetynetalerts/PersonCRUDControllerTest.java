package com.safetynet.safetynetalerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.safetynet.safetynetalerts.controller.PersonCRUDController;
import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.Person;

@WebMvcTest(PersonCRUDController.class)
public class PersonCRUDControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonDAO personDAO;

	@Test
	public void showAllPersons() throws Exception {
		// ARRANGE
		List<Person> listPerson = new ArrayList<Person>();
		when(personDAO.findAll()).thenReturn(listPerson);

		// ACT
		MvcResult mvcResult = mockMvc.perform(get("/person")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(personDAO, times(1)).findAll();
	}

	@Test
	public void showPersonById() throws Exception {
		// ARRANGE
		Person person = new Person();
		String firstName = "AAAA";
		person.setFirstName(firstName);
		when(personDAO.findById(any(String.class))).thenReturn(person);

		// ACT
		MvcResult mvcResult = mockMvc.perform(get("/person/Someone")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(personDAO, times(1)).findById(any(String.class));
	}

	@Disabled
	@Test
	public void addPerson() throws Exception {
		// ARRANGE
		List<Person> listPerson = new ArrayList<Person>();
		Person person = new Person();
		when(personDAO.save(person)).thenReturn(listPerson);

		// ACT
		MvcResult mvcResult = mockMvc
				.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\":\"ZZZZ\"}"))
				.andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 201);
		verify(personDAO, times(1)).save(person);
	}

	@Test
	public void updatePerson() throws Exception {
		// ARRANGE
		Person person = new Person();
		String firstName = "AAAA";
		person.setFirstName(firstName);
		when(personDAO.update(any(String.class), (any(Person.class)))).thenReturn(person);

		// ACT
		MvcResult mvcResult = mockMvc.perform(
				put("/person/Someone").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\":\"ZZZZ\"}"))
				.andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(personDAO, times(1)).update(any(String.class), (any(Person.class)));
	}

	@Test
	public void deletePerson() throws Exception {
		// ARRANGE
		Mockito.doNothing().when(personDAO).deleteById("firstNameAndlastName");

		// ACT
		MvcResult mvcResult = mockMvc.perform(delete("/person/Someone")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(personDAO, times(1)).deleteById(any(String.class));
	}
}
