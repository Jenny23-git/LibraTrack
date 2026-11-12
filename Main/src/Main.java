import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;


public class Main {


    static Scanner scanner = new Scanner(System.in);

         // add book
    static void addBook(Library library) {

        System.out.print("Enter a new book title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter author: ");
        String newAuthor = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String newIsbn = scanner.nextLine();

        Book newBook = new Book(newTitle, newAuthor, newIsbn, true);

        library.addBook(newBook);

        System.out.println("Book added successfully!");
    }



        // search and display book

    static void searchByTitle(Library library) {

        System.out.print("\nEnter book title to search: ");
        String search = scanner.nextLine();

        Book found = library.searchBook(search);
        if (found != null) {

            System.out.println("Book found: " + found.getTitle());
            System.out.println("Author: " + found.getAuthor());
            System.out.println("ISBN: " + found.getIsbn());

        } else {

            System.out.println("Book not found.");
        }
    }


    // Display available books using Streams

    static void displayAvailableBooks(Library library) {

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

    static void printcatalog(Library library) {

        System.out.println("\nLIBRARY CATALOG");

        int count = 1;

        for (Book book : library.getBooks()) {

            System.out.println("\nBook " + count++);
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());

            if (book.isAvailable()) {
                System.out.println("Status: Available");
            } else {
                System.out.println("Status: Checked Out");
            }
        }
    }

             // Checkout/ Return

    static void checkout(Library library) {

        System.out.print("\nEnter book title to checkout/return: ");
        String bookTitle = scanner.nextLine();

        Book found = library.searchBook(bookTitle);

        if (found == null) {
            System.out.println("Book not found.");
        } else if (found.isAvailable()) {

            library.borrowBook(bookTitle);
            System.out.println("Book checked out.");

        } else {

            library.returnBook(bookTitle);
            System.out.println("Book returned.");
        }
    }

    // Register a library member
    static void registerMember(Library library) {

        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter member name: ");
        String name = scanner.nextLine();

        System.out.println("Select member type:");
        System.out.println("1. Student");
        System.out.println("2. Faculty");
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

        System.out.println("Member registered successfully!");
        System.out.println("Borrowing limit: " + member.getBorrowLimit());
    }

    // Display registered members
    static void displayMembers(Library library) {

        System.out.println("\nREGISTERED LIBRARY MEMBERS");

        for (Member member : library.getMembers()) {
            System.out.println("Member ID: " + member.getMemberId());
            System.out.println("Name: " + member.getName());
            System.out.println("Borrowing Limit: " + member.getBorrowLimit());
            System.out.println("------------------------");
        }
    }

    public static void main(String[] args) {

        // Create Library object
        Library library = new Library();

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





