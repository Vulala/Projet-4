package com.safetynet.safetynetalerts.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgeCalculator {

	/**
	 * Calculate the age of a person from the date of birth.<br>
	 * The count of children, adults and the age of the person are accessible via
	 * getters.
	 * 
	 * @param birthDate from the JSON file.
	 */

	private long children = 0;
	private long adults = 0;
	private long age = 0;
	private List<Long> listAge = new ArrayList<Long>();

	public void calculateDate(Date birthdate) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // Equal to the JSON file
		long DaysToHave18YearsOld = 6570; // Numbers of days to have 18years old.

		LocalDate localDate = LocalDate.now(); // Get the current LocalDate
		LocalDate birthDateToLocalDate = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		// Convert the Date from JSON to LocalDate, it use the system time zone.
		String localDateFormatToString = localDate.format(dateTimeFormatter);
		String birthDateFormatToString = birthDateToLocalDate.format(dateTimeFormatter);
		// Format the LocalDate according to the DateTimeFormatter

		LocalDate parsedLocalDate = LocalDate.parse(localDateFormatToString, dateTimeFormatter);
		LocalDate parsedBirthDate = LocalDate.parse(birthDateFormatToString, dateTimeFormatter);

		long daysBetween = ChronoUnit.DAYS.between(parsedBirthDate, parsedLocalDate);
		// Calculate the days between the two values

		age = daysBetween / 365;
		listAge.add(age);
		// Calculate the age in years from days

		if (daysBetween < DaysToHave18YearsOld) {
			children++;
		} else {
			adults++;
		}

	}

	public long getChildren() {
		return children;
	}

	public long getAdults() {
		return adults;
	}

	public long getAge() {
		return age;
	}

	public List<Long> getListAge() {
		return listAge;
	}

}
