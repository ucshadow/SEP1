import java.io.Serializable;

/**
 * @author Nikolay D Nikolav, Yusuf A Farah, Radu G Orleanu, Catalin Udrea
 * @version 1.0
 */
public class Reservation implements Serializable {
    private Guest guest;
    private Arrival arrival;
    private Departure departure;
    private String roomType;
    private boolean bookingInitiator;
    private boolean lateArrivalNotice;
    private boolean priorityGuest;
    private int roomNumber;

    /**
     * Constructor initializing Address
     *
     * @param guest             for initializing the constructor.
     * @param arrival           for initializing the constructor.
     * @param departure         for initializing the constructor.
     * @param roomType          for initializing the constructor.
     * @param bookingInitiator  for initializing the constructor.
     * @param lateArrivalNotice for initializing the constructor.
     * @param priorityGuest     for initializing the constructor.
     */
    public Reservation(Guest guest, Arrival arrival, Departure departure, String roomType,
                       boolean bookingInitiator, boolean lateArrivalNotice, boolean priorityGuest) {
        this.guest = guest;
        this.arrival = arrival;
        this.departure = departure;
        this.roomType = roomType;
        this.bookingInitiator = bookingInitiator;
        this.lateArrivalNotice = lateArrivalNotice;
        this.priorityGuest = priorityGuest;
    }

    /**
     * Get guest for reservation.
     *
     * @return guest Guest for reservation.
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * Set guest for reservation.
     *
     * @param guest takes Guest for reservation.
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    /**
     * Get arrival for reservation.
     *
     * @return arrival Arrival for reservation.
     */
    public Arrival getArrival() {
        return arrival;
    }

    /**
     * Set arrival for reservation.
     *
     * @param arrival takes Arrival for reservation.
     */
    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    /**
     * Get departure for reservation.
     *
     * @return departure Departure for reservation.
     */
    public Departure getDeparture() {
        return departure;
    }

    /**
     * Set departure for reservation.
     * @param departure takes Departure for reservation.
     */

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    /**
     * Get room type for reservation.
     * @return roomType Room type for reservation.
     */

    public String getRoomType() {
        return roomType;
    }

    /**
     * Set room type for reservation.
     * @param roomType takes Room type for reservation.
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * Get if guest is booking initiator for reservation.
     * @return bookingInitiator Booking initiator for reservation.
     */
    public boolean isBookingInitiator() {
        return bookingInitiator;
    }

    /**
     * Set booking initiator for reservation.
     * @param bookingInitiator takes boolean for a guest for reservation.
     */
    public void setBookingInitiator(boolean bookingInitiator) {
        this.bookingInitiator = bookingInitiator;
    }

    /**
     * Get if guest is arriving late for reservation.
     * @return lateArrivalNotice Late arrival for reservation.
     */
    public boolean isLateArrivalNotice() {
        return lateArrivalNotice;
    }

    /**
     * Set late arrival notice for reservation.
     * @param lateArrivalNotice takes boolean for a guest for reservation.
     */
    public void setLateArrivalNotice(boolean lateArrivalNotice) {
        this.lateArrivalNotice = lateArrivalNotice;
    }

    /**
     * Get if guest is a priority guest for reservation.
     * @return priorityGuest  Priority guest for reservation.
     */
    public boolean isPriorityGuest() {
        return priorityGuest;
    }

    /**
     * Set priority guest for reservation.
     * @param priorityGuest takes boolean for a guest for reservation.
     */
    public void setPriorityGuest(boolean priorityGuest) {
        this.priorityGuest = priorityGuest;
    }

    /**
     * Get room number for reservation.
     * @return roomNumber Room number for reservation.
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Set room number for reservation.
     * @param roomNumber takes roomnumber for a guest for reservation.
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Get copy of reservation
     * @return Reservation Reservation copy
     */
    public Reservation copy() {
        return new Reservation(guest, arrival, departure, roomType,
                bookingInitiator, lateArrivalNotice, priorityGuest);
    }
    /**
     * check if a reservation is equal to another reservation.
     *
     * @param obj Object for comparing
     * @return true or false. if the whole reservation is equal after comparing with obj will return true, else will return false.
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Reservation)) {
            return false;
        }

        Reservation reservation = (Reservation) obj;

        if (bookingInitiator != reservation.bookingInitiator) return false;
        if (lateArrivalNotice != reservation.lateArrivalNotice) return false;
        if (priorityGuest != reservation.priorityGuest) return false;
        if (!guest.equals(reservation.guest)) return false;
        if (!arrival.equals(reservation.arrival)) return false;
        if (!departure.equals(reservation.departure)) return false;
        return roomType.equals(reservation.roomType);
    }
    /**
     * return a String
     *
     * @return String containing guest, arrival, departure, roomtype, booking initiator , late arrival, priority guest.
     */
    @Override
    public String toString() {
        return "Reservation{" +
                "guest=" + guest +
                ", arrival=" + arrival +
                ", departure=" + departure +
                ", roomType='" + roomType + '\'' +
                ", bookingInitiator=" + bookingInitiator +
                ", lateArrivalNotice=" + lateArrivalNotice +
                ", priorityGuest=" + priorityGuest +
                '}';
    }
}
