import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * A class containing the GUI for check out.
 *
 * @author Nikolay D Nikolov
 * @version 1.0
 */

// toDo: discount price is busted...

public class CheckOutGUI {

    //private JFrame mainFrame;
    private JPanel mainPanelForFields, mainPanelForLabels, leftPanel;
    private JLabel firstName, middleName, lastName, country, city, postCode, street,
            phoneNumber, nationality, dateOfBirth, arrival, departure, roomType, roomNumber, price, warnings;
    private JTextField discountField;
    private ArrayList<JLabel> allJLabelsForFields, allJLabelsForLabels;
    private Reservation reservation;
    private MyButtonListener listener;
    private KeyPressEvent keyLogger;
    private JButton checkOutButton, cancel;
    private JTabbedPane parent;
    private HotelManager hm = new HotelManager();

    /**
     * No-argument constructor initializing the CheckOutGUI's tab, design, JLabels and the button and key press listener.
     */

    public CheckOutGUI(JTabbedPane parent) {
        this.parent = parent;
        allJLabelsForFields = new ArrayList<>();
        allJLabelsForLabels = new ArrayList<>();
        listener = new MyButtonListener();
        keyLogger = new KeyPressEvent();

        prepareGUI();


    }

    /**
     * Method for creating the design on the GUI.
     */

    public void prepareGUI() {
        designGUI();
    }

    /**
     * Method for GUI design.
     */

    public void designGUI() {
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 920));

        mainPanelForFields = new JPanel(new GridLayout(16, 1, 2, 2));
        mainPanelForFields.setPreferredSize(new Dimension(300, 700));


        mainPanelForLabels = new JPanel(new GridLayout(16, 1, 2, 2));
        mainPanelForLabels.setPreferredSize(new Dimension(100, 700));

        cancel = new JButton("Cancel");
        cancel.addActionListener(listener);

        checkOutButton = new JButton("Check out");
        checkOutButton.addActionListener(listener);

        discountField = new JTextField();
        discountField.addKeyListener(keyLogger);
        discountField.setPreferredSize(new Dimension(100, 20));

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
        allJLabelsForFields.add(roomNumber = new JLabel(""));
        allJLabelsForFields.add(price = new JLabel(""));
        allJLabelsForFields.add(warnings = new JLabel(""));
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
        allJLabelsForLabels.add(new JLabel("Price"));
        allJLabelsForLabels.add(new JLabel("Warnings"));
        for (int i = 0; i < allJLabelsForFields.size(); i++) {
            mainPanelForFields.add(allJLabelsForFields.get(i));
            mainPanelForLabels.add(allJLabelsForLabels.get(i));
        }


        leftPanel.add(mainPanelForLabels, BorderLayout.WEST);
        leftPanel.add(mainPanelForFields, BorderLayout.EAST);
        leftPanel.add(new JLabel("Discount"), BorderLayout.WEST);
        leftPanel.add(discountField);
        leftPanel.add(checkOutButton);
        leftPanel.add(cancel);


    }

    /**
     * Method for preparing an object for check out.
     */

    public void getDataForCheckOut() {
        firstName.setText(reservation.getGuest().getName().getFirstName());
        middleName.setText(reservation.getGuest().getName().getMiddleName());
        lastName.setText(reservation.getGuest().getName().getLastName());
        country.setText(reservation.getGuest().getAddress().getCountry());
        city.setText(reservation.getGuest().getAddress().getCity());
        postCode.setText(reservation.getGuest().getAddress().getPostCode());
        street.setText(reservation.getGuest().getAddress().getStreet());
        phoneNumber.setText(String.valueOf(reservation.getGuest().getPhoneNumber()));
        nationality.setText(reservation.getGuest().getNationality());
        dateOfBirth.setText(String.valueOf(reservation.getGuest().getDateOfBirth()));
        arrival.setText(String.valueOf(reservation.getArrival().getCheckInDate()));
        departure.setText(String.valueOf(reservation.getDeparture().getCheckOutDate()));
        roomType.setText(String.valueOf(reservation.getRoomType()));
        roomNumber.setText(String.valueOf(reservation.getRoomNumber()));
        price.setText(String.valueOf(hm.getTotalPrice(reservation, 0)));

    }

    /**
     * Button listener which implements ActionListener.
     */
    private class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == checkOutButton) {
                hm.checkOut(reservation);
                parent.setSelectedIndex(0);
            }
            if (e.getSource() == cancel) {
                int choice = JOptionPane.showConfirmDialog(null,
                        "Do you want to exit the check out", "Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    parent.setSelectedIndex(0);
                }
            }

        }
    }

    /**
     * KeyListener for the discount field. Activates each time a key is released in the discount field.
     */
    private class KeyPressEvent implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {

            if (isValidNumber(discountField.getText())) {
                double discount = Double.parseDouble(discountField.getText());
                if (discount >= 0 && discount <= 100) {
                    price.setText(hm.getTotalPrice(reservation, Double.parseDouble(discountField.getText())));
                    warnings.setText("");
                } else {
                    warnings.setText("<html><font color='red'>Discount should be between 0 and 100</font></html>");
                }

            }
            if (!isValidNumber(discountField.getText()) ||
                    discountField.getText().isEmpty()) {
                price.setText(hm.getTotalPrice(reservation, 0));
            }
            if(!isValidNumber(discountField.getText())) {
                warnings.setText("<html><font color='red'>Discount should be between 0 and 100</font></html>");
            }
        }
    }

    /**
     * verifies if the String can be parsed as a double.
     * @param num number to be checked
     * @return true or false. If the entire string can be parsed as a double, the method will return true, else it returns false.
     */
    public boolean isValidNumber(String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *  Sets the reservation in Check out window to the reservation that was passed to this method.
     * @param reservation
     */
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * Returns the CheckOut main JPanel
     * @return leftPanel, the main JPanel of this class
     */

    public JPanel getAvailableTab() {
        return leftPanel;
    }
}