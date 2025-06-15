package Animal;

import java.util.ArrayList;
import java.util.Scanner;


public class ZooAnimal {
	static ArrayList<Animal> animals = new ArrayList<>();
	static Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {

		while(true) {
			System.out.println("\nZoo Management System");
            System.out.println("1. Add Animal");
            System.out.println("2. Display All Animals");
            System.out.println("3. Find Oldest Animal");
            System.out.println("4. Calculate Average Age");
            System.out.println("5. Find Heaviest Elephant");
            System.out.println("6. Sort Animals by Added Date");
            System.out.println("7. Delete Animal by Name");
            System.out.println("8. Find Animals Above Age");
            System.out.println("9. Find First Added Animal");
            System.out.println("10. Find Fastest Animal");
            System.out.println("11. Find Animal with Highest Attribute");
            System.out.println("12. Find Most Unique Animals");
            System.out.println("13. Update Animal Attribute");
            System.out.println("14. Delete Animals by Type");
            System.out.println("15. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice) {
	            case 1: addAnimal(); break;
	            case 2: displayAllAnimals(); break;
	            case 3: findOldestAnimal(); break;
	            case 4: calculateAverageAge(); break;
	            case 5: findHeaviestElephant(); break;
	            case 6: sortByAddedDate(); break;
	            case 7:
	            	System.out.println("Enter name: ");
	            	String name = scanner.nextLine();
	            	deleteAnimalByName(name); break;	            	
	            case 8: findAnimalsAboveAge(); break;
	            case 9: findFirstAddedAnimal(); break;
	            case 10: findFastestAnimal(); break;
	            case 11: findHighestAttribute(); break;
	            case 12: findMostUniqueAnimals(); break;
	            case 13: updateAnimalAttribute(); break;
//	            case 14: deleteAnimalsByType(); break;
//	            case 15: System.exit(0);
//	            default: System.out.println("Invalid option!");
            }
		}
	}
	
	static void addAnimal() {
		System.out.print("Choose type: (1.Elephant, 2. Lion, 3. Parrot)");
		int type = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.println("Enter age: ");
		int age = scanner.nextInt();
		
		if(type == 1) {
			System.out.println("Enter weight: ");
			double weight = scanner.nextDouble();
			animals.add(new Elephant(name, age, weight));
		}else if(type == 2) {
			System.out.println("Enter speed: ");
			double speed = scanner.nextDouble();
			animals.add(new Lion(name, age, speed));
		}else if(type == 3) {
			System.out.println("Can talk (true/false): ");
			Boolean canTalk = scanner.nextBoolean();
			animals.add(new Parrot(name, age, canTalk));
		}
		System.out.println("Animal added successfully!");
	}

	static void displayAllAnimals() {
		if(animals.isEmpty()) return;
		for(Animal a : animals) {
			System.out.println(a.getDetails());
		}
	}
	
	// 3.1
	public static void findOldestAnimal() {
		if(animals == null) return;
		
		Animal oldest = animals.get(0);
		for(int i = 0;i < animals.size();i++) {
			if(animals.get(i).getAge() > oldest.getAge()) {
				oldest = animals.get(i);
			}
		}
		System.out.println("\nThe oldest animal: ");
		System.out.println(oldest.getDetails());
	}
	
	// 3.2
	public static void calculateAverageAge() {
		if(animals.isEmpty()) return;
		double sum = 0;
		
		for(int i = 0;i < animals.size();i++) {
			sum += animals.get(i).getAge();
		}
		double avg = sum / animals.size();
		System.out.println("\nAverage age: " + avg);
	}
	
	// 3.3
	public static void findHeaviestElephant() {
		if(animals.isEmpty()) return;
		
		Elephant heaviest = null;
		for(Animal a : animals) {
			if(a instanceof Elephant) {
				Elephant el = (Elephant) a;
				if(heaviest == null || el.getWeight() > heaviest.getWeight()) {
					heaviest = el;
				}
			}
		}
		if(heaviest == null) {
			System.out.println("No found!");
		}else {
			System.out.println("\nHeaviest elephant: " + heaviest.getDetails());
		}
	}
	
	// 3.4
	public static void sortByAddedDate() {
		if(animals.size() == 0) return;
		
		ArrayList<Animal> sorted = new ArrayList<>(animals);
		for(int i = 0;i < sorted.size() - 1;i++) {
			for(int j = 0; j < sorted.size() - i - 1; j++) {
				if(animals.get(j).getDateTime().isBefore(sorted.get(j+1).getDateTime())) {
					Animal temp = animals.get(j);
					sorted.set(i, sorted.get(j+1));
					sorted.set(j+1, temp);
				}
			}
		}
		System.out.println("\nAnimals Sorted by Added Time (Recent First):");
		for(int i = 0;i < sorted.size();i++) {
			Animal animal = sorted.get(i);
			System.out.println(animal.getDetails() + "Add " + animal.getDateTime());
		}
	}
	
	//3.5 
	public static void deleteAnimalByName(String name) {
		for(int i = 0;i < animals.size();i++) {
			if(animals.get(i).getName().equals(name)) {
				animals.remove(i);
				System.out.println("Remove successfully!");
				return;
			}
		}
	}
	
	// 3.6
	public static void findAnimalsAboveAge() {
		System.out.println("Enter age: ");
		int age = scanner.nextInt();
		Boolean found = false;
		
		for(int i = 0;i < animals.size();i++) {
			if(animals.get(i).getAge() > age) {
				System.out.println(animals.get(i).getDetails());
				found = true;
			}
		}
	}
	
	// 3.7 
	public static void findFirstAddedAnimal() {
		if(animals.isEmpty()) return;
		
		Animal first = animals.get(0);
		for(int i = 0;i < animals.size();i++) {
			if(animals.get(i).getDateTime().isBefore(first.getDateTime())) {
				first = animals.get(i);
			}
		}
		System.out.println("\nFirst added anmial: ");
        System.out.println(first.getDetails() + ", Added: " + first.getDateTime());

	}
	
	// 3.8
	public static void findFastestAnimal() {
		Animal faster = null;
		
		double maxFaster = 0;
		for(Animal animal : animals) {
			if(animal instanceof Lion) {
				Lion lion = (Lion) animal;
				if(faster == null || lion.speed > maxFaster) {
					faster = lion;
					maxFaster = lion.speed;
				}
			}
		}
		if(faster == null) {
			System.err.println("No found!");
		}else {
			System.out.println(faster.getDetails());
		}
		
	}
	
	// 3.9
	public static void findHighestAttribute() {
		if(animals.isEmpty()) return;
		
		Elephant heaviest = null;
		Lion fastest = null;
		Parrot isTalking = null;
		
		for(Animal animal : animals) {
			if(animal instanceof Elephant) {
				Elephant el = (Elephant) animal;
				if(heaviest == null || el.weight > heaviest.weight) {
					heaviest = el;
				}
			}else if(animal instanceof Lion) {
				Lion li = (Lion) animal;
				if(fastest == null || li.speed > fastest.speed) {
					fastest = li;
				}
			}else if(animal instanceof Parrot) {
				Parrot pa = (Parrot) animal;
				if(pa.canTalk && isTalking == null) {
					isTalking = pa;
				} 
			}
		}
		
		if(heaviest != null) {
			System.out.println("\nHeaviest elephant: " + heaviest.getDetails());
		}else {
			System.out.println("No found!");
		}
		if(fastest != null) {
			System.out.println("\nFastest lion: " + fastest.getDetails());		
		}
		else {
			System.out.println("No found!");
		}
		if(isTalking != null) {
			System.out.println("\nTalking Parrot: " + isTalking.getDetails());
		}
		else {
			System.out.println("No found!");
		}
	}
	
	// 3.10
	public static void findMostUniqueAnimals() {
		if(animals.isEmpty()) return;
		
		Elephant heaviest = null;
		Lion fastest = null;
		Parrot isTalking = null;
		
		for(Animal animal : animals) {
			if(animal instanceof Elephant) {
				Elephant el = (Elephant) animal;
				if(heaviest == null || el.weight > heaviest.weight) {
					heaviest = el;
				}
			}else if(animal instanceof Lion) {
				Lion li = (Lion) animal;
				if(fastest == null || li.speed > fastest.speed) {
					fastest = li;
				}
			}else if(animal instanceof Parrot) {
				Parrot pa = (Parrot) animal;
				if(pa.canTalk && isTalking == null) {
					isTalking = pa;
				} 
			}
		}
		
		if(heaviest != null && fastest != null && isTalking != null) {
			System.out.println("Heaviest Elephant " + heaviest.getDetails());
		}else {
			System.out.println("No found!");
		}
	}
	
	public static void updateAnimalAttribute() {

		System.out.println("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter attribute (weight/speed/canTalk): ");
        String attribute = scanner.nextLine();
        System.out.print("Enter new value: ");
        String value = scanner.nextLine();
		
		for(int i = 0;i < animals.size();i++) {
			Animal animal = animals.get(i);
			if(animal.getName().equals(name)) {
				if(animal instanceof Elephant && attribute.equals("weight")) {
					((Elephant) animal).setWeight(Double.parseDouble(value));
					System.out.println("Elephant weights updated!");
					System.out.println(animal.getDetails());
					return;
				}else if(animal instanceof Lion && attribute.equals("speed")) {
					((Lion) animal).setSpeed(Double.parseDouble(value));
					System.out.println("Lion speeds updated!");
					System.out.println(animal.getDetails());
					return;
				}else if(animal instanceof Parrot && attribute.equals("canTalk")) {
					((Parrot) animal).setCanTalk(Boolean.parseBoolean(value));
					System.out.println("Parrot weights updated!");
					System.out.println(animal.getDetails());
					return;
				}
			}
		}
		System.out.println("Not found!");
	}

}
