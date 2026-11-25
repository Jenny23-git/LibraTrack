package Model;

/**
 * Represents a library transaction.
 * Stores information about the book, transaction type, and date.
 */
public class Transaction {

    private Book book;
    private String transactionType;
    private String transactionDate;

    /**
     * Creates a new transaction.
     *
     * @param book the book involved in the transaction
     * @param transactionType the type of transaction
     * @param transactionDate the date of the transaction
     */
    public Transaction(Book book, String transactionType, String transactionDate) {
        this.book = book;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the book involved in the transaction.
     *
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Gets the transaction type.
     *
     * @return the transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Gets the transaction date.
     *
     * @return the transaction date
     */
    public String getTransactionDate() {
        return transactionDate;
    }
}