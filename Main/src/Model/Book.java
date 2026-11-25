package Model;

import Service.Searchable;
import Service.Borrowable;
import java.util.LinkedList;
import java.util.Queue;


public class Book implements Searchable, Borrowable {

    // Private fields (Encapsulation)
    private String title;
    private String author;
    private String isbn;
    private boolean available;
    private Queue<Member> reservationQueue = new LinkedList<>();


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

    // Add a member to the reservation waitlist
    public void addReservation(Member member) {
        reservationQueue.offer(member);
    }

    // Get and remove the next member in the queue
    public Member getNextReservation() {
        return reservationQueue.poll();
    }

    // Check whether the book has reservations
    public boolean hasReservations() {
        return !reservationQueue.isEmpty();
    }

    // Get the number of members waiting
    public int getReservationCount() {
        return reservationQueue.size();
    }

    // toString method
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
