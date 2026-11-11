import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Predicate;

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


        // linear Search for a book

    // Generic Search Method using Predicate
    static Book searchBook(Library library, Predicate<Book> condition) {

        for (Book book : library.getBooks()) {

            if (condition.test(book)) {
                return book;
            }
        }

        return null;
    }

        // search and display book

    static void searchByTitle(Library library) {

        System.out.print("\nEnter book title to search: ");
        String search = scanner.nextLine();

        Book found = searchBook(library, book ->
                book.matches(search)
        );
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

        Book found = searchBook(library, book ->
                book.getTitle().equalsIgnoreCase(bookTitle)
        );

        if (found.isAvailable()) {

            found.borrow();
            System.out.println("Book checked out.");

        } else {

            found.returnBook();
            System.out.println("Book returned.");
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

        // Menu-driven loop
        String choice = "";

        while (!choice.equals("6")) {

            System.out.println("\n===== LIBRATRACK MENU =====");
            System.out.println("1. Display all books");
            System.out.println("2. Add a book");
            System.out.println("3. Search for a book");
            System.out.println("4. Checkout / Return a book");
            System.out.println("5. Display available books (sorted)");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            Runnable action = menu.get(choice);

            if (action != null) {
                action.run();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }



    }
}





