package Animal;

class Elephant extends Animal{
	double weight;

	public Elephant(String name, int age, double weight) {
		super(name, age);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String getDetails() {
		return "Elephant - Name: " + name + ", Age: " + age + ", Weight: " + weight + " kg";
	}
}
