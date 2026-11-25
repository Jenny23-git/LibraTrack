package Model;

import java.util.LinkedList;

/**
 * Represents a library member.
 * Stores member information and maintains borrowing history.
 */
public class Member {

    private String memberId;
    private String name;
    private LinkedList<Transaction> borrowHistory = new LinkedList<>();

    /**
     * Creates a new library member.
     *
     * @param memberId the unique ID of the member
     * @param name the name of the member
     */
    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    /**
     * Gets the member's ID.
     *
     * @return the member ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * Gets the member's name.
     *
     * @return the member name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the borrowing limit for this member.
     *
     * @return the borrowing limit
     */
    public int getBorrowLimit() {
        return 0;
    }

    /**
     * Adds a transaction to the member's borrowing history.
     *
     * @param transaction the transaction to add
     */
    public void addTransaction(Transaction transaction) {
        borrowHistory.add(transaction);
    }

    /**
     * Gets the member's borrowing history.
     *
     * @return the list of borrowing transactions
     */
    public LinkedList<Transaction> getBorrowHistory() {
        return borrowHistory;
    }
}