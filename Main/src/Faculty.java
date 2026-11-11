public class Faculty extends Member {

    public Faculty(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public int getBorrowLimit() {
        return 5;
    }
}