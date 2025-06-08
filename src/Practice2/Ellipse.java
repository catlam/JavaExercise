package Practice2;

public class Ellipse extends Shape{
	double majorAxis, minAxis;

	public Ellipse(Color color, double borderWidth, double x, double y, double majorAxis, double minAxis) {
		super(color, borderWidth, x, y);
		this.majorAxis = majorAxis;
		this.minAxis = minAxis;
	}

	@Override
	public double getArea() {
		return Math.PI * (majorAxis/2) * (minAxis/2);
	}
	
	
}
