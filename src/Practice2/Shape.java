package Practice2;

enum Color{
	RED, BLACK, PINK, WHITE, BLUE, GRAY, GREEN, YELLOW
}

interface IShape{
	double getArea();
	double getDistance();
	Color getColor();
	void display();
}

public abstract class Shape implements IShape{
	Color color;
	double borderWidth;
	double x, y;
	
	public Shape(Color color, double borderWidth, double x, double y) {
		super();
		this.color = color;
		this.borderWidth = borderWidth;
		this.x = x;
		this.y = y;
	}


	@Override
	public double getDistance() {
		return Math.sqrt(x * x + y * y);
	}

	@Override
	public Color getColor() {
		return color;
	}


	public void display() {
        System.out.printf("%s - Color: %s, Border: %.2f, Position: (%.1f, %.1f), Area: %.2f, Distance: %.2f\n",
                this.getClass().getSimpleName(), color, borderWidth, x, y, getArea(), getDistance());
    }

	
}
