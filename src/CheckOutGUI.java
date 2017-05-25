import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

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
    private ArrayList<Reservation> allInHouseGuests;
    private double discount;
    private Reservation res;
    private MyButtonListener listener;
    private KeyPressEvent keyLogger;
    private JButton checkOutButton, cancel;
    private JTabbedPane parent;
    private HotelManager hm = new HotelManager();

    /**
     * No-argument constructor initializing the check out GUI.
     */
    public CheckOutGUI(JTabbedPane parent) {
        this.parent = parent;

        allInHouseGuests = new ArrayList<>();
        allJLabelsForFields = new ArrayList<>();
        allJLabelsForLabels = new ArrayList<>();
        listener = new MyButtonListener();
        keyLogger = new KeyPressEvent();

        prepareGUI();


    }

    /**
     * Method preparing GUI for starting.
     */
    public void prepareGUI() {
        designGUI();
    }

    /**
     * Method for GUI design.
     */
    public void designGUI() {

        //getDataForCheckIn(res);
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
     * Method preparing an object for check out.
     */

    public void getDataForCheckOut() {
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
        roomNumber.setText(String.valueOf(res.getRoomNumber()));
        price.setText(String.valueOf(hm.getTotalPrice(res, discount)));

    }

    /**
     * Action listener for buttons.
     */
    private class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //checkOutButton button for checking out the person.
            if (e.getSource() == checkOutButton) {
                hm.checkOut(res);
                parent.setSelectedIndex(0);
            }
            //cancel button to close the window.
            if (e.getSource() == cancel) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit the check out", "Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    parent.setSelectedIndex(0);
                }
            }

        }
    }

    class KeyPressEvent implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {

            if (isValidNumber(discountField.getText())) {
                double discount = Double.parseDouble(discountField.getText());
                if (e.getSource() == discountField && discount >= 0 && discount <= 100) {
                    price.setText(hm.getTotalPrice(res, Double.parseDouble(discountField.getText())));
                    warnings.setText("");
                } else {
                    warnings.setText("<html><font color='red'>Discount should be between 0 and 100</font></html>");
                }

            }
            if (e.getSource() == discountField && !isValidNumber(discountField.getText()) ||
                    discountField.getText().isEmpty()) {
                price.setText(hm.getTotalPrice(res, 0));
            }
            if(!isValidNumber(discountField.getText())) {
                warnings.setText("<html><font color='red'>Discount should be between 0 and 100</font></html>");
            }
        }
    }

    public boolean isValidNumber(String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setRes(Reservation res) {
        this.res = res;
    }

    public JPanel getAvailableTab() {
        return leftPanel;
    }
}