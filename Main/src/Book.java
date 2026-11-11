public class Book implements Searchable, Borrowable  {

    // Private fields (Encapsulation)
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    // Constructor
    public Book(String title, String author, String isbn, boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean matches(String keyword) {
        return title.equalsIgnoreCase(keyword)
                || author.equalsIgnoreCase(keyword)
                || isbn.equalsIgnoreCase(keyword);
    }

    @Override
    public void borrow() {
        if (available) {
            available = false;
        }
    }

    @Override
    public void returnBook() {
        available = true;
    }

    // toString method
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", available=" + available +
                '}';
    }
}
