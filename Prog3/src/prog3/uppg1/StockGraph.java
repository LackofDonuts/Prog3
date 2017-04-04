package prog3.uppg1_Nordstrom_40880_Nordman_40867;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by d on 27.3.2017.
 */
/*
public class StockGraph extends JPanel{
    private JFrame frame;
    private JPanel panel;

    void paintShit() {
        frame = new JFrame("Test graph");
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);

        Graph graph = new Graph();
        graph.setPreferredSize(new Dimension(600, 500));
        panel.add(graph);

        panel.setVisible(true);
        frame.setVisible(true);
        frame.pack();
    }
}
*/

class StockGraph extends JPanel {
    ArrayList<Double> ticker1 = new ArrayList<>();
    ArrayList<Double> ticker2 = new ArrayList<>();
    int yVals = 10;
    Point mouse;

    public StockGraph() {

    }

    public StockGraph(ArrayList<Double> t1, Point m) {
        ticker1 = t1;
        Collections.reverse(ticker1);
        mouse = m;
    }

    public StockGraph(ArrayList<Double> t1, ArrayList<Double> t2, Point m) {
        ticker1 = t1;
        ticker2 = t2;
        Collections.reverse(ticker1);
        Collections.reverse(ticker2);
        mouse = m;
    }

    protected void paintComponent(Graphics g) {
        PointerInfo a = MouseInfo.getPointerInfo();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int width = super.getWidth();
        int height = super.getHeight();
        int xPAD = 5, yPAD = 10;
        int pointWidth = 4;

        g2.setColor(Color.WHITE);
        g2.fillRect(xPAD, yPAD, getWidth() - (2 * xPAD), getHeight() - 2 * yPAD);
        g2.setColor(Color.DARK_GRAY);

        Double max1 = getMaxScore(ticker1);
        Double min1 = getMinScore(ticker1);

        Double max2 = getMaxScore(ticker2);
        Double min2 = getMinScore(ticker2);


        double xScale1 = ((double) getWidth() - (2 * xPAD)) / (ticker1.size() - 1);
        double yScale1 = ((double) getHeight() - 2 * yPAD) / (max1 - min1);
        double xScale2 = ((double) getWidth() - (2 * xPAD)) / (ticker2.size() - 1);
        double yScale2 = ((double) getHeight() - 2 * yPAD) / (max2 - min2);

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < ticker1.size(); i++) {
            int x1 = (int) (i * xScale1 + xPAD);
            int y1 = (int) ((max1 - ticker1.get(i)) * yScale1 + yPAD);
            graphPoints.add(new Point(x1, y1));
        }

        List<Point> graphPoints2 = new ArrayList<>();
        for (int i = 0; i < ticker2.size(); i++) {
            int x1 = (int) (i * xScale2 + xPAD);
            int y1 = (int) ((max2 - ticker2.get(i)) * yScale2 + yPAD);
            graphPoints2.add(new Point(x1, y1));
        }


        //y
        for (int i = 0; i < yVals + 1; i++) {
            int x0 = xPAD + yPAD;
            int x1 = pointWidth + xPAD;
            int y0 = height - ((i * (height - yPAD * 2)) / yVals + yPAD);
            int y1 = y0;
            if (ticker1.size() > 0) {
                g2.setColor(Color.lightGray);
                g2.drawLine(xPAD + 1 + pointWidth, y0, getWidth() - xPAD, y1);
                g2.setColor(Color.black);
            }
            g2.drawLine(x0 - 15, y0, xPAD, y1);
        }

        //x
        for (int i = 0; i < ticker1.size(); i++) {
            if (ticker1.size() > 1) {
                int x0 = i * (getWidth() - xPAD * 2) / (ticker1.size() - 1) + xPAD;
                int x1 = x0;
                int y0 = getHeight() - yPAD;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((ticker1.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(Color.lightGray);
                    g2.drawLine(x0, getHeight() - yPAD - 1 - pointWidth, x1, yPAD);
                    g2.setColor(Color.black);
                }
                //g2.drawLine(x0, y0, x1, y1);
            }
        }
        g2.setColor(Color.black);
        g2.draw(new Line2D.Double(xPAD, yPAD, xPAD, height - yPAD));
        g2.draw(new Line2D.Double(xPAD, height - yPAD, width - xPAD, height - yPAD));


        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(2f));
        System.out.println(mouse);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            if (mouse.getX() == x1 || mouse.getX() == x2) {
                g2.setColor(Color.cyan);
            }
            g2.drawLine(x1, y1, x2, y2);
            g2.setColor(Color.red);
        }

        g2.setColor(Color.blue);
        g2.setStroke(new BasicStroke(2f));
        for (int i = 0; i < graphPoints2.size() - 1; i++) {
            int x1 = graphPoints2.get(i).x;
            int y1 = graphPoints2.get(i).y;
            int x2 = graphPoints2.get(i + 1).x;
            int y2 = graphPoints2.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

    }

    private double getMinScore(ArrayList<Double> scores) {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore(ArrayList<Double> scores) {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }
}
