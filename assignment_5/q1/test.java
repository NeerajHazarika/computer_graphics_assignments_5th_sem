import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class test extends Applet implements ActionListener {
    int scale = 30;
    int originX = 0;
    int originY = 0;
    Label label_x, label_y, label_xn, label_yn, label_xmin, label_xmax, label_ymin, label_ymax;
    TextField textField_x, textField_y, textField_xn, textField_yn, textField_xmin, textField_xmax, textField_ymin,
            textField_ymax;
    Button B1, B2, B3;
    int x1, y1, x2, y2, xmin, xmax, ymin, ymax;

    public void init() {
        textField_x = new TextField(10);
        textField_y = new TextField(10);
        textField_xn = new TextField(10);
        textField_yn = new TextField(10);
        textField_xmin = new TextField(10);
        textField_xmax = new TextField(10);
        textField_ymin = new TextField(10);
        textField_ymax = new TextField(10);
        label_x = new Label("x1");
        label_y = new Label("y1");
        label_xn = new Label("x2");
        label_yn = new Label("y2");
        label_xmin = new Label("xmin");
        label_xmax = new Label("xmax");
        label_ymin = new Label("ymin");
        label_ymax = new Label("ymax");
        add(label_x);
        add(textField_x);
        add(label_y);
        add(textField_y);
        add(label_xn);
        add(textField_xn);
        add(label_yn);
        add(textField_yn);
        add(label_xmin);
        add(textField_xmin);
        add(label_xmax);
        add(textField_xmax);
        add(label_ymin);
        add(textField_ymin);
        add(label_ymax);
        add(textField_ymax);
        Font myFont = new Font("Serif", Font.BOLD, 25);
        label_x.setFont(myFont);
        label_y.setFont(myFont);
        label_xn.setFont(myFont);
        label_yn.setFont(myFont);
        label_xmin.setFont(myFont);
        label_xmax.setFont(myFont);
        label_ymin.setFont(myFont);
        label_ymax.setFont(myFont);
        setBackground(Color.white);
        originX = (getX() + getWidth()) / 2;
        originY = (getY() + getHeight()) / 2;
        B3 = new Button("Submit");
        add(B3);
        B3.addActionListener(this);
        B1 = new Button("zoomin");
        add(B1);
        B1.addActionListener(this);
        B2 = new Button("zoomout");
        add(B2);
        B2.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == B3) {
            x1 = Integer.parseInt(textField_x.getText());
            y1 = Integer.parseInt(textField_y.getText());
            x2 = Integer.parseInt(textField_xn.getText());
            y2 = Integer.parseInt(textField_yn.getText());
            xmin = Integer.parseInt(textField_xmin.getText());
            xmax = Integer.parseInt(textField_xmax.getText());
            ymin = Integer.parseInt(textField_ymin.getText());
            ymax = Integer.parseInt(textField_ymax.getText());
            repaint();
        }
        if (e.getSource() == B1) {
            if (scale < 70) {
                scale = scale + 10;
                repaint();

            }
        } else if (e.getSource() == B2) {
            if (scale > 10) {
                scale = scale - 10;
                repaint();
            }
        }
    }

    // draw window
    public void Windowclip(int xmin, int ymin, int xmax, int ymax, Graphics g, Color c) {

        g.setColor(c);
        g.drawLine(originX + (xmin * scale), originY - (ymin * scale), originX + (xmax * scale),
                originY - (ymin * scale));
        g.drawLine(originX + (xmin * scale), originY - (ymin * scale), originX + (xmin * scale),
                originY - (ymax * scale));
        g.drawLine(originX + (xmax * scale), originY - (ymin * scale), originX + (xmax * scale),
                originY - (ymax * scale));
        g.drawLine(originX + (xmin * scale), originY - (ymax * scale), originX + (xmax * scale),
                originY - (ymax * scale));
    }

    // identify the region in which point lies in, Top Bottom Right Left
    int TBRL(int xa, int ya) {
        if (xa >= xmin && xa <= xmax && ya <= ymax && ya >= ymin) {
            return 0000;
        } else if (xa < xmin && ya > ymax) {
            return 1001;
        } else if (xa >= xmin && xa <= xmax && ya > ymax) {
            return 1000;
        } else if (xa > xmax && ya > ymax) {
            return 1010;
        } else if (xa < xmin && ya >= ymin && ya <= ymax) {
            return 0001;
        } else if (xa > xmax && ya >= ymin && ya <= ymax) {
            return 0010;
        } else if (xa < xmin && ya < ymin) {
            return 0101;
        } else if (xa >= xmin && xa <= xmax && ya < ymin) {
            return 0100;
        } else {
            return 0110;
        }
    }

    public void plotPoint(int xa, int ya, Color c, Graphics g) {
        originX = (getX() + getWidth()) / 2;
        originY = (getY() + getHeight()) / 2;
        Color temp = g.getColor();
        g.setColor(c);
        g.fillOval(originX + scale * xa - 5, originY - scale * ya - 5, 10, 10);
        g.setColor(temp);
    }

    // line clipping algo
    public void plotLine(int x1, int y1, int x2, int y2, Color c, Graphics g) {
        int dy = y2 - y1;
        int dx = x2 - x1;
        int xa = x1;
        int ya = y1;

        if (dx == 0) { // if line to plot is horizontal, slope = 0
            while (ya <= y2) {
                if (xa >= xmin && xa <= xmax && ya >= ymin && ya <= ymax) // plot only within the window, omit rest
                    plotPoint(xa, ya, c, g);
                ya++;
            }
        } else {
            double m = Math.abs((y2 - y1) / (x2 - x1));

            if (m < 1) { // slope < 1
                int p = 2 * dy - dx;
                while (xa <= x2) {
                    if (p >= 0) {
                        if (xa >= xmin && xa <= xmax && ya >= ymin && ya <= ymax) // plot only within the window, omit
                                                                                  // rest
                            plotPoint(xa, ya, c, g);
                        ya = ya + 1;
                        p = p + 2 * dy - 2 * dx;
                    } else {
                        if (xa >= xmin && xa <= xmax && ya >= ymin && ya <= ymax) // plot only within the window, omit
                                                                                  // rest
                            plotPoint(xa, ya, c, g);
                        p = p + 2 * dy;
                    }
                    xa = xa + 1;
                }
            } else { // slope >= 1
                int p = 2 * dx - dy;
                while (ya <= y2) {
                    if (p >= 0) {
                        if (xa >= xmin && xa <= xmax && ya >= ymin && ya <= ymax) // plot only within the window, omit
                                                                                  // rest
                            plotPoint(xa, ya, c, g);
                        xa = xa + 1;
                        p = p + 2 * dx - 2 * dy;
                    } else {
                        if (xa >= xmin && xa <= xmax && ya >= ymin && ya <= ymax) // plot only within the window, omit
                                                                                  // rest
                            plotPoint(xa, ya, c, g);
                        p = p + 2 * dx;
                    }
                    ya = ya + 1;
                }
            }
        }
    }

    // identify the regions the line lies in
    public void identifyLinesRegion(int x1, int y1, int x2, int y2, Graphics g, Color c) {
        g.setColor(c);
        if (((TBRL(x1, y1)) | (TBRL(x2, y2))) == 0) // if line completely lies inside window
            plotLine(x1, y1, x2, y2, c, g);
        else if ((TBRL(x1, y1) & TBRL(x2, y2)) != 0) // if the line lie completely outside window
            plotLine(x1, y1, x2, y2, c, g);
        else // if the line lies partially inside window
            plotLine(x1, y1, x2, y2, c, g);
    }

    public void paint(Graphics g) {

        // coordinate plane grid structure
        g.setColor(Color.yellow);
        for (int i = originY; i >= 0; i -= scale) {
            g.drawLine(0, i, getWidth(), i);
        }
        for (int i = originY; i <= getHeight(); i += scale) {
            g.drawLine(0, i, getWidth(), i);
        }
        for (int i = originX; i >= 0; i -= scale) {
            g.drawLine(i, 0, i, getHeight());
        }
        for (int i = originX; i <= getWidth(); i += scale) {
            g.drawLine(i, 0, i, getHeight());
        }

        // coordinate axis
        g.setColor(Color.red);
        g.drawLine(0, originY, getWidth(), originY);
        g.drawLine(originX, 0, originX, getHeight());

        // draw window
        Windowclip(xmin, ymin, xmax, ymax, g, Color.blue);

        // identify in which region the line lies in, clip it accordingly
        identifyLinesRegion(x1, y1, x2, y2, g, Color.blue);

        plotPoint(x1, y1, Color.pink, g);
        plotPoint(x2, y2, Color.pink, g);
    }
}