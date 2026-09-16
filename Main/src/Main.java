import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        //  Parallel arrays
        String[] titles = {"Eloquent_JavaScript", "Web Development", "Database Systems"};
        String[] authors = {"John Smith", "Mary Jones", "David Brown"};
        String[] isbns = {"1111", "2222", "3333"};
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


    }
}
