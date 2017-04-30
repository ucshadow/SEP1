public class Departure {
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

    public String toString() {
        return "Departure{" +
                "checkOutDate=" + checkOutDate +
                '}';
    }
}

