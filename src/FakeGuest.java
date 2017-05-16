package FakeSep;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class FakeGuest implements Serializable {
	private ArrayList<Object> firstName;
	private ArrayList<Object> middleName;
	private ArrayList<Object> lastName;
	private ArrayList<Object> phoneNumber;
	private ArrayList<Object> country;
	private ArrayList<Object> city;
	private ArrayList<Object> postCode;
	private ArrayList<Object> street;
	private ArrayList<Object> nationality;
	private ArrayList<Object> birthday;
	private ArrayList<Object> CheckInOut;
	private ArrayList<Object> roomType;
	private ArrayList<Object> initator;
	private ArrayList<Object> lateArrivelNotice;
	private ArrayList<Object> priorityGuest;
	private int number1, number2, number3, number4, number5, number6, number7,
			number8, number9, number10, number11, number12, number13, number14,
			number15, number16, number17, number18, number19, number20,
			number21, number22, number23, number24, number25, number26,
			number27, number28, number29, number30, number31, number32;

	Random randomNumber = new Random();

	public void randomGuest() {
		firstName = new ArrayList<>();
		middleName = new ArrayList<>();
		lastName = new ArrayList<>();
		phoneNumber = new ArrayList<>();
		country = new ArrayList<>();
		city = new ArrayList<>();
		postCode = new ArrayList<>();
		street = new ArrayList<>();
		nationality = new ArrayList<>();
		birthday = new ArrayList<>();
		CheckInOut = new ArrayList<>();
		roomType = new ArrayList<>();
		initator = new ArrayList<>();
		lateArrivelNotice = new ArrayList<>();
		priorityGuest = new ArrayList<>();
		String phonenumber = "";
		String sstreet = "";
		String bday = "";
		String InOut = "";

		String[] firstNamee = { "Aron", "Asia", "Lucio", "Tracy", "Wan",
				"Cherrie", "Kindra", "Oren", "Barney", "Collen", "Loris",
				"Phebe", "Seth", "Camie", "Emery", "Renae", "Victor", "Mae",
				"Anh", "Mariann", "Elfrieda", "Lashaunda", "Una", "Maribel",
				"Yasmin", "Kit", "Pamila", "Petra", "Rossie", "Sadye",
				"Hollis", "Alphonso", "Terrie", "Bessie", "Mathilda", "Nadine",
				"Domonique", "Ava", "Lizette", "Jamaal", "Cornelius", "Lionel",
				"Ayesha", "Julene", "Hae", "Mackenzie", "Arlinda", "Lyda",
				"Kimiko", "Norris", "Agatha", "Piper", "Jeffrey", "Tamekia",
				"Treva", "Russel", "Deloras", "Maxwell", "Andria", "Ellena",
				"Hsiu", "Margrett", "Marina", "Kathe", "Tessa", "Adina",
				"Mahalia", "Candyce", "Terrence", "Min", "Evangeline", "Eric",
				"Nilsa", "Peg", "Neda", "Darci", "Buster", "Hyacinth",
				"Roosevelt", "Georgianne", "Valarie", "Kandra", "James",
				"Rhea", "Coral", "Shantay", "Laurel", "Ninfa", "Shayla",
				"Shonda", "Cyrus", "Hector", "Somer", "Celena", "Tennille",
				"Antionette", "Mitchell", "Isaiah", "Jaye", "Alexa"

		};
		String[] middleNamee = { "Smith", "Jones", "Brown", "Johnson",
				"Williams", "Miller", "Taylor", "Wilson", "Davis", "White",
				"Clark", "Hall", "Thomas", "Thompson", "Moore", "Hill",
				"Martin", "College", "Mary", "Hayes", "Page", "Rose",
				"Patterson", "Ann", "Crawford", "Arnold", "House", "Harvey",
				"Davidson", "Ferguson", "Park", "Myers", "Ryan", "Freeman",
				"Pierce", "Fellows", "Douglas", "Tucker", "Dixon", "Bradley",
				"Hopkins", "Potter", "Loyd", "Curtis", "Howell", "Carroll",
				"Edward", "Blair", "Joseph", "Reid", "Dunn", "Barker", "Dunn",
				"Armstrong", "Lawrence", "City", "Black", "Lane", "Pery",
				"Street", "Wheeler", "Gordon", "Powell", "Webster", "Aron",
				"Asia", "Lucio", "Tracy", "Wan", "Cherrie", "Kindra", "Oren",
				"Barney", "Collen", "Loris", "Phebe", "Seth", "Camie", "Emery",
				"Renae", "Victor", "Mae", "Anh", "Mariann", "Elfrieda",
				"Lashaunda", "Una", "Maribel", "Yasmin", "Kit", "Pamila",
				"Petra", "Rossie", "Sadye", "Hollis", "Alphonso", "Terrie",
				"Bessie", "Mathilda", "Nadine", "Domonique", "Ava", "Lizette",
				"Jamaal", "Cornelius", "Lionel", "Ayesha", "Julene", "Hae",
				"Mackenzie", "Arlinda", "Lyda", "Kimiko", "Norris", "Agatha",
				"Piper", "Jeffrey", "Tamekia", "Treva", "Russel", "Deloras",
				"Maxwell", "Andria", "Ellena", "Hsiu", "Margrett", "Marina",
				"Kathe", "Tessa", "Adina", "Mahalia", "Candyce", "Terrence",
				"Min", "Evangeline", "Eric", "Nilsa", "Peg", "Neda", "Darci",
				"Buster", "Hyacinth", "Roosevelt", "Georgianne", "Valarie",
				"Kandra", "James", "Rhea", "Coral", "Shantay", "Laurel",
				"Ninfa", "Shayla", "Shonda", "Cyrus", "Hector", "Somer",
				"Celena", "Tennille", "Antionette", "Mitchell", "Isaiah",
				"Jaye", "Alexa" };
		String[] lastNamee = { "Smith", "Jones", "Brown", "Johnson",
				"Williams", "Miller", "Taylor", "Wilson", "Davis", "White",
				"Clark", "Hall", "Thomas", "Thompson", "Moore", "Hill",
				"Martin", "College", "Mary", "Hayes", "Page", "Rose",
				"Patterson", "Ann", "Crawford", "Arnold", "House", "Harvey",
				"Davidson", "Ferguson", "Park", "Myers", "Ryan", "Freeman",
				"Pierce", "Fellows", "Douglas", "Tucker", "Dixon", "Bradley",
				"Hopkins", "Potter", "Loyd", "Curtis", "Howell", "Carroll",
				"Edward", "Blair", "Joseph", "Reid", "Dunn", "Barker", "Dunn",
				"Armstrong", "Lawrence", "City", "Black", "Lane", "Pery",
				"Street", "Wheeler", "Gordon", "Powell", "Webster", };
		String[] countryy = { "Denmark", "Germany", "Sweden", "Finland",
				"Rusia", "Bulgaria", "UK", "Scotland", "Northen Ireland",
				"Republic of Ireland", "Romainia", "Moldova", "Slovakia",
				"Slovenia", "USA", "Brazil", "Portugal", "France", "Spain",
				"Hungary", "Canada", "China", "Israel", "Malta", "Malawi",
				"OAE", "Irak", "Turkey", "Greece", "Taiwan", "Australia",
				"Belgium", "Poland", "New Zealand", "Shri Lanka", "Georgia",
				"Egypt", "Livan", "Norway", "Chili", "Argentina", "C'divoar",
				"Estonia", "Litva" };
		String[] cityy = { "Copenhagen", "Aarhus", "Horsens", "Randers",
				"Vejle", "Odense", "Berlin", "Hamburg", "Dublin", "Honk Kong",
				"Bucurest", "Budapest", "Bratislava", "Sofia", "Cork", "Maimi",
				"Las Vegas", "Los Angelis", "Porto", "Milano", "Rome", "Viena",
				"Hanover", "Malta", "Oaho", "Dubai", "Zurich", "Barcelona",
				"Madrid", "Paris", "Sidney", "Moscow", "Istanbul", "London",
				"Prague", "Amsterdam", "Stockholm", "Riga", "Oslo", "Zagreb",
				"Reykjavik", "Edinburgh", "Florence", "Manchester" };
		String[] postcodee = { "2500", "2600", "2700", "2800", "2900", "3000",
				"3100", "3200", "3300", "3400", "3500", "3600", "8000", "8100",
				"8200", "8300", "8400", "8500", "20001", "20002", "20003",
				"20004", "20010", "20012", "06", "08", "3320", "213", "5588",
				"6676", "12", "15", "33456", "3366", "8913", "1355", "50",
				"213", "US22123", "AS5656", "12331", "KD22", "EM123", "9DD912" };
		String[] streett = { "Agerbakken", "SouthCircularRoad", "Mainstreet",
				"LowStreet", "HighSteet", "ShortStreet", "QueenStreet",
				"KingStreet", "street" };
		String[] nationalityy = { "Danish", "Romanian", "Bulgarian", "British",
				"Swedish", "French", "Belgium", "German", "Polish",
				"Portogies", "Russian", "Japanies", "Egyption", "Malawian",
				"American", "Serbian", "Norwigan", "Australian", "Italian",
				"Spanish" };
		String[] roomtypee = { "singleroom", "doubleroom", "singlesuite",
				"doublesuite", "triplesuite" };
		String[] trueOrFalse = { "true", "false" };
		for (int i = 0; i < 3005; i++) {
			number1 = randomNumber.nextInt(100);
			number2 = randomNumber.nextInt(164);
			number3 = randomNumber.nextInt(64);
			firstName.add(i, firstNamee[number1]);
			middleName.add(i, middleNamee[number2]);
			lastName.add(i, lastNamee[number3]);
			number4 = randomNumber.nextInt(10);
			while (number4 == 0) {
				number4 = randomNumber.nextInt(10);
				if (number4 != 0) {
					break;
				}
			}
			number5 = randomNumber.nextInt(10);
			number6 = randomNumber.nextInt(10);
			number7 = randomNumber.nextInt(10);
			number8 = randomNumber.nextInt(10);
			number9 = randomNumber.nextInt(10);
			number10 = randomNumber.nextInt(10);
			number11 = randomNumber.nextInt(10);
			phonenumber = "" + number4 + number5 + number6 + number7 + number8
					+ number9 + number10 + number11;
			phoneNumber.add(i, phonenumber);
			number12 = randomNumber.nextInt(44);
			country.add(i, countryy[number12]);
			number13 = randomNumber.nextInt(44);
			city.add(i, cityy[number13]);
			number14 = randomNumber.nextInt(44);
			postCode.add(i, postcodee[number14]);
			number15 = randomNumber.nextInt(9);
			number16 = randomNumber.nextInt(3);
			number17 = randomNumber.nextInt(5);
			number18 = randomNumber.nextInt(9);
			sstreet = streett[number15] + " " + number16 + number17 + number18;
			if (number16 == 0) {
				sstreet = streett[number15] + " " + number17 + number18;
			}
			street.add(i, sstreet);
			number19 = randomNumber.nextInt(20);
			nationality.add(nationalityy[number19]);
			number20 = randomNumber.nextInt(31);
			number21 = randomNumber.nextInt(12);
			number22 = randomNumber.nextInt(2017);
			if (number21 == 2) {
				number20 = randomNumber.nextInt(28);
			}
			while (number22 < 1920) {
				number22 = randomNumber.nextInt(2017);
			}
			bday = "" + number20 + "," + number21 + "," + number22;
			birthday.add(i, bday);
			number23 = randomNumber.nextInt(31);
			number24 = randomNumber.nextInt(12);
			number25 = randomNumber.nextInt(2017);
			if (number24 == 2) {
				number23 = randomNumber.nextInt(28);
			}
			while (number25 < 2015) {
				number25 = randomNumber.nextInt(2017);
			}
			number26 = number23 + (number26 = randomNumber.nextInt(10));
			number27 = number24 + (number27 = randomNumber.nextInt(2));
			number28 = number25;
			if (number27 == 12) {
				number28 = number25 + 1;
			}

			InOut = "" + number23 + "," + number24 + "," + number25 + ","
					+ number26 + "," + number27 + "," + number28;
			CheckInOut.add(i, InOut);
			number29 = randomNumber.nextInt(5);
			roomType.add(i, roomtypee[number29]);
			number30 = randomNumber.nextInt(2);
			initator.add(i, trueOrFalse[number30]);
			number31 = randomNumber.nextInt(2);
			lateArrivelNotice.add(i, trueOrFalse[number31]);
			number32 = randomNumber.nextInt(2);
			priorityGuest.add(i, trueOrFalse[number32]);
			System.out.println("done");

		}

	}

	public String getFirstName(int index) {
		return (String) firstName.get(index);
	}

	public String getMiddleName(int index) {
		return (String) middleName.get(index);
	}

	public String getLastName(int index) {
		return (String) lastName.get(index);
	}

	public String getPhoneNumber(int index) {
		return (String) phoneNumber.get(index);
	}

	public String getCountry(int index) {
		return (String) country.get(index);
	}

	public String getCity(int index) {
		return (String) city.get(index);
	}

	public String getPostCode(int index) {
		return (String) postCode.get(index);
	}

	public String getStreet(int index) {
		return (String) street.get(index);
	}

	public String getNationality(int index) {
		return (String) nationality.get(index);
	}

	public String getBirthday(int index) {
		return (String) birthday.get(index);
	}

	public String getCheckInOut(int index) {
		return (String) CheckInOut.get(index);
	}

	public String getRoomType(int index) {
		return (String) roomType.get(index);
	}

	public String getInitiator(int index) {
		return (String) initator.get(index);
	}

	public String getLateArrivalNotice(int index) {
		return (String) lateArrivelNotice.get(index);
	}

	public String getPriorityGuest(int index) {
		return (String) priorityGuest.get(index);
	}

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		FakeGuest random = new FakeGuest();
		System.out.println("bka");
		random.randomGuest();
		System.out.println("Start writing");
		ObjectOutputStream write = null;
		try {
			FileOutputStream FakeGuest = new FileOutputStream(
					"fakeguestList.bin");
			write = new ObjectOutputStream(FakeGuest);
			ArrayList<Reservation> temp = new ArrayList<>();
			for (int i = 0; i < 200; i++) {
				write.writeObject(random.getFirstName(i) + ","
						+ random.getMiddleName(i) + "," + random.getLastName(i)
						+ "," + random.getPhoneNumber(i) + ","
						+ random.getCountry(i) + "," + random.getCity(i) + ","
						+ random.getPostCode(i) + "," + random.getStreet(i)
						+ "," + random.getNationality(i) + ","
						+ random.getBirthday(i) + "," + random.getCheckInOut(i)
						+ "," + random.getRoomType(i) + ","
						+ random.getInitiator(i) + ","
						+ random.getLateArrivalNotice(i) + ","
						+ random.getPriorityGuest(i));

			}

		} finally {
			if (write != null) {
				try {
					write.close();
				} catch (IOException e) {
					System.out.println("IO Error closing file ");
				}
			}
		}
		System.out.println("done writing");
		System.out.println("Start reading");
		ArrayList<Object> objs = new ArrayList<Object>();
		ObjectInputStream read = null;
		try {
			FileInputStream FakeGuest = new FileInputStream("fakeguestList.bin");
			read = new ObjectInputStream(FakeGuest);
			while (true) {
				try {

					System.out.println(read.readObject());
				} catch (EOFException eof) {
					System.out.println("End of file");
					break;
				}
			}
		} finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					System.out.println("IO Error closing file ");
				}
			}
		}
		System.out.println("End");

	}
}
