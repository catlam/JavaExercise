package Animal;

class Parrot extends Animal{
	boolean canTalk;

	public Parrot(String name, int age, boolean canTalk) {
		super(name, age);
		this.canTalk = canTalk;
	}

	public boolean isCanTalk() {
		return canTalk;
	}

	public void setCanTalk(boolean canTalk) {
		this.canTalk = canTalk;
	}
	
	public String getDetails() {
		return "Parrots - Name: " + name + ", Age: " + age + ", Can talk: " + canTalk + (canTalk ? "Yes" : "No");
	}
}
