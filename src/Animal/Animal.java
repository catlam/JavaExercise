package Animal;

import java.time.LocalDateTime;

abstract class Animal {
	protected String name;
	protected int age;
	LocalDateTime dateTime;
	
	public Animal(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		this.dateTime = LocalDateTime.now();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public abstract String getDetails();
	
	
	
}
