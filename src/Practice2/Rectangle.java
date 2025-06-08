package Practice2;

public class Rectangle extends Shape {
	double width, height;

	public Rectangle(Color color, double borderWidth, double x, double y, double width, double height) {
		super(color, borderWidth, x, y);
		this.width = width;
		this.height = height;
	}

	@Override
	public double getArea() {
		return width * height;
	}
	
}
