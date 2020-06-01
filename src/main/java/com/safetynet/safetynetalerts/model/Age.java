package com.safetynet.safetynetalerts.model;

import java.util.List;

public class Age {

	private List<Long> age;

	public Age(List<Long> age) {
		super();
		this.age = age;
	}

	public List<Long> getAge() {
		return age;
	}

	public void setAge(List<Long> age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Age [age=" + age + "]";
	}

}
