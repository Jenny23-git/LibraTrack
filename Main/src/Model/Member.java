package Model;

import java.util.LinkedList;

public class Member {

    private String memberId;
    private String name;
    private LinkedList<Transaction> borrowHistory = new LinkedList<>();


    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getBorrowLimit() {
        return 0;
    }

    public void addTransaction(Transaction transaction) {
        borrowHistory.add(transaction);
    }

    public LinkedList<Transaction> getBorrowHistory() {
        return borrowHistory;
    }

}