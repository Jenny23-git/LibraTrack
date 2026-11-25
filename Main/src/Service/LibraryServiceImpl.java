package Service;

import Model.Book;
import Model.Member;
import Repository.Library;

import java.util.List;
import java.util.Deque;

public class LibraryServiceImpl implements LibraryService {

    private final Library library;

    public LibraryServiceImpl(Library library) {
        this.library = library;
    }

    @Override
    public void addBook(Book book) {
        library.addBook(book);
    }

    @Override
    public void addMember(Member member) {
        library.addMember(member);
    }

    @Override
    public List<Member> getMembers() {
        return library.getMembers();
    }

    @Override
    public List<Book> getBooks() {
        return library.getBooks();
    }

    @Override
    public List<Book> getBooksSortedByTitle() {
        return library.getBooksSortedByTitle();
    }

    @Override
    public Deque<Book> getRecentlyReturnedBooks() {
        return library.getRecentlyReturnedBooks();
    }

    @Override
    public Book searchBook(String keyword) {
        return library.searchBook(keyword);
    }

    @Override
    public boolean borrowBook(String keyword, Member member) {
        return library.borrowBook(keyword, member);
    }

    @Override
    public boolean returnBook(String keyword, Member member) {
        return library.returnBook(keyword, member);
    }

    @Override
    public boolean reserveBook(String keyword, Member member) {
        return library.reserveBook(keyword, member);
    }
}
