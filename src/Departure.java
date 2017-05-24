import java.io.Serializable;

public class Departure implements Serializable {
    private DateHandler checkOutDate;

    public Departure(DateHandler checkOutDate){
        this.checkOutDate = checkOutDate;
    }

    public DateHandler getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(DateHandler checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Departure copy(){
        return new Departure(checkOutDate);
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Departure)) return false;

        Departure arrival = (Departure) obj;

        return checkOutDate.equals(arrival.checkOutDate);
    }

    public String toString() {
        return checkOutDate.toString();
    }
}

