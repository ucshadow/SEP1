import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A class containing the GUI for check in.
 *
 * @author Radu G Orleanu, Yusuf A Farah
 * @version 1.0
 */
public class CheckInGUI {

    private JPanel mainPanelForFields, mainPanelForLabels, leftPanel;
    private JLabel firstName, middleName, lastName, country, city, postCode, street,
            phoneNumber, nationality, dateOfBirth, arrival, departure, roomType, roomNumberLabel;
    private JComboBox roomNumberField;
    private ArrayList<JLabel> allJLabelsForFields, allJLabelsForLabels;
    private Reservation res;
    private MyButtonListener listener;
    private JButton checkIn, cancel;
    private JTabbedPane parent;
    private ArrayList<Integer> singleRooms, twinRoom, kingSize, kingSize2, singleSuite, doubleSuite, tripleSuite;

    /**
     * No-argument constructor initializing the CheckInGUI's tab, design, JLabels and the button listener.
     */

    public CheckInGUI(JTabbedPane parent) {

        this.parent = parent;
        allJLabelsForFields = new ArrayList<>();
        allJLabelsForLabels = new ArrayList<>();
        listener = new MyButtonListener();

        designGUI();


    }

    /**
     * Method for creating the design of the GUI.
     */

    public void designGUI() {

        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 920));

        mainPanelForFields = new JPanel(new GridLayout(15, 1, 2, 2));
        mainPanelForFields.setPreferredSize(new Dimension(200, 700));


        mainPanelForLabels = new JPanel(new GridLayout(15, 1, 2, 2));
        mainPanelForLabels.setPreferredSize(new Dimension(100, 700));

        cancel = new JButton("Cancel");
        cancel.addActionListener(listener);

        checkIn = new JButton("Check in");
        checkIn.addActionListener(listener);

        roomNumberField = new JComboBox();
        Font f = new Font("Arial", Font.BOLD, 25);
        roomNumberField.setPreferredSize(new Dimension(100, 25));
        roomNumberField.setFont(f);

        allJLabelsForFields.add(firstName = new JLabel(""));
        allJLabelsForFields.add(middleName = new JLabel(""));
        allJLabelsForFields.add(lastName = new JLabel(""));
        allJLabelsForFields.add(country = new JLabel(""));
        allJLabelsForFields.add(city = new JLabel(""));
        allJLabelsForFields.add(postCode = new JLabel(""));
        allJLabelsForFields.add(street = new JLabel(""));
        allJLabelsForFields.add(phoneNumber = new JLabel(""));
        allJLabelsForFields.add(nationality = new JLabel(""));
        allJLabelsForFields.add(dateOfBirth = new JLabel(""));
        allJLabelsForFields.add(arrival = new JLabel(""));
        allJLabelsForFields.add(departure = new JLabel(""));
        allJLabelsForFields.add(roomType = new JLabel(""));
        allJLabelsForFields.add(roomNumberLabel = new JLabel("asd"));
        allJLabelsForLabels.add(new JLabel("First name"));
        allJLabelsForLabels.add(new JLabel("Middle name"));
        allJLabelsForLabels.add(new JLabel("Last name"));
        allJLabelsForLabels.add(new JLabel("Country"));
        allJLabelsForLabels.add(new JLabel("City"));
        allJLabelsForLabels.add(new JLabel("Post code"));
        allJLabelsForLabels.add(new JLabel("Street"));
        allJLabelsForLabels.add(new JLabel("Phone number"));
        allJLabelsForLabels.add(new JLabel("Nationality"));
        allJLabelsForLabels.add(new JLabel("Date of birth"));
        allJLabelsForLabels.add(new JLabel("Arrival"));
        allJLabelsForLabels.add(new JLabel("Departure"));
        allJLabelsForLabels.add(new JLabel("Room type"));
        allJLabelsForLabels.add(new JLabel("Room number"));
        for (int i = 0; i < allJLabelsForFields.size(); i++) {
            mainPanelForFields.add(allJLabelsForFields.get(i));
            mainPanelForLabels.add(allJLabelsForLabels.get(i));
        }

        mainPanelForFields.remove(mainPanelForFields.getComponent(13));
        mainPanelForFields.add(roomNumberField, 13);

        leftPanel.add(mainPanelForLabels, BorderLayout.WEST);
        leftPanel.add(mainPanelForFields, BorderLayout.EAST);
        leftPanel.add(checkIn);
        leftPanel.add(cancel);
    }

    /**
     * Method for preparing an object for check in.
     *
     * @param res the reservation used for check in
     */

    public void getDataForCheckIn(Reservation res) {
        this.res = res;
        firstName.setText(res.getGuest().getName().getFirstName());
        middleName.setText(res.getGuest().getName().getMiddleName());
        lastName.setText(res.getGuest().getName().getLastName());
        country.setText(res.getGuest().getAddress().getCountry());
        city.setText(res.getGuest().getAddress().getCity());
        postCode.setText(res.getGuest().getAddress().getPostCode());
        street.setText(res.getGuest().getAddress().getStreet());
        phoneNumber.setText(String.valueOf(res.getGuest().getPhoneNumber()));
        nationality.setText(res.getGuest().getNationality());
        dateOfBirth.setText(String.valueOf(res.getGuest().getDateOfBirth()));
        arrival.setText(String.valueOf(res.getArrival().getCheckInDate()));
        departure.setText(String.valueOf(res.getDeparture().getCheckOutDate()));
        roomType.setText(String.valueOf(res.getRoomType()));

    }


    /**
     * Action listener for buttons.
     */

    private class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == checkIn) {
                HotelManager hm = new HotelManager();
                hm.checkIn(res, Integer.parseInt(roomNumberField.getSelectedItem().toString()));
                parent.setSelectedIndex(0);
            }
            if (e.getSource() == cancel) {
                roomNumberField = new JComboBox();
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit the check in", "Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    parent.setSelectedIndex(0);
                }
            }
        }
    }

    /**
     * A method that generates an ArrayList of integers representing the room numbers.
     *
     * @param number1 the first integer
     * @param number2 the second integer
     * @return ArrayList of integers containing integers representing all room numbers.
     */

    public ArrayList<Integer> generateRoomNumber(int number1, int number2) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = number1; i < number2 + 1; i++) {
            temp.add(i);
            if (number1 == number2) {
                break;
            }
        }
        return temp;
    }

    /**
     * A method to set room numbers for each roomType and then remove the room number from the available room section if that room has a guest
     *
     * @param res the specific reservation
     */

    public void setRoomNumber(Reservation res) {


        ArrayList<Reservation> inHouse = new FileAdapter().getAllGuests("inHouseGuests.bin");
        singleRooms = generateRoomNumber(101, 110);
        twinRoom = generateRoomNumber(111, 116);
        kingSize = generateRoomNumber(117, 119);
        kingSize2 = generateRoomNumber(201, 219);
        for (int i = 0; i < kingSize2.size(); i++) {
            kingSize.add(kingSize2.get(i));
        }
        singleSuite = generateRoomNumber(303, 304);
        doubleSuite = new ArrayList<Integer>();
        doubleSuite.add(302);
        tripleSuite = new ArrayList<Integer>();
        tripleSuite.add(301);


        for (int i = 0; i < inHouse.size(); i++) {
            if (singleRooms.contains(inHouse.get(i).getRoomNumber())) {
                singleRooms.remove(singleRooms.indexOf(inHouse.get(i).getRoomNumber()));
            }
            if (twinRoom.contains(inHouse.get(i).getRoomNumber())) {
                twinRoom.remove(twinRoom.indexOf(inHouse.get(i).getRoomNumber()));
            }
            if (kingSize.contains(inHouse.get(i).getRoomNumber())) {
                kingSize.remove(kingSize.indexOf(inHouse.get(i).getRoomNumber()));
            }
            if (singleSuite.contains(inHouse.get(i).getRoomNumber())) {
                singleSuite.remove(singleSuite.indexOf(inHouse.get(i).getRoomNumber()));
            }
            if (doubleSuite.contains(inHouse.get(i).getRoomNumber())) {
                doubleSuite.remove(doubleSuite.indexOf(inHouse.get(i).getRoomNumber()));
            }
            if (tripleSuite.contains(inHouse.get(i).getRoomNumber())) {
                tripleSuite.remove(tripleSuite.indexOf(inHouse.get(i).getRoomNumber()));
            }
        }
        roomNumberField.revalidate();


        if (res.getRoomType().equals("single room")) {

            for (int i = 0; i < singleRooms.size(); i++) {

                roomNumberField.addItem(singleRooms.get(i));
            }
        }
        if (res.getRoomType().equals("double room-twin beds")) {

            for (int i = 0; i < twinRoom.size(); i++) {
                roomNumberField.addItem(twinRoom.get(i));
            }
        }
        if (res.getRoomType().equals("double room-kingsize")) {
            for (int i = 0; i < kingSize.size(); i++) {
                roomNumberField.addItem(kingSize.get(i));
            }
        }
        if (res.getRoomType().equals("single bedroom suite")) {
            for (int i = 0; i < singleSuite.size(); i++) {
                roomNumberField.addItem(singleSuite.get(i));
            }
        }
        if (res.getRoomType().equals("three bedroom suite")) {
            for (int i = 0; i < tripleSuite.size(); i++) {
                roomNumberField.addItem(tripleSuite.get(i));
            }
        }
        if (res.getRoomType().equals("two bedroom suite")) {
            for (int i = 0; i < doubleSuite.size(); i++) {
                roomNumberField.addItem(doubleSuite.get(i));
            }
        }

    }

    /**
     * A method to return the leftPanel of this class as a JPanel.
     *
     * @return JPanel, leftPanel representing the main panel of this tab, where all GUI elements from check in are contained
     */

    public JPanel getAvailableTab() {
        return leftPanel;
    }
}