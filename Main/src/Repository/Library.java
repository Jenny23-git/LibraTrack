package Repository;

import Model.Book;
import Model.Member;
import Model.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.TreeMap;
import java.time.LocalDate;

/**
 * Manages the library's books, members, borrowing, returning,
 * reservations, and recently returned books.
 */
public class Library {

    private Map<String, Book> books;
    private List<Member> members;
    private Deque<Book> recentlyReturnedBooks;
    private Map<String, Book> booksByTitle;

    /**
     * Initializes the library collections.
     */
    public Library() {
        books = new HashMap<>();
        members = new ArrayList<>();
        recentlyReturnedBooks = new ArrayDeque<>();
        booksByTitle = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    /**
     * Adds a new book to the library.
     *
     * @param book the book to add
     */
    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            System.out.println("A book with this ISBN already exists.");
            return;
        }

        books.put(book.getIsbn(), book);
        booksByTitle.put(book.getTitle(), book);
    }

    /**
     * Registers a new library member.
     *
     * @param member the member to register
     */
    public void addMember(Member member) {
        members.add(member);
    }

    /**
     * Retrieves all registered members.
     *
     * @return a list of library members
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * Retrieves all books in the library.
     *
     * @return a list containing all books
     */
    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    /**
     * Retrieves books sorted alphabetically by title.
     *
     * @return a sorted list of books
     */
    public List<Book> getBooksSortedByTitle() {
        return new ArrayList<>(booksByTitle.values());
    }

    /**
     * Retrieves recently returned books.
     *
     * @return a deque of recently returned books
     */
    public Deque<Book> getRecentlyReturnedBooks() {
        return recentlyReturnedBooks;
    }

    /**
     * Searches for a book using its title, author, or ISBN.
     *
     * @param keyword the search keyword
     * @return the matching book, or null if not found
     */
    public Book searchBook(String keyword) {
        for (Book book : books.values()) {
            if (book.matches(keyword)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Borrows an available book.
     *
     * @param keyword the book search keyword
     * @return true if borrowing succeeds, otherwise false
     */
    public boolean borrowBook(String keyword) {
        Book book = searchBook(keyword);

        if (book != null && book.isAvailable()) {
            book.borrow();
            return true;
        }

        return false;
    }

    /**
     * Borrows a book for a registered member and records the transaction.
     *
     * @param keyword the book search keyword
     * @param member the member borrowing the book
     * @return true if borrowing succeeds, otherwise false
     */
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

    /**
     * Returns a borrowed book and records the transaction.
     * If reservations exist, the book is assigned to the next member.
     *
     * @param keyword the book search keyword
     * @param member the member returning the book
     * @return true if returning succeeds, otherwise false
     */
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

                System.out.println("Model.Book assigned to next member in queue: "
                        + nextMember.getName());
            } else {
                book.returnBook();
                recentlyReturnedBooks.push(book);
            }

            return true;
        }

        return false;
    }

    /**
     * Reserves an unavailable book for a member.
     *
     * @param keyword the book search keyword
     * @param member the member making the reservation
     * @return true if reservation succeeds, otherwise false
     */
    public boolean reserveBook(String keyword, Member member) {
        Book book = searchBook(keyword);

        if (book != null && !book.isAvailable()) {
            book.addReservation(member);
            return true;
        }

        return false;
    }

    /**
     * Returns a borrowed book without recording a member transaction.
     * If reservations exist, the book is assigned to the next member.
     *
     * @param keyword the book search keyword
     * @return true if returning succeeds, otherwise false
     */
    public boolean returnBook(String keyword) {
        Book book = searchBook(keyword);

        if (book != null && !book.isAvailable()) {

            if (book.hasReservations()) {
                Member nextMember = book.getNextReservation();
                book.borrow();

                System.out.println("Model.Book assigned to next member in queue: "
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