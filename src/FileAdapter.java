import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FileAdapter {
    private MyFileIO fileIO;
    private String fileName;

    public FileAdapter(String fileName) {
        this.fileName = fileName;
        fileIO = new MyFileIO();
    }

    public void writeToFile(ArrayList<Room> room) {
        for (int i = 0; i < room.size(); i++) {
            try {
                fileIO.appendToFile(fileName, room.get(i).toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(Room room) {
        try {
            fileIO.appendToFile(fileName, room.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllRooms() {
        ArrayList<String> rooms = new ArrayList<String>();
        try {
            rooms = fileIO.readArrayFromFile(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public boolean checkAvailability(String roomType, Arrival arrival, Departure departure, String fileName) {
        DateHandler d = new DateHandler(1,1, 2017);
        ArrayList<Guest> tempGuests = parseItems(fileName);
        for (int i = 0; i < tempGuests.size(); i++) {
            if(tempGuests.get(i).getRoomType().equals(roomType)){
                if(d.isBeforeRange(arrival.getCheckInDate(), tempGuests.get(i).getArrival().getCheckInDate())){
//                    if(d.isBeforeRange(arrival.getCheckInDate(), tempGuests.get()))
                    int counter = 0;
                }
            }
        }
    }

    public ArrayList<Guest> findReservationByName(String firstName, String fileName) {
        ArrayList<Guest> allGuests = parseItems(fileName);
        ArrayList<Guest> guestsByFirstName = new ArrayList<Guest>();

        for (int i = 0; i < allGuests.size(); i++) {
            if(allGuests.get(i).getGuestData().getName().getFirstName().equals(firstName)){
                guestsByFirstName.add(allGuests.get(i));
            }
        }
        return guestsByFirstName;
    }

    public ArrayList<Guest> parseItems(String fileName) {
        ArrayList<String> allReservations = new ArrayList<String>();
        ArrayList<Guest> foundGuests = new ArrayList<Guest>();
        try {
            allReservations = fileIO.readArrayFromFile(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < allReservations.size(); i++) {
            String[] data = allReservations.get(i).split(",");
            String firstName_ = data[0];
            String middleName = data[1];
            String lastName = data[2];
            long phoneNumber = Long.parseLong(data[3]);
            String country = data[4];
            String city = data[5];
            String postCode = data[6];
            String street = data[7];
            String nationality = data[8];

            int dayOfBirth = Integer.parseInt(data[9]);
            int monthOfBirth = Integer.parseInt(data[10]);
            int yearOfBirth = Integer.parseInt(data[11]);
            DateHandler dateOfBirth = new DateHandler(dayOfBirth, monthOfBirth, yearOfBirth);

            int checkInDay = Integer.parseInt(data[12]);
            int checkInMonth = Integer.parseInt(data[13]);
            int checkInYear = Integer.parseInt(data[14]);
            Arrival arrival = new Arrival(new DateHandler(checkInDay, checkInMonth, checkInYear));

            int checkOutDay = Integer.parseInt(data[15]);
            int checkOutMonth = Integer.parseInt(data[16]);
            int checkOutYear = Integer.parseInt(data[17]);
            Departure departure = new Departure(new DateHandler(checkOutDay, checkOutMonth, checkOutYear));

            String roomType = data[18];
            boolean bookingInitiator = Boolean.parseBoolean(data[19]);
            boolean lateArrivalNotice = Boolean.parseBoolean(data[20]);
            boolean priorityGuest = Boolean.parseBoolean(data[21]);

            Name fullName = new Name(firstName_, middleName, lastName);
            Address fullAddress = new Address(country, city, postCode, street);
            GuestData fullGuestData = new GuestData(fullName, phoneNumber, fullAddress, nationality, dateOfBirth.toString());
            Guest fullGuest = new Guest(fullGuestData, arrival, departure, roomType, bookingInitiator, lateArrivalNotice, priorityGuest);

            foundGuests.add(fullGuest);
        }
        return foundGuests;
    }
}
