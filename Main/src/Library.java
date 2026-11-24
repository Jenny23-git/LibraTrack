import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.TreeMap;
import java.time.LocalDate;

public class Library {

    private Map<String, Book> books;
    private List<Member> members;
    private Deque<Book> recentlyReturnedBooks;
    private Map<String, Book> booksByTitle;

    // Constructor
    public Library() {
        books = new HashMap<>();
        members = new ArrayList<>();
        recentlyReturnedBooks = new ArrayDeque<>();
        booksByTitle = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    // Add a book to the library
    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            System.out.println("A book with this ISBN already exists.");
            return;
        }

        books.put(book.getIsbn(), book);
        booksByTitle.put(book.getTitle(), book);
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
        return new ArrayList<>(books.values());
    }

    // Get books sorted alphabetically by title
    public List<Book> getBooksSortedByTitle() {
        return new ArrayList<>(booksByTitle.values());
    }

    // Get recently returned books
    public Deque<Book> getRecentlyReturnedBooks() {
        return recentlyReturnedBooks;
    }

    // Search for a book using the Searchable interface
    public Book searchBook(String keyword) {
        for (Book book : books.values()) {
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

    public boolean borrowBook(String keyword, Member member) {
        Book book = searchBook(keyword);

        if (book != null && book.isAvailable()) {
            book.borrow();

            member.addTransaction(new Transaction(
                    book, "BORROW", LocalDate.now().toString()
            ));

            return true;
        }

        return false;
    }

    public boolean returnBook(String keyword, Member member) {
        Book book = searchBook(keyword);

        if (book != null && !book.isAvailable()) {
            member.addTransaction(new Transaction(
                    book, "RETURN", LocalDate.now().toString()
            ));

            if (book.hasReservations()) {
                Member nextMember = book.getNextReservation();
                book.borrow();

                nextMember.addTransaction(new Transaction(
                        book, "BORROW", LocalDate.now().toString()
                ));

                System.out.println("Book assigned to next member in queue: "
                        + nextMember.getName());
            } else {
                book.returnBook();
                recentlyReturnedBooks.push(book);
            }

            return true;
        }

        return false;
    }


    // Reserve a book for a member
    public boolean reserveBook(String keyword, Member member) {
        Book book = searchBook(keyword);

        if (book != null && !book.isAvailable()) {
            book.addReservation(member);
            return true;
        }

        return false;
    }

    // Return a book
    public boolean returnBook(String keyword) {
        Book book = searchBook(keyword);

        if (book != null && !book.isAvailable()) {

            if (book.hasReservations()) {
                Member nextMember = book.getNextReservation();
                book.borrow();

                System.out.println("Book assigned to next member in queue: "
                        + nextMember.getName());
            } else {
                book.returnBook();
                recentlyReturnedBooks.push(book);
            }

            return true;
        }

        return false;
    }
}