import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHandler implements Serializable {

    private int day;
    private int month;
    private int year;

    public DateHandler(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isBefore(DateHandler date) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date thisDate = f.parse(toString());
            Date other = f.parse(date.toString());
            if(thisDate.before(other)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isBeforeRange(DateHandler first, DateHandler second){
        SimpleDateFormat g = new SimpleDateFormat("dd/MM/yyyy");

        try{
            Date firstDate = g.parse(first.toString());
            Date secondDate = g.parse(second.toString());
            return firstDate.before(secondDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public boolean isAfter(DateHandler date) {
//        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//        try {
//            Date thisDate = f.parse(toString());
//            Date other = f.parse(date.toString());
//            if(thisDate.after(other)) {
//                return true;
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public DateHandler currentDate() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);

        return new DateHandler(day, month, year);
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof DateHandler)) {
            return false;
        }

        DateHandler other = (DateHandler) obj;
        if(other.day != day) return false;
        if(other.month != month) return false;
        return other.year == year;
    }

    public DateHandler copy() {
        return new DateHandler(day, month, year);
    }



    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
