import java.util.*;

/**
 * The FakeGuest class generates fake data for The Reservation class
 *
 * @author  Catalin Udrea
 * @version 1.0
 * @since   2017-05-17
 */

public class FakeGuest {
    private String[] streets = {"Creek Road", "Summit Street", "River Road", "Hudson Street", "Lafayette Street",
            "Ridge Street", "Cardinal Drive", "Heritage Drive", "Oak Street", "Main Street South", "4th Street",
            "Myrtle Avenue", "Strawberry Lane", "Monroe Drive", "Arlington Avenue", "Hanover Court", "Pin Oak Drive",
            "Windsor Drive", "Essex Court", "4th Street North", "Spruce Street", "Roberts Road", "High Street",
            "Hawthorne Lane", "Creekside Drive", "4th Street West", "Bay Street", "9th Street", "Hillside Drive",
            "Garfield Avenue", "Hill Street", "Meadow Street", "Overlook Circle", "Bridle Lane", "Division Street",
            "Holly Court", "Route 44", "Olive Street", "Adams Street", "2nd Avenue"};

    private String[] girlNames = {"Twyla", "Dorathy", "Missy", "Alishia", "Brenda", "Blanch", "Thea", "Alfreda",
            "Mireille", "Dione", "Agnus", "Felicia", "Avelina", "Leonia", "Rosie", "Jaimee", "Rosella", "Annamarie",
            "Alexa", "Crista", "France", "Zenia", "Seema", "Rosa", "Jackqueline", "Elaina", "Latonia", "Eboni",
            "Evalyn", "Hannah", "Kristeen", "Tula", "Kathryne", "Elisa", "Zina", "Wendolyn", "Joanne", "Anika",
            "Loise", "Fabiola", "Cathryn", "Jerlene", "Kari", "Erlene", "Doretha", "Marcy", "Shawanna", "Marica",
            "Vina", "Manda"};

    private String[] famNames = {"Luther", "Hershel", "Brandon", "Reed", "Cliff", "Darell", "Lupe", "Johnnie", "Jorge",
            "Felton", "Aldo", "Kraig", "Calvin", "Elwood", "Sam", "Andre", "Kelly", "Amado", "Gerard", "Moises", "Raptor",
            "Tracey", "Alvaro", "Quentin", "Mel", "Jared", "Horacio", "Fredrick", "Moshe", "Buford", "Sonny", "Jackie",
            "Branden", "Kristofer", "Dylan", "Kris", "Claudio", "Wilfred", "Alexis", "Daniel", "Darrick", "Kim",
            "Benton", "Shawn", "Lyndon", "Garret", "Josef", "Barton", "Cedrick", "Tory"};

    private String[] boyNames = {"Brooks", "Columbus", "Lee", "Everette", "Raymundo", "Dexter", "Joesph", "Eli",
            "Ramon", "Javier", "Deon", "Donte", "Art", "Michel", "Perry", "Dean", "Aaron", "Jamie", "Clifton",
            "Terence", "Kennith", "Britt", "Harold", "Vincenzo", "Jacob", "Jules", "Harley", "Evan", "Alex", "Eddy",
            "Anton", "Teodoro", "Rolando", "Keneth", "Hosea", "Rodrigo", "Pablo", "Derick", "Kenneth", "Lino",
            "Demetrius", "Del", "Burt", "Nicolas", "Adan", "Russell", "Jospeh", "Troy", "Don", "Caleb"};

    private String[] roomTypes = {"single room", "double room", "double room-twin beds", "double room-kingsize",
            "single bedroom suite", "two bedroom suite", "three bedroom suite"};

    private String[] city = { "Copenhagen", "Aarhus", "Horsens", "Randers",
            "Vejle", "Odense", "Berlin", "Hamburg", "Dublin", "Honk Kong",
            "Bucurest", "Budapest", "Bratislava", "Sofia", "Cork", "Maimi",
            "Las Vegas", "Los Angelis", "Porto", "Milano", "Rome", "Viena",
            "Hanover", "Malta", "Oaho", "Dubai", "Zurich", "Barcelona",
            "Madrid", "Paris", "Sidney", "Moscow", "Istanbul", "London",
            "Prague", "Amsterdam", "Stockholm", "Riga", "Oslo", "Zagreb",
            "Reykjavik", "Edinburgh", "Florence", "Manchester" };

    private Random rnd = new Random();

    private int[] randomArrivalAndDeparture() {
        long l = Math.abs(rnd.nextLong());
        Date date = new Date(Math.abs(l - (long)(86000000 + (Math.random() * ((86000000 * Math.random() * 20) - 86000000)))));
        Date date2 = new Date(Math.abs(l));

        GregorianCalendar g = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        g.setTime(date);
        g2.setTime(date2);

        return new int[] {g.get(Calendar.DAY_OF_MONTH), g.get(Calendar.MONTH) + 1, g2.get(Calendar.DAY_OF_MONTH),
                g2.get(Calendar.MONTH) + 1};
    }

    private String getCountry() {
        String[] isoCountries = Locale.getISOCountries();
        Locale locale = new Locale("en", isoCountries[rnd.nextInt(249)]);
        return locale.getDisplayCountry();
    }

    private String getDateOfBirth() {
        long l = Math.abs(rnd.nextLong());
        Date date2 = new Date(Math.abs(l));

        GregorianCalendar g2 = new GregorianCalendar();
        g2.setTime(date2);

        return String.valueOf(g2.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(g2.get(Calendar.MONTH) + 1) +
                "/" + String.valueOf((1935 + rnd.nextInt(70)));
    }

    private String getRoomType() {
        return roomTypes[rnd.nextInt(7)];
    }

    private boolean giveMeABoolean() {
        int x = rnd.nextInt(10);
        if(x <= 3) {
            return true;
        }
        return false;
    }

    private String[] getAName() {
        String[] name = new String[3];
        int gender = rnd.nextInt(10);
        if(gender <= 5) {
            name[0] = boyNames[rnd.nextInt(50)];
            name[1] = boyNames[rnd.nextInt(50)];
            name[2] = famNames[rnd.nextInt(50)];
        } else {
            name[0] = girlNames[rnd.nextInt(50)];
            name[1] = girlNames[rnd.nextInt(50)];
            name[2] = famNames[rnd.nextInt(50)];
        }
        return name;
    }

    public Reservation makeNewReservation() {
        int range = 1000000000 - 100000000 + 1;
        int randomNum =  rnd.nextInt(range) + 100000000;
        String[] fullName = getAName();
        int[] randomArrAndDep = randomArrivalAndDeparture();

        Address address = new Address(getCountry(), city[rnd.nextInt(city.length)], String.valueOf(rnd.nextInt(10000)),
                streets[rnd.nextInt(streets.length)]);
        Name name = new Name(fullName[0], fullName[1], fullName[2]);
        Guest guest = new Guest(name, randomNum, address, address.getCountry(), getDateOfBirth());
        Arrival arrival = new Arrival(new DateHandler(randomArrAndDep[0], randomArrAndDep[1], 2017));
        Departure departure = new Departure(new DateHandler(randomArrAndDep[2], randomArrAndDep[3], 2017));
        Reservation reservation = new Reservation(guest, arrival, departure, getRoomType(), true,
                giveMeABoolean(), giveMeABoolean());

        return reservation;
    }
}