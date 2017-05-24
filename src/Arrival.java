import java.io.Serializable;

public class Arrival implements Serializable {
    private DateHandler checkInDate;

    public Arrival(DateHandler checkInDate){
        this.checkInDate = checkInDate;
    }

    public DateHandler getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(DateHandler checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Arrival copy(){
        return new Arrival(checkInDate);
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Arrival)) return false;

        Arrival arrival = (Arrival) obj;

        return checkInDate.equals(arrival.checkInDate);
    }

    public String toString() {
        return checkInDate.toString();
    }
}
