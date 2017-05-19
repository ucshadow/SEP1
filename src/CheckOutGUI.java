import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by ndnik on 19/05/2017.
 */
public class CheckOutGUI {

    private JFrame mainFrame;
    private JPanel mainPanelForFields, mainPanelForLabels, leftPanel;
    private JLabel firstName, middleName, lastName, country, city, postCode, street,
            phoneNumber, nationality, dateOfBirth, arrival, departure, roomType, roomNumber, price,
            firstNameLabel, middleNameLabel, lastNameLabel, countryLabel, cityLabel, postCodeLabel, streetLabel,
            phoneNumberLabel, nationalityLabel, dateOfBirthLabel, arrivalLabel, departureLabel, roomTypeLabel, roomNumberLabel, priceLabel, discountLabel;
    private JTextField discountField;
    private ArrayList<JLabel> allJlabelsForFields, allJlabelsForLabels;
    private ArrayList<Reservation> allInHouseGuests;
    private String firstNameString, middleNameString, lastNameString, countryString,
            cityString, postCodeString, streetString, phoneNumberString,
            nationalityString, dateOfBirthString, arrivalString, departureString,
            roomTypeString, roomNumberString, priceString;
    private double discount;
    private Reservation res;
    private MyButtonListener listener;
    private JButton calculate, cancel;

    public CheckOutGUI() {
        allInHouseGuests = new ArrayList<Reservation>();
        allJlabelsForFields = new ArrayList<JLabel>();
        allJlabelsForLabels = new ArrayList<JLabel>();
        listener = new MyButtonListener();

        prepareGUI();


    }

    public void prepareGUI() {
        designGUI();

        mainFrame = new JFrame("Check out");
        mainFrame.add(leftPanel);


        mainFrame.setSize(1440, 960);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
    }

    public void designGUI() {

        getDataForCheckOut(res);
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 920));

        mainPanelForFields = new JPanel(new GridLayout(15, 1, 2, 2));
        mainPanelForFields.setPreferredSize(new Dimension(100, 700));


        mainPanelForLabels = new JPanel(new GridLayout(15, 1, 2, 2));
        mainPanelForLabels.setPreferredSize(new Dimension(100, 700));

        cancel = new JButton("Cancel");
        cancel.addActionListener(listener);

        calculate = new JButton("Calculate");
        calculate.addActionListener(listener);

        discountField = new JTextField();
        discountField.addActionListener(listener);
        discountField.setPreferredSize(new Dimension(100, 20));

        allJlabelsForFields.add(firstName = new JLabel(firstNameString));
        allJlabelsForFields.add(middleName = new JLabel(middleNameString));
        allJlabelsForFields.add(lastName = new JLabel(lastNameString));
        allJlabelsForFields.add(country = new JLabel(countryString));
        allJlabelsForFields.add(city = new JLabel(cityString));
        allJlabelsForFields.add(postCode = new JLabel(postCodeString));
        allJlabelsForFields.add(street = new JLabel(streetString));
        allJlabelsForFields.add(phoneNumber = new JLabel(phoneNumberString));
        allJlabelsForFields.add(nationality = new JLabel(nationalityString));
        allJlabelsForFields.add(dateOfBirth = new JLabel(dateOfBirthString));
        allJlabelsForFields.add(arrival = new JLabel(arrivalString));
        allJlabelsForFields.add(departure = new JLabel(departureString));
        allJlabelsForFields.add(roomType = new JLabel(roomTypeString));
        allJlabelsForFields.add(roomNumber = new JLabel(roomNumberString));
        allJlabelsForFields.add(price = new JLabel(priceString));
        allJlabelsForLabels.add(firstNameLabel = new JLabel("First name"));
        allJlabelsForLabels.add(middleNameLabel = new JLabel("Middle name"));
        allJlabelsForLabels.add(lastNameLabel = new JLabel("Last name"));
        allJlabelsForLabels.add(countryLabel = new JLabel("Country"));
        allJlabelsForLabels.add(cityLabel = new JLabel("City"));
        allJlabelsForLabels.add(postCodeLabel = new JLabel("Post code"));
        allJlabelsForLabels.add(streetLabel = new JLabel("Street"));
        allJlabelsForLabels.add(phoneNumberLabel = new JLabel("Phone number"));
        allJlabelsForLabels.add(nationalityLabel = new JLabel("Nationality"));
        allJlabelsForLabels.add(dateOfBirthLabel = new JLabel("Date of birth"));
        allJlabelsForLabels.add(arrivalLabel = new JLabel("Arrival"));
        allJlabelsForLabels.add(departureLabel = new JLabel("Departure"));
        allJlabelsForLabels.add(roomTypeLabel = new JLabel("Room type"));
        allJlabelsForLabels.add(roomNumberLabel = new JLabel("Room number"));
        allJlabelsForLabels.add(priceLabel = new JLabel("Price"));
        for (int i = 0; i < allJlabelsForFields.size(); i++) {
            mainPanelForFields.add(allJlabelsForFields.get(i));
            mainPanelForLabels.add(allJlabelsForLabels.get(i));
        }


        leftPanel.add(mainPanelForLabels, BorderLayout.WEST);
        leftPanel.add(mainPanelForFields, BorderLayout.EAST);
        leftPanel.add(discountLabel = new JLabel("Discount"), BorderLayout.WEST);
        leftPanel.add(discountField);
        leftPanel.add(calculate);
        leftPanel.add(cancel);


    }


    public void getDataForCheckOut(Reservation res) {
        FileAdapter fa = new FileAdapter();
        allInHouseGuests.addAll(fa.getAllGuests("reservations.bin"));
        res = allInHouseGuests.get(75);
        for (int i = 0; i < allInHouseGuests.size(); i++) {
            if (allInHouseGuests.get(i).equals(res)) {
                firstNameString = allInHouseGuests.get(i).getGuest().getName().getFirstName();
                middleNameString = allInHouseGuests.get(i).getGuest().getName().getMiddleName();
                lastNameString = allInHouseGuests.get(i).getGuest().getName().getLastName();
                countryString = allInHouseGuests.get(i).getGuest().getAddress().getCountry();
                cityString = allInHouseGuests.get(i).getGuest().getAddress().getCity();
                postCodeString = allInHouseGuests.get(i).getGuest().getAddress().getPostCode();
                streetString = allInHouseGuests.get(i).getGuest().getAddress().getStreet();
                phoneNumberString = String.valueOf(allInHouseGuests.get(i).getGuest().getPhoneNumber());
                nationalityString = allInHouseGuests.get(i).getGuest().getNationality();
                dateOfBirthString = String.valueOf(allInHouseGuests.get(i).getGuest().getDateOfBirth());
                arrivalString = String.valueOf(allInHouseGuests.get(i).getArrival());
                departureString = String.valueOf(allInHouseGuests.get(i).getDeparture());
                roomTypeString = String.valueOf(allInHouseGuests.get(i).getRoomType());
                roomNumberString = String.valueOf(allInHouseGuests.get(i).getRoomNumber());
                priceString = String.valueOf(new HotelManager().checkOut(res, discount));
                System.out.println(new HotelManager().checkOut(res, discount));
                System.out.println(allInHouseGuests.get(i).toString());


            }

        }
    }

    private class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println(discountField.getText());

            if (e.getSource() == calculate && discountField.getText().length() > 0) {
                if (discount > 100 || discount < 0) {
                    JOptionPane.showMessageDialog(null, "Invalid discount", "My msg", JOptionPane.WARNING_MESSAGE);


                    priceString = "0";
                    price.setText(priceString);
                    price.revalidate();

                }
                else if (discount < 100 || discount > 0) {


                    res = allInHouseGuests.get(75);

                    discount = Double.parseDouble(discountField.getText());
                    priceString = new HotelManager().checkOut(res, discount);
                    price.setText(priceString);
                    price.revalidate();
                }
            } else if (e.getSource() == cancel) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit the check out", "Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(1);
                }
            }
        }
    }


    public static void main(String[] args) {
        CheckOutGUI c = new CheckOutGUI();

    }
}