import java.io.Serializable;

/**
 * @author Catalin Udrea
 * @version 1.0
 */
public class Departure implements Serializable {
    private DateHandler checkOutDate;
    /**
     * Constructor initializing Departure.
     * @param checkOutDate for initializing the constructor.
     */
    public Departure(DateHandler checkOutDate){
        this.checkOutDate = checkOutDate;
    }
    /**
     * Get check out date for reservation.
     * @return checkOutDate for reservation.
     */

    public DateHandler getCheckOutDate() {
        return checkOutDate;
    }
    /**
     * Set check out date for reservation
     * @param checkOutDate takes check out date for reservation.
     */
    public void setCheckOutDate(DateHandler checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    /**
     * Get copy of Departure
     * @return checkOutDate Departure copy.
     */
    public Departure copy(){
        return new Departure(checkOutDate);
    }
    /**
     * check if a departure is equal to another departure.
     *
     * @param obj Object for comparing
     * @return true or false. if the whole departure is equal after comparing with obj will return true, else will return false.
     */
    public boolean equals(Object obj) {
        if(!(obj instanceof Departure)) return false;

        Departure arrival = (Departure) obj;

        return checkOutDate.equals(arrival.checkOutDate);
    }
    /**
     * return a String
     *
     * @return String containing check out date.
     */
    public String toString() {
        return "Departure{" +
                "checkOutDate=" + checkOutDate +
                '}';
    }
}

