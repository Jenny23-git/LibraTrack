package App;

import Model.*;
import Repository.Library;
import Service.LibraryService;
import Service.LibraryServiceImpl;

import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;


public class Main {


    static Scanner scanner = new Scanner(System.in);

         // add book
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



        // search and display book

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


    // Display available books using Streams

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

        // Print full catalog

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

    // Display recently returned books
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

    // Display books sorted alphabetically by title
    static void displaySortedCatalog(LibraryService library) {
        System.out.println("\nCATALOG SORTED BY TITLE");

        for (Book book : library.getBooksSortedByTitle()) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("--------------------");
        }
    }

             // Checkout/ Return

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

        System.out.print("Enter book title to checkout/return: ");
        String bookTitle = scanner.nextLine();

        Book found = library.searchBook(bookTitle);

        if (found == null) {
            System.out.println("Model.Book not found.");
        } else if (found.isAvailable()) {

            if (library.borrowBook(bookTitle, selectedMember)) {
                System.out.println("Model.Book checked out.");
            }

        } else {

            if (library.returnBook(bookTitle, selectedMember)) {
                System.out.println("Model.Book returned.");
            }
        }
    }

    static void reserveBook(LibraryService library) {
        System.out.print("Enter member ID: ");
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

        System.out.print("Enter book title to reserve: ");
        String title = scanner.nextLine();

        if (library.reserveBook(title, selectedMember)) {
            System.out.println("Reservation added successfully.");
        } else {
            System.out.println("Model.Book not found or currently available.");
        }
    }



    // Register a library member
    static void registerMember(LibraryService library) {

        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter member name: ");
        String name = scanner.nextLine();

        System.out.println("Select member type:");
        System.out.println("1. Model.Student");
        System.out.println("2. Model.Faculty");
        System.out.print("Enter choice: ");

        String type = scanner.nextLine();

        Member member;

        if (type.equals("1")) {
            member = new Student(memberId, name);
        } else if (type.equals("2")) {
            member = new Faculty(memberId, name);
        } else {
            System.out.println("Invalid member type.");
            return;
        }

        library.addMember(member);

        System.out.println("Model.Member registered successfully!");
        System.out.println("Borrowing limit: " + member.getBorrowLimit());
    }

    // Display registered members
    static void displayMembers(LibraryService library) {

        System.out.println("\nREGISTERED LIBRARY MEMBERS");

        for (Member member : library.getMembers()) {
            System.out.println("Model.Member ID: " + member.getMemberId());
            System.out.println("Name: " + member.getName());
            System.out.println("Borrowing Limit: " + member.getBorrowLimit());
            System.out.println("------------------------");
        }
    }

    // Display a member's borrowing history
    static void displayBorrowHistory(LibraryService library) {

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

        System.out.println("\nBORROWING HISTORY FOR: "
                + selectedMember.getName());

        if (selectedMember.getBorrowHistory().isEmpty()) {
            System.out.println("No transaction history found.");
            return;
        }

        for (Transaction transaction : selectedMember.getBorrowHistory()) {
            System.out.println("Model.Book: " + transaction.getBook().getTitle());
            System.out.println("Model.Transaction: " + transaction.getTransactionType());
            System.out.println("Date: " + transaction.getTransactionDate());
            System.out.println("--------------------");
        }
    }

    public static void main(String[] args) {

        // Create LibraryService using its implementation
        LibraryService library = new LibraryServiceImpl(new Library());

        // Add initial books
        library.addBook(new Book(
                "The Alchemist", "Paulo Coelho",
                "978-0-062-31123-6", true
        ));

        library.addBook(new Book(
                "Atomic Habits", "James Clear",
                "978-0-735-21129-2", false
        ));

        library.addBook(new Book(
                "Pride and Prejudice", "Jane Austen",
                "978-0-141-43951-8", true
        ));

        // Create menu using Map and Runnable
        Map<String, Runnable> menu = new HashMap<>();

        menu.put("1", () -> printcatalog(library));
        menu.put("2", () -> addBook(library));
        menu.put("3", () -> searchByTitle(library));
        menu.put("4", () -> checkout(library));
        menu.put("5", () -> displayAvailableBooks(library));
        menu.put("6", () -> registerMember(library));
        menu.put("7", () -> displayMembers(library));
        menu.put("9", () -> reserveBook(library));
        menu.put("10", () -> displayRecentlyReturnedBooks(library));
        menu.put("11", () -> displaySortedCatalog(library));
        menu.put("12", () -> displayBorrowHistory(library));

        // Menu-driven loop
        String choice = "";

        while (!choice.equals("8")) {

            System.out.println("\n===== LIBRATRACK MENU =====");
            System.out.println("1. Display all books");
            System.out.println("2. Add a book");
            System.out.println("3. Search for a book");
            System.out.println("4. Checkout / Return a book");
            System.out.println("5. Display available books (sorted)");
            System.out.println("6. Register a member");
            System.out.println("7. Display registered members");
            System.out.println("8. Exit");
            System.out.println("9. Reserve a Model.Book");
            System.out.println("10. Recently Returned Books");
            System.out.println("11. Display Books Sorted by Title");
            System.out.println("12. Display Model.Member Borrowing History");

            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            Runnable action = menu.get(choice);

            if (choice.equals("8")) {
                System.out.println("Thank you for using LibraTrack!");
            } else if (action != null) {
                action.run();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }



    }
}





