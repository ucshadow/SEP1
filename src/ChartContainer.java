import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChartContainer {

    private JFrame window;
    private JPanel[] bars;
    private JPanel barsContainer;
    private int width;

    public ChartContainer(int width, int height) {
        this.width = width;

        window = new JFrame("Chart");
        window.setSize(width, height);
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                //window.setVisible(false);
                System.exit(0);
            }
        });
        window.setVisible(true);
        bars = new JPanel[12];
        barsContainer = new JPanel();
        barsContainer.setLayout(new BoxLayout(barsContainer, BoxLayout.Y_AXIS));
        window.add(barsContainer);
    }

    public void setBarsData(int[] data, String[] months) {
        int biggest = getHighest(data);
        if(biggest == 0) biggest = 1;
        for(int i = 0; i < data.length; i++) {
            int h = (width - 200) * data[i] / biggest;
            if(h <= 0) h = 1;
            BarChart b = new BarChart(20, 10, h, 30, months[i], data[i], width);
            bars[i] = b;
        }
    }

    public void drawBars() {
        for(int i = 0; i < bars.length; i++) {
            if(bars[i] != null) {
                barsContainer.add(bars[i]);
            }
        }
        //addSortButton();
        window.revalidate();
    }

    private void addSortButton() {
        JButton sortButton = new JButton("Sort");
        sortButton.setAlignmentX(200);
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("click");
            }
        });
        barsContainer.add(sortButton);
    }

    private int getHighest(int[] arr) {
        int res = arr[0];
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > res) {
                res = arr[i];
            }
        }
        return res;
    }

}
