import java.util.ArrayList;
import java.util.List;

public class Library {

    // Store books using an ArrayList
    private List<Book> books;

    // Constructor
    public Library() {
        books = new ArrayList<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Get all books
    public List<Book> getBooks() {
        return books;
    }
}