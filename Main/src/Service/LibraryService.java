package Service;

import Model.Book;
import Model.Member;

import java.util.List;
import java.util.Deque;

/**
 * Defines the main operations available in the library management system.
 * Provides methods for managing books, members, borrowing, returning,
 * searching, and reserving books.
 */
public interface LibraryService {

        /**
         * Adds a new book to the library.
         *
         * @param book the book to add
         */
        void addBook(Book book);

        /**
         * Adds a new member to the library.
         *
         * @param member the member to add
         */
        void addMember(Member member);

        /**
         * Retrieves all registered library members.
         *
         * @return a list of library members
         */
        List<Member> getMembers();

        /**
         * Retrieves all books in the library.
         *
         * @return a list of books
         */
        List<Book> getBooks();

        /**
         * Retrieves books sorted alphabetically by title.
         *
         * @return a sorted list of books
         */
        List<Book> getBooksSortedByTitle();

        /**
         * Retrieves recently returned books.
         *
         * @return a deque containing recently returned books
         */
        Deque<Book> getRecentlyReturnedBooks();

        /**
         * Searches for a book using a keyword.
         *
         * @param keyword the search keyword
         * @return the matching book, or null if not found
         */
        Book searchBook(String keyword);

        /**
         * Borrows a book for a library member.
         *
         * @param keyword the book search keyword
         * @param member the member borrowing the book
         * @return true if borrowing succeeds, otherwise false
         */
        boolean borrowBook(String keyword, Member member);

        /**
         * Returns a borrowed book.
         *
         * @param keyword the book search keyword
         * @param member the member returning the book
         * @return true if returning succeeds, otherwise false
         */
        boolean returnBook(String keyword, Member member);

        /**
         * Reserves an unavailable book for a library member.
         *
         * @param keyword the book search keyword
         * @param member the member making the reservation
         * @return true if reservation succeeds, otherwise false
         */
        boolean reserveBook(String keyword, Member member);

}