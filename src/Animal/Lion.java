package Animal;

class Lion extends Animal {
	double speed;

	public Lion(String name, int age, double speed) {
		super(name, age);
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public String getDetails() {
		return "Lion - Name: " + name + ", Age: " + age + ", Speed: " + speed + "km/h";
	}
}
