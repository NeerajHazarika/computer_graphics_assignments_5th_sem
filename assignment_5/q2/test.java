import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class test extends Applet implements ActionListener {
    int scale = 30;
    int originX = 0;
    int originY = 0;
    Label l3, l4, l5, l6;
    TextField t3, t4, t5, t6;
    Button B1, B2, B3;
    int xmin, xmax, ymin, ymax;

    public void init() {
        xmin = -(getWidth() / 2);
        xmax = (getWidth() / 2);
        ymin = -(getHeight() / 2);
        ymax = (getHeight() / 2);

        t3 = new TextField(10);
        t4 = new TextField(10);
        t5 = new TextField(10);
        t6 = new TextField(10);
        l3 = new Label("xmin");
        l4 = new Label("xmax");
        l5 = new Label("ymin");
        l6 = new Label("ymax");
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        add(l5);
        add(t5);
        add(l6);
        add(t6);
        Font myFont = new Font("Serif", Font.BOLD, 25);
        l3.setFont(myFont);
        l4.setFont(myFont);
        l5.setFont(myFont);
        l6.setFont(myFont);
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
            xmin = Integer.parseInt(t3.getText());
            xmax = Integer.parseInt(t4.getText());
            ymin = Integer.parseInt(t5.getText());
            ymax = Integer.parseInt(t6.getText());
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

    public void polygon(Graphics g) {
        draw(-17, -17, 17, -17, g, Color.blue);
        draw(17, -17, 0, 20, g, Color.blue);
        draw(0, 20, -17, -17, g, Color.blue);

    }

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

    public void draw(int x1, int y1, int x2, int y2, Graphics g, Color c) {
        g.setColor(c);
        if (((TBRL(x1, y1)) | (TBRL(x2, y2))) == 0)
            plotLine(x1, y1, x2, y2, c, g);
        else if ((TBRL(x1, y1) & TBRL(x2, y2)) != 0) {
            plotLine(x1, y1, x2, y2, c, g);
        } else
            plotLine(x1, y1, x2, y2, c, g);
    }

    public void plotLine(int x1, int y1, int x2, int y2, Color c, Graphics g) {
        int dy = y2 - y1;
        int dx = x2 - x1;
        if (dx < 0)
            dx *= -1;
        if (dy < 0)
            dy *= -1;
        int xinc = 1, yinc = 1;
        if (x1 > x2)
            xinc = -1;
        if (y1 > y2)
            yinc = -1;
        int xa = x1;
        int ya = y1;
        if (dx == 0) {
            while (ya != y2) {
                if (xa >= xmin && xa <= xmax && ya >= ymin && ya <= ymax)
                    plotPoint(xa, ya, c, g);
                ya += yinc;
            }

        }

        else {
            double m = Math.abs((y2 - y1) / (x2 - x1));
            if (m < 1) {
                int p = 2 * dy - dx;
                while (xa != x2) {
                    if (p >= 0) {
                        if (xa >= xmin && xa <= xmax && ya >= ymin && ya <= ymax)
                            plotPoint(xa, ya, c, g);
                        ya += yinc;
                        p = p + 2 * dy - 2 * dx;
                    } else {
                        if (xa >= xmin && xa <= xmax && ya >= ymin && ya <= ymax)
                            plotPoint(xa, ya, c, g);
                        p = p + 2 * dy;
                    }
                    xa += xinc;
                }
            } else {
                int p = 2 * dx - dy;
                while (ya != y2) {
                    if (p >= 0) {
                        if (xa >= xmin && xa <= xmax && ya >= ymin && ya <= ymax)
                            plotPoint(xa, ya, c, g);
                        xa += xinc;
                        p = p + 2 * dx - 2 * dy;
                    } else {
                        if (xa >= xmin && xa <= xmax && ya >= ymin && ya <= ymax)
                            plotPoint(xa, ya, c, g);
                        p = p + 2 * dx;
                    }
                    ya += yinc;
                }
            }
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

    public void paint(Graphics g) {
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
        g.setColor(Color.red);
        g.drawLine(0, originY, getWidth(), originY);
        g.drawLine(originX, 0, originX, getHeight());
        Windowclip(xmin, ymin, xmax, ymax, g, Color.red);
        polygon(g);
    }
}