public class Student extends Member {

    public Student(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public int getBorrowLimit() {
        return 3;
    }
}