import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A class containing methods which later on will be used from our GUI.
 *
 * @author Nikolay D Nikolav, Yusuf A Farah, Radu G Orleanu, Catalin Udrea
 * @version 1.0
 */


public class HotelManager implements Serializable {
    private Price price;
    private FileAdapter fileAdapter;

    /**
     * No-argument constructor. Used to initialize HotelManager.
     */
    public HotelManager() {
        fileAdapter = new FileAdapter();
        this.price = new Price();
    }

    /**
     * Check in method. Used to check in a reservation.
     *
     * @param reservation takes specific reserervation.
     * @param roomNumber  takes specific room number.
     */
    public void checkIn(Reservation reservation, int roomNumber) {
        fileAdapter.removeSingleObjectFromFile("reservations.bin", reservation);
        reservation.setRoomNumber(roomNumber);
        fileAdapter.appendToFile("inHouseGuests.bin", reservation);
    }

    /**
     * Check out method. Used to check out person.
     *
     * @param reservation takes specific reservation.
     */

    public void checkOut(Reservation reservation) {
        fileAdapter.removeSingleObjectFromFile("inHouseGuests.bin", reservation);
        fileAdapter.appendToFile("pastReservations.bin", reservation);
    }


    /**
     * Total price method. Used to calculate the price upon check out.
     *
     * @param reservation takes specific reservation.
     * @param discount    takes discount so calculates the price upon check out.
     * @return price Price with the discount.
     */
    public String getTotalPrice(Reservation reservation, double discount) {

        Calendar cal = new GregorianCalendar(reservation.getArrival().getCheckInDate().getYear(),
                reservation.getArrival().getCheckInDate().getMonth() - 1,
                reservation.getArrival().getCheckInDate().getDay());
        Calendar cal2 = new GregorianCalendar(reservation.getDeparture().getCheckOutDate().getYear(),
                reservation.getDeparture().getCheckOutDate().getMonth() - 1,
                reservation.getDeparture().getCheckOutDate().getDay());
        int total = cal2.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);
        double totalPrice = price.getRoomPrice(reservation.getRoomType()) * total;
        if (discount == 0) {
            return String.valueOf(totalPrice);
        }
        double finalPrice = Math.round((discount * totalPrice) / 100);
        return String.valueOf(totalPrice - finalPrice);
    }

    /**
     * Create reservation method. Used to create reservation and save it in the system.
     *
     * @param reservation takes specific reservation.
     */
    public void createReservation(Reservation reservation) {
        fileAdapter.appendToFile("reservations.bin", reservation);
    }

    /**
     * Availability method. Used to check availability between dates.
     *
     * @param arrival   takes specific date for arraving.
     * @param departure takes specific date for departure.
     * @return availability Returns specific numbers of available rooms.
     */
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
                    && ((compare.get(i).getArrival().getCheckInDate().isBefore(departure)))) {
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
}
