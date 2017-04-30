public class Room {

    private String roomType;
    private int roomNumber;
    private double roomPrice;
    private Price price;

    public Room(String roomType, int roomNumber){
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        // roomPrice will be based on roomType and duration of stay
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
