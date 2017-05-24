import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * A class containing date which later on will be used to create a different date objects.
 * @author Catalin Udrea
 * @version 1.0
 */
public class DateHandler implements Serializable {

    private int day;
    private int month;
    private int year;

    /**
     * Constructor initializing DateHandler
     * @param day  for initializing the constructor.
     * @param month  for initializing the constructor.
     * @param year  for initializing the constructor.
     */
    public DateHandler(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Get day for all other classes that require DateHandler.
     * @return day Day for all other classes that require DateHandler.
     */
    public int getDay() {
        return day;
    }

    /**
     * Set day for all other classes that require DateHandler.
     * @param day takes day for all other classes that require DateHandler.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Get month for all other classes that require DateHandler.
     * @return month Month for all other classes that require DateHandler.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Set month for all other classes that require DateHandler.
     * @param month takes month for all other classes that require DateHandler.
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Get year for all other classes that require DateHandler.
     * @return year Year for all other classes that require DateHandler.
     */
    public int getYear() {
        return year;
    }

    /**
     * Set year for all other classes that require DateHandler.
     * @param year takes year for all other classes that require DateHandler.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Checks if a date is before another date.
     * @param date for comparing
     * @return true or false.  if the date number 1 is before date number 2 will return true, else will return false.
     */
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

//    public boolean isBeforeRange(DateHandler first, DateHandler second){
//        SimpleDateFormat g = new SimpleDateFormat("dd/MM/yyyy");
//
//        try{
//            Date firstDate = g.parse(first.toString());
//            Date secondDate = g.parse(second.toString());
//            return firstDate.before(secondDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

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

    /**
     * Get today's date.
     * @return today's date.
     */
    public DateHandler currentDate() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);

        return new DateHandler(day, month, year);
    }
    /**
     * check if a date is equal to another date.
     *
     * @param obj Object for comparing
     * @return true or false. if the date is equal after comparing with obj will return true, else will return false.
     */
    public boolean equals(Object obj) {
        if(!(obj instanceof DateHandler)) {
            return false;
        }

        DateHandler other = (DateHandler) obj;
        if(other.day != day) return false;
        if(other.month != month) return false;
        return other.year == year;
    }
    /**
     * Get copy of DateHandler
     * @return DateHandler DateHandler copy
     */
    public DateHandler copy() {
        return new DateHandler(day, month, year);
    }
    /**
     * return a String
     *
     * @return String containing day, month and year.
     */
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
