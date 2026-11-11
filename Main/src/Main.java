import java.util.Scanner;
import java.util.function.Predicate;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.HashMap;
import java.util.Map;

public class Main {

    // STEP 1: Book Class goes here
    static class Book {
        String title;
        String author;
        String isbn;
        boolean available;

        Book(String title, String author, String isbn, boolean available) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.available = available;
        }
    }

    static Scanner scanner = new Scanner(System.in);

        // library Catalog

    static class LibraryCatalog {

        String[] titles;
        String[] authors;
        String[] isbns;
        boolean[] available;

        LibraryCatalog(String[] titles, String[] authors,
                       String[] isbns, boolean[] available) {

            this.titles = titles;
            this.authors = authors;
            this.isbns = isbns;
            this.available = available;
        }
    }

         // add book

    static LibraryCatalog addBook(LibraryCatalog catalog){

        System.out.print("Enter a new book title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter author: ");
        String newAuthor = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String newIsbn = scanner.nextLine();

        String[] tempTitles = new String[catalog.titles.length + 1];
        String[] tempAuthors = new String[catalog.authors.length + 1];
        String[] tempIsbns = new String[catalog.isbns.length + 1];
        boolean[] tempAvailable = new boolean[catalog.available.length + 1];


        // copy existing books

        for (int i = 0; i < catalog.titles.length; i++) {

            tempTitles[i] = catalog.titles[i];
            tempAuthors[i] = catalog.authors[i];
            tempIsbns[i] = catalog.isbns[i];
            tempAvailable[i] = catalog.available[i];
        }

        //Add new book

        int index = catalog.titles.length;

        tempTitles[index] = newTitle;
        tempAuthors[index] = newAuthor;
        tempIsbns[index] = newIsbn;
        tempAvailable[index] = true;

        System.out.println("Book added successfully");

        return new LibraryCatalog(
                tempTitles,
                tempAuthors,
                tempIsbns,
                tempAvailable
        );
    }


        // linear Search for a book

    // Generic Search Method using Predicate
    static int searchBook(LibraryCatalog catalog, Predicate<Book> condition) {

        for (int i = 0; i < catalog.titles.length; i++) {

            Book book = new Book(
                    catalog.titles[i],
                    catalog.authors[i],
                    catalog.isbns[i],
                    catalog.available[i]
            );

            if (condition.test(book)) {
                return i;
            }
        }

        return -1;
    }

        // search and display book

  static void searchByTitle (LibraryCatalog catalog) {

        System.out.print("\nEnter book title to search: ");
         String search = scanner.nextLine();

      int found = searchBook(catalog, book ->
              book.title.equalsIgnoreCase(search)
      );

        if (found != -1) {

           System.out.println("Book found: " + catalog.titles[found]);
           System.out.println("Author: " + catalog.authors[found]);
           System.out.println("ISBN: " + catalog.isbns[found]);

       } else {

            System.out.println("Book not found.");
      }
  }


    // Display available books using Streams

    static void displayAvailableBooks(LibraryCatalog catalog) {

        System.out.println("\nAVAILABLE BOOKS (SORTED BY TITLE)");

        IntStream.range(0, catalog.titles.length)

                // Filter books that are available
                .filter(i -> catalog.available[i])

                // Sort books alphabetically by title
                .boxed()
                .sorted(Comparator.comparing(
                        (Integer i) -> catalog.titles[i],
                        String.CASE_INSENSITIVE_ORDER
                ))

                // Display available books
                .forEach(i -> {
                    System.out.println("\nTitle: " + catalog.titles[i]);
                    System.out.println("Author: " + catalog.authors[i]);
                    System.out.println("ISBN: " + catalog.isbns[i]);
                });
    }

        // Print full catalog

static void printcatalog(LibraryCatalog catalog) {

    System.out.println("\nLIBRARY CATALOG");

    for (int i = 0; i < catalog.titles.length; i++) {

        System.out.println("\nBook " + (i + 1));
        System.out.println("Title: " + catalog.titles[i]);
        System.out.println("Author: " + catalog.authors[i]);
        System.out.println("ISBN: " + catalog.isbns[i]);

        if (catalog.available[i]) {

            System.out.println("Status: Available");

        } else {

            System.out.println("Status: Checked Out");
        }
    }
}

             // Checkout/ Return

static void checkout(LibraryCatalog catalog) {

    System.out.print("\nEnter book title to checkout/return: ");
    String book = scanner.nextLine();

    int bookIndex = searchBook(catalog, bookItem ->
            bookItem.title.equalsIgnoreCase(book)
    );

    if (bookIndex != -1) {

        if (catalog.available[bookIndex]) {

                  catalog.available[bookIndex] = false;

                  System.out.println("Book checked out.");

        } else {

                  catalog.available[bookIndex] = true;

                  System.out.println("Book returned.");
              }

    } else {

            System.out.println("Book not found.");
            }
    }



    public static void main(String[] args) {

        //  Parallel arrays

        String[] titles = {"The Alchemist", "Atomic Habits", "Pride and Prejudice"};
        String[] authors = {"Paulo Coelho", "James Clear", "Jane Austen"};
        String[] isbns = {"978-0-062-31123-6", "978-0-735-21129-2", "978-0-141-43951-8"};
        boolean[] available = {true, false, true};

        // create Library Catalog

        LibraryCatalog[] catalog = {
                new LibraryCatalog(titles, authors, isbns, available)
        };

        // Create menu using Map and Runnable
        Map<String, Runnable> menu = new HashMap<>();

        menu.put("1", () -> printcatalog(catalog[0]));
        menu.put("2", () -> catalog[0] = addBook(catalog[0]));
        menu.put("3", () -> searchByTitle(catalog[0]));
        menu.put("4", () -> checkout(catalog[0]));
        menu.put("5", () -> displayAvailableBooks(catalog[0]));
        menu.put("6", () -> System.out.println("Thank you for using LibraTrack!"));

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





