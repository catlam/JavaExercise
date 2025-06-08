package Practice2;

public class Circle extends Shape {
	private double radius;

	public Circle(Color color, double borderWidth, double x, double y, double radius) {
		super(color, borderWidth, x, y);
		this.radius = radius;
	}

	@Override
	public double getArea() {
		return Math.PI * Math.pow(radius, 2);
	}
	
	
}
