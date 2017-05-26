import java.io.Serializable;

/**
 * A class containing price which later on will be used by the HotelManager class in order to completed checkOut method and set master prices.
 * @author Radu G Orleanu
 * @version 1.0
 */
public class Price implements Serializable {

    private double singleRoom;
    private double doubleRoom;
    private double singleSuite;
    private double doubleSuite;
    private double tripleSuite;

    /**
     * No-argument constructor initializing prices.
     */
    public Price(){
        singleRoom = 125;
        doubleRoom = 165;
        singleSuite = 249;
        doubleSuite = 329;
        tripleSuite = 399;
    }

    /**
     * Get single room for reservation.
     * @return singeRoom room type for reservation.
     */
    public double getSingleRoom() {
        return singleRoom;
    }

    /**
     * Set single room for reservation.
     * @param singleRoom takes room type for reservation.
     */
    public void setSingleRoom(double singleRoom) {
        this.singleRoom = singleRoom;
    }

    public double getSingleSuite() {
        return singleSuite;
    }

    /**
     * Set single suite for reservation.
     * @param singleSuite takes room type for reservation.
     */
    public void setSingleSuite(double singleSuite) {
        this.singleSuite = singleSuite;
    }

    /**
     *Get double suite for reservation.
     * @return doubleSuite room type for reservation.
     */
    public double getDoubleSuite() {
        return doubleSuite;
    }

    /**
     * Set double room for reservation.
     * @param doubleSuite takes room type for reservation.
     */
    public void setDoubleSuite(double doubleSuite) {
        this.doubleSuite = doubleSuite;
    }

    /**
     * Get triple suite for reservation
     * @return tripleSuite room type for reservation.
     */
    public double getTripleSuite() {
        return tripleSuite;
    }

    /**
     * Set triple suite for reservation.
     * @param tripleSuite takes room type for reservation.
     */
    public void setTripleSuite(double tripleSuite) {
        this.tripleSuite = tripleSuite;
    }

    /**
     * Get room price for specific type of room.
     * @param roomType takes room type for reservation.
     * @return price for specific room type.
     */
    public double getRoomPrice(String roomType) {
        if (roomType.equals("single room")) {
            return singleRoom;
        }

        if (roomType.equals("double room-twin beds")) {
            return doubleRoom;
        }
        if (roomType.equals("double room-kingsize")) {
            return doubleRoom;
        }
        if (roomType.equals("single bedroom suite")) {
            return singleSuite;
        }
        if (roomType.equals("two bedroom suite")) {
            return doubleSuite;
        }
        if (roomType.equals("three bedroom suite")) {
            return tripleSuite;
        }
        return 0;
    }
}
