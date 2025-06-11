package PracticeEx;

class TextBook extends Book{
	private String subject;
	private int grade;
	

	public TextBook(String id, String title, double price, int stock, String subject, int grade) {
		super(id, title, price, stock);
		this.subject = subject;
		this.grade = grade;
	}


	@Override
	public void displayInfo() {
        System.out.println("TextBook: " + title + " | Subject: " + subject + " | Grade: " + grade);
	}


	
	
}