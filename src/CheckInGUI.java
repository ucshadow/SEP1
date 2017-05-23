import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JTextField roomNumberField;
    private ArrayList<JLabel> allJlabelsForFields, allJlabelsForLabels;
    private ArrayList<Reservation> allInHouseGuests;
    private Reservation res;
    private MyButtonListener listener;
    private JButton checkIn, cancel;
    //private JTabbedPane checkOut;

    /**
     * No-argument constructor initializing the check out GUI.
     */
    public CheckInGUI() {

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

        roomNumberField = new JTextField();
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

    /**
     * Action listener for buttons.
     */
    private class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //checkIn button for checking in the person.
            if (e.getSource() == checkIn){
                HotelManager hm  = new HotelManager();
                hm.checkIn(res, Integer.parseInt(roomNumberField.getText()));
            }
                    //cancel button to close the window.
            else if (e.getSource() == cancel) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit the check in", "Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(1);
                }
            }
        }
    }

    public JPanel getAvailableTab() {
        return leftPanel;
    }
}