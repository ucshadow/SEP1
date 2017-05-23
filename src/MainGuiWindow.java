import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import javax.swing.border.Border;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

// toDo: all guest currently in the hotel window!!!!  Radu and Yusuf


// toDo: auto refresh main window on check in and check out!!!
// toDO: toggle between windows on clicks
// toDo: search functionality on home window

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

    private ArrayList<Reservation> arrivals = new ArrayList<>();
    private ArrayList<Reservation> departures = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

    private MyButtonListener listener = new MyButtonListener();

    private CreateReservationWindowGUI createReservationWindowGUI = new CreateReservationWindowGUI();
    private Search search = new Search();
    private CheckAvailability checkAvailability = new CheckAvailability();
    private CheckOutGUI checkOutGUI = new CheckOutGUI();
    private CheckInGUI checkInGUI = new CheckInGUI();

    public MainGuiWindow() {
        mainWindow();

        reservations = adapter.getAllGuests("reservations.bin");


        addTab("Create reservation", createReservationWindowGUI.getAvailabilityTab());
        addTab("Search reservation", search.getAvailabilityTab());
        addTab("Check availability", checkAvailability.getAvailabilityTab());
        addTab("Check out", checkOutGUI.getAvailableTab());
        addTab("Check in", checkInGUI.getAvailableTab());

        getArrivalsForToday();
        getDeparturesForToday();
        getAllDeparturesForToday();
        getAllArrivalsForToday();

    }

    public void addTab(String title, JPanel tab) {
        tabPane.add(title, tab);
    }

    private void mainWindow() {

        adapter = new FileAdapter();

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

        allArrivalsTable.setPreferredScrollableViewportSize(new Dimension(570, 600));
        allArrivalsScrollPane = new JScrollPane(allArrivalsTable);
        allArrivalsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        left.add(allArrivalsScrollPane);

        allDeparturesTable = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        allDeparturesTable.setPreferredScrollableViewportSize(new Dimension(570, 600));
        allDeparturesScrollPane = new JScrollPane(allDeparturesTable);
        allDeparturesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        right.add(allDeparturesScrollPane);


        // The two text areas  begin here.
        leftSearchLabel = new JLabel("Search Arrivals:");
        leftSearchLabel.setPreferredSize(new Dimension(100, 50));

        rightSearchLabel = new JLabel("Search Departures:");
        rightSearchLabel.setPreferredSize(new Dimension(113, 50));

        leftSearch = new JTextField(10);
        searchPanelLeft = new JPanel();
        searchPanelLeft.setPreferredSize(new Dimension(500, 50));
        searchPanelLeft.add(leftSearchLabel, BorderLayout.WEST);
        searchPanelLeft.add(leftSearch, BorderLayout.EAST);
        left.add(searchPanelLeft, BorderLayout.NORTH);
        //Right
        rightSearch = new JTextField(10);
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


    public void getAllArrivalsForToday() {
        Object[][] data = new Object[arrivals.size()][5];

        for (int i = 0; i < arrivals.size(); i++) {
            DateHandler d = new DateHandler(1, 1, 1);
            if (arrivals.get(i).getArrival().getCheckInDate().equals(d.currentDate())) {
                data[i][0] = arrivals.get(i).getGuest().getName().getFirstName();
                data[i][1] = arrivals.get(i).getGuest().getName().getMiddleName();
                data[i][2] = arrivals.get(i).getGuest().getName().getLastName();
                data[i][3] = arrivals.get(i).getGuest().getAddress().getCountry();
                data[i][4] = arrivals.get(i).getGuest().getPhoneNumber();
            }

        }
        dtm = new DefaultTableModel(data, titles);
        allArrivalsTable.setModel(dtm);
    }

    public void getAllDeparturesForToday() {
        Object[][] data = new Object[departures.size()][5];

        for (int i = 0; i < departures.size(); i++) {
            DateHandler d = new DateHandler(1, 1, 1);
            if (departures.get(i).getDeparture().getCheckOutDate().equals(d.currentDate())) {
                data[i][0] = departures.get(i).getGuest().getName().getFirstName();
                data[i][1] = departures.get(i).getGuest().getName().getMiddleName();
                data[i][2] = departures.get(i).getGuest().getName().getLastName();
                data[i][3] = departures.get(i).getGuest().getAddress().getCountry();
                data[i][4] = departures.get(i).getGuest().getPhoneNumber();
            }
        }
        dtm = new DefaultTableModel(data, titles);
        allDeparturesTable.setModel(dtm);
    }


    public void getArrivalsForToday() {
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

    private class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //calculate button for checking out the person.
            if (e.getSource() == leftButton) {
                if(allArrivalsTable.getSelectedRow() >= 0) {
                    checkInGUI.getDataForCheckIn(arrivals.get(allArrivalsTable.getSelectedRow()));
                    tabPane.setSelectedIndex(5);
                }
            }
            if (e.getSource() == rightButton) {
                System.out.println("right click");
                if(allDeparturesTable.getSelectedRow() >= 0) {
                    System.out.println(departures.get(allDeparturesTable.getSelectedRow()).getGuest().getName());
                    checkOutGUI.getDataForCheckOut(departures.get(allDeparturesTable.getSelectedRow()));
                    tabPane.setSelectedIndex(4);
                }
            }

        }
    }
}