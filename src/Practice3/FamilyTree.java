package Practice3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FamilyTree {
	private Person root;
	
	public FamilyTree(Person root) {
		super();
		this.root = root;
	}

	// a) Calculate total number of people in family tree
	public static int getTotalPeople (Person person) {
		if(person == null) return 0;
		int cnt = 1;
		for(Person child : person.getChildren()) {
			cnt += getTotalPeople(child);
		}
		return cnt;
	}
	
	
	// b) List of people with same birthday
	public static Map<String, List<Person>> getPeopleWithSameBirthday(Person person) {
        Map<String, List<Person>> birthdayMap = new HashMap<>();
        collectPeopleWithSameBirthday(person, birthdayMap);
        
        // Remove entries if only one person
        birthdayMap.entrySet().removeIf(entry -> entry.getValue().size() <= 1);
        return birthdayMap;
    }

    private static void collectPeopleWithSameBirthday(Person person, Map<String, List<Person>> birthdayMap) {
        if (person == null) return;
        
        String dayMonth = person.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM"));
        birthdayMap.computeIfAbsent(dayMonth, k -> new ArrayList<>()).add(person);
        for (Person child : person.getChildren()) {
            collectPeopleWithSameBirthday(child, birthdayMap);
        }
    }
 
    
    
    // c) Find person by name and print details
    public static Person findPersonByName(Person person, String name) {
    	if(person == null) return null;
    	
    	if(person.getFullName().equals(name)) {
    		System.out.println("Found: " + person.getFullName() + ", Birthday: " + 
    				person.getBirthday() + ", Age: " + person.getAge());
    		return person;
    	}
    	for(Person child : person.getChildren()) {
    		Person found = findPersonByName(child, name);
    		if(found != null) return found;
    	}
		return null;	
    }
    
    
    // d) Check if Person 1 is an ancestor of Person 2.
    public static boolean isAncestor(Person person1, Person person2) {
        if (person2 == null || person2 == null) return false;
        for(Person child : person1.getChildren()) {
        	if(child == person2 || isAncestor(child, person2)) {
        		return true;
        	}
        }
        return false;
    }
    
    // e) 
    public static boolean isRelatedWithinThreeGenerations(Person person1, Person person2) {
        if (person1 == null || person2 == null) return false;
        
        Person current = person1;
        for (int i = 0; i < 3 && current != null; i++) {
            if (current == person2) return true;
            current = current.getFather() != null ? current.getFather() : current.getMother();
        }

        return isAncestorWithinThreeLevels(person1, person2, 3);
    }

    private static boolean isAncestorWithinThreeLevels(Person person1, Person person2, int levels) {
        if (levels < 0 || person1 == null || person2 == null) return false;
        for (Person child : person1.getChildren()) {
            if (child == person2 || isAncestorWithinThreeLevels(child, person2, levels - 1)) {
                return true;
            }
        }
        return false;
    }
    
    // f) Return all grandChildren
    public static List<Person> getGrandChildren(Person person){
    	List<Person> grandChildren = new ArrayList<>();
    	if(person == null) return grandChildren;
    	for(Person child : person.getChildren()) {
    		grandChildren.addAll(child.getChildren());
    	}
    	return grandChildren;
    }

    // g) Return all people older than specified age
    public static List<Person> getPeopleOlderThan(Person person, int age){
    	List<Person> olderPeople = new ArrayList<>();
    	if(person == null) return olderPeople;
    	if(person.getAge() > age) {
    		olderPeople.add(person);
    	}
    	for(Person child : person.getChildren()) {
    		olderPeople.addAll(getPeopleOlderThan(child, age));
    	}
    	return olderPeople;
    }
    
    // h) Calculate depth of family tree
    public static int getTreeDepth(Person person) {
    	if(person == null) return 0;
    	int maxDepth = 0 ;
    	for(Person child : person.getChildren()) {
    		maxDepth = Math.max(maxDepth, getTreeDepth(child));
    	}
    	return maxDepth + 1;
    }
    
    // i) The generation gap between two person
    public static int GapBetweenTwoPeople(Person person1, Person person2) {
    	if(isAncestor(person2, person1)) {
    		return GapBetweenTwoPeople(person2, person1); 
    	}
    	int gap = 0;
    	Person current = person2;
    	
    	while(current != null) {
    		if(current == person1) return gap;
    		current = current.getFather() != null ? current.getFather() : current.getMother();
    		gap++;
    	}
		return -1;
    	
    }
    
    
   
    
    
    
}
