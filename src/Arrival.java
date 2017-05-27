import java.io.Serializable;

/**
 * A class containing check in date which later on will be used to create a reservation.
 * @author Yusuf A Farah
 * @version 1.0
 */
public class Arrival implements Serializable {
    private DateHandler checkInDate;

    /**
     * Constructor initializing Arrival
     *
     * @param checkInDate for initializing inside the constructor.
     */

    public Arrival(DateHandler checkInDate){
        this.checkInDate = checkInDate;
    }

    /**
     * Gets the check in date for the reservation.
     *
     * @return checkInDate, DateHandler representing the check in date of the reservation.
     */

    public DateHandler getCheckInDate() {
        return checkInDate;
    }

    /**
     * Replaces the check in date for the reservation
     *
     * @param checkInDate the check in date to replace with.
     */

    public void setCheckInDate(DateHandler checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Gets copy of Arrival
     *
     * @return checkInDate, a copy of Arrival.
     */

    public Arrival copy(){
        return new Arrival(checkInDate);
    }

    /**
     * Check if an arrival is equal to another.
     *
     * @param obj Object for comparing
     * @return true or false. If the entire arrival is equal with obj, the method will return true, else it returns false.
     */
    public boolean equals(Object obj) {
        if(!(obj instanceof Arrival)) return false;

        Arrival arrival = (Arrival) obj;

        return checkInDate.equals(arrival.checkInDate);
    }
    /**
     * Returns a String
     *
     * @return String, representing the check in date.
     */

    public String toString() {
        return checkInDate.toString();
    }
}
