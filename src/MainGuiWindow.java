import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

// toDo: all guest currently in the hotel window!!!!  Radu


// toDo: auto refresh main window on check in and check out!!! -- DONE

// toDO: toggle between windows on clicks  -- DONE

// toDo: search functionality on home window  Yusuf

// toDo use HM for arr and dep

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

    private boolean inDisabledWindow = false;

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

       // int[] i = { 1, 2, 3 };

    }

    public void addTab(String title, JPanel tab) {
        tabPane.add(title, tab);
    }

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


        right.setPreferredSize(new Dimension(650, 700));
        left.setPreferredSize(new Dimension(650, 700));

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

// Add Main panel to Tab
        // toDo: I can make them load all at the same time, but slower
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


    public void getArrivalsForToday() {
        ArrayList<Reservation> reservations = adapter.getAllGuests("reservations.bin");
        DateHandler d = new DateHandler(1, 1, 1);
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getArrival().getCheckInDate().equals(d.currentDate())) {
                arrivals.add(reservations.get(i));
            }
        }

    }

    public void getDeparturesForToday() {
        ArrayList<Reservation> inHouse = adapter.getAllGuests("inHouseGuests.bin");
        DateHandler d = new DateHandler(1, 1, 1);
        for (int i = 0; i < inHouse.size(); i++) {
            if (inHouse.get(i).getDeparture().getCheckOutDate().equals(d.currentDate())) {
                departures.add(inHouse.get(i));
            }
        }
    }

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
        public void actionPerformed(ActionEvent e) {
            //calculate button for checking out the person.
            if (e.getSource() == leftButton) {
                if(allArrivalsTable.getSelectedRow() >= 0) {
                    checkInGUI.getDataForCheckIn(arrivals.get(allArrivalsTable.getSelectedRow()));
                    checkInGUI.setRoomNumber(arrivals.get(allArrivalsTable.getSelectedRow()));
                    tabPane.setSelectedIndex(5);
                    refresh();
                }
            }
            if (e.getSource() == rightButton) {
                //System.out.println("right click");
                if(allDeparturesTable.getSelectedRow() >= 0) {
                    //System.out.println(departures.get(allDeparturesTable.getSelectedRow()).getGuest().getName());
                    //checkOutGUI.getDataForCheckOut(departures.get(allDeparturesTable.getSelectedRow()));
                    checkOutGUI.setRes(departures.get(allDeparturesTable.getSelectedRow()));
                    checkOutGUI.getDataForCheckOut();
                    tabPane.setSelectedIndex(4);
                    refresh();
                }
            }

        }
    }

    ChangeListener changeListener = new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
//            if(sourceTabbedPane.getSelectedIndex() == 4 || sourceTabbedPane.getSelectedIndex() == 5) {
//                inDisabledWindow = true;
//            }
//            if(sourceTabbedPane.getSelectedIndex() == 0 && inDisabledWindow) {
//                System.out.println("refreshing main window...");
//                inDisabledWindow = false;
//                refresh();
//            }
//            // refresh on create
//            if(sourceTabbedPane.getSelectedIndex() == 1) {
//                refresh();
//            }
            // refresh each time you switch to the main window
            if(sourceTabbedPane.getSelectedIndex() == 0) {
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

        public void keyReleased(KeyEvent e) {
            ArrayList<Reservation> foundNames = new ArrayList<Reservation>();
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

        public void keyReleased(KeyEvent e) {
            ArrayList<Reservation> foundNames = new ArrayList<Reservation>();
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