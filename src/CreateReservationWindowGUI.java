import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateReservationWindowGUI {
    private JFrame mainFrame;
    private JTabbedPane CreateReservationPane;
    private JPanel reservationPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel guestDataLabels;
    private JPanel guestDataTextFields;
    private JPanel guestDataCheckBoxes;
    private JLabel firstNameLabel, middleNameLabel, lastNameLabel, countryLabel, cityLabel, postCodeLabel, streetLabel, phoneNumberLabel, nationalityLabel, dateOfBirthLabel, arrivalLabel, departureLabel, roomTypeLabel, bookingInitiatorLabel, lateArrivalNoticeLabel, priorityGuestLabel;
    private JTextField firstName, middleName, lastName, country, city, postCode, street, phoneNumber, nationality, dateOfBirth, arrival, departure, roomType;
    private JCheckBox bookingInitiator, lateArrivalNotice, priorityGuest;
    private JList allGuests;
    private JScrollPane allGuestScroll;
    private JButton save;
    private ArrayList<JLabel> allJLabels;
    private ArrayList<JTextField> allTextFields;
    private ArrayList<Reservation> allReservations;
    private MyButtonListener listner;

    public CreateReservationWindowGUI() {
        listner = new MyButtonListener();
        mainFrame = new JFrame("Create reservation");
        save = new JButton("SAVE");
        save.addActionListener(listner);
        left();
        takeAllGuest();
        right();
        reservationPanel = new JPanel();
        reservationPanel.setPreferredSize(new Dimension(1440, 960));
        reservationPanel.add(leftPanel, BorderLayout.WEST);
        reservationPanel.add(rightPanel, BorderLayout.EAST);
        reservationPanel.add(save,BorderLayout.SOUTH);
        CreateReservationPane = new JTabbedPane();
        CreateReservationPane.addTab("CreateReservation", reservationPanel);
        mainFrame.add(CreateReservationPane);

        mainFrame.setSize(1440, 960);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

        // Exit the application when the window is closed
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center window to screen
        mainFrame.setLocationRelativeTo(null);

    }

    private class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == save) {
                CreaterForReservation();

            }
        }

    }

    public void left() {
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(700, 700));

        guestDataLabels = new JPanel(new GridLayout(13, 1, 2, 2));
        guestDataLabels.setPreferredSize(new Dimension(100, 450));

        guestDataCheckBoxes = new JPanel(new GridLayout(3, 3, 2, 2));
        guestDataCheckBoxes.setPreferredSize(new Dimension(450, 150));

        guestDataTextFields = new JPanel(new GridLayout(13, 1, 2, 2));
        guestDataTextFields.setPreferredSize(new Dimension(350, 450));

        allTextFields = new ArrayList<JTextField>();
        allJLabels = new ArrayList<JLabel>();

        allTextFields.add(firstName = new JTextField());
        allTextFields.add(middleName = new JTextField());
        allTextFields.add(lastName = new JTextField());
        allTextFields.add(country = new JTextField());
        allTextFields.add(city = new JTextField());
        allTextFields.add(postCode = new JTextField());
        allTextFields.add(street = new JTextField());
        allTextFields.add(phoneNumber = new JTextField());
        allTextFields.add(nationality = new JTextField());
        allTextFields.add(dateOfBirth = new JTextField());
        allTextFields.add(arrival = new JTextField());
        allTextFields.add(departure = new JTextField());
        allTextFields.add(roomType = new JTextField());

        allJLabels.add(firstNameLabel = new JLabel("First name"));
        allJLabels.add(middleNameLabel = new JLabel("Middle name"));
        allJLabels.add(lastNameLabel = new JLabel("Last name"));
        allJLabels.add(countryLabel = new JLabel("Country"));
        allJLabels.add(cityLabel = new JLabel("City"));
        allJLabels.add(postCodeLabel = new JLabel("Post code"));
        allJLabels.add(streetLabel = new JLabel("Street"));
        allJLabels.add(phoneNumberLabel = new JLabel("Phone number"));
        allJLabels.add(nationalityLabel = new JLabel("Nationality"));
        allJLabels.add(dateOfBirthLabel = new JLabel("Date of birth"));
        allJLabels.add(arrivalLabel = new JLabel("Arrival"));
        allJLabels.add(departureLabel = new JLabel("Departure"));
        allJLabels.add(roomTypeLabel = new JLabel("Room type"));

        guestDataCheckBoxes.add(bookingInitiatorLabel = new JLabel("Booking initiator"));
        guestDataCheckBoxes.add(bookingInitiator = new JCheckBox());
        guestDataCheckBoxes.add(lateArrivalNoticeLabel = new JLabel("Late arrival notice"));
        guestDataCheckBoxes.add(lateArrivalNotice = new JCheckBox());
        guestDataCheckBoxes.add(priorityGuestLabel = new JLabel("Priority guest"));
        guestDataCheckBoxes.add(priorityGuest = new JCheckBox());

        for (int i = 0; i < allJLabels.size(); i++) {
            guestDataLabels.add(allJLabels.get(i));
        }

        for (int i = 0; i < allTextFields.size(); i++) {
            guestDataTextFields.add(allTextFields.get(i));
        }

        leftPanel.add(guestDataLabels);
        leftPanel.add(guestDataTextFields);
        leftPanel.add(guestDataCheckBoxes);
    }


    public void right() {

        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(600, 700));

        allGuests = new JList(allReservations.toArray());
        allGuests.setPreferredSize(new Dimension(600, 700));

        allGuestScroll = new JScrollPane(allGuests);
        allGuestScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        allGuestScroll.setPreferredSize(new Dimension(600, 400));
        rightPanel.add(allGuestScroll);

    }

    public ArrayList<Reservation> takeAllGuest() {
        FileAdapter fa = new FileAdapter();
        ArrayList<Reservation> reservations = fa.getAllGuests("reservations.bin");
        ArrayList<Reservation> pastReservation = fa.getAllGuests("pastReservations.bin");
        ArrayList<Reservation> inHouse = fa.getAllGuests("inHouseGuests.bin");
        allReservations = new ArrayList<Reservation>();
        int a = 0;
        for (int i = 0; i < reservations.size(); i++) {
            allReservations.add(reservations.get(i));
            a++;
        }
        int b = a;
        for (int j = 0; j < pastReservation.size(); j++) {
            allReservations.add(b,pastReservation.get(j));
            b++;
        }
        int c = b;
        for (int k = 0; k < inHouse.size(); k++) {
            allReservations.add(c,inHouse.get(k));
        }
        return allReservations;
    }


    public void CreaterForReservation() {


        DateHandler date = new DateHandler(Integer.parseInt(dateOfBirth.getText().split("/")[0]),
                Integer.parseInt(dateOfBirth.getText().split("/")[1]),
                Integer.parseInt(dateOfBirth.getText().split("/")[2]));

        Arrival arr = new Arrival(new DateHandler(Integer.parseInt(arrival.getText().split("/")[0]),
                Integer.parseInt(arrival.getText().split("/")[1]),
                Integer.parseInt(arrival.getText().split("/")[2])));
        Departure dep = new Departure(new DateHandler(Integer.parseInt(departure.getText().split("/")[0]),
                Integer.parseInt(departure.getText().split("/")[1]),
                Integer.parseInt(departure.getText().split("/")[2])));
        Name name = new Name(firstName.getText(), middleName.getText(), lastName.getText());
        Address add = new Address(country.getText(), city.getText(), postCode.getText(), street.getText());
        Guest guest = new Guest(name, Long.parseLong(phoneNumber.getText()), add, nationality.getText(), dateOfBirth.getText());
        Reservation rs = new Reservation(guest, arr, dep, roomType.getText(), bookingInitiator.isSelected(),
                lateArrivalNotice.isSelected(), priorityGuest.isSelected());
        HotelManager hm = new HotelManager();
        hm.createReservation(rs);
        System.out.println("We did itttttt");
    }


    public static void main(String[] args) {
        CreateReservationWindowGUI a = new CreateReservationWindowGUI();
    }

}
