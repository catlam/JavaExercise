package Practice3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Create first family tree
        Person root1 = createFamilyTree1();
        // Create second family tree
        Person root2 = createFamilyTree2();

        while (true) {
            // Display menu
            System.out.println("\n=== Family Tree Menu ===");
            System.out.println("a: Count total people in the family tree");
            System.out.println("b: Find people with the same birthday");
            System.out.println("c: Find a person by name and show details");
            System.out.println("d: Check if one person is an ancestor of another");
            System.out.println("e: Check if two people are related within 3 generations");
            System.out.println("f: List all grandchildren of a person");
            System.out.println("g: Find people older than a specified age");
            System.out.println("h: Calculate the depth of the family tree");
            System.out.println("i: Find the generation gap between two people");
            System.out.println("q: Quit");
            System.out.println("Which question do you want to see the result for? (a-i, or 'q' to quit)");

            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("q")) break;

            System.out.println("Choose family tree (1 or 2):");
            int treeChoice;
            try {
                treeChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid tree choice. Please enter 1 or 2.");
                continue;
            }
            Person root = (treeChoice == 1) ? root1 : (treeChoice == 2) ? root2 : null;
            if (root == null) {
                System.out.println("Invalid tree choice. Please enter 1 or 2.");
                continue;
            }

            switch (choice) {
                case "a":
                    System.out.println("Total people: " + FamilyTree.getTotalPeople(root));
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
                            System.out.print("Born on " + dayMonth + ": ");
                            for (int i = 0; i < people.size(); i++) {
                                System.out.print(people.get(i).getFullName());
                                if (i < people.size() - 1) System.out.print(", ");
                            }
                            System.out.println();
                        }
                    }
                    break;
                case "c":
                    System.out.println("Enter name to find:");
                    String name = scanner.nextLine();
                    FamilyTree.findPersonByName(root, name);
                    break;
                case "d":
                    System.out.println("Enter name of possible ancestor:");
                    String name1 = scanner.nextLine();
                    System.out.println("Enter name of possible descendant:");
                    String name2 = scanner.nextLine();
                    Person p1 = FamilyTree.findPersonByName(root, name1);
                    Person p2 = FamilyTree.findPersonByName(root, name2);
                    if (p1 != null && p2 != null) {
                        System.out.println(name1 + " is ancestor of " + name2 + ": " + 
                            FamilyTree.isAncestor(p1, p2));
                    }
                    break;
                case "e":
                    System.out.println("Enter first person's name:");
                    name1 = scanner.nextLine();
                    System.out.println("Enter second person's name:");
                    name2 = scanner.nextLine();
                    p1 = FamilyTree.findPersonByName(root, name1);
                    p2 = FamilyTree.findPersonByName(root, name2);
                    if (p1 != null && p2 != null) {
                        System.out.println(name1 + " and " + name2 + " are related within 3 generations: " + 
                            FamilyTree.isRelatedWithinThreeGenerations(p1, p2));
                    }
                    break;
                case "f":
                    System.out.println("Enter person's name:");
                    name = scanner.nextLine();
                    Person person = FamilyTree.findPersonByName(root, name);
                    if (person != null) {
                        System.out.println("Grandchildren of " + name + ":");
                        for (Person gc : FamilyTree.getGrandchildren(person)) {
                            System.out.println(gc.getFullName());
                        }
                    }
                    break;
                case "g":
                    System.out.println("Enter age:");
                    try {
                        int age = Integer.parseInt(scanner.nextLine());
                        List<Person> older = FamilyTree.getPeopleOlderThan(root, age);
                        System.out.println("People older than " + age + ":");
                        for (Person p : older) {
                            System.out.println(p.getFullName());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age. Please enter a number.");
                    }
                    break;
                case "h":
                    System.out.println("Tree depth: " + FamilyTree.getTreeDepth(root));
                    break;
                case "i":
                    System.out.println("Enter first person's name:");
                    name1 = scanner.nextLine();
                    System.out.println("Enter second person's name:");
                    name2 = scanner.nextLine();
                    p1 = FamilyTree.findPersonByName(root, name1);
                    p2 = FamilyTree.findPersonByName(root, name2);
                    if (p1 != null && p2 != null) {
                        int gap = FamilyTree.getGenerationGap(p1, p2);
                        System.out.println("Generation gap between " + name1 + " and " + name2 + ": " + gap);
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a-i or q.");
            }
        }
        scanner.close();
    }

    private static Person createFamilyTree1() {
        
        Person john = new Person("John", LocalDate.of(1955, 1, 1), 70);
        Person mary = new Person("Mary", LocalDate.of(1957, 6, 15), 68);
        Person bob = new Person("Bob", LocalDate.of(1980, 1, 1), 45);
        Person sarah = new Person("Sarah", LocalDate.of(1985, 6, 6), 40);
        Person alice = new Person("Alice", LocalDate.of(2005, 1, 1), 20);
        Person tom = new Person("Tom", LocalDate.of(2010, 6, 15), 15);

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

        john.addChild(bob);
        john.addChild(sarah);
        mary.addChild(bob);
        mary.addChild(sarah);
        bob.addChild(alice);
        sarah.addChild(tom);

        return john;
    }

    private static Person createFamilyTree2() {
        
        Person george = new Person("George", LocalDate.of(1945, 10, 10), 80);
        Person emma = new Person("Emma", LocalDate.of(1975, 3, 20), 50);
        Person frank = new Person("Frank", LocalDate.of(1973, 3, 20), 52);
        Person lisa = new Person("Lisa", LocalDate.of(2000, 4, 15), 25);
        Person mike = new Person("Mike", LocalDate.of(2003, 5, 12), 22);
        Person anna = new Person("Anna", LocalDate.of(2020, 1, 1), 5);

        george.setFather(null);
        george.setMother(null);
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

        george.addChild(emma);
        emma.addChild(lisa);
        emma.addChild(mike);
        frank.addChild(mike);
        lisa.addChild(anna);

        return george;
    }
}