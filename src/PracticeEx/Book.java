package PracticeEx;

abstract class Book{
	protected String id, title;
	protected double price;
	protected int stock;
	

	public Book(String id, String title, double price, int stock) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.stock = stock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public abstract void displayInfo();

}


