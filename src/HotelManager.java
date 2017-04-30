import java.util.ArrayList;

public class HotelManager {
    private Room price;
    private ArrayList<Room> rooms;
    private DateHandler dateHandler;
    private FileAdapter fileAdapter;
    // we don't have a guest because all methods take a guest

    public HotelManager() {
//        fileAdapter = new FileAdapter();
        rooms = new ArrayList<Room>();
        dateHandler = new DateHandler(1,1,2017);
    }

    public void setToPriorityGuest(Guest guest){
        guest.setPriorityGuest(true);
    }

    public void createReservation(Guest guest, String roomType){
        for (int i = 0; i <rooms.size(); i++){
            if(rooms.get(i).getRoomType().equals(roomType)&&rooms.get(i)){

            }
        }
    }

    public void modifyReservation(Guest guest, String roomType){

    }

    public void cancelReservation(Guest guest){

    }

    public void checkIn(Guest guest, int roomNumber){

    }



}
