import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class containing the GUI for check out.
 *
 * @author Nikolay D Nikolov
 * @version 1.0
 */
public class CheckInGUI {

    //private JFrame mainFrame;
    private JPanel mainPanelForFields, mainPanelForLabels, leftPanel;
    private JLabel firstName, middleName, lastName, country, city, postCode, street,
            phoneNumber, nationality, dateOfBirth, arrival, departure, roomType, roomNumberLabel;
    private JComboBox<Integer> roomNumberField;
    private ArrayList<JLabel> allJlabelsForFields, allJlabelsForLabels;
    private ArrayList<Reservation> allInHouseGuests;
    private Reservation res;
    private MyButtonListener listener;
    private JButton checkIn, cancel;
    private JTabbedPane parent;
    //private JTabbedPane checkOut;

    /**
     * No-argument constructor initializing the check out GUI.
     */
    public CheckInGUI(JTabbedPane parent) {

        this.parent = parent;

        allInHouseGuests = new ArrayList<Reservation>();
        allJlabelsForFields = new ArrayList<JLabel>();
        allJlabelsForLabels = new ArrayList<JLabel>();
        listener = new MyButtonListener();

        prepareGUI();


    }

    /**
     * Method preparing GUI for starting.
     */
    public void prepareGUI() {
        designGUI();
        //checkOut = new JTabbedPane();
//
//        mainFrame = new JFrame("Check out");
//        mainFrame.add(leftPanel);
//
//        mainFrame.add(checkOut);
        //checkOut.addTab("Check out",leftPanel);
//        mainFrame.setSize(1440, 960);
//        mainFrame.setVisible(true);
//        mainFrame.setResizable(false);
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame.setLocationRelativeTo(null);
    }

    /**
     * Method for GUI design.
     */
    public void designGUI() {

        //getDataForCheckIn(res);
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

        roomNumberField = new JComboBox<>();
        Font f = new Font("Arial", Font.BOLD, 25);
        roomNumberField.setPreferredSize(new Dimension(100, 25));
        roomNumberField.setFont(f);

        allJlabelsForFields.add(firstName = new JLabel(""));
        allJlabelsForFields.add(middleName = new JLabel(""));
        allJlabelsForFields.add(lastName = new JLabel(""));
        allJlabelsForFields.add(country = new JLabel(""));
        allJlabelsForFields.add(city = new JLabel(""));
        allJlabelsForFields.add(postCode = new JLabel(""));
        allJlabelsForFields.add(street = new JLabel(""));
        allJlabelsForFields.add(phoneNumber = new JLabel(""));
        allJlabelsForFields.add(nationality = new JLabel(""));
        allJlabelsForFields.add(dateOfBirth = new JLabel(""));
        allJlabelsForFields.add(arrival = new JLabel(""));
        allJlabelsForFields.add(departure = new JLabel(""));
        allJlabelsForFields.add(roomType = new JLabel(""));
        allJlabelsForFields.add(roomNumberLabel = new JLabel("asd"));
        allJlabelsForLabels.add(new JLabel("First name"));
        allJlabelsForLabels.add(new JLabel("Middle name"));
        allJlabelsForLabels.add(new JLabel("Last name"));
        allJlabelsForLabels.add(new JLabel("Country"));
        allJlabelsForLabels.add(new JLabel("City"));
        allJlabelsForLabels.add(new JLabel("Post code"));
        allJlabelsForLabels.add(new JLabel("Street"));
        allJlabelsForLabels.add(new JLabel("Phone number"));
        allJlabelsForLabels.add(new JLabel("Nationality"));
        allJlabelsForLabels.add(new JLabel("Date of birth"));
        allJlabelsForLabels.add(new JLabel("Arrival"));
        allJlabelsForLabels.add(new JLabel("Departure"));
        allJlabelsForLabels.add(new JLabel("Room type"));
        allJlabelsForLabels.add(new JLabel("Room number"));
        for (int i = 0; i < allJlabelsForFields.size(); i++) {
            mainPanelForFields.add(allJlabelsForFields.get(i));
            mainPanelForLabels.add(allJlabelsForLabels.get(i));
        }

        System.out.println(mainPanelForFields.getComponent(13).toString());
        mainPanelForFields.remove(mainPanelForFields.getComponent(13));
        mainPanelForFields.add(roomNumberField, 13);

        //mainPanelForFields.add(roomNumberField);


        leftPanel.add(mainPanelForLabels, BorderLayout.WEST);
        leftPanel.add(mainPanelForFields, BorderLayout.EAST);
        leftPanel.add(checkIn);
        leftPanel.add(cancel);
    }

    /**
     * Method preparing an object for check out.
     *
     * @param res the reservation for check out
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


    public void setRoomNumber(Reservation res) {

        ArrayList<Reservation> inHouse = new FileAdapter().getAllGuests("inHouseGuests.bin");

        ArrayList<Integer> singleRooms = generateRoomNumber(101, 110);
        ArrayList<Integer> twinRoom = generateRoomNumber(111, 116);
        ArrayList<Integer> kingSize = generateRoomNumber(117, 119);
        ArrayList<Integer> kingSize2 = generateRoomNumber(201, 219);
        for (int i = 0; i < kingSize2.size(); i++) {
            kingSize.add(kingSize2.get(i));
        }
        ArrayList<Integer> singleSuite = generateRoomNumber(303, 304);
        ArrayList<Integer> doubleSuite = new ArrayList<Integer>();
        doubleSuite.add(302);
        ArrayList<Integer> tripleSuite = new ArrayList<Integer>();
        tripleSuite.add(301);


        for (int i = 0; i < inHouse.size(); i++) {
            if (singleRooms.contains(inHouse.get(i).getRoomNumber())) {
                singleRooms.remove(inHouse.get(i).getRoomNumber());
            }
            if (twinRoom.contains(inHouse.get(i).getRoomNumber())) {
                twinRoom.remove(inHouse.get(i).getRoomNumber());
            }
            if (kingSize.contains(inHouse.get(i).getRoomNumber())) {
                kingSize.remove(inHouse.get(i).getRoomNumber());
            }
            if (singleSuite.contains(inHouse.get(i).getRoomNumber())) {
                singleSuite.remove(inHouse.get(i).getRoomNumber());
            }
            if (doubleSuite.contains(inHouse.get(i).getRoomNumber())) {
                doubleSuite.remove(inHouse.get(i).getRoomNumber());
            }
            if (tripleSuite.contains(inHouse.get(i).getRoomNumber())) {
                tripleSuite.remove(inHouse.get(i).getRoomNumber());
            }
        }

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
    // toDo: add waring for bad input

    /**
     * Action listener for buttons.
     */
    private class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //checkIn button for checking in the person.
            if (e.getSource() == checkIn) {
                HotelManager hm = new HotelManager();
                hm.checkIn(res, Integer.parseInt(roomNumberField.getSelectedItem().toString()));
                parent.setSelectedIndex(0);
            }
            if (e.getSource() == cancel) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit the check in", "Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    parent.setSelectedIndex(0);
                }
            }
        }
    }

//    public boolean isValidNumber(String num) {
//        try {
//            Integer.parseInt(num);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public JPanel getAvailableTab() {
        return leftPanel;
    }
}