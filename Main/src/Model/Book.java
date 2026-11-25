package Model;

import Service.Searchable;
import Service.Borrowable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a book in the library system.
 * Stores book information, manages availability, and handles reservations.
 *
 * @author LibraTrack Team
 */
public class Book implements Searchable, Borrowable {

    // Private fields (Encapsulation)
    private String title;
    private String author;
    private String isbn;
    private boolean available;
    private Queue<Member> reservationQueue = new LinkedList<>();

    /**
     * Creates a new book with the given details.
     *
     * @param title the title of the book
     * @param author the author of the book
     * @param isbn the ISBN number of the book
     * @param available whether the book is available
     */
    public Book(String title, String author, String isbn, boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

    /**
     * Gets the book title.
     * @return the book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the book author.
     * @return the book author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the ISBN number.
     * @return the ISBN number
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Checks whether the book is available.
     * @return true if available, otherwise false
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Updates the book title.
     * @param title the new book title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Updates the book author.
     * @param author the new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Updates the ISBN number.
     * @param isbn the new ISBN number
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Updates the book availability.
     * @param available the new availability status
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Checks whether the book matches a search keyword.
     *
     * @param keyword the search keyword
     * @return true if the title, author, or ISBN matches
     */
    @Override
    public boolean matches(String keyword) {
        return title.equalsIgnoreCase(keyword)
                || author.equalsIgnoreCase(keyword)
                || isbn.equalsIgnoreCase(keyword);
    }

    /**
     * Marks the book as borrowed if it is available.
     */
    @Override
    public void borrow() {
        if (available) {
            available = false;
        }
    }

    /**
     * Marks the book as available after it is returned.
     */
    @Override
    public void returnBook() {
        available = true;
    }

    /**
     * Adds a member to the reservation waiting queue.
     *
     * @param member the member making the reservation
     */
    public void addReservation(Member member) {
        reservationQueue.offer(member);
    }

    /**
     * Retrieves and removes the next member in the reservation queue.
     *
     * @return the next member, or null if the queue is empty
     */
    public Member getNextReservation() {
        return reservationQueue.poll();
    }

    /**
     * Checks whether the book has pending reservations.
     *
     * @return true if reservations exist, otherwise false
     */
    public boolean hasReservations() {
        return !reservationQueue.isEmpty();
    }

    /**
     * Gets the number of members waiting for the book.
     *
     * @return the reservation queue size
     */
    public int getReservationCount() {
        return reservationQueue.size();
    }

    /**
     * Returns a string representation of the book.
     *
     * @return the book details as a string
     */
    @Override
    public String toString() {
        return "Model.Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", available=" + available +
                '}';
    }
}