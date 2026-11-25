package Service;

import Model.Book;
import Model.Member;
import Repository.Library;

import java.util.List;
import java.util.Deque;

/**
 * Implements the LibraryService interface.
 * Delegates library operations to the Library repository.
 */
public class LibraryServiceImpl implements LibraryService {

    private final Library library;

    /**
     * Creates a library service using the given repository.
     *
     * @param library the library repository
     */
    public LibraryServiceImpl(Library library) {
        this.library = library;
    }

    /**
     * Adds a book to the library.
     *
     * @param book the book to add
     */
    @Override
    public void addBook(Book book) {
        library.addBook(book);
    }

    /**
     * Adds a member to the library.
     *
     * @param member the member to add
     */
    @Override
    public void addMember(Member member) {
        library.addMember(member);
    }

    /**
     * Retrieves all library members.
     *
     * @return a list of members
     */
    @Override
    public List<Member> getMembers() {
        return library.getMembers();
    }

    /**
     * Retrieves all library books.
     *
     * @return a list of books
     */
    @Override
    public List<Book> getBooks() {
        return library.getBooks();
    }

    /**
     * Retrieves books sorted by title.
     *
     * @return a sorted list of books
     */
    @Override
    public List<Book> getBooksSortedByTitle() {
        return library.getBooksSortedByTitle();
    }

    /**
     * Retrieves recently returned books.
     *
     * @return a deque of recently returned books
     */
    @Override
    public Deque<Book> getRecentlyReturnedBooks() {
        return library.getRecentlyReturnedBooks();
    }

    /**
     * Searches for a book using a keyword.
     *
     * @param keyword the search keyword
     * @return the matching book, or null if not found
     */
    @Override
    public Book searchBook(String keyword) {
        return library.searchBook(keyword);
    }

    /**
     * Borrows a book for a member.
     *
     * @param keyword the book search keyword
     * @param member the member borrowing the book
     * @return true if borrowing succeeds, otherwise false
     */
    @Override
    public boolean borrowBook(String keyword, Member member) {
        return library.borrowBook(keyword, member);
    }

    /**
     * Returns a borrowed book.
     *
     * @param keyword the book search keyword
     * @param member the member returning the book
     * @return true if returning succeeds, otherwise false
     */
    @Override
    public boolean returnBook(String keyword, Member member) {
        return library.returnBook(keyword, member);
    }

    /**
     * Reserves an unavailable book.
     *
     * @param keyword the book search keyword
     * @param member the member making the reservation
     * @return true if reservation succeeds, otherwise false
     */
    @Override
    public boolean reserveBook(String keyword, Member member) {
        return library.reserveBook(keyword, member);
    }
}