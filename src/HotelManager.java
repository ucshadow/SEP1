import java.util.ArrayList;

public class HotelManager {
    private Room price;
    private ArrayList<Room> rooms;
    private DateHandler dateHandler;
    private FileAdapter fileAdapter;
    private ArrayList<Reservation> allReservations;
    // we don't have a guest because all methods take a guest

    public HotelManager() {
        fileAdapter = new FileAdapter("reservations.bin");
        rooms = new ArrayList<Room>();
        dateHandler = new DateHandler(1, 1, 2017);
        allReservations = fileAdapter.getAllGuests(fileAdapter.getFileName());
    }

//    public void checkIn(Reservation guest, int roomNumber){
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

    public void createReservation(Reservation reservation, String roomType) {
        reservation.setRoomType(roomType);
        fileAdapter.writeToFile(reservation);
        // this method will not check for availability. We will have checked the availability
        // before we get to this method.
    }

    public ArrayList<Reservation> modifyReservation(String firstName) {
        ArrayList<Reservation> foundReservations = new ArrayList<Reservation>();
        for (int i = 0; i < allReservations.size(); i++) {
            if (allReservations.get(i).getGuest().getName().getFirstName().equals(firstName)) {
                foundReservations.add(allReservations.get(i));
            }
        }
        return foundReservations;
        // the method will be changed later with all the code that will change
        // the rest of the reservation
    }

    public void cancelReservation(Reservation reservation) {
        for (int i = 0; i < allReservations.size(); i++) {
            if (allReservations.get(i).equals(reservation)) {
                allReservations.remove(i);
                fileAdapter.writeToFile((Reservation[]) allReservations.toArray());
                break;
            }
        }
    }

    public ArrayList<Reservation> getArrivalList(DateHandler today) {
        ArrayList<Reservation> arr = new ArrayList<Reservation>();
        for (int i = 0; i < allReservations.size(); i++) {
            if (allReservations.get(i).getArrival().getCheckInDate().equals(today)) {
                arr.add(allReservations.get(i));
            }
        }
        return arr;
    }

    public ArrayList<Reservation> getDepartureList(DateHandler today) {
        ArrayList<Reservation> dep = new ArrayList<Reservation>();
        for (int i = 0; i < allReservations.size(); i++) {
            if (allReservations.get(i).getDeparture().getCheckOutDate().equals(today)) {
                dep.add(allReservations.get(i));
            }
        }
        return dep;
    }

//    public Room[] getAvailabilityFromDate(DateHandler arrival){
//    }
//    remains to be implemented tomorrow or the day after tomorrow

    public String getAvailabilityFromDateInterval(DateHandler arrival, DateHandler departure) {
        int countSingleBedroomSuite = 0;
        int countTwoBedroomSuite = 0;
        int countThreeBedroomSuite = 0;
        int singleRoom = 0;
        int doubleRoom = 0;
        int twinRoom = 0;
        int kingSizeRoom = 0;
        ArrayList<Reservation> compare = new ArrayList<Reservation>();
        ArrayList<Reservation> temp = new ArrayList<>();
        compare = fileAdapter.getAllGuests("reservations.txt");
        for (int i = 0; i < compare.size(); i++) {
            if ((!(compare.get(i).getDeparture().getCheckOutDate().isBefore(arrival)))
                    && (!(compare.get(i).getArrival().getCheckInDate().isBefore(departure)))) {
                temp.add(compare.get(i));
            }
        }

        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getRoomType().equals("single bedroom suite")) {
                countSingleBedroomSuite++;
            }
            if (temp.get(i).getRoomType().equals("two bedroom suite")) {
                countTwoBedroomSuite++;
            }
            if (temp.get(i).getRoomType().equals("three bedroom suite")) {
                countThreeBedroomSuite++;
            }
            if (temp.get(i).getRoomType().equals("single room")) {
                singleRoom++;
            }
            if (temp.get(i).getRoomType().equals("double room")) {
                doubleRoom++;
            }
            if (temp.get(i).getRoomType().equals("double room-twin beds")) {
                twinRoom++;
            }
            if (temp.get(i).getRoomType().equals("double room-kingsize")) {
                kingSizeRoom++;
            }


        }

        if (countSingleBedroomSuite <= 2) {
            countSingleBedroomSuite = 2 - countSingleBedroomSuite;
        } else {
            countSingleBedroomSuite = 0;
        }

        if (countTwoBedroomSuite >= 1) {
            countTwoBedroomSuite = 1 - countTwoBedroomSuite;
        } else {
            countTwoBedroomSuite = 0;
        }


        if (countThreeBedroomSuite >= 1) {
            countThreeBedroomSuite = 1 - countThreeBedroomSuite;
        } else {
            countThreeBedroomSuite = 0;
        }

        if (singleRoom >= 10) {
            singleRoom = 0;
        } else {
            singleRoom = 10 - singleRoom;
        }

        if (doubleRoom >= 28) {
            doubleRoom = 28 - doubleRoom;
        } else {
            doubleRoom = 0;
        }

        if (twinRoom >= 6) {
            twinRoom = 6 - twinRoom;
        } else {
            twinRoom = 0;
        }

        if (kingSizeRoom >= 22) {
            kingSizeRoom = 22 - kingSizeRoom;
        } else {
            kingSizeRoom = 0;
        }

        String str = "Single room : " + singleRoom + ",Double room :" + doubleRoom
                + ",Double room-twin bed" + twinRoom + ",Double room-kingsize bed :"
                + kingSizeRoom + ",Single suite" + countSingleBedroomSuite + ",Double suite"
                + countTwoBedroomSuite + ",Triple Suite :" + countThreeBedroomSuite;
        return str;
    }

    public void setMasterPrices(ArrayList<Price> prices) {
        fileAdapter.writeToFileObj(prices);
    }

    // remember to implement the changeSmokingType and addExtraBed in the GUI
}
