import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

/**
 * This class holds the JFrame (the window)  of the GUI and also the JTabbedPane.
 * It also holds the default GUI tab named "Home". All other tabs are instantiated here.
 *
 * @author Yusuf A Farah, Catalin Udrea
 * @version 1.0
 */


public class MainGuiWindow {
    private FileAdapter adapter = new FileAdapter();

    private JFrame mainWindow = new JFrame();
    private JTabbedPane tabPane = new JTabbedPane();

    private JPanel mainPanel = new JPanel();
    private JPanel left = new JPanel();
    private JPanel right = new JPanel();
    private JPanel buttonPanelLeft;
    private JPanel buttonPanelRight;
    private JPanel searchPanelLeft;
    private JPanel searchPanelRight;
    private JPanel logo = new JPanel();

    // inHouse Guests
    private DefaultTableModel dtmIHG;
    private String[] titlesIHG;
    private JPanel bottom = new JPanel();
    private JTable allInHouseGuests;
    private JScrollPane allInHouseGuestsScroll;
    private ArrayList<Reservation> inHouseGuestsArray = new ArrayList<>();


    private DefaultTableModel dtm;
    private String[] titles;
    private JTable allArrivalsTable;
    private JTable allDeparturesTable;

    private JScrollPane allArrivalsScrollPane;
    private JScrollPane allDeparturesScrollPane;

    private JLabel leftSearchLabel;
    private JLabel rightSearchLabel;

    private JTextField leftSearch;
    private JTextField rightSearch;

    private JButton rightButton;
    private JButton leftButton;

    // The Searches
    private arrSearch arrPresser;
    private depSearch depPresser;

    private ArrayList<Reservation> arrivals = new ArrayList<>();
    private ArrayList<Reservation> departures = new ArrayList<>();

    private MyButtonListener listener = new MyButtonListener();

    private CreateReservationWindowGUI createReservationWindowGUI = new CreateReservationWindowGUI(tabPane);
    private Search search = new Search(tabPane);
    private CheckAvailability checkAvailability = new CheckAvailability();
    private CheckOutGUI checkOutGUI = new CheckOutGUI(tabPane);
    private CheckInGUI checkInGUI = new CheckInGUI(tabPane);

    private Object[][] arrCol;
    private Object[][] depCol;

    /**
     * No-argument constructor used for initialising window params, adding tabs to the tab pane,
     * disabling the check-in and check-out tabs and loading all data from the bin files.
     */
    public MainGuiWindow() {
        mainWindow();


        addTab("Create reservation", createReservationWindowGUI.getAvailabilityTab());
        addTab("Search reservation", search.getAvailabilityTab());
        addTab("Check availability", checkAvailability.getAvailabilityTab());
        addTab("Check out", checkOutGUI.getAvailableTab());
        addTab("Check in", checkInGUI.getAvailableTab());

        tabPane.setEnabledAt(4, false);
        tabPane.setEnabledAt(5, false);
        tabPane.addChangeListener(changeListener);

        refresh();

    }

    /**
     * Adds a tab to the tab pane with a title. The tab is passed by each separate Class
     * that creates a new tab.
     *
     * @param title the title of the tab
     * @param tab   the JPanel that will be added to the JTabbedPane
     */
    public void addTab(String title, JPanel tab) {
        tabPane.add(title, tab);
    }

    /**
     * Empties the arrayLists that contain reservation data, and re-reads the data from the file.
     * The method is called in the constructor and each time data is modified in a file.
     * It will refresh the displayed data in the main window ("Home")
     */
    public void refresh() {
        arrivals.clear();
        departures.clear();

        getArrivalsForToday();
        getDeparturesForToday();
        getAllDeparturesForToday(departures);
        getAllArrivalsForToday(arrivals);
        getAllInHouseGuests();

        left.revalidate();
        right.revalidate();
    }

    /**
     * The window that hods the GUI.
     */
    private void mainWindow() {

        adapter = new FileAdapter();

        //Declare
        arrPresser = new arrSearch();
        depPresser = new depSearch();

        mainWindow.setSize(1440, 960);
        mainWindow.setLocation(300, 20);
        mainWindow.setLayout(new FlowLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(1440, 960));
        mainWindow.setIconImage(new ImageIcon("logo_.png").getImage());
        mainWindow.setTitle("Overlook Hotel");


        right.setPreferredSize(new Dimension(650, 700));
        left.setPreferredSize(new Dimension(650, 700));

        logo.setPreferredSize(new Dimension(64, 64));
        try {
            BufferedImage myPicture = ImageIO.read(new File("logo_.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setPreferredSize(new Dimension(151, 151));
            logo.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //The JTable Begins
        titles = new String[5];
        titles[0] = "First name";
        titles[1] = "Middle name";
        titles[2] = "Last name";
        titles[3] = "Country";
        titles[4] = "Phone Number";

        dtm = new DefaultTableModel(titles, 0);
        allArrivalsTable = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        allArrivalsTable.setPreferredScrollableViewportSize(new Dimension(570, 300));
        allArrivalsScrollPane = new JScrollPane(allArrivalsTable);
        allArrivalsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        left.add(allArrivalsScrollPane);

        allDeparturesTable = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        allDeparturesTable.setPreferredScrollableViewportSize(new Dimension(570, 300));
        allDeparturesScrollPane = new JScrollPane(allDeparturesTable);
        allDeparturesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        right.add(allDeparturesScrollPane);


        // The two text areas  begin here.
        leftSearchLabel = new JLabel("Search Arrivals:");
        leftSearchLabel.setPreferredSize(new Dimension(100, 50));

        rightSearchLabel = new JLabel("Search Departures:");
        rightSearchLabel.setPreferredSize(new Dimension(113, 50));

        leftSearch = new JTextField(10);
        leftSearch.addKeyListener(arrPresser);
        searchPanelLeft = new JPanel();
        searchPanelLeft.setPreferredSize(new Dimension(500, 50));
        searchPanelLeft.add(leftSearchLabel, BorderLayout.WEST);
        searchPanelLeft.add(leftSearch, BorderLayout.EAST);
        left.add(searchPanelLeft, BorderLayout.NORTH);
        //Right
        rightSearch = new JTextField(10);
        rightSearch.addKeyListener(depPresser);
        searchPanelRight = new JPanel();
        searchPanelRight.setPreferredSize(new Dimension(500, 50));

        searchPanelRight.add(rightSearchLabel, BorderLayout.WEST);
        searchPanelRight.add(rightSearch, BorderLayout.EAST);
        right.add(searchPanelRight, BorderLayout.NORTH);

        // The two buttons begin here.
        rightButton = new JButton("Check Out");
        rightButton.addActionListener(listener);
        buttonPanelRight = new JPanel();
        buttonPanelRight.setPreferredSize(new Dimension(500, 50));
        buttonPanelRight.add(rightButton);
        right.add(buttonPanelRight, BorderLayout.SOUTH);


        //Left
        leftButton = new JButton("Check In");
        leftButton.addActionListener(listener);
        buttonPanelLeft = new JPanel();
        buttonPanelLeft.setPreferredSize(new Dimension(500, 50));
        buttonPanelLeft.add(leftButton);
        left.add(buttonPanelLeft, BorderLayout.SOUTH);

        //Bottom
        // default table model for inHouseGuests table

        //The JTable Begins
        titlesIHG = new String[8];
        titlesIHG[0] = "First name";
        titlesIHG[1] = "Middle name";
        titlesIHG[2] = "Last name";
        titlesIHG[3] = "Country";
        titlesIHG[4] = "Phone Number";
        titlesIHG[5] = "Arrival";
        titlesIHG[6] = "Departure";
        titlesIHG[7] = "Room number";
        dtmIHG = new DefaultTableModel(titlesIHG, 0);

        //inHouseGuests table
        bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(1000, 500));
        allInHouseGuests = new JTable(dtmIHG) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        allInHouseGuestsScroll = new JScrollPane(allInHouseGuests);

        allInHouseGuestsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        allInHouseGuestsScroll.setPreferredSize(new Dimension(1000, 400));
        allInHouseGuests.revalidate();
        bottom.add(allInHouseGuestsScroll, BorderLayout.NORTH);
        mainPanel.add(bottom, BorderLayout.SOUTH);

        mainPanel.add(left, BorderLayout.WEST);
        mainPanel.add(right, BorderLayout.EAST);
        mainPanel.add(logo, BorderLayout.CENTER);

        // Add Main panel to Tab
        tabPane.addTab("Home", mainPanel);

        mainWindow.add(tabPane);
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);

        //Exit On Close event
        mainWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }


    /**
     * Iterates an arrayList of reservations and checks if the arrival date of any reservation
     * matches the current date. It sets the table data in the Arrival panel of the "Home" tab
     * to be equal to all the found reservations that do.
     *
     * @param arrivals an ArrayList of Reservation objects
     */
    public void getAllArrivalsForToday(ArrayList<Reservation> arrivals) {
        arrCol = new Object[arrivals.size()][5];

        for (int i = 0; i < arrivals.size(); i++) {
            DateHandler d = new DateHandler(1, 1, 1);
            if (arrivals.get(i).getArrival().getCheckInDate().equals(d.currentDate())) {
                arrCol[i][0] = arrivals.get(i).getGuest().getName().getFirstName();
                arrCol[i][1] = arrivals.get(i).getGuest().getName().getMiddleName();
                arrCol[i][2] = arrivals.get(i).getGuest().getName().getLastName();
                arrCol[i][3] = arrivals.get(i).getGuest().getAddress().getCountry();
                arrCol[i][4] = arrivals.get(i).getGuest().getPhoneNumber();
            }

        }
        dtm = new DefaultTableModel(arrCol, titles);
        allArrivalsTable.setModel(dtm);
    }

    /**
     * Iterates an arrayList of reservations and checks if the departure date of any reservation
     * matches the current date. It sets the table data in the Departure panel of the "Home" tab
     * to be equal to all the found reservations that do.
     *
     * @param departures an ArrayList of Reservation objects
     */
    public void getAllDeparturesForToday(ArrayList<Reservation> departures) {
        depCol = new Object[departures.size()][5];

        for (int i = 0; i < departures.size(); i++) {
            DateHandler d = new DateHandler(1, 1, 1);
            if (departures.get(i).getDeparture().getCheckOutDate().equals(d.currentDate())) {
                depCol[i][0] = departures.get(i).getGuest().getName().getFirstName();
                depCol[i][1] = departures.get(i).getGuest().getName().getMiddleName();
                depCol[i][2] = departures.get(i).getGuest().getName().getLastName();
                depCol[i][3] = departures.get(i).getGuest().getAddress().getCountry();
                depCol[i][4] = departures.get(i).getGuest().getPhoneNumber();
            }
        }
        dtm = new DefaultTableModel(depCol, titles);
        allDeparturesTable.setModel(dtm);
    }


    /**
     * Gets all the reservations from the reservations file and fills the arrivals ArrayList with any reservation
     * that has the Arrival date equals to today's date
     */
    public void getArrivalsForToday() {
        ArrayList<Reservation> reservations = adapter.getAllGuests("reservations.bin");
        DateHandler d = new DateHandler(1, 1, 1);
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getArrival().getCheckInDate().equals(d.currentDate())) {
                arrivals.add(reservations.get(i));
            }
        }

    }

    /**
     * Gets all the reservations from the reservations file and fills the departures ArrayList with any reservation
     * that has the Departure date equals to today's date
     */
    public void getDeparturesForToday() {
        ArrayList<Reservation> inHouse = adapter.getAllGuests("inHouseGuests.bin");
        DateHandler d = new DateHandler(1, 1, 1);
        for (int i = 0; i < inHouse.size(); i++) {
            if (inHouse.get(i).getDeparture().getCheckOutDate().equals(d.currentDate())) {
                departures.add(inHouse.get(i));
            }
        }
    }

    /**
     * Reads the inHouseGuest file and each Reservation in that file
     * is added as data to the allInHouseGuests table.
     */
    public void getAllInHouseGuests() {
        inHouseGuestsArray = adapter.getAllGuests("inHouseGuests.bin");
        Object[][] dataIHG = new Object[inHouseGuestsArray.size()][8];
        for (int i = 0; i < inHouseGuestsArray.size(); i++) {
            dataIHG[i][0] = inHouseGuestsArray.get(i).getGuest().getName().getFirstName();
            dataIHG[i][1] = inHouseGuestsArray.get(i).getGuest().getName().getMiddleName();
            dataIHG[i][2] = inHouseGuestsArray.get(i).getGuest().getName().getLastName();
            dataIHG[i][3] = inHouseGuestsArray.get(i).getGuest().getAddress().getCountry();
            dataIHG[i][4] = inHouseGuestsArray.get(i).getGuest().getPhoneNumber();
            dataIHG[i][5] = inHouseGuestsArray.get(i).getArrival();
            dataIHG[i][6] = inHouseGuestsArray.get(i).getDeparture();
            dataIHG[i][7] = inHouseGuestsArray.get(i).getRoomNumber();
        }
        dtmIHG = new DefaultTableModel(dataIHG, titlesIHG);
        allInHouseGuests.setModel(dtmIHG);
        allInHouseGuests.revalidate();
    }

    private class MyButtonListener implements ActionListener {

        /**
         * Performs an action based on the button clicked. It either activates the check-in tab or the check-out tab
         * with the selected data from the table row.
         *
         * @param e the action event
         */
        public void actionPerformed(ActionEvent e) {
            // check-in button
            if (e.getSource() == leftButton) {
                if (allArrivalsTable.getSelectedRow() >= 0) {
                    checkInGUI.getDataForCheckIn(arrivals.get(allArrivalsTable.getSelectedRow()));
                    checkInGUI.setRoomNumber(arrivals.get(allArrivalsTable.getSelectedRow()));
                    tabPane.setSelectedIndex(5);
                    refresh();
                }
            }
            // check-out button
            if (e.getSource() == rightButton) {
                if (allDeparturesTable.getSelectedRow() >= 0) {
                    checkOutGUI.setReservation(departures.get(allDeparturesTable.getSelectedRow()));
                    checkOutGUI.getDataForCheckOut();
                    tabPane.setSelectedIndex(4);
                    refresh();
                }
            }

        }
    }

    /**
     * This method will call the refresh method each time the "Home" tab is pressed.
     *
     * @see #refresh()
     */
    ChangeListener changeListener = new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
            if (sourceTabbedPane.getSelectedIndex() == 0) {
                refresh();
            }
        }
    };

    // Event listeners for search
    //Arrivals Search
    class arrSearch implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }


        public void keyPressed(KeyEvent e) {

        }

        /**
         * Filters the Arrival table based on the user input
         *
         * @param e key event
         */
        public void keyReleased(KeyEvent e) {
            ArrayList<Reservation> foundNames = new ArrayList<>();
            getAllArrivalsForToday(arrivals);

            for (int i = 0; i < arrCol.length; i++) {
                String fullName = String.valueOf(arrCol[i][0].toString().toLowerCase());
                if (fullName.contains(leftSearch.getText().toLowerCase())) {
                    foundNames.add(arrivals.get(i));
                }
            }
            getAllArrivalsForToday(foundNames);


        }
    }

    //Departures Search
    class depSearch implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }


        public void keyPressed(KeyEvent e) {

        }

        /**
         * Filters the Departure table based on the user input
         *
         * @param e key event
         */
        public void keyReleased(KeyEvent e) {
            ArrayList<Reservation> foundNames = new ArrayList<>();
            getAllDeparturesForToday(departures);

            for (int i = 0; i < depCol.length; i++) {
                String fullName = String.valueOf(depCol[i][0].toString().toLowerCase());
                if (fullName.contains(rightSearch.getText().toLowerCase())) {
                    foundNames.add(departures.get(i));
                }
            }
            getAllDeparturesForToday(foundNames);


        }
    }

}