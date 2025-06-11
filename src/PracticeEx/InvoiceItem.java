package PracticeEx;

public class InvoiceItem {
	private Book book;
	private int quantity;
	
	
	public InvoiceItem(Book book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	}
	
	public double getTotalPrice() {
		return book.getPrice() * quantity;	
	}

	@Override
	public String toString() {
		return "InvoiceItem [book=" + book + ", quantity=" + quantity + ", getTotalPrice()=" + getTotalPrice() + "]";
	}
	
	
}
