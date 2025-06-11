package Practice3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
	private String fullName;
	private LocalDate birthday;
	private int age;
	private Person father;
	private Person mother;
	private List<Person> children;
	
	

	public Person(String fullName, LocalDate birthday, int age) {
		super();
		this.fullName = fullName;
		this.birthday = birthday;
		this.age = age;
		this.children = new ArrayList<>();
	}



	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birdthYear) {
		this.birthday = birdthYear;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public List<Person> getChildren() {
		return children;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	}



	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void addChild(Person child) {
        this.children.add(child);
    }

	@Override
	public String toString() {
		return "Person [fullName=" + fullName + ", birthday=" + birthday + "]";
	}

//	public void printFamilyTree(String indent) {
//        System.out.println(indent + this);
//        for (Person child : children) {
//            child.printFamilyTree(indent + "    ");
//        }
//	}
	
	
;}
