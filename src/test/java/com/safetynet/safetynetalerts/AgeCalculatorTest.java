package com.safetynet.safetynetalerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.AgeCalculator;
import com.safetynet.safetynetalerts.service.JSONReader;

class AgeCalculatorTest {

	private AgeCalculator ageCalculator = new AgeCalculator();

	@Test
	void givenABirthDate_whenCalculateDate_thenItGetTheAge() {
		// ARRANGE
		Calendar birthdate1 = Calendar.getInstance();
		Calendar birthdate2 = Calendar.getInstance();

		birthdate1.set(2010, 01, 01);
		Date birthDateChild = birthdate1.getTime();
		birthdate2.set(1010, 01, 01);
		Date birthDateAdult = birthdate2.getTime();

		// ACT
		ageCalculator.calculateDate(birthDateChild);
		long ageChild = ageCalculator.getAge();
		ageCalculator.calculateDate(birthDateAdult);
		long ageAdult = ageCalculator.getAge();

		// ASSERT
		assertEquals(ageChild, 10);
		assertEquals(ageAdult, 1010);
	}

	@Test
	void givenChildrenBirthDates_whenCalculateDate_thenItCountTheNumberOfChildren() {
		// ARRANGE
		Calendar child1 = Calendar.getInstance();
		Calendar child2 = Calendar.getInstance();

		child1.set(2010, 10, 10);
		Date birthDate1 = child1.getTime();
		child2.set(2019, 9, 9);
		Date birthDate2 = child2.getTime();

		// ACT
		ageCalculator.calculateDate(birthDate1);
		ageCalculator.calculateDate(birthDate2);
		long children = ageCalculator.getChildren();
		long adults = ageCalculator.getAdults();

		// ASSERT
		assertEquals(children, 2);
		assertEquals(adults, 0);
	}

	@Test
	void givenAdultsBirthDates_whenCalculateDate_thenItCountTheNumberOfAdults() {
		// ARRANGE
		Calendar adult1 = Calendar.getInstance();
		Calendar adult2 = Calendar.getInstance();

		adult1.set(2000, 01, 01);
		Date birthDate1 = adult1.getTime();
		adult2.set(1950, 5, 5);
		Date birthDate2 = adult2.getTime();

		// ACT
		ageCalculator.calculateDate(birthDate1);
		ageCalculator.calculateDate(birthDate2);
		long adults = ageCalculator.getAdults();
		long children = ageCalculator.getChildren();

		// ASSERT
		assertEquals(adults, 2);
		assertEquals(children, 0);

	}

	@Test
	void givenBirthDatesFromJSON_whenCalculateDate_thenItCountTheNumberOfAdultsAndChildren() throws Exception {
		// ARRANGE
		List<MedicalRecord> listMedicalRecords = new JSONReader().getData().getMedicalRecords();

		// ACT
		for (MedicalRecord medicalRecord : listMedicalRecords) {
			ageCalculator.calculateDate(medicalRecord.getBirthdate());
		}
		long adults = ageCalculator.getAdults();
		long children = ageCalculator.getChildren();

		// ASSERT
		assertEquals(adults, 18);
		assertEquals(children, 5);

	}

	@Test
	void givenBirthDatesFromJSON_whenCalculateDate_thenItGiveTheAgeOfAdultsAndChildren() throws Exception {
		// ARRANGE
		List<MedicalRecord> listMedicalRecords = new JSONReader().getData().getMedicalRecords();

		// ACT
		for (MedicalRecord medicalRecord : listMedicalRecords) {
			ageCalculator.calculateDate(medicalRecord.getBirthdate());
		}
		long ageOfTheLastRecord = ageCalculator.getAge();

		// ASSERT
		assertEquals(ageOfTheLastRecord, 74);

	}
}
