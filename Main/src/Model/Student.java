package Model;

/**
 * Represents a student library member.
 * Students have a borrowing limit of three books.
 */
public class Student extends Member {

    /**
     * Creates a new student member.
     *
     * @param memberId the unique ID of the student
     * @param name the student's name
     */
    public Student(String memberId, String name) {
        super(memberId, name);
    }

    /**
     * Gets the borrowing limit for a student.
     *
     * @return the maximum number of books a student can borrow (3)
     */
    @Override
    public int getBorrowLimit() {
        return 3;
    }
}