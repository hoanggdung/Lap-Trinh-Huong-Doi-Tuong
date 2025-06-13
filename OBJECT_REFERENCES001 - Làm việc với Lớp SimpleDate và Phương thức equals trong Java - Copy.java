class SimpleDate {
    private int day, month, year;

    public SimpleDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // cùng tham chiếu
        if (obj == null || getClass() != obj.getClass()) return false;
        SimpleDate other = (SimpleDate) obj;
        return this.day == other.day && this.month == other.month && this.year == other.year;
    }
}

public class Main {
    public static void main(String[] args) {
        SimpleDate givenDate = new SimpleDate(1, 2, 2000);

        System.out.println(givenDate.equals("heh")); // false, khác kiểu
        System.out.println(givenDate.equals(new SimpleDate(5, 2, 2012))); // false, khác ngày
        System.out.println(givenDate.equals(new SimpleDate(1, 2, 2000))); // true, cùng ngày
    }
}
