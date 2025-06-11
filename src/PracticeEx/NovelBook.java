package PracticeEx;

class NovelBook extends Book{
	private String author;
	private String genre;
	

	public NovelBook(String id, String title, double price, int stock, String author, String genre) {
		super(id, title, price, stock);
		this.author = author;
		this.genre = genre;
	}


	@Override
	public void displayInfo() {
        System.out.println("NovelBook: " + title + " | Author: " + author + " | Genre: " + genre);
	}
		
}
