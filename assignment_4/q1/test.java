import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class test extends Applet implements ActionListener, MouseWheelListener {

  int originX, originY;
  int height, width;
  int gap = 20;
  int toothFlag = 0;
  int earShapeFlag = 0;
  int earSizeFlag = 0;
  int bodySpotFlag = 0;
  int legArmSpotFlag = 0;
  int tailShapeFlag = 1;
  int bodyHairFlag = 0;
  int legArmSizeFlag = 0;
  Button b1 = new Button(" + ");
  Button b2 = new Button(" - ");
  Button b3 = new Button(" Beak Shape ");
  Button b4 = new Button(" Ear Shape ");
  Button b5 = new Button(" Ear Size ");
  Button b6 = new Button(" Body Spot ");
  Button b7 = new Button(" Body Hair ");
  Button b8 = new Button(" Leg/Arm Size ");
  Button b9 = new Button(" Leg/Arm Spot ");
  Button b10 = new Button(" Tail Shape ");

  public void init() {
    setBackground(Color.WHITE);
    b1.setBackground(new Color(31, 70, 144));
    b2.setBackground(new Color(255, 229, 180));
    b3.setBackground(new Color(255, 229, 180));
    b4.setBackground(new Color(255, 229, 180));
    b5.setBackground(new Color(255, 229, 180));
    b6.setBackground(new Color(255, 229, 180));
    b7.setBackground(new Color(255, 229, 180));
    b8.setBackground(new Color(255, 229, 180));
    b9.setBackground(new Color(255, 229, 180));
    b10.setBackground(new Color(255, 229, 180));
    add(b1);
    add(b2);
    add(b3);
    add(b4);
    add(b5);
    add(b6);
    add(b7);
    add(b8);
    add(b9);
    add(b10);
    addMouseWheelListener(this);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    b4.addActionListener(this);
    b5.addActionListener(this);
    b6.addActionListener(this);
    b7.addActionListener(this);
    b8.addActionListener(this);
    b9.addActionListener(this);
    b10.addActionListener(this);
  }

  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    height = getHeight();
    width = getWidth();
    originX = (getX() + width) / 2;
    originY = (getY() + height) / 2;
    // drawGrid(g);
    // drawShape(g);
    // drawCircle(g, 10, -12, 12);
    drawXaxis(g);
    drawYaxis(g);
    // drawEllipse(g, 5, 10, 0, 0, 45);
    drawShape(g);
  }

  // Function to draw origin
  public void drawOriginCircle(Graphics g) {
    g.setColor(Color.RED);
    g.fillOval(originX - 5, originY - 5, 10, 10);
  }

  // Function for plotting points (points are rectangle not circle)
  public void plotPoint(Graphics g, int x, int y, Color c) {
    g.setColor(c);
    g.fillRect(
        originX + (x * gap) - gap / 2,
        originY - (y * gap) - gap / 2,
        gap,
        gap);
  }

  // Function to draw X-axis
  public void drawXaxis(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(0, originY - 2, width, 4);
  }

  // Function to draw Y-axis
  public void drawYaxis(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(originX - 2, 0, 4, height);
  }

  // Function to draw the Grid
  public void drawGrid(Graphics g) {
    drawHorizontalLines(g);
    drawVeritcalLines(g);
  }

  // Function to draw the horizontal lines of the grid
  public void drawHorizontalLines(Graphics g) {
    g.setColor(Color.RED);
    for (int i = originX; i <= width; i += gap) {
      g.drawLine(i, 0, i, height);
    }
    for (int i = originX; i >= 0; i -= gap) {
      g.drawLine(i, 0, i, height);
    }
  }

  // Function to draw the vertical lines of the grid
  public void drawVeritcalLines(Graphics g) {
    g.setColor(Color.RED);
    for (int i = originY; i <= height; i += gap) {
      g.drawLine(0, i, width, i);
      // add coordinate text
    }
    for (int i = originY; i >= 0; i -= gap) {
      g.drawLine(0, i, width, i);
    }
  }

  // Function for the buttons
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == b1)
      zoom(10);

    if (e.getSource() == b2)
      zoom(-10);

    if (e.getSource() == b3) {
      if (toothFlag == 0)
        toothFlag = 1;
      else
        toothFlag = 0;
      repaint();
    }

    if (e.getSource() == b4) {
      if (earShapeFlag == 0)
        earShapeFlag = 1;
      else
        earShapeFlag = 0;
      repaint();
    }

    if (e.getSource() == b5) {
      if (earSizeFlag == 0)
        earSizeFlag = 1;
      else
        earSizeFlag = 0;
      repaint();
    }

    if (e.getSource() == b6) {
      if (bodySpotFlag == 0)
        bodySpotFlag = 1;
      else
        bodySpotFlag = 0;
      repaint();
    }

    if (e.getSource() == b7) {
      if (bodyHairFlag == 0)
        bodyHairFlag = 1;
      else
        bodyHairFlag = 0;
      repaint();
    }

    if (e.getSource() == b8) {
      if (legArmSizeFlag == 0)
        legArmSizeFlag = 1;
      else if (legArmSizeFlag == 1)
        legArmSizeFlag = 2;
      else
        legArmSizeFlag = 0;
      repaint();
    }

    if (e.getSource() == b9) {
      if (legArmSpotFlag == 0)
        legArmSpotFlag = 1;
      else
        legArmSpotFlag = 0;
      repaint();
    }

    if (e.getSource() == b10) {
      if (tailShapeFlag == 1)
        tailShapeFlag = 2;
      else if (tailShapeFlag == 2)
        tailShapeFlag = 3;
      else
        tailShapeFlag = 1;
      repaint();
    }
  }

  // Function for the mousewheel
  public void mouseWheelMoved(MouseWheelEvent e) {
    int z = e.getWheelRotation();
    zoom(z);
  }

  // Function for the zoom in feature
  public void zoom(int i) {
    if (gap + i >= 1 && gap + i <= 300) {
      gap += i;
      repaint();
    }
  }

  // func to draw circle
  public void drawCircle(Graphics g, int r, int x1, int y1) {
    int x = 0;
    int y = r;
    double p = (double) 5 / 4 - r;
    plotPoint(g, x + x1, y + y1, Color.black);
    plotPoint(g, x + x1, -y + y1, Color.black);
    plotPoint(g, y + x1, x + y1, Color.black);
    plotPoint(g, -y + x1, x + y1, Color.black);
    while (x <= y) {
      if (p < 0) {
        x = x + 1;
        p = p + 2 * x + 1;
      } else {
        x = x + 1;
        y = y - 1;
        p = p + (2 * x) + 1 - (2 * y);
      }
      plotPoint(g, x + x1, y + y1, Color.black);
      plotPoint(g, y + x1, x + y1, Color.black);
      plotPoint(g, -x + x1, y + y1, Color.black);
      plotPoint(g, -y + x1, x + y1, Color.black);
      plotPoint(g, x + x1, -y + y1, Color.black);
      plotPoint(g, y + x1, -x + y1, Color.black);
      plotPoint(g, -x + x1, -y + y1, Color.black);
      plotPoint(g, -y + x1, -x + y1, Color.black);
    }
  }

  // func to draw ellipse
  public void drawEllipse(Graphics g, int rx, int ry, int tx, int ty, double degree) {
    double radian = Math.toRadians(degree);
    int x = 0;
    int y = ry;
    double p1 = (ry * ry) - (rx * rx * ry) + (double) (rx * rx) / 4;
    plotPoint(
        g,
        (int) ((x * Math.cos(radian)) - (y * Math.sin(radian)) + tx),
        (int) ((x * Math.sin(radian)) + (y * Math.cos(radian)) + ty),
        Color.red);
    plotPoint(
        g,
        (int) ((x * Math.cos(radian)) - ((-y) * Math.sin(radian)) + tx),
        (int) ((x * Math.sin(radian)) + ((-y) * Math.cos(radian)) + ty),
        Color.red);
    while (2 * ry * ry * x <= 2 * rx * rx * y) {
      if (p1 < 0) {
        x++;
        p1 = p1 + (2 * ry * ry * x) + (ry * ry);
      } else {
        x++;
        y--;
        p1 = p1 + (2 * ry * ry * x) + (ry * ry) - (2 * rx * rx * y);
      }
      plotPoint(
          g,
          (int) ((x * Math.cos(radian)) - (y * Math.sin(radian)) + tx),
          (int) ((x * Math.sin(radian)) + (y * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) (((-x) * Math.cos(radian)) - (y * Math.sin(radian)) + tx),
          (int) (((-x) * Math.sin(radian)) + (y * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) ((x * Math.cos(radian)) - ((-y) * Math.sin(radian)) + tx),
          (int) ((x * Math.sin(radian)) + ((-y) * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) (((-x) * Math.cos(radian)) - ((-y) * Math.sin(radian)) + tx),
          (int) (((-x) * Math.sin(radian)) + ((-y) * Math.cos(radian)) + ty),
          Color.red);
    }

    double p2 = (ry * ry * (x + 0.5) * (x + 0.5)) +
        ((y - 1) * (y - 1) * rx * rx) -
        rx *
            rx *
            ry *
            ry;
    while (2 * ry * ry * x > 2 * rx * rx * y && (y != 0)) {
      if (p2 < 0) {
        x++;
        y--;
        p2 = p2 + (2 * ry * ry * x) - (2 * rx * rx * y) + (rx * rx);
      } else {
        y--;
        p2 = p2 - (2 * rx * rx * y) + (rx * rx);
      }

      plotPoint(
          g,
          (int) ((x * Math.cos(radian)) - (y * Math.sin(radian)) + tx),
          (int) ((x * Math.sin(radian)) + (y * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) (((-x) * Math.cos(radian)) - (y * Math.sin(radian)) + tx),
          (int) (((-x) * Math.sin(radian)) + (y * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) ((x * Math.cos(radian)) - ((-y) * Math.sin(radian)) + tx),
          (int) ((x * Math.sin(radian)) + ((-y) * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) (((-x) * Math.cos(radian)) - ((-y) * Math.sin(radian)) + tx),
          (int) (((-x) * Math.sin(radian)) + ((-y) * Math.cos(radian)) + ty),
          Color.red);
      // plotPoint(g, x, y, Color.red);
      // plotPoint(g, -x, y, Color.red);
      // plotPoint(g, x, -y, Color.red);
      // plotPoint(g, -x, -y, Color.red);
    }
  }

  // func to draw line using DDA
  void DDALine(Graphics g, int x0, int y0, int x1, int y1) {
    int dx = (x1 - x0);
    int dy = (y1 - y0);

    int step;
    if (Math.abs(dx) > Math.abs(dy)) {
      step = Math.abs(dx);
    } else {
      step = Math.abs(dy);
    }

    float x_incr = (float) dx / step;
    float y_incr = (float) dy / step;
    float x = (float) x0;
    float y = (float) y0;

    for (int i = 0; i < step; i++) {
      plotPoint(g, Math.round(x), Math.round(y), Color.green);
      x += x_incr;
      y += y_incr;
    }
  }

  // func to draw triangle
  public void drawTriangle(Graphics g, int x0, int y0, int x1, int y1, int x2, int y2) {
    DDALine(g, x0, y0, x1, y1);
    DDALine(g, x1, y1, x2, y2);
    DDALine(g, x2, y2, x0, y0);
  }

  // func to draw tooth
  public void drawTooth(Graphics g) {
    DDALine(g, -78, 65, -78, 63);
    DDALine(g, -76, 65, -76, 63);
    DDALine(g, -74, 65, -74, 63);
    DDALine(g, -72, 65, -72, 63);
    DDALine(g, -70, 65, -70, 63);
    DDALine(g, -68, 65, -68, 63);

    DDALine(g, -78, 62, -78, 60);
    DDALine(g, -76, 62, -76, 60);
    DDALine(g, -74, 62, -74, 60);
    DDALine(g, -72, 62, -72, 60);
    DDALine(g, -70, 62, -70, 60);
    DDALine(g, -68, 62, -68, 60);
  }

  // func to draw beak
  public void drawBeak(Graphics g) {
    DDALine(g, -80, 65, -64, 69);
    DDALine(g, -80, 65, -64, 65);
    DDALine(g, -64, 65, -64, 69);
    DDALine(g, -80, 61, -64, 61);
    DDALine(g, -64, 61, -64, 56);
    DDALine(g, -80, 61, -64, 56);

    // drawCircle(g,6, -38, 78);
    if (toothFlag == 1)
      drawTooth(g);
  }

  // func to draw ear
  public void drawEar(Graphics g) {
    if (earSizeFlag == 0) {
      // small ear size
      if (earShapeFlag == 0)
        drawCircle(g, 6, -38, 80);
      else
        drawTriangle(g, -38, 72, -48, 78, -30, 90);
    } else {
      // large ear size
      if (earShapeFlag == 0)
        drawCircle(g, 10, -38, 80);
      else
        drawTriangle(g, -35, 70, -52, 78, -30, 95);
    }
  }

  public void drawSpotBody(Graphics g) {
    if (bodySpotFlag == 1) {
      drawCircle(g, 2, 8 - 20, 50);
      drawCircle(g, 2, -2 - 20, 50);
      drawCircle(g, 2, -12 - 20, 50);

      drawCircle(g, 2, 18 - 12, 40);
      drawCircle(g, 2, 8 - 12, 40);
      drawCircle(g, 2, -2 - 12, 40);
      drawCircle(g, 2, -12 - 12, 40);
      drawCircle(g, 2, -22 - 12, 40);
      drawCircle(g, 2, -32 - 12, 40);

      drawCircle(g, 2, 18 - 12, 30);
      drawCircle(g, 2, 8 - 12, 30);
      drawCircle(g, 2, -2 - 12, 30);
      drawCircle(g, 2, -12 - 12, 30);
      drawCircle(g, 2, -22 - 12, 30);
      drawCircle(g, 2, -32 - 12, 30);

      drawCircle(g, 2, 18 - 4, 20);
      drawCircle(g, 2, 8 - 4, 20);
      drawCircle(g, 2, -2 - 4, 20);
      drawCircle(g, 2, -12 - 4, 20);
      drawCircle(g, 2, -22 - 4, 20);
      drawCircle(g, 2, -32 - 4, 20);
      drawCircle(g, 2, -42 - 4, 20);

      drawCircle(g, 2, 18, 10);
      drawCircle(g, 2, 8, 10);
      drawCircle(g, 2, -2, 10);
      drawCircle(g, 2, -12, 10);
      drawCircle(g, 2, -22, 10);
      drawCircle(g, 2, -32, 10);
      drawCircle(g, 2, -42, 10);

      drawCircle(g, 2, 8 + 4, 0);
      drawCircle(g, 2, -2 + 4, 0);
      drawCircle(g, 2, -12 + 4, 0);
      drawCircle(g, 2, -22 + 4, 0);
      drawCircle(g, 2, -32 + 4, 0);
      drawCircle(g, 2, -42 + 4, 0);

      drawCircle(g, 2, -2 + 8, -10);
      drawCircle(g, 2, -12 + 8, -10);
      drawCircle(g, 2, -22 + 8, -10);
      drawCircle(g, 2, -32 + 8, -10);
    }
  }

  public void drawHairBody(Graphics g) {
    if (bodyHairFlag == 1) {
      DDALine(g, -30, 39, -60, 43);
      DDALine(g, -22, 50, -19, 80);
      DDALine(g, -12, 40, -4, 70);
      DDALine(g, -9, 20, 14, 35);
      DDALine(g, 4, 35, 25, 54);
      DDALine(g, 11, 20, 35, 37);
      DDALine(g, 7, 7, 25, -15);
      DDALine(g, -11, -7, -30, -30);
      DDALine(g, -20, 2, -45, -17);
      DDALine(g, -29, 16, -49, 21);
    }
  }

  public void drawBody(Graphics g) {
    drawSpotBody(g);
    drawHairBody(g);
  }

  public void drawSpotLegArm(Graphics g) {
    if (legArmSpotFlag == 1 && legArmSizeFlag == 0) {

      // right thigh spots
      drawCircle(g, 1, 8, -25);
      drawCircle(g, 1, 3, -25);
      drawCircle(g, 1, 9, -30);
      drawCircle(g, 1, 4, -30);
      drawCircle(g, 1, 5, -35);
      drawCircle(g, 1, 10, -35);
      drawCircle(g, 1, 6, -40);
      drawCircle(g, 1, 11, -40);
      drawCircle(g, 1, 7, -45);
      drawCircle(g, 1, 12, -45);

      // right shin
      drawCircle(g, 1, 9, -57);
      drawCircle(g, 1, 6, -62);
      drawCircle(g, 1, 10, -62);
      drawCircle(g, 1, 5, -67);
      drawCircle(g, 1, 9, -67);
      drawCircle(g, 1, 5, -72);

      // left thigh
      drawCircle(g, 1, -9, -27);
      drawCircle(g, 1, -14, -27);
      drawCircle(g, 1, -10, -32);
      drawCircle(g, 1, -15, -32);
      drawCircle(g, 1, -11, -37);
      drawCircle(g, 1, -16, -37);
      drawCircle(g, 1, -12, -42);
      drawCircle(g, 1, -17, -42);
      drawCircle(g, 1, -14, -47);
      drawCircle(g, 1, -19, -47);

      // left shin
      drawCircle(g, 1, -26, -59);
      drawCircle(g, 1, -21, -59);
      drawCircle(g, 1, -29, -64);
      drawCircle(g, 1, -24, -64);
      drawCircle(g, 1, -33, -69);
      drawCircle(g, 1, -28, -69);

      // right upper arm
      drawCircle(g, 1, -55, 10);
      drawCircle(g, 1, -52, 7);
      drawCircle(g, 1, -58, 7);
      drawCircle(g, 1, -55, 4);
      drawCircle(g, 1, -61, 4);
      drawCircle(g, 1, -58, 1);

      // right lower arm
      drawCircle(g, 1, -68, -3);
      drawCircle(g, 1, -72, -2);
      drawCircle(g, 1, -76, -2);

      // left upper arm
      drawCircle(g, 1, -55, 19);
      drawCircle(g, 1, -59, 17);
      drawCircle(g, 1, -65, 17);
      drawCircle(g, 1, -62, 14);
      drawCircle(g, 1, -68, 14);

      // left lower arm
      drawCircle(g, 1, -78, 14);
      drawCircle(g, 1, -82, 17);

    } else if (legArmSpotFlag == 1 && legArmSizeFlag == 1) {
      // Leg Spots

      drawCircle(g, 1, 8, -25);
      drawCircle(g, 1, 3, -25);
      drawCircle(g, 1, -7, -25);
      drawCircle(g, 1, -12, -25);

      drawCircle(g, 1, 13, -30);
      drawCircle(g, 1, 8, -30);
      drawCircle(g, 1, 3, -30);
      drawCircle(g, 1, -7, -30);
      drawCircle(g, 1, -12, -30);
      drawCircle(g, 1, -17, -30);

      drawCircle(g, 1, 13 - 2, -35);
      drawCircle(g, 1, 8 - 2, -35);
      drawCircle(g, 1, 3 - 2, -35);
      drawCircle(g, 1, -7 - 2, -35);
      drawCircle(g, 1, -12 - 2, -35);
      drawCircle(g, 1, -17 - 2, -35);

      drawCircle(g, 1, 13, -40);
      drawCircle(g, 1, 8, -40);
      drawCircle(g, 1, 3, -40);
      drawCircle(g, 1, -7, -40);
      drawCircle(g, 1, -12, -40);
      drawCircle(g, 1, -17, -40);
      drawCircle(g, 1, -12, -40);

      drawCircle(g, 1, 13, -45);
      drawCircle(g, 1, 8, -45);
      drawCircle(g, 1, 3, -45);
      drawCircle(g, 1, -7 - 2, -45);
      drawCircle(g, 1, -12 - 2, -45);
      drawCircle(g, 1, -17 - 2, -45);

      drawCircle(g, 1, 13 + 2, -50);
      drawCircle(g, 1, 8 + 2, -50);
      drawCircle(g, 1, 3 + 2, -50);
      drawCircle(g, 1, -7 - 4, -50);
      drawCircle(g, 1, -12 - 4, -50);
      drawCircle(g, 1, -17 - 4, -50);

      drawCircle(g, 1, 13 + 4, -55);
      drawCircle(g, 1, 8 + 4, -55);
      drawCircle(g, 1, 3 + 4, -55);
      drawCircle(g, 1, -7 - 6, -55);
      drawCircle(g, 1, -12 - 6, -55);
      drawCircle(g, 1, -17 - 6, -55);

      drawCircle(g, 1, 13, -70);
      drawCircle(g, 1, 8, -70);
      drawCircle(g, 1, 3, -70);
      drawCircle(g, 1, -7 - 18, -70);
      drawCircle(g, 1, -12 - 18, -70);

      drawCircle(g, 1, 8, -75);
      drawCircle(g, 1, 3, -75);
      drawCircle(g, 1, -7 - 18 - 5, -75);
      drawCircle(g, 1, -12 - 18 - 5, -75);

      drawCircle(g, 1, 8, -80);
      drawCircle(g, 1, 3, -80);
      drawCircle(g, 1, -7 - 18 - 10, -80);
      drawCircle(g, 1, -12 - 18 - 10, -80);

      drawCircle(g, 1, 8, -85);
      drawCircle(g, 1, 3, -85);
      drawCircle(g, 1, -7 - 18 - 10, -85);
      drawCircle(g, 1, -12 - 18 - 10, -85);

      // arm Spots
      // right upper arm spots
      drawCircle(g, 1, -62, 7);
      drawCircle(g, 1, -56, 7);
      drawCircle(g, 1, -50, 7);

      drawCircle(g, 1, -66, 2);
      drawCircle(g, 1, -60, 2);
      drawCircle(g, 1, -54, 2);

      drawCircle(g, 1, -70, -3);
      drawCircle(g, 1, -64, -3);
      drawCircle(g, 1, -58, -3);

      drawCircle(g, 1, -68, -8);

      // right lower arm
      drawCircle(g, 1, -82, -8);
      drawCircle(g, 1, -82, -13);

      drawCircle(g, 1, -87, -8);
      drawCircle(g, 1, -87, -13);

      drawCircle(g, 1, -92, -7);
      drawCircle(g, 1, -92, -12);

      drawCircle(g, 1, -97, -6);
      drawCircle(g, 1, -97, -11);

      drawCircle(g, 1, -97, -6);
      drawCircle(g, 1, -97, -11);

      // left upper arm
      drawCircle(g, 1, -56, 20);

      drawCircle(g, 1, -62, 20);
      drawCircle(g, 1, -62, 15);

      drawCircle(g, 1, -68, 19);
      drawCircle(g, 1, -68, 13);

      drawCircle(g, 1, -74, 17);
      drawCircle(g, 1, -74, 11);

      drawCircle(g, 1, -80, 15);
      drawCircle(g, 1, -80, 9);

      // left lower Arm
      drawCircle(g, 1, -91, 16);
      drawCircle(g, 1, -94, 12);

      drawCircle(g, 1, -96, 19);
      drawCircle(g, 1, -100, 15);

      drawCircle(g, 1, -100, 22);
      drawCircle(g, 1, -104, 18);

      drawCircle(g, 1, -106, 23);

    } else if (legArmSpotFlag == 1 && legArmSizeFlag == 2) {

      // right upper arm
      drawCircle(g, 1, -53, 7);
      drawCircle(g, 1, -49, 3);

      drawCircle(g, 1, -57, 3);
      drawCircle(g, 1, -53, -1);

      drawCircle(g, 1, -61, -1);
      drawCircle(g, 1, -57, -5);

      drawCircle(g, 1, -65, -5);
      drawCircle(g, 1, -61, -9);

      drawCircle(g, 1, -69, -9);
      drawCircle(g, 1, -65, -13);

      drawCircle(g, 1, -73, -13);
      drawCircle(g, 1, -69, -17);

      drawCircle(g, 1, -73, -21);

      // right lower Arm
      drawCircle(g, 1, -88, -21);

      drawCircle(g, 1, -94, -17);
      drawCircle(g, 1, -94, -24);

      drawCircle(g, 1, -100, -16);
      drawCircle(g, 1, -100, -23);

      drawCircle(g, 1, -106, -15);
      drawCircle(g, 1, -106, -22);

      drawCircle(g, 1, -112, -18);

      // left upper arm
      drawCircle(g, 1, -58, 27);
      drawCircle(g, 1, -58, 21);

      drawCircle(g, 1, -65, 25);
      drawCircle(g, 1, -65, 19);

      drawCircle(g, 1, -72, 23);
      drawCircle(g, 1, -72, 17);

      drawCircle(g, 1, -79, 21);
      drawCircle(g, 1, -79, 15);

      drawCircle(g, 1, -86, 19);
      drawCircle(g, 1, -86, 13);

      // left upper arm
      drawCircle(g, 1, -103, 17);
      drawCircle(g, 1, -107, 13);

      drawCircle(g, 1, -110, 21);
      drawCircle(g, 1, -114, 17);

      drawCircle(g, 1, -117, 25);
      drawCircle(g, 1, -121, 20);

      // right thigh
      drawCircle(g, 1, 9, -27);
      drawCircle(g, 1, 4, -29);
      drawCircle(g, 1, -1, -31);

      drawCircle(g, 1, 11, -35);
      drawCircle(g, 1, 6, -37);
      drawCircle(g, 1, 1, -39);

      drawCircle(g, 1, 13, -43);
      drawCircle(g, 1, 8, -45);
      drawCircle(g, 1, 3, -47);

      drawCircle(g, 1, 15, -51);
      drawCircle(g, 1, 10, -53);
      drawCircle(g, 1, 5, -55);

      drawCircle(g, 1, 17, -59);
      drawCircle(g, 1, 12, -61);
      drawCircle(g, 1, 7, -63);

      drawCircle(g, 1, 19, -67);
      drawCircle(g, 1, 14, -69);
      drawCircle(g, 1, 9, -71);

      // right shin
      drawCircle(g, 1, 12, -89);
      drawCircle(g, 1, 17, -92);

      drawCircle(g, 1, 10, -94);
      drawCircle(g, 1, 15, -97);

      drawCircle(g, 1, 8, -99);
      drawCircle(g, 1, 13, -102);

      drawCircle(g, 1, 6, -104);
      drawCircle(g, 1, 11, -107);

      drawCircle(g, 1, 4, -109);
      drawCircle(g, 1, 9, -112);

      // left thigh
      drawCircle(g, 1, -21, -27);
      drawCircle(g, 1, -15, -29);
      drawCircle(g, 1, -9, -31);

      drawCircle(g, 1, -24, -34);
      drawCircle(g, 1, -18, -36);
      drawCircle(g, 1, -12, -38);

      drawCircle(g, 1, -27, -41);
      drawCircle(g, 1, -21, -43);
      drawCircle(g, 1, -15, -45);
      drawCircle(g, 1, -9, -47);

      drawCircle(g, 1, -30, -48);
      drawCircle(g, 1, -24, -50);
      drawCircle(g, 1, -18, -52);
      drawCircle(g, 1, -12, -54);

      drawCircle(g, 1, -29, -56);
      drawCircle(g, 1, -23, -58);
      drawCircle(g, 1, -17, -60);

      drawCircle(g, 1, -30, -64);
      drawCircle(g, 1, -24, -66);
      drawCircle(g, 1, -18, -68);

      // left shin
      drawCircle(g, 1, -37, -84);
      drawCircle(g, 1, -32, -88);

      drawCircle(g, 1, -42, -90);
      drawCircle(g, 1, -37, -94);

      drawCircle(g, 1, -47, -96);
      drawCircle(g, 1, -42, -100);

      drawCircle(g, 1, -49, -104);
    }
  }

  public void drawArmLeg(Graphics g) {
    if (legArmSizeFlag == 0) {

      drawEllipse(g, 20, 7, 8, -36, 105); // right thigh
      drawEllipse(g, 15, 5, 8, -65, 70); // right shin
      drawCircle(g, 4, 2, -80); // right toe

      drawEllipse(g, 20, 7, -15, -37, 75); // left thigh
      drawEllipse(g, 15, 5, -27, -64, 50); // left shin
      drawCircle(g, 4, -36, -77); // left toe

      drawEllipse(g, 13, 5, -56, 6, 45); // right upper arm
      drawEllipse(g, 10, 4, -73, -2, 175); // right lower arm
      drawCircle(g, 3, -85, -2); // right hand

      drawEllipse(g, 13, 5, -63, 16, 20); // left upper arm
      drawEllipse(g, 10, 4, -80, 16, 150); // left lower arm
      drawCircle(g, 3, -91, 21); // left hand

    } else if (legArmSizeFlag == 1) {

      drawEllipse(g, 25, 10, 10, -42, 105); // right thigh
      drawEllipse(g, 25, 10, -15, -42, 75); // left thigh
      drawEllipse(g, 20, 8, -35, -78, 50); // left shin
      drawEllipse(g, 20, 8, 6, -80, 70); // right shin
      drawCircle(g, 6, -5, -98); // right toe
      drawCircle(g, 6, -52, -94); // left toe
      drawEllipse(g, 20, 8, -62, 0, 45); // right upper arm
      drawEllipse(g, 20, 8, -70, 16, 20); // left upper arm
      drawEllipse(g, 15, 7, -100, 18, 150); // left lower arm
      drawEllipse(g, 15, 7, -90, -10, 175); // right lower arm
      drawCircle(g, 6, -115, 27); // left hand
      drawCircle(g, 6, -108, -6); // right hand
    } else {
      drawEllipse(g, 35, 14, 10, -52, 105); // right thigh
      drawEllipse(g, 24, 8, 11, -100, 70); // right shin
      drawCircle(g, 6, -3, -122); // right toe

      drawEllipse(g, 35, 14, -21, -50, 75); // left thigh
      drawEllipse(g, 24, 8, -40, -92, 50); // left shin
      drawCircle(g, 6, -59, -110); // left toe

      drawEllipse(g, 26, 10, -65, -8, 45); // right upper arm
      drawEllipse(g, 20, 8, -100, -20, 175); // right lower arm
      drawCircle(g, 6, -122, -16); // right hand

      drawEllipse(g, 26, 10, -75, 19, 20); // left upper arm
      drawEllipse(g, 20, 8, -111, 18, 150); // left lower arm
      drawCircle(g, 6, -128, 30); // left hand
    }
  }

  public void drawTail(Graphics g) {
    if (tailShapeFlag == 1) {
      drawEllipse(g, 10, 6, 28, 0, 25);
    } else {
      if (tailShapeFlag == 2) {
        drawTriangle(g, 15, 5, 18, -5, 45, 15);
      } else {
        drawCircle(g, 5, 20, 0);
        drawCircle(g, 3, 20, 0);
        DDALine(g, 20, 4, 30, 25);
        DDALine(g, 22, 4, 36, 23);
        DDALine(g, 23, 2, 40, 18);
        DDALine(g, 24, 1, 44, 13);
        DDALine(g, 24, 0, 47, 8);
        DDALine(g, 24, -1, 49, -1);
        DDALine(g, 23, -2, 47, -8);
        DDALine(g, 22, -3, 45, -15);
        DDALine(g, 21, -4, 41, -20);
      }
    }
  }

  // func to draw animal
  public void drawShape(Graphics g) {
    // animal structure w/o variation
    drawEllipse(g, 35, 45, -15, 19, 30); // body
    drawCircle(g, 17, -50, 62); // face
    drawCircle(g, 5, -54, 65); // eyes

    drawBeak(g);
    drawEar(g);
    drawBody(g);
    drawArmLeg(g);
    drawSpotLegArm(g);
    drawTail(g);

  }
}