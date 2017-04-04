package prog3.uppg1_Nordstrom_40880_Nordman_40867;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.MouseInfo;
import java.util.ArrayList;

/**
 * Created by Dannyy on 6.3.2017.
 */
public class Uppg1View {

    private Uppg1Model model;
    private JFrame frame;
    private JPanel pane, paneMain, graphArea;
    private JButton queryButton;
    private JLabel tick1, tick2, startDate, endDate, exampleDate;
    JTextField textTick1, textTick2, startDateTick, endDateTick;
    private JRadioButton usd, eur, sek;
    private JTextArea infoArea;
    private ButtonGroup bg;
    GridBagConstraints c;
    JScrollPane scroll;
    StockGraph gt;

    Uppg1View(Uppg1Model model) {
        this.model = model;
        //Skapa fönstren dit allting läggs
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        paneMain = new JPanel();
        frame.getContentPane().add(paneMain);
        pane = new JPanel(new GridBagLayout());
        paneMain.add(pane);
        frame.setSize(600, 1000);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //Instantiera alla komponenter och sätt deras egenskaper
        queryButton = new JButton("Do query");
        tick1 = new JLabel("Ticker 1 ");
        tick2 = new JLabel("Ticker 2 ");
        startDate = new JLabel("Startdatum ");
        endDate = new JLabel("Slutdatum ");
        exampleDate = new JLabel("I formatet dd.mm.yyyy");
        textTick1 = new JTextField(10);
        textTick2 = new JTextField(10);
        startDateTick = new JTextField(10);
        endDateTick = new JTextField(10);
        usd = new JRadioButton("USD");
        eur = new JRadioButton("EUR");
        sek = new JRadioButton("SEK");
        usd.setActionCommand("USD");
        eur.setActionCommand("EUR");
        sek.setActionCommand("SEK");
        bg = new ButtonGroup();
        bg.add(usd);
        bg.add(eur);
        bg.add(sek);
        usd.setSelected(true);
        infoArea = new JTextArea(20, 45);
        infoArea.setEditable(false);
        infoArea.setAutoscrolls(true);
        scroll = new JScrollPane(infoArea);
        graphArea = new JPanel();
        graphArea.setPreferredSize(new Dimension(480, 500));

        c = new GridBagConstraints();
        //Initial values för gridbag
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        c.ipady = 5;
        c.insets = new Insets(0, 115, 6, 3);


        //Labels
        pane.add(tick1, c);
        c.gridy++;
        pane.add(tick2, c);
        c.gridy++;
        pane.add(startDate, c);
        c.gridy++;
        pane.add(endDate, c);

        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 6, 0);

        //Inputfields
        pane.add(textTick1, c);
        c.gridy = 1;
        pane.add(textTick2, c);
        c.gridy = 2;
        startDateTick.setText("1.1.2017");
        pane.add(startDateTick, c);
        c.gridy = 3;
        endDateTick.setText("30.3.2017");
        pane.add(endDateTick, c);
        c.gridx = 2;
        c.anchor = GridBagConstraints.WEST;
        pane.add(exampleDate, c);
        //Knappen
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        pane.add(queryButton, c);

        //Valuta radiobuttons
        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 0;
        c.gridx = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_END;

        pane.add(usd, c);
        c.gridy = 1;
        pane.add(eur, c);
        c.gridy = 2;
        pane.add(sek, c);

        //Stora textfältet
        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.EAST;
        c.gridheight = 3;
        c.gridwidth = 3;
        c.insets = new Insets(5, 5, 5, 5);
        pane.add(scroll, c);

        //Initial mode av grafen
        c.gridx = 0;
        c.gridy = 8;
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 4;
        c.gridwidth = 4;
        c.insets = new Insets(5, 5, 5, 5);
        graphArea.setPreferredSize(new Dimension(400, 450));
        pane.add(graphArea);
        StockGraph gt = new StockGraph();
        gt.setPreferredSize(new Dimension(400, 450));
        graphArea.add(gt);

        //Visa allting
        frame.pack();
        frame.setVisible(true);
    }

    Point getCursorLocation() {
        return MouseInfo.getPointerInfo().getLocation();
    }



    void addListener(ActionListener doQueryListener) {
        queryButton.addActionListener(doQueryListener);
    }

    void displayErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void setInfoArea(String s) {
        infoArea.setText(s);
    }

    public String getTicker1() {
        return textTick1.getText();
    }

    public String getTicker2() {
        return textTick2.getText();
    }

    public String getStartDate() {
        return startDateTick.getText();
    }

    public String getEndDate() {
        return endDateTick.getText();
    }

    public String getCurrency() {
        return bg.getSelection().getActionCommand();
    }

    public void setGraph(ArrayList<Double> d) {
        gt = new StockGraph(d, getCursorLocation());
        pane.add(gt, c);
        frame.pack();
    }

    public void setGraph(ArrayList<Double> d1, ArrayList<Double> d2) {
        gt = new StockGraph(d1, d2, getCursorLocation());
        pane.add(gt, c);
        frame.pack();
    }
}


/* OLD GRAPH CODE
class DrawOuterGraph extends JPanel {
    final int PAD = 15;
    double[] data1, data2;

    DrawOuterGraph() {
        data1 = null;
        data2 = null;
    }
    DrawOuterGraph(ArrayList<Double> d1) {
        data1 = new double[d1.size()];
        for (int i = 0; i < d1.size(); i++) {
            data1[i] = d1.get(i);
        }
    }
    DrawOuterGraph(ArrayList<Double> d1, ArrayList<Double> d2) {
        data1 = new double[d1.size()];
        for (int i = 0; i < d1.size(); i++) {
            data1[i] = d1.get(i);
        }
        data2 = new double[d2.size()];
        for (int i = 0; i < d2.size(); i++) {
            data2[i] = d2.get(i);
        }
    }

    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();

        g2.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD));

        g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD));

        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();

        String s = "Värde";
        float sy = PAD + ((h - 2 * PAD) - s.length() * sh) / 2 + lm.getAscent();
        for (int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float) font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw) / 2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }

        s = "Red = ticker1 ----- Blue = ticker2";
        sy = h - PAD + (PAD - sh) / 2 + lm.getAscent();
        float sw = (float) font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw) / 2;
        g2.drawString(s, sx, sy);

        // Draw lines.
        if (data1 != null) {
            double xInc = (double) (w - 2 * PAD) / (data1.length);
            double scale = (double) getMax1() / (h - 2 * PAD);
            double yscale = Math.abs(getMax1() - getMin1());
            g2.setPaint(Color.red);
            for (int i = 0; i < data1.length - 1; i++) {
                System.out.println(scale);
                double x1 = PAD + i * xInc;
                double y1 = h - PAD - scale * data1[i];
                double x2 = PAD + (i + 1) * xInc;
                double y2 = (h - PAD - scale * data1[i + 1]) + 1;
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
            }
        }
        if (data2 != null) {
            double xInc = (double) (w - 2 * PAD) / (data2.length);
            double scale = (double) (h - 2 * PAD) / getMax2();
            g2.setPaint(Color.blue);
            for (int i = 0; i < data2.length - 1; i++) {
                double x1 = PAD + i * xInc;
                double y1 = h - PAD - scale * data2[i];
                double x2 = PAD + (i + 1) * xInc;
                double y2 = h - PAD - scale * data2[i + 1];
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
            }
        }
    }

    private double getMax1() {
        double max = -Integer.MAX_VALUE;
        for(int i = 0; i < data1.length; i++) {
            if(data1[i] > max)
                max = data1[i];
        }
        return max*2;
    }
    private double getMin1() {
        double min = Integer.MAX_VALUE;
        for(int i = 0; i < data1.length; i++) {
            if(data1[i] < min) {
                min = data1[i];
            }
        }
        return min;
    }
    private double getMax2() {
        double max = -Integer.MAX_VALUE;
        for(int i = 0; i < data2.length; i++) {
            if(data2[i] > max)
                max = data2[i];
        }
        return max*2;
    }
}
*/