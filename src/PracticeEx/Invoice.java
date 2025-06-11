package PracticeEx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
	private String id;
	private LocalDate date;
	private Customer customer;
	private List<InvoiceItem> items;
	
	public Invoice(String id, LocalDate date, Customer customer) {
		this.id = id;
		this.date = date;
		this.customer = customer;
		this.items = new ArrayList<>();
	}
	
	public void addItem(InvoiceItem item) {
		items.add(item);
	}
	
	public double getTotalAmout() {
		double sum = 0;
		for(InvoiceItem item : items) {
			sum += item.getTotalPrice();
		}
		return sum;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", date=" + date + ", customer=" + customer + ", items=" + items
				+ ", getTotalAmout()=" + getTotalAmout() + "]";
	}
	
	
}
