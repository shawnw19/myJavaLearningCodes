package DateRecord_RecordImplementation;

public class DateTester {
    public static void main(String[] args) {
        Date today = new Date(2,29);
        Date date = new Date(today);
        System.out.println("Calibrated date1: ");
        System.out.println(date.toString());

        Date date2 = new Date(new Date(4,30));
        System.out.println("Calibrated date2: ");
        System.out.println(date2.toString());

        Date date3 = new Date(new Date(12,10));
        System.out.println("Calibrated date3: ");
        System.out.println(date3.toString());

        Date date4 = new Date(new Date(12,31));
        System.out.println("Calibrated date4: ");
        System.out.println(date4.toString());
    }
}
