package Service;

import Model.Book;
import Model.Member;

import java.util.List;
import java.util.Deque;

public interface LibraryService {

        void addBook(Book book);

        void addMember(Member member);

        List<Member> getMembers();

        List<Book> getBooks();

        List<Book> getBooksSortedByTitle();

        Deque<Book> getRecentlyReturnedBooks();

        Book searchBook(String keyword);

        boolean borrowBook(String keyword, Member member);

        boolean returnBook(String keyword, Member member);

        boolean reserveBook(String keyword, Member member);

}
