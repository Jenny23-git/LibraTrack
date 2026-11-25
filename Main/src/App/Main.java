package App;

import Model.*;
import Repository.Library;
import Service.LibraryService;
import Service.LibraryServiceImpl;

import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Main application class for LibraTrack.
 * Provides a menu-driven system for managing books and library members.
 */
public class Main {

    static Scanner scanner = new Scanner(System.in);

    /**
     * Adds a new book to the library using information entered by the user.
     *
     * @param library the library service used to add the book
     */
    static void addBook(LibraryService library) {

        System.out.print("Enter a new book title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter author: ");
        String newAuthor = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String newIsbn = scanner.nextLine();

        Book newBook = new Book(newTitle, newAuthor, newIsbn, true);

        library.addBook(newBook);

        System.out.println("Model.Book added successfully!");
    }

    /**
     * Searches for a book by title and displays its details.
     *
     * @param library the library service used to search for books
     */
    static void searchByTitle(LibraryService library) {

        System.out.print("\nEnter book title to search: ");
        String search = scanner.nextLine();

        Book found = library.searchBook(search);
        if (found != null) {

            System.out.println("Model.Book found: " + found.getTitle());
            System.out.println("Author: " + found.getAuthor());
            System.out.println("ISBN: " + found.getIsbn());

        } else {

            System.out.println("Model.Book not found.");
        }
    }

    /**
     * Displays all available books sorted alphabetically by title.
     *
     * @param library the library service used to retrieve books
     */
    static void displayAvailableBooks(LibraryService library) {

        System.out.println("\nAVAILABLE BOOKS (SORTED BY TITLE)");

        library.getBooks().stream()

                // Filter books that are available
                .filter(Book::isAvailable)

                // Sort books alphabetically by title
                .sorted(Comparator.comparing(
                        Book::getTitle,
                        String.CASE_INSENSITIVE_ORDER
                ))

                // Display available books
                .forEach(book -> {
                    System.out.println("\nTitle: " + book.getTitle());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("ISBN: " + book.getIsbn());
                });
    }

    /**
     * Displays the complete library catalog, including book availability
     * and the number of members waiting for each book.
     *
     * @param library the library service used to retrieve books
     */
    static void printcatalog(LibraryService library) {

        System.out.println("\nLIBRARY CATALOG");

        int count = 1;

        for (Book book : library.getBooks()) {

            System.out.println("\nModel.Book " + count++);
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());

            if (book.isAvailable()) {
                System.out.println("Status: Available");
            } else {
                System.out.println("Status: Checked Out");
            }

            System.out.println("Members Waiting: " + book.getReservationCount());

        }
    }

    /**
     * Displays books that have recently been returned.
     *
     * @param library the library service used to retrieve returned books
     */
    static void displayRecentlyReturnedBooks(LibraryService library) {
        System.out.println("\nRECENTLY RETURNED BOOKS");

        if (library.getRecentlyReturnedBooks().isEmpty()) {
            System.out.println("No recently returned books.");
            return;
        }

        for (Book book : library.getRecentlyReturnedBooks()) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("--------------------");
        }
    }

    /**
     * Displays all books sorted alphabetically by title.
     *
     * @param library the library service used to retrieve sorted books
     */
    static void displaySortedCatalog(LibraryService library) {
        System.out.println("\nCATALOG SORTED BY TITLE");

        for (Book book : library.getBooksSortedByTitle()) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("--------------------");
        }
    }

    /**
     * Allows a registered member to check out or return a book.
     *
     * @param library the library service used for borrowing and returning
     */
    static void checkout(LibraryService library) {

        System.out.print("\nEnter member ID: ");
        String memberId = scanner.nextLine();

        Member selectedMember = null;

        for (Member member : library.getMembers()) {
            if (member.getMemberId().equals(memberId)) {
                selectedMember = member;
                break;
            }
        }

        if (selectedMember == null) {
            System.out.println("Model.Member not found.");
            return;
        }

        System.out.print("Enter book title to checkout/return:");
    }


}