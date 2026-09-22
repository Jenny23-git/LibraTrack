import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        //  Parallel arrays
        String[] titles = {"The Alchemist", "Atomic Habits", "Pride and Prejudice"};
        String[] authors = {"Paulo Coelho", "James Clear", "Jane Austen"};
        String[] isbns = {"978-0-062-31123-6", "978-0-735-21129-2", "978-0-141-43951-8"};
        boolean[] available = {true, false, true};

        Scanner scanner = new Scanner(System.in);

        // Add a new book
        System.out.print("Enter a new book title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter author: ");
        String newAuthor = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String newIsbn = scanner.nextLine();

        String[] tempTitles = new String[titles.length + 1];
        String[] tempAuthors = new String[authors.length + 1];
        String[] tempIsbns = new String[isbns.length + 1];
        boolean[] tempAvailable = new boolean[available.length + 1];

        for (int i = 0; i < titles.length; i++) {
            tempTitles[i] = titles[i];
            tempAuthors[i] = authors[i];
            tempIsbns[i] = isbns[i];
            tempAvailable[i] = available[i];
        }

        int index = titles.length;
        tempTitles[index] = newTitle;
        tempAuthors[index] = newAuthor;
        tempIsbns[index] = newIsbn;
        tempAvailable[index] = true;

        titles = tempTitles;
        authors = tempAuthors;
        isbns = tempIsbns;
        available = tempAvailable;

        // Search for a book
        System.out.print("\nEnter book title to search: ");
        String search = scanner.nextLine();

        int found = -1;

        for (int i = 0; i < titles.length; i++) {
            if (titles[i].equalsIgnoreCase(search)) {
                found = i;
                break;
            }
        }

        if (found != -1) {
            System.out.println("Book found: " + titles[found]);
            System.out.println("Author: " + authors[found]);
            System.out.println("ISBN: " + isbns[found]);
        } else {
            System.out.println("Book not found.");
        }

        // Print full catalog
        System.out.println("\n===== LIBRARY CATALOG =====");

        for (int i = 0; i < titles.length; i++) {
            System.out.println("\nBook " + (i + 1));
            System.out.println("Title: " + titles[i]);
            System.out.println("Author: " + authors[i]);
            System.out.println("ISBN: " + isbns[i]);

            if (available[i]) {
                System.out.println("Status: Available");
            } else {
                System.out.println("Status: Checked Out");
            }
        }

        //  Checkout / Return
        System.out.print("Enter book title to checkout/return: ");
        String book = scanner.nextLine();

        int bookIndex = -1;

        for (int i = 0; i < titles.length; i++) {
            if (titles[i].equalsIgnoreCase(book)) {
                bookIndex = i;
                break;
            }
        }

        if (bookIndex != -1) {
            if (available[bookIndex]) {
                available[bookIndex] = false;
                System.out.println("Book checked out.");
            } else {
                available[bookIndex] = true;
                System.out.println("Book returned.");
            }
        } else {
            System.out.println("Book not found.");
        }

        scanner.close();
    }
}
