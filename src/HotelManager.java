import java.util.ArrayList;

public class HotelManager {
    private Room price;
    private ArrayList<Room> rooms;
    private DateHandler dateHandler;
    private FileAdapter fileAdapter;
    private ArrayList<Guest> allGuests;
    // we don't have a guest because all methods take a guest

    public HotelManager() {
        fileAdapter = new FileAdapter("reservations.bin");
        rooms = new ArrayList<Room>();
        dateHandler = new DateHandler(1, 1, 2017);
        allGuests = fileAdapter.getAllGuests(fileAdapter.getFileName());
    }

//    public void checkIn(Guest guest, int roomNumber){
//
//    }
//
//    public String checkOut(String firstName){
//
//    }
//
//    public String checkOut(int roomNumber){
//
//    }

    public void createReservation(Guest guest, String roomType) {
        guest.setRoomType(roomType);
        fileAdapter.writeToFile(guest);
        // this method will not check for availability. We will have checked the availability
        // before we get to this method.
    }

    public ArrayList<Guest> modifyReservation(String firstName) {
        ArrayList<Guest> foundGuests = new ArrayList<Guest>();
        for (int i = 0; i < allGuests.size(); i++) {
            if (allGuests.get(i).getGuestData().getName().getFirstName().equals(firstName)) {
                foundGuests.add(allGuests.get(i));
            }
        }
        return foundGuests;
        // the method will be changed later with all the code that will change
        // the rest of the reservation
    }

    public void cancelReservation(Guest guest) {
        for (int i = 0; i < allGuests.size(); i++) {
            if (allGuests.get(i).equals(guest)) {
                allGuests.remove(i);
                fileAdapter.writeToFile((Guest[]) allGuests.toArray());
                break;
            }
        }
    }

    public ArrayList<Guest> getArrivalList(DateHandler today) {
        ArrayList<Guest> arr = new ArrayList<Guest>();
        for (int i = 0; i < allGuests.size(); i++) {
            if (allGuests.get(i).getArrival().getCheckInDate().equals(today)) {
                arr.add(allGuests.get(i));
            }
        }
        return arr;
    }

    public ArrayList<Guest> getDepartureList(DateHandler today) {
        ArrayList<Guest> dep = new ArrayList<Guest>();
        for (int i = 0; i < allGuests.size(); i++) {
            if (allGuests.get(i).getDeparture().getCheckOutDate().equals(today)) {
                dep.add(allGuests.get(i));
            }
        }
        return dep;
    }

//    public Room[] getAvailabilityFromDate(DateHandler arrival){
//    }
//    remains to be implemented tomorrow or the day after tomorrow

//    public Room[] getAvailabilityFromDateInterval(DateHandler arrival, DateHandler departure){
//    }
//    remains to be implemented tomorrow or the day after tomorrow

    public void setMasterPrices(ArrayList<Price> prices){
        fileAdapter.writeToFileObj(prices);
    }

    // remember to implement the changeSmokingType and addExtraBed in the GUI
}
