package Practice2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ShapeMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<IShape> shapes = new ArrayList<>();
		
		System.out.println("Enter number shape: ");
		int n = Integer.parseInt(scanner.nextLine());
		
		for(int i = 0;i < n; i++) {
			System.out.println("Shape #" + (i+1));
			
            System.out.print("Choose type (1: Circle, 2: Rectangle, 3: Ellipse): ");
            int type = Integer.parseInt(scanner.nextLine());
            
            Color color = inputColor(scanner);
            		
            System.out.println("Enter border width: ");
            double borderWidth = Double.parseDouble(scanner.nextLine());
            
            System.out.println("Enter x: ");
            double x = Double.parseDouble(scanner.nextLine());
            
            System.out.println("Enter y: ");
            double y = Double.parseDouble(scanner.nextLine());
            
            switch(type) {
            case 1:
            	System.out.println("Enter radius: ");
            	double radius = Double.parseDouble(scanner.nextLine());
            	shapes.add(new Circle(color,borderWidth, x, y, radius));
            	break;
            	
            case 2:
            	System.out.println("Enter width: ");
            	double width = Double.parseDouble(scanner.nextLine());
            	System.out.println("Enter height: ");
            	double height = Double.parseDouble(scanner.nextLine());
            	shapes.add(new Rectangle(color, borderWidth, x, y,width, height));
            	break;
            	
            case 3:
            	System.out.println("Enter major axis: ");
            	double majorAxis = Double.parseDouble(scanner.nextLine());
            	System.out.println("Enter min axis: ");
            	double minAxis = Double.parseDouble(scanner.nextLine());
            	shapes.add(new Ellipse(color, borderWidth, x, y, majorAxis, minAxis));
            	break;
            	
            default:
            	System.out.println("Invalid shape type!");
            	i--;
            }
		}
		
        // a) Display all shapes with area
		System.out.println("-------------List of shape-------------");
		shapes.forEach(IShape::display);
		
		
		 	findLargestArea(shapes);         // b)
	        printDistances(shapes);          // c)
	        countCircles(shapes);            // d)
	        sortByDistance(shapes);  
	        
		 
	}

	public static Color inputColor(Scanner sc) {
		while (true) {
			System.out.println("Enter color (RED, BLACK, PINK, WHITE, BLUE, GRAY, GREEN, YELLOW): ");
			String inputColor = sc.nextLine().toUpperCase();
			try {
				return Color.valueOf(inputColor);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid color. Please try again!");
			}
		}
	}
	
	public static void findLargestArea(List<IShape> shapes) {
    	if (shapes == null || shapes.isEmpty()) {
    		System.out.println("No shapes to find the largest area from.");
            return; 
        }
    	
    	IShape largest = shapes.get(0);
    	for(IShape s : shapes) {
    		if(s.getArea() > largest.getArea()) {
    			largest = s;
    		}
    	}
    	System.out.println("\nShape with largest area:");
        largest.display();
    }
	
	public  static void printDistances(List<IShape> shapes) {
		System.out.println("\nDistance from origin (0,0): ");
		for(IShape s : shapes) {
            System.out.printf("%s - Distance: %.2f\n", s.getClass().getSimpleName(), s.getDistance());
		}
	}
	
	public static void countCircles(List<IShape> shapes) {
		int cnt = 0;
		for(IShape s: shapes) {
			if(s instanceof Circle) {
				cnt++;
			}
		}
		System.out.println("\nNumber of Circle: " + cnt);
	}
	
	public static void sortByDistance(List<IShape> shapes) {
        shapes.sort((s1, s2) -> Double.compare(s1.getDistance(), s2.getDistance())); 
        System.out.println("\nShape sorted by distance: ");
        shapes.forEach(IShape::display);
	}
}
