public class Transaction {

    private Book book;
    private String transactionType;
    private String transactionDate;


    public Transaction(Book book, String transactionType, String transactionDate) {
        this.book = book;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public Book getBook() {
        return book;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }
}
