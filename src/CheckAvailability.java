import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * @author Catalin Udrea
 */
public class CheckAvailability {

    private HotelManager hm = new HotelManager();

    private JPanel controlPanel;

    private JPanel left;
    private JPanel right;
    private JTextField fromField;
    private JTextField toField;
    private JTextArea roomData;
    private JTextPane warnings;
    private JLabel label;
    private boolean error;


    /**
     * No-argument constructor for initialing CheckAvailability
     */
    public CheckAvailability() {
        prepareGUI();
    }

    /**
     * A method preparing the GUI for launch.
     */
    private void prepareGUI() {

        left = new JPanel();
        right = new JPanel();
        right.setPreferredSize(new Dimension(700, 860));
        left.setPreferredSize(new Dimension(700, 860));
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        controlPanel.add(left);
        controlPanel.add(right);
        prepareSearchWindow();
    }

    /**
     * A method containing the design of the GUI
     */
    private void prepareSearchWindow() {
        roomData = new JTextArea(hm.getAvailabilityFromDateInterval(new DateHandler(1, 1, 2200),
                new DateHandler(1, 2, 2200)));
        JScrollPane listScroller = new JScrollPane(roomData);
        listScroller.setPreferredSize(new Dimension(700, 400));
        roomData.setEditable(false);
        right.add(listScroller);

        fromField = new JTextField();
        fromField.setPreferredSize(new Dimension(100, 25));

        toField = new JTextField();
        toField.setPreferredSize(new Dimension(100, 25));
        toField.addKeyListener(new KeyPressEvent());
        fromField.addKeyListener(new KeyPressEvent());

        label = new JLabel("Arrival and Departure dates (dd/mm/YYYY) ");
        label.setLabelFor(toField);

        warnings = new JTextPane();
        warnings.setPreferredSize(new Dimension(300, 25));
        warnings.setEditable(false);
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet asset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.RED);
        warnings.setCharacterAttributes(asset, false);

        left.add(label);
        left.add(fromField);
        left.add(toField);
        left.add(warnings);
    }

    /**
     * The method takes two date handlers and executes the getAvailabilityFromDate using them and displaying the resulting String s in the roomData JTextArea
     * @param d1 DateHandler object representing arrival date
     * @param d2 DateHandler object representing departure date
     */

    private void displayRooms(DateHandler d1, DateHandler d2) {

        String s = hm.getAvailabilityFromDateInterval(d1, d2);
        roomData.setText(s);
        roomData.revalidate();
    }

    /**
     * A class that listens to any activity regarding the keyboard
     */
    class KeyPressEvent implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }

        /**
         * A method to listen for key presses.
         *
         * @param e event representing the action event.
         */
        public void keyPressed(KeyEvent e) {
            if ((e.getSource().equals(toField) || e.getSource().equals(fromField)) && e.getKeyCode() == 10) {
                String[] str = fromField.getText().split("/");
                String[] str2 = toField.getText().split("/");
                error = true;
                if (!(isValidDate(fromField.getText()) && isValidDate(toField.getText()))) {
                    warnings.setText("Please use the format provided");
                    error = false;
                } else {
                    DateHandler d1 = new DateHandler(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
                    DateHandler d2 = new DateHandler(Integer.parseInt(str2[0]), Integer.parseInt(str2[1]), Integer.parseInt(str2[2]));
                    if (d1.isBefore(d2)) {
                        displayRooms(d1, d2);
                    } else {
                        error = false;

                        warnings.setText("Departure is before Arrival");
                        roomData.setText("Single room: " + 0 + "\nDouble room: " + 0
                                + "\nDouble room-twin bed: " + 0 + "\nDouble room-kingsize bed: "
                                + 0 + "\nSingle suite: " + 0 + "\nDouble suite: "
                                + 0 + "\nTriple Suite: " + 0);
                    }
                }
                if (error) {
                    warnings.setText("");
                }

            }
        }

        /**
         * Checks if the entered dates are in the correct format.
         *
         * @param str takes the date as a string
         * @return true or false. If dates are entered in the proper way, the method returns true , else it returns false.
         */

        public boolean isValidDate(String str) {
            String[] arr = str.split("/");
            if (arr[0].chars().allMatch(Character::isDigit) && arr[0].length() == 2) {
                if (arr[1].chars().allMatch(Character::isDigit) && arr[1].length() == 2) {
                    if (arr[2].chars().allMatch(Character::isDigit) && arr[2].length() == 4) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void keyReleased(KeyEvent e) {

        }
    }

    /**
     * A method that returns the controlPanel of this class as a JPanel.
     *
     * @return JPanel, controlPanel representing the main panel of this tab, where all GUI elements from check availability are contained
     */

    public JPanel getAvailabilityTab() {
        return controlPanel;
    }

}
