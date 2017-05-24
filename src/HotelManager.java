import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// toDo: if file is empty use write instead of append;
public class HotelManager implements Serializable {
    //    private Room room;
    private Price price;
    //    private ArrayList<Room> rooms;
    private DateHandler dateHandler;
    private FileAdapter fileAdapter;
    private ArrayList<Reservation> allReservations;
    // we don't have a guest because all methods take a guest

    public HotelManager() {
        fileAdapter = new FileAdapter();
        this.price = new Price();
//        rooms = new ArrayList<Room>();
        dateHandler = new DateHandler(1, 1, 2017);
        // toDO: fix for empty file!!
        allReservations = fileAdapter.getAllGuests("inHouseGuests.bin");
    }

    public void checkIn(Reservation reservation, int roomNumber) {
        fileAdapter.removeSingleObjectFromFile("reservations.bin", reservation);
        reservation.setRoomNumber(roomNumber);
        fileAdapter.appendToFile("inHouseGuests.bin", reservation);
    }

    // toDo: modify to not check-out and return the string at the same time
    public void checkOut(Reservation reservation) {

        Calendar cal = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        cal.setTime(new Date((reservation.getArrival().getCheckInDate().getYear())
                , (reservation.getArrival().getCheckInDate().getMonth() - 1)
                , reservation.getArrival().getCheckInDate().getDay()));
        cal2.setTime(new Date((reservation.getDeparture().getCheckOutDate().getYear())
                , (reservation.getDeparture().getCheckOutDate().getMonth() - 1)
                , reservation.getDeparture().getCheckOutDate().getDay()));
        fileAdapter.removeSingleObjectFromFile("inHouseGuests.bin", reservation);
        fileAdapter.appendToFile("pastReservations.bin", reservation);

    }


    // added this for concern splitting
    public String getTotalPrice(Reservation reservation, double discount) {

        Calendar cal = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        cal.setTime(new Date((reservation.getArrival().getCheckInDate().getYear())
                , (reservation.getArrival().getCheckInDate().getMonth() - 1)
                , reservation.getArrival().getCheckInDate().getDay()));
        cal2.setTime(new Date((reservation.getDeparture().getCheckOutDate().getYear())
                , (reservation.getDeparture().getCheckOutDate().getMonth() - 1)
                , reservation.getDeparture().getCheckOutDate().getDay()));
        int total = cal2.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);
        double totalPrice = price.getRoomPrice(reservation.getRoomType()) * total;
        if (discount == 0) {
            return String.valueOf(totalPrice);
        }
        double finalPrice = Math.round((discount * totalPrice) / 100);
        return String.valueOf(totalPrice - finalPrice);
    }

//    public String checkOut(int roomNumber){
//
//    }

    public void createReservation(Reservation reservation) {
        fileAdapter.appendToFile("reservations.bin", reservation);
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
        fileAdapter.removeSingleObjectFromFile("reservations.bin", reservation);
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
        int twinRoom = 0;
        int kingSizeRoom = 0;
        ArrayList<Reservation> temp = new ArrayList<>();
        ArrayList<Reservation> compare = fileAdapter.getAllGuests("reservations.bin");
        compare.addAll(fileAdapter.getAllGuests("inHouseGuests.bin"));
        for (int i = 0; i < compare.size(); i++) {
            if ((!(compare.get(i).getDeparture().getCheckOutDate().isBefore(arrival)))
                    && (!(compare.get(i).getArrival().getCheckInDate().isBefore(departure)))) {
                temp.add(compare.get(i));
            }
        }

        for (int i = 0; i < temp.size(); i++) {
            //System.out.println(temp.get(i));
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

        if (countTwoBedroomSuite <= 1) {
            countTwoBedroomSuite = 1 - countTwoBedroomSuite;
        } else {
            countTwoBedroomSuite = 0;
        }


        if (countThreeBedroomSuite <= 1) {
            countThreeBedroomSuite = 1 - countThreeBedroomSuite;
        } else {
            countThreeBedroomSuite = 0;
        }

        if (singleRoom <= 10) {
            singleRoom = 10 - singleRoom;
        } else {
            singleRoom = 0;
        }

        if (twinRoom <= 6) {
            twinRoom = 6 - twinRoom;
        } else {
            twinRoom = 0;
        }

        if (kingSizeRoom <= 22) {
            kingSizeRoom = 22 - kingSizeRoom;
        } else {
            kingSizeRoom = 0;
        }

        String str = "Single room: " + singleRoom +
                "\nDouble room-twin bed: " + twinRoom + "\nDouble room-kingsize bed: "
                + kingSizeRoom + "\nSingle suite: " + countSingleBedroomSuite + "\nDouble suite: "
                + countTwoBedroomSuite + "\nTriple Suite: " + countThreeBedroomSuite;
        return str;
    }

    public void setMasterPrices(ArrayList<Double> prices) {
        fileAdapter.writeToFileObj("prices.bin", prices);
    }

    // remember to implement the changeSmokingType and addExtraBed in the GUI
}
