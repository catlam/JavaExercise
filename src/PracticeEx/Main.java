package PracticeEx;

import java.time.LocalDate;
import java.util.*;

public class Main {
    static List<Book> books = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Invoice> invoices = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedData(); // Create sample data

        while (true) {
            System.out.println("\n=== BOOKSTORE MENU ===");
            System.out.println("1. Show all books");
            System.out.println("2. Show all customers");
            System.out.println("3. Create new invoice");
            System.out.println("4. Show all invoices");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1: showBooks(); break;
                case 2: showCustomers(); break;
                case 3: createInvoice(); break;
                case 4: showInvoices(); break;
                case 5: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    static void seedData() {
        books.add(new TextBook("B001", "Math Grade 12", 5.5, 10, "Math", 12));
        books.add(new TextBook("B002", "Physics Grade 11", 6.0, 8, "Physics", 11));
        books.add(new NovelBook("B003", "Harry Potter", 12.0, 5, "J.K. Rowling", "Fantasy"));
        books.add(new NovelBook("B004", "Sherlock Holmes", 10.0, 7, "Arthur Conan Doyle", "Detective"));

        customers.add(new Customer("C001", "Alice Nguyen", "alice@gmail.com", "0123456789"));
        customers.add(new Customer("C002", "Bob Tran", "bob@yahoo.com", "0987654321"));
    }

    static void showBooks() {
        System.out.println("\n--- List of Books ---");
        for (Book book : books) {
            book.displayInfo();
            System.out.println("Price: $" + book.getPrice() + " | Stock: " + book.getStock());
            System.out.println("----------------------");
        }
    }

    static void showCustomers() {
        System.out.println("\n--- List of Customers ---");
        for (Customer customer : customers) {
            System.out.println(customer.toString());
            System.out.println("--------------------------");
        }
    }

    static void createInvoice() {
        System.out.println("\n--- Create New Invoice ---");
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = findCustomerById(customerId);
        
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        String invoiceId = "INV" + (invoices.size() + 1);
        Invoice invoice = new Invoice(invoiceId, LocalDate.now(), customer);

        while (true) {
            System.out.print("Enter book ID to buy (or 'done' to finish): ");
            String bookId = scanner.nextLine();
            if (bookId.equalsIgnoreCase("done")) break;

            Book book = findBookById(bookId);
            if (book == null) {
                System.out.println("Book not found.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (qty <= 0 || qty > book.getStock()) {
                System.out.println("Invalid quantity. Available: " + book.getStock());
                continue;
            }

            book.setStock(book.getStock() - qty); // reduce stock
            invoice.addItem(new InvoiceItem(book, qty));
        }

        invoices.add(invoice);
        System.out.println("Invoice created successfully!");
    }

    static void showInvoices() {
        if (invoices.isEmpty()) {
            System.out.println("No invoices found.");
            return;
        }

        for (Invoice invoice : invoices) {
            System.out.println("\n--- Invoice Detail ---");
            System.out.println(invoice.toString());
            System.out.println("------------------------");
        }
    }

    static Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }

    static Book findBookById(String id) {
        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(id)) return b;
        }
        return null;
    }
}
