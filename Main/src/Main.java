import java.util.Scanner;

public class Main {

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

    static int searchBook(String[] titles,String search){

        int found = -1;

        for (int i = 0; i < titles.length; i++) {

            if (titles[i].equalsIgnoreCase(search)) {

                found = i;
                break;
            }
        }

        return found;
    }


        // search and display book

  static void searchByTitle (LibraryCatalog catalog) {

        System.out.print("\nEnter book title to search: ");
         String search = scanner.nextLine();

         int found = searchBook(catalog.titles, search);

        if (found != -1) {

           System.out.println("Book found: " + catalog.titles[found]);
           System.out.println("Author: " + catalog.authors[found]);
           System.out.println("ISBN: " + catalog.isbns[found]);

       } else {

            System.out.println("Book not found.");
      }
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

    int bookIndex = searchBook(catalog.titles, book);

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

        LibraryCatalog catalog = new LibraryCatalog(
                titles, authors, isbns, available
        );


        // Display catalog
        printcatalog(catalog);

        // Add a new book
        catalog = addBook(catalog);

        // Search for a book
        searchByTitle(catalog);

        // Checkout / Return
        checkout(catalog);

        // Display updated catalog
        printcatalog(catalog);
    }
}





