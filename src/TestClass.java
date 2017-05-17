import java.io.IOException;
import java.util.ArrayList;

public class TestClass {

    public static void main(String[] args) {
        FakeGuest fgt = new FakeGuest();
        //Reservation r = fgt.makeNewReservation();
        //Reservation r2 = fgt.makeNewReservation();
        //Reservation r3 = fgt.makeNewReservation();

        HotelManager hm = new HotelManager();
        FileAdapter fa = new FileAdapter();
        //MyFileIO mf = new MyFileIO();

//        System.out.println(r);
//        hm.createReservation(r);
//
        //fa.writeToFile("reservations.bin", r);
        //fa.appendToFile("reservations.bin", r2);
        //fa.writeToFile("inHouseGuests.bin", r3);

        //hm.checkIn(r2, 201);


//
        ArrayList<Reservation> arr;
        ArrayList<Reservation> res;
        ArrayList<Reservation> past;
//
//
        arr = fa.getAllGuests("inHouseGuests.bin");
        res = fa.getAllGuests("reservations.bin");
        past = fa.getAllGuests("pastReservations.bin");
//
//
//
//        for(Reservation r: arr) {
//            if(r.getGuest().getName().getFirstName().equals("Marica")) {
//                System.out.println("needs to pay" + hm.checkOut(r, 10));
//            }
//        }

//                for(Reservation r: res) {
//            if(r.getGuest().getName().getFirstName().equals("Raymundo")) {
//                hm.checkIn(r, 203);
//            }
//        }

        System.out.println("In house: ");
        for(Object obj: arr) {
            System.out.println( obj);
        }

        System.out.println("\n");

        System.out.println("reservations: ");

        for(Object obj: res) {
            System.out.println( obj);
        }

        System.out.println("\n");

        System.out.println("Past reservations: ");
//
        for(Object obj: past) {
            System.out.println( obj);
        }

    }

}
