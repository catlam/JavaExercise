package Practice3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FamilyTree {
	

    // a) Calculate total number of people in the family tree
	public static int getTotalPeople(Person person) {
		if (person == null) return 0;
		int cnt = 1;
		for(Person child : person.getChildren()) {
			cnt++;
		}
		return cnt;
	}

	
	// b) Return map of people with same birthday (day and month)
    public static Map<String, List<Person>> getPeopleWithSameBirthday(Person person) {
        Map<String, List<Person>> birthdayMap = new HashMap<>();
        collectPeopleWithSameBirthday(person, birthdayMap);
        // Remove entries with only one person
        birthdayMap.entrySet().removeIf(entry -> entry.getValue().size() <= 1);
        return birthdayMap;
    }

    private static void collectPeopleWithSameBirthday(Person person, Map<String, List<Person>> birthdayMap) {
        if (person == null) return;
        // Get day and month as key (e.g., "01-01")
        String dayMonth = person.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM"));
        birthdayMap.computeIfAbsent(dayMonth, k -> new ArrayList<>()).add(person);
        for (Person child : person.getChildren()) {
            collectPeopleWithSameBirthday(child, birthdayMap);
        }
    }
    
    
    // c) Find person by name and print details
    public static Person findPersonByName(Person person, String name) {
        if (person == null) return null;
        if (person.getFullName().equals(name)) {
            System.out.println("Found: " + person.getFullName() + ", Birthday: " + 
                person.getBirthday() + ", Age: " + person.getAge());
            return person;
        }
        for (Person child : person.getChildren()) {
            Person found = findPersonByName(child, name);
            if (found != null) return found;
        }
        return null;
    }
    
    
    // d) Check if person1 is ancestor of person2
    public static boolean isAncestor(Person person1, Person person2) {
        if (person1 == null || person2 == null) return false;
        for (Person child : person1.getChildren()) {
            if (child == person2 || isAncestor(child, person2)) {
                return true;
            }
        }
        return false;
    }
    
    
 // e) Check if two people are related within three generations
    public static boolean isRelatedWithinThreeGenerations(Person person1, Person person2) {
        if (person1 == null || person2 == null) return false;
        
        // Check if person2 is parent, grandparent, or great-grandparent
        Person current = person1;
        for (int i = 0; i < 3 && current != null; i++) {
            if (current == person2) return true;
            current = current.getFather() != null ? current.getFather() : current.getMother();
        }

        // Check if person2 is child, grandchild, or great-grandchild
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
    
    
    // f) Return all grandchildren
    public static List<Person> getGrandchildren(Person person) {
        List<Person> grandchildren = new ArrayList<>();
        if (person == null) return grandchildren;
        for (Person child : person.getChildren()) {
            grandchildren.addAll(child.getChildren());
        }
        return grandchildren;
    }
    
    
    // g) Return all people older than specified age
    public static List<Person> getPeopleOlderThan(Person person, int age) {
        List<Person> result = new ArrayList<>();
        if (person == null) return result;
        if (person.getAge() > age) {
            result.add(person);
        }
        for (Person child : person.getChildren()) {
            result.addAll(getPeopleOlderThan(child, age));
        }
        return result;
    }
    
    
    // h) Calculate depth of family tree
    public static int getTreeDepth(Person person) {
        if (person == null) return 0;
        int maxDepth = 0;
        for (Person child : person.getChildren()) {
            maxDepth = Math.max(maxDepth, getTreeDepth(child));
        }
        return maxDepth + 1;
    }
    
    
    // i) Return generation gap between two people
    public static int getGenerationGap(Person person1, Person person2) {
        if (person1 == null || person2 == null) return -1;
        
        // Find the depth of both people from a common ancestor
        Person current = person1;
        int depth1 = 0;
        while (current != null) {
            if (current == person2) {
                return depth1;
            }
            for (Person child : current.getChildren()) {
                int gap = getGenerationGapFromDescendant(child, person2, 1);
                if (gap != -1) {
                    return depth1 + gap;
                }
            }
            current = current.getFather() != null ? current.getFather() : current.getMother();
            depth1++;
        }
        return -1;
    }

    private static int getGenerationGapFromDescendant(Person person, Person person2, int depth) {
        if (person == null) return -1;
        if (person == person2) return depth;
        for (Person child : person.getChildren()) {
            int gap = getGenerationGapFromDescendant(child, person2, depth + 1);
            if (gap != -1) return gap;
        }
        return -1;
    }

}
