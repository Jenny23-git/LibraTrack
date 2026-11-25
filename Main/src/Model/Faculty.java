package Model;

/**
 * Represents a faculty library member.
 * Faculty members have a borrowing limit of five books.
 */
public class Faculty extends Member {

    /**
     * Creates a new faculty member.
     *
     * @param memberId the unique ID of the faculty member
     * @param name the faculty member's name
     */
    public Faculty(String memberId, String name) {
        super(memberId, name);
    }

    /**
     * Gets the borrowing limit for a faculty member.
     *
     * @return the maximum number of books a faculty member can borrow (5)
     */
    @Override
    public int getBorrowLimit() {
        return 5;
    }
}