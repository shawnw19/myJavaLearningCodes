package DateRecord_RecordImplementation;

//based on book exp. 1.10 pp22
//Programming Language Processors in Java
//implement record data type

public class Date {
    private Date today;
    private int month=0, day=0;
    private int [] days ={31,28,31,30,31,30,
                    31,31,30,31,30,31};

    public Date(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public Date(Date today) {
        this.today = today;
        if (today.day<days[today.month-1]){
            month = today.month;
            day = today.day+1;
        }else
        if ((today.month)/12!=0){
            month = (today.month+1)%12;
            day =1;
        }else {
            month=1;
            day=1;
        }
        if (today.month==2 && today.day==29){
            month=3;
            day=1;
        }
    }


    @Override
    public String toString() {
        return "Date{" +
                "month=" + month +
                ", day=" + day +
                '}';
    }
}
