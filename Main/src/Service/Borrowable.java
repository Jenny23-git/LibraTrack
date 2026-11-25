package Service;

/**
 * Defines the borrowing and returning operations for library items.
 */
public interface Borrowable {

    /**
     * Borrows an available library item.
     */
    void borrow();

    /**
     * Returns a borrowed library item.
     */
    void returnBook();

}