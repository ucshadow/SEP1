import java.io.Serializable;

public class Price implements Serializable {

    private double singleRoom;
    private double doubleRoom;
    private double singleSuite;
    private double doubleSuite;
    private double tripleSuite;

    public Price(double singleRoom, double doubleRoom, double singleSuite, double doubleSuite, double tripleSuite) {
        this.singleRoom = singleRoom;
        this.doubleRoom = doubleRoom;
        this.singleSuite = singleSuite;
        this.doubleSuite = doubleSuite;
        this.tripleSuite = tripleSuite;
    }

    public Price(){
        singleRoom = 125;
        doubleRoom = 165;
        singleSuite = 249;
        doubleSuite = 329;
        tripleSuite = 399;
    }

    public double getSingleRoom() {
        return singleRoom;
    }

    public void setSingleRoom(double singleRoom) {
        this.singleRoom = singleRoom;
    }

    public double getDoubleRoom() {
        return doubleRoom;
    }

    public void setDoubleRoom(double doubleRoom) {
        this.doubleRoom = doubleRoom;
    }

    public double getSingleSuite() {
        return singleSuite;
    }

    public void setSingleSuite(double singleSuite) {
        this.singleSuite = singleSuite;
    }

    public double getDoubleSuite() {
        return doubleSuite;
    }

    public void setDoubleSuite(double doubleSuite) {
        this.doubleSuite = doubleSuite;
    }

    public double getTripleSuite() {
        return tripleSuite;
    }

    public void setTripleSuite(double tripleSuite) {
        this.tripleSuite = tripleSuite;
    }

    public double getRoomPrice(String roomType) {
        if (roomType.equals("single room")) {
            return singleRoom;
        }
        if (roomType.equals("double room")) {
            return doubleRoom;
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

//    public String toString() {
//        return "Price{" +
//                "singleRoom=" + singleRoom +
//                ", doubleRoom=" + doubleRoom +
//                ", singleSuite=" + singleSuite +
//                ", doubleSuite=" + doubleSuite +
//                ", tripleSuite=" + tripleSuite +
//                '}';
//    }
}
