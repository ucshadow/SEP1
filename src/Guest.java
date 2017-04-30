public class Guest {
    private GuestData guestData;
    private Arrival arrival;
    private Departure departure;
    private String roomType;
    private boolean bookingInitiator;
    private boolean lateArrivalNotice;
    private boolean priorityGuest;

    public Guest(GuestData guestData, Arrival arrival, Departure departure, String roomType,
                 boolean bookingInitiator, boolean lateArrivalNotice, boolean priorityGuest) {
        this.guestData = guestData;
        this.arrival = arrival;
        this.departure = departure;
        this.roomType = roomType;
        this.bookingInitiator = bookingInitiator;
        this.lateArrivalNotice = lateArrivalNotice;
        this.priorityGuest = priorityGuest;
    }

    public GuestData getGuestData() {
        return guestData;
    }

    public void setGuestData(GuestData guestData) {
        this.guestData = guestData;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isBookingInitiator() {
        return bookingInitiator;
    }

    public void setBookingInitiator(boolean bookingInitiator) {
        this.bookingInitiator = bookingInitiator;
    }

    public boolean isLateArrivalNotice() {
        return lateArrivalNotice;
    }

    public void setLateArrivalNotice(boolean lateArrivalNotice) {
        this.lateArrivalNotice = lateArrivalNotice;
    }

    public boolean isPriorityGuest() {
        return priorityGuest;
    }

    public void setPriorityGuest(boolean priorityGuest) {
        this.priorityGuest = priorityGuest;
    }

    public Guest copy(){
        return new Guest(guestData, arrival, departure, roomType,
        bookingInitiator, lateArrivalNotice, priorityGuest);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Guest guest = (Guest) obj;

        if (bookingInitiator != guest.bookingInitiator) return false;
        if (lateArrivalNotice != guest.lateArrivalNotice) return false;
        if (priorityGuest != guest.priorityGuest) return false;
        if (guestData != null ? !guestData.equals(guest.guestData) : guest.guestData != null) return false;
        if (arrival != null ? !arrival.equals(guest.arrival) : guest.arrival != null) return false;
        if (departure != null ? !departure.equals(guest.departure) : guest.departure != null) return false;
        return roomType != null ? roomType.equals(guest.roomType) : guest.roomType == null;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestData=" + guestData +
                ", arrival=" + arrival +
                ", departure=" + departure +
                ", roomType='" + roomType + '\'' +
                ", bookingInitiator=" + bookingInitiator +
                ", lateArrivalNotice=" + lateArrivalNotice +
                ", priorityGuest=" + priorityGuest +
                '}';
    }
}
