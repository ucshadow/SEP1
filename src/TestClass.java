import java.io.IOException;
import java.util.ArrayList;

public class TestClass {

    public static void main(String[] args) {



//        FakeGuest fgt = new FakeGuest();
//        Reservation r = fgt.makeNewReservation();
//        //Reservation r2 = fgt.makeNewReservation();
//        //Reservation r3 = fgt.makeNewReservation();
//
//        HotelManager hm = new HotelManager();
//        FileAdapter fa = new FileAdapter();
////        ArrayList<Reservation> incoming = fa.getAllGuests("reservations.bin");
////        ArrayList<Reservation> inHouse = fa.getAllGuests("inHouseGuests.bin");
        //CheckAvailability s = new CheckAvailability();
        new MainGuiWindow();
//
//        for(int i = 0; i < 500; i++) {
//            fa.appendToFile("pastReservations.bin", fgt.makeNewReservation());
//        }
        //MyFileIO mf = new MyFileIO();

//        System.out.println(r);
//        hm.createReservation(r);
//
        //fa.writeToFile("reservations.bin", r);
        //fa.appendToFile("reservations.bin", r2);
        //fa.writeToFile("pastReservations.bin", r);

        //hm.checkIn(r2, 201);


//
//        ArrayList<Reservation> arr;
//        ArrayList<Reservation> res;
//        ArrayList<Reservation> past;
////
////
//        arr = fa.getAllGuests("inHouseGuests.bin");
//        res = fa.getAllGuests("reservations.bin");
//        past = fa.getAllGuests("pastReservations.bin");
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

//        System.out.println("In house: ");
//        for(Object obj: arr) {
//            System.out.println( obj);
//        }
//
//        System.out.println("\n");
//
//        System.out.println("reservations: ");
//
//        for(Object obj: res) {
//            System.out.println( obj);
//        }
//
//        System.out.println("\n");
//
//        System.out.println("Past reservations: ");
////
//        for(Object obj: past) {
//            System.out.println( obj);
//        }

//        FileAdapter fa = new FileAdapter();
//        ArrayList<Reservation> arr = fa.getAllGuests("pastReservations.bin");
//
//        ChartContainer c = new ChartContainer(800, 600);
//        int[] data = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//
//        for(Reservation r: arr) {
//            data[r.getArrival().getCheckInDate().getMonth() - 1] += 1;
//        }
//
//        String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
//        c.setBarsData(data, months);
//        c.drawBars();

        //new Search();

//        Price p = new Price();
//        FileAdapter fa = new FileAdapter();
////        fa.writeToFileObj("prices.bin", p);
//        Object o = fa.readFromFileObj("prices.bin");
//        Price pp = (Price) o;
//        System.out.println(pp);

    }

}
