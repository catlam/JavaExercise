package Practice3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		Person root1 = createFamilyTree1();
		Person root2 = createFamilyTree2();

        
        
        while(true) {
        	System.out.println("\na. The total number of people in family.");
        	System.out.println("b. List of people with the same birthday.");
        	System.out.println("c. Find a person by name in the family tree.");
        	System.out.println("d. Write method to check if Person 1 is an ancestor (parent, grandparent, great-grandparent, etc.) of Person 2");
        	System.out.println("e. Write method with two parameters (two Person objects) to check if the two people are related within three generations.");
        	System.out.println("f. Return all the grandchildren of a person.");
        	System.out.println("g. Return all people in the family tree who are older than a specified age.");
        	System.out.println("h. Return the depth of the family tree (the maximum depth from the root to the leaf).");
        	System.out.println("i. Return the generation gap between two people.");
        	System.out.println("q. Exit the program.");
        	
            System.out.print("Enter question you want to see result (a-i, or 'q to quit): ");
            String choice = scanner.nextLine().toLowerCase();
            
            if(choice.equals("q")) break;
            
            System.out.print("Choose family tree (1 or 2): ");
            int treeChoice;
            
            try {
            	treeChoice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid tree choice. Please enter 1 or 2!");
				continue;
			}
            
            Person root = (treeChoice == 1) ? root1 : (treeChoice == 2) ? root2 : null;
            if(root == null) {
            	System.out.println("Invalid tree choice. Please enter 1 or 2!");
            	continue;
            }
            
            System.out.println("Family tree " + treeChoice + ": ");
            printFamilyTree(root);
            
            switch(choice) {
	            case "a":
	            	System.out.println("\nTotal people: " + FamilyTree.getTotalPeople(root));
	            	break;
	            case "b":
                	Map<String, List<Person>> sameBirthdayMap = FamilyTree.getPeopleWithSameBirthday(root);
                    if (sameBirthdayMap.isEmpty()) {
                        System.out.println("No people share the same birthday (day and month).");
                    } else {
                        System.out.println("People with the same birthday:");
                        for (Map.Entry<String, List<Person>> entry : sameBirthdayMap.entrySet()) {
                            String dayMonth = entry.getKey();
                            List<Person> people = entry.getValue();
                            System.out.print(dayMonth + ": ");
                            for (int i = 0; i < people.size(); i++) {
                                System.out.print(people.get(i).getFullName());
                                if (i < people.size() - 1) System.out.print(", ");
                            }
                            System.out.println();
                        }
                    }
                    break;
	            case "c":
	            	System.out.print("\nEnter name to find: ");
	            	String name = scanner.nextLine();
	            	FamilyTree.findPersonByName(root, name);
	            	break;
	            
	            case "d":
	            	System.out.print("\nEnter name of ancestor: ");
	            	String name1 = scanner.nextLine();
	            	System.out.print("Enter name of descendant: ");
	            	String name2 = scanner.nextLine();
	            	
	            	Person p1 = FamilyTree.findPersonByName(root, name1);
	            	Person p2 = FamilyTree.findPersonByName(root, name2);
	            	
	            	if(p1 != null && p2 != null) {
	            		System.out.println(name1 + " is an ancestor of " + name2 + ": " 
	            				+ FamilyTree.isAncestor(p1, p2));
	            	}
	            	break;
	            case "e":
	            	System.out.print("Enter name 1: ");
	            	name1 = scanner.nextLine();
	            	System.out.print("Enter name 2: ");
	            	name2 = scanner.nextLine();
	            	p1 = FamilyTree.findPersonByName(root, name1);
	            	p2 = FamilyTree.findPersonByName(root, name2);
	            	if(p1 != null && p2 != null) {
	            		System.out.println(name1 + " and " + name2 + " are related within three generations: " + FamilyTree.isRelatedWithinThreeGenerations(p1, p2));
	            	}
	            	break;
	            case "f":
	            	System.out.print("\nEnter person's name: ");
	            	name = scanner.nextLine();
	            	Person person = FamilyTree.findPersonByName(root, name);
	            	if(person != null) {
	            		System.out.println("Grandchildren of " + name + ": ");
	            		for(Person gc : FamilyTree.getGrandChildren(person)) {
	            			System.out.println(gc.getFullName());
	            		}
	            	}
	            	break;
	            case "g":
	            	System.out.print("Enter age: ");
	            	try {
						int age = Integer.parseInt(scanner.nextLine());
						List<Person> older = FamilyTree.getPeopleOlderThan(root, age);
						System.out.println("People older than " + age);
						for(Person p : older) {
							System.out.println(p.getFullName() + " - Age: " +  p.getAge());
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid age. Please enter again!");
					}
	            	break;
	            case "h":
	            	System.out.println("Tree depth: " + FamilyTree.getTreeDepth(root));
	            	break;
	            case "i":
	            	System.out.print("Enter name 1: ");
	            	name1 = scanner.nextLine();
	            	System.out.print("Enter name 2: ");
	            	name2 = scanner.nextLine();
	            	p1 = FamilyTree.findPersonByName(root, name1);
	            	p2 = FamilyTree.findPersonByName(root, name2);
	            	if(p1 != null && p2 != null) {
	            		System.out.println("The generation gap between of " + name1 + " and " + name2 + ": " 
	            				+ FamilyTree.GapBetweenTwoPeople(p1, p2));	
	            	}
	            	break;
	            default:
	            	System.out.println("Invalid. Please enter again!");           	
            }
        }
         
        
        scanner.close();
        
	}
	
	private static Person createFamilyTree1() {
		
//		John  +  Mary
//		       |         
//		   +---+----+    
//		   |        |    
//		  Bob     Sarah  
//		   |         |   
//		 Alice      Tom  
//		   |   
//	      Alan				
		
		Person john = new Person("John", LocalDate.of(1945, 1, 1), 80);
		Person mary = new Person("Mary", LocalDate.of(1947, 6, 15), 78);
        Person bob = new Person("Bob", LocalDate.of(1960, 1, 1), 65);
        Person sarah = new Person("Sarah", LocalDate.of(1965, 6, 6), 60);
        Person alice = new Person("Alice", LocalDate.of(1995, 1, 1), 30);
        Person tom = new Person("Tom", LocalDate.of(2000, 6, 15), 25);
        Person alan = new Person("Alan", LocalDate.of(2020, 5,10), 5);
        
        john.setFather(null);
        john.setMother(null);
        mary.setFather(null);
        mary.setMother(null);
        
        
        bob.setFather(john);
        bob.setMother(mary);
        sarah.setFather(john);
        sarah.setMother(mary);
        
        alice.setFather(bob);
        tom.setFather(null);
        tom.setMother(sarah);
        alan.setMother(alice);
        

        john.addChild(bob);
        john.addChild(sarah);
        mary.addChild(bob);
        mary.addChild(sarah);
        bob.addChild(alice);
        sarah.addChild(tom);
        alice.addChild(alan);
        
		return john;
	}
	
	private static Person createFamilyTree2() {
//        George
//        |
//      Emma       Frank
//     /    \         |
// Lisa     Mike     |
///   \      |       |
//Anna  David  (Mike)   |
//|
//Sophie

		
		Person george = new Person("George", LocalDate.of(1945, 10, 10), 80);
		Person emma = new Person("Emma", LocalDate.of(1975, 3, 20), 50);
		Person frank = new Person("Frank", LocalDate.of(1973, 3, 20), 52);
		Person lisa = new Person("Lisa", LocalDate.of(2000, 4, 15), 25);
		Person mike = new Person("Mike", LocalDate.of(2003, 5, 12), 22);
		Person anna = new Person("Anna", LocalDate.of(2020, 1, 1), 5);
		Person david = new Person("David", LocalDate.of(2002, 7, 7), 23);
		Person sophie = new Person("Sophie", LocalDate.of(2023, 2, 1), 2);

		emma.setFather(george);
		emma.setMother(null);

		frank.setFather(null);
		frank.setMother(null);

		lisa.setFather(null);
		lisa.setMother(emma);

		mike.setFather(frank);
		mike.setMother(emma);

		anna.setFather(null);
		anna.setMother(lisa);

		david.setFather(mike);       
		david.setMother(lisa);

		sophie.setFather(null);
		sophie.setMother(anna);      

		george.addChild(emma);

		emma.addChild(lisa);
		emma.addChild(mike);

		frank.addChild(mike);

		lisa.addChild(anna);
		lisa.addChild(david);

		mike.addChild(david);

		anna.addChild(sophie);

        
        return george;
	}
	
	public static void printFamilyTree(Person person) {
	    printFamilyTreeHelper(person, "", true);
	}

	private static void printFamilyTreeHelper(Person person, String prefix, boolean isTail) {
	    if (person == null) return;

	    System.out.println(prefix + (isTail ? "└── " : "├── ") + person.getFullName() + " (Birthday: " + person.getBirthday() + ")");
	    
	    List<Person> children = person.getChildren();
	    for (int i = 0; i < children.size() - 1; i++) {
	        printFamilyTreeHelper(children.get(i), prefix + (isTail ? "    " : "│   "), false);
	    }

	    if (!children.isEmpty()) {
	        printFamilyTreeHelper(children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true);
	    }
	}



}
