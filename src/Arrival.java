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
     * @param checkInDate for initializing the constructor.
     */
    public Arrival(DateHandler checkInDate){
        this.checkInDate = checkInDate;
    }

    /**
     * Get check in date for reservation.
     * @return checkInDate for reservation.
     */
    public DateHandler getCheckInDate() {
        return checkInDate;
    }

    /**
     * Set check in date for reservation
     * @param checkInDate takes check in date for reservation.
     */
    public void setCheckInDate(DateHandler checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Get copy of Arrival
     * @return checkInDate Arrival copy.
     */
    public Arrival copy(){
        return new Arrival(checkInDate);
    }

    /**
     * check if a arrival is equal to another arrival.
     *
     * @param obj Object for comparing
     * @return true or false. if the whole arrival is equal after comparing with obj will return true, else will return false.
     */
    public boolean equals(Object obj) {
        if(!(obj instanceof Arrival)) return false;

        Arrival arrival = (Arrival) obj;

        return checkInDate.equals(arrival.checkInDate);
    }
    /**
     * return a String
     *
     * @return String containing check in date.
     */
    public String toString() {
        return "Arrival{" +
                "checkInDate=" + checkInDate +
                '}';
    }
}
