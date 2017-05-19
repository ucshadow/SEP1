//import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import javax.swing.border.Border;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by P luto on 17-May-17.
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
//  private JButton getButton;

    private ArrayList<Reservation> arrivals = new ArrayList<>();
    private ArrayList<Reservation> departures = new ArrayList<>();

    public MainGuiWindow() {
        mainWindow();

        getArrivalsForToday();
        getDeparturesForToday();
        getAllDeparturesForToday();
        getAllArrivalsForToday();

    }

    private void mainWindow() {

        adapter = new FileAdapter();

        mainWindow.setSize(1394, 730);
        mainWindow.setLayout(new FlowLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(1354, 730));


        right.setPreferredSize(new Dimension(600, 700));
        left.setPreferredSize(new Dimension(600, 700));

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

        allArrivalsTable.setPreferredScrollableViewportSize(new Dimension(500, allArrivalsTable.getRowHeight() * 18));
        allArrivalsScrollPane = new JScrollPane(allArrivalsTable);
        allArrivalsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        left.add(allArrivalsScrollPane);

        allDeparturesTable = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        allDeparturesTable.setPreferredScrollableViewportSize(new Dimension(500, allDeparturesTable.getRowHeight() * 18));
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
        buttonPanelRight = new JPanel();
        buttonPanelRight.setPreferredSize(new Dimension(500, 50));
        buttonPanelRight.add(rightButton);
        right.add(buttonPanelRight, BorderLayout.SOUTH);
        //Left
        leftButton = new JButton("Check In");
        buttonPanelLeft = new JPanel();
        buttonPanelLeft.setPreferredSize(new Dimension(500, 50));
        buttonPanelLeft.add(leftButton);
        left.add(buttonPanelLeft, BorderLayout.SOUTH);

        mainPanel.add(left, BorderLayout.WEST);
        mainPanel.add(right, BorderLayout.EAST);

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


    public void getAllArrivalsForToday() {
        Object[][] data = new Object[arrivals.size()][5];

        for (int i = 0; i < arrivals.size(); i++) {
            DateHandler d = new DateHandler(1, 1, 1);
            if (arrivals.get(i).getArrival().getCheckInDate().equals(d.currentDate())) {
                data[i][0] = arrivals.get(i).getDeparture().getCheckOutDate();
                data[i][1] = arrivals.get(i).getGuest().getName().getMiddleName();
                data[i][2] = arrivals.get(i).getGuest().getName().getLastName();
                data[i][3] = arrivals.get(i).getGuest().getAddress().getCountry();
                data[i][4] = arrivals.get(i).getGuest().getPhoneNumber();
            }

        }
        System.out.println(allArrivalsTable);
        dtm = new DefaultTableModel(data, titles);
        System.out.println(dtm);
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
        System.out.println(departures);
        dtm = new DefaultTableModel(data, titles);
        allDeparturesTable.setModel(dtm);
    }


    public void getArrivalsForToday() {
        ArrayList<Reservation> tempArr = adapter.getAllGuests("reservations.bin");
        DateHandler d = new DateHandler(1, 1, 1);
        for (int i = 0; i < tempArr.size(); i++) {
            if (tempArr.get(i).getArrival().getCheckInDate().equals(d.currentDate())) {
                arrivals.add(tempArr.get(i));
            }
        }

    }

    public JTabbedPane getTabPane() {
        return tabPane;
    }

    public JFrame getMainFrame() {
        return mainWindow;
    }


    public void getDeparturesForToday() {
        ArrayList<Reservation> temp = adapter.getAllGuests("reservations.bin");
        DateHandler d = new DateHandler(1, 1, 1);
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getDeparture().getCheckOutDate().equals(d.currentDate())) {
                departures.add(temp.get(i));
            }
        }
    }
}
// ActionListener for Button click me that returns person
//    private class MyPersonListener implements ActionListener
//    {
//        public void actionPerformed(ActionEvent e)
//        {
//            Name input = arrivals.get(departures.get(i).getGuest().getName());
//            JOptionPane.showMessageDialog(null, "You Chose: "+ input);
//        }
//    }
//}

// List-Way
//
//        //Arrival and departure
//        arrivalGuest = new JList(arrivals.toArray());
//        JScrollPane arrivalsScroller = new JScrollPane(arrivalGuest);
//        arrivalsScroller.setPreferredSize(new Dimension(500, 500));
//        arrivalsScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        arrivalsScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//        left.add(arrivalsScroller, BorderLayout.NORTH);
//
//        departureGuests= new JList(departures.toArray());
//        JScrollPane departuresScroller = new JScrollPane(departureGuests);
//        departuresScroller.setPreferredSize(new Dimension(500, 500));
//        departuresScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        departuresScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//        right.add(departuresScroller, BorderLayout.NORTH);
