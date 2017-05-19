//import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import javax.swing.border.Border;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Pluto on 17-May-17.
 */

public class MainGuiWindow {
    private FileAdapter adapter = new FileAdapter();

    private JFrame mainWindow = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JTabbedPane tabPane = new JTabbedPane();

    private JPanel left = new JPanel();
    private JPanel right = new JPanel();

//    private JList arrivalGuest;
//    private JList departureGuests;

    private JTable allArrivalsTable;
    private String[] titles;
    private DefaultTableModel dtm;

    private JScrollPane allArrivalsScrollPane;

    private JButton rightButton;
    private JButton leftButton;

    private JPanel buttonPanelLeft;
    private JPanel buttonPanelRight;
    private ArrayList<Name> arrivals = new ArrayList<>();
    private ArrayList<Name> departures = new ArrayList<>();

    private JButton getButton;

    public MainGuiWindow() {
        getAllArrivalsForToday();

        //getAllDeparturesForToday();
        mainWindow();

    }

    private void mainWindow(){

        mainWindow.setSize(1394 ,730);
        mainWindow.setLayout(new FlowLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        adapter = new FileAdapter();



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

        titles = new String[3];
        titles[0] = "First name";
        titles[1] = "Last name";
        titles[2] = "Country";

        dtm = new DefaultTableModel(titles, 0);

        allArrivalsTable = new JTable(dtm);
        allArrivalsTable.setPreferredScrollableViewportSize(new Dimension(500, allArrivalsTable.getRowHeight()*18));
        allArrivalsScrollPane = new JScrollPane(allArrivalsTable);
        allArrivalsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        right.add(allArrivalsScrollPane);


        rightButton = new JButton("Check In");
        buttonPanelRight = new JPanel();
        buttonPanelRight.setPreferredSize(new Dimension(500,50));

        buttonPanelRight.add(rightButton);
        right.add(buttonPanelRight, BorderLayout.SOUTH);

        leftButton = new JButton("Check Out");
        buttonPanelLeft = new JPanel();
        buttonPanelLeft.setPreferredSize(new Dimension(500,50));

        buttonPanelLeft.add(leftButton);
        left.add(buttonPanelLeft, BorderLayout.SOUTH);

        left.setPreferredSize(new Dimension(600 ,700));
        right.setPreferredSize(new Dimension(600 ,700));

        tabPane.addTab("Home", mainPanel);

        mainPanel.add(left, BorderLayout.WEST);
        mainPanel.add(right, BorderLayout.EAST);
        mainPanel.setPreferredSize(new Dimension(1354 ,730));

        mainWindow.add(tabPane);
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);

        mainWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent windowEvent){
                System.exit(0);
            }
        });
    }


    public void getAllArrivalsForToday(){

        ArrayList<Reservation> temp = adapter.getAllGuests("reservations.bin");
        Object[][] data = new Object[temp.size()][3];

        for(int i = 0; i<temp.size();i++)
        {
            DateHandler d = new DateHandler(1,1,1);
            if (temp.get(i).getArrival().getCheckInDate().equals(d.currentDate())){
                data[i][0] = temp.get(i).getGuest().getName().getFirstName();
                data[i][1] =temp.get(i).getGuest().getName().getMiddleName();
                data[i][2] =temp .get(i).getGuest().getName().getLastName();
            }

        }
        System.out.println(allArrivalsTable);
        dtm = new DefaultTableModel(data, titles);
        System.out.println(dtm);
        allArrivalsTable.setModel(dtm);


    }



//    public void getAllArrivalsForToday(){
//        ArrayList<Reservation> temp = adapter.getAllGuests("reservations.bin");
//        DateHandler d = new DateHandler(1,1,1);
//        for (int i = 0; i<temp.size();i++){
//            if (temp.get(i).getArrival().getCheckInDate().equals(d.currentDate())){
//                arrivals.add(temp.get(i).getGuest().getName());
//            }
//        }
//
//    }
//
//    public void getAllDeparturesForToday(){
//
//        ArrayList<Reservation> temp = adapter.getAllGuests("reservations.bin");
//        DateHandler d = new DateHandler(1,1,1);
//        for (int i = 0; i<temp.size();i++){
//            if (temp.get(i).getDeparture().getCheckOutDate().equals(d.currentDate())){
//                departures.add(temp.get(i).getGuest().getName());
//            }
//        }
//
//    }

//    private class MyPersonListener implements ActionListener
//    {
//        public void actionPerformed(ActionEvent e)
//        {
//
//            Name input = arrivals.get(departures.);
//            JOptionPane.showMessageDialog(null, "You wrote: "+ input);
//        }
//    }

}

