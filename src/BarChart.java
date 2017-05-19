import javax.swing.*;
import java.awt.*;

public class BarChart extends JPanel {
    private int barX;
    private int barY;
    private int barWidth;
    private int barHeight;
    private String text;
    private int numericalData;
    private int windowWidth;

    public BarChart(int barX, int barY, int barWidth, int barHeight, String text, int numericalData, int windowWidth) {
        this.barX = barX;
        this.barY = barY;
        this.barWidth = barWidth;
        this.barHeight = barHeight;
        this.text = text;
        this.numericalData = numericalData;
        this.windowWidth = windowWidth;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(barColor());

        g.fillRect(barX, barY, barWidth, barHeight);
        g.setColor(Color.WHITE);
        g.drawString(text, barX + 10, barY + 20);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(numericalData), barWidth + 20, barY + 20);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(barWidth + 2 * barX, barHeight + 2 * barY);
    }

    private Color barColor() {
        Color[] colors = new Color[]{
                Color.decode("#007c29"),
                Color.decode("#1c7c00"),
                Color.decode("#397c00"),
                Color.decode("#5d7c00"),
                Color.decode("#737c00"),
                Color.decode("#7c6b00"),
                Color.decode("#7c5d00"),
                Color.decode("#7c4000"),
                Color.decode("#7c2300"),
                Color.decode("#7c0000")
        };

        int index = windowWidth / barWidth;
        if(index > colors.length - 1) {
            index = colors.length - 1;
        }
        return colors[index];
    }
}