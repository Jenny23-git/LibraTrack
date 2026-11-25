import Model.Book;
import Repository.Library;
import Model.Member;



public class LibraryTest {


    public void testSearchBook() {

        Library library = new Library();

        Book book = new Book(
                "The Alchemist",
                "Paulo Coelho",
                "978-0-062-31123-6",
                true
        );

        library.addBook(book);
        Book foundBook = library.searchBook("The Alchemist");

        if (foundBook != null) {
            System.out.println("PASS: Book search test successful!");
        } else {
            System.out.println("FAIL: Book was not found.");
        }
    }

    public void testCheckoutBook() {

        Library library = new Library();

        Book book = new Book(
                "The Alchemist",
                "Paulo Coelho",
                "978-0-062-31123-6",
                true
        );

        Member member = new Member("M001", "Janet");

        library.addBook(book);
        library.addMember(member);

        boolean result = library.borrowBook("The Alchemist", member);

        if (result) {
            System.out.println("PASS: Book checkout test successful!");
        } else {
            System.out.println("FAIL: Book checkout test failed.");
        }
    }

    public void testReserveBook() {

        Library library = new Library();

        Book book = new Book(
                "The Alchemist",
                "Paulo Coelho",
                "978-0-062-31123-6",
                true
        );

        Member member = new Member("M002", "Janet");

        library.addBook(book);
        library.addMember(member);

        library.borrowBook("The Alchemist", member);

        boolean result = library.reserveBook("The Alchemist", member);

        if (result) {
            System.out.println("PASS: Book reservation test successful!");
        } else {
            System.out.println("FAIL: Book reservation test failed.");
        }
    }

    public static void main(String[] args) {
        LibraryTest test = new LibraryTest();
        test.testSearchBook();
    }
}
