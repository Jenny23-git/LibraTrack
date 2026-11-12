import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private List<Member> members;

    // Constructor
    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Register a library member
    public void addMember(Member member) {
        members.add(member);
    }

    // Display all registered members
    public List<Member> getMembers() {
        return members;
    }

    // Get all books
    public List<Book> getBooks() {
        return books;
    }

    // Search for a book using the Searchable interface
    public Book searchBook(String keyword) {
        for (Book book : books) {
            if (book.matches(keyword)) {
                return book;
            }
        }
        return null;
    }

    // Borrow a book
    public boolean borrowBook(String keyword) {
        Book book = searchBook(keyword);

        if (book != null && book.isAvailable()) {
            book.borrow();
            return true;
        }

        return false;
    }

    // Return a book
    public boolean returnBook(String keyword) {
        Book book = searchBook(keyword);

        if (book != null && !book.isAvailable()) {
            book.returnBook();
            return true;
        }

        return false;
    }
}