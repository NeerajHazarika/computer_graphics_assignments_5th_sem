import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class test extends Applet implements ActionListener, MouseWheelListener {

  int originX, originY;
  int height, width;
  int gap = 20;
  int parent1 = 0;
  int parent2 = 0;
  int child = 0;
  int parent1_features[] = new int[8];
  int parent2_features[] = new int[8];

  Button b1 = new Button(" + ");
  Button b2 = new Button(" - ");
  Button b3 = new Button(" Animal 1 ");
  Button b4 = new Button(" Animal 2 ");
  Button b5 = new Button("Combined Animal");

  public void init() {
    setBackground(Color.WHITE);
    b1.setBackground(new Color(31, 70, 144));
    b2.setBackground(new Color(255, 229, 180));
    b3.setBackground(new Color(255, 229, 180));
    b4.setBackground(new Color(255, 229, 180));
    b5.setBackground(new Color(255, 229, 180));

    add(b1);
    add(b2);
    add(b3);
    add(b4);
    add(b5);

    addMouseWheelListener(this);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    b4.addActionListener(this);
    b5.addActionListener(this);

  }

  public int random_number(int min, int max) {
    return (int) (Math.random() * (max - min + 1) + min);
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

    /*
     * 
     * drawShape(Graphics g, int shift, int toothFlag, int earShapeFlag, int
     * earSizeFlag, int bodySpotFlag, int legArmSpotFlag, int tailShapeFlag, int
     * bodyHairFlag, int legArmSizeFlag)
     * 2 + 8 parameters
     * for 8 parameters, (0/1, 0/1, 0/1, 0/1, 0/1, 1/2/3, 0/1, 0/1/2)
     */

    /*
     * Multi Point Crossover
     * Multi point crossover is a generalization of the one-point crossover wherein
     * alternating segments are swapped to get new off-springs.
     * 
     * let the two point selected be index 2 and 5
     * 0-1: parent1
     * 2-5: parent2
     * 6-7: parent1
     */

    if (parent1 == 0) {
      drawShape(g, -400, 0, 0, 0, 0,
          0, 1, 0, 0);

    } else {
      drawShape(g, -400, parent1_features[0], parent1_features[1], parent1_features[2], parent1_features[3],
          parent1_features[4], parent1_features[5], parent1_features[6], parent1_features[7]);
    }

    if (parent2 == 0) {
      drawShape(g, -200, 0, 0, 0, 0,
          0, 1, 0, 0);

    } else {

      drawShape(g, -200, parent2_features[0], parent2_features[1], parent2_features[2], parent2_features[3],
          parent2_features[4], parent2_features[5], parent2_features[6], parent2_features[7]);
    }
    if (child == 0) {
      drawShape(g, 0, 0, 0, 0, 0,
          0, 1, 0, 0);
    } else
      drawShape(g, 0, parent1_features[0], parent1_features[1], parent2_features[2], parent2_features[3],
          parent2_features[4], parent1_features[5], parent1_features[6], parent1_features[7]);
  }

  // Function to draw origin
  public void drawOriginCircle(Graphics g) {
    g.setColor(Color.RED);
    g.fillOval(originX - 5, originY - 5, 10, 10);
  }

  // Function for plotting points
  public void plotPoint(Graphics g, int shift, int y, Color c) {
    g.setColor(c);
    g.fillRect(
        originX + ((shift + 200) * gap) - gap / 2,
        originY - ((y) * gap) - gap / 2,
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
      if (parent1 == 0) {
        parent1 = 1;
        parent1_features[0] = random_number(0, 2); // toothFlag
        parent1_features[1] = random_number(0, 2); // earShapeFlag
        parent1_features[2] = random_number(0, 2); // earSizeFlag
        parent1_features[3] = random_number(0, 2); // bodySpotFlag
        parent1_features[4] = random_number(0, 2); // legArmSpotFlag
        parent1_features[5] = random_number(1, 4); // tailShapeFlag
        parent1_features[6] = random_number(0, 2); // bodyHairFlag
        parent1_features[7] = random_number(0, 3); // legArmSizeFlag
      } else {
        parent1 = 0;
      }
      repaint();
    }

    if (e.getSource() == b4) {
      if (parent2 == 0) {
        parent2 = 1;
        parent2_features[0] = random_number(0, 2); // toothFlag
        parent2_features[1] = random_number(0, 2); // earShapeFlag
        parent2_features[2] = random_number(0, 2); // earSizeFlag
        parent2_features[3] = random_number(0, 2); // bodySpotFlag
        parent2_features[4] = random_number(0, 2); // legArmSpotFlag
        parent2_features[5] = random_number(1, 4); // tailShapeFlag
        parent2_features[6] = random_number(0, 2); // bodyHairFlag
        parent2_features[7] = random_number(0, 3); // legArmSizeFlag
      } else {
        parent2 = 0;
      }
      repaint();
    }

    if (e.getSource() == b5) {
      if (child == 0)
        child = 1;
      else
        child = 0;
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

  public void drawCircle(Graphics g, int r, int x1, int y1) {
    int shift = 0;
    int y = r;
    double p = (double) 5 / 4 - r;
    plotPoint(g, shift + x1, y + y1, Color.black);
    plotPoint(g, shift + x1, -y + y1, Color.black);
    plotPoint(g, y + x1, shift + y1, Color.black);
    plotPoint(g, -y + x1, shift + y1, Color.black);
    while (shift <= y) {
      if (p < 0) {
        shift = shift + 1;
        p = p + 2 * shift + 1;
      } else {
        shift = shift + 1;
        y = y - 1;
        p = p + (2 * shift) + 1 - (2 * y);
      }
      plotPoint(g, shift + x1, y + y1, Color.black);
      plotPoint(g, y + x1, shift + y1, Color.black);
      plotPoint(g, -shift + x1, y + y1, Color.black);
      plotPoint(g, -y + x1, shift + y1, Color.black);
      plotPoint(g, shift + x1, -y + y1, Color.black);
      plotPoint(g, y + x1, -shift + y1, Color.black);
      plotPoint(g, -shift + x1, -y + y1, Color.black);
      plotPoint(g, -y + x1, -shift + y1, Color.black);
    }
  }

  public void drawEllipse(
      Graphics g,
      int rx,
      int ry,
      int tx,
      int ty,
      double degree) {
    double radian = Math.toRadians(degree);
    int shift = 0;
    int y = ry;
    double p1 = (ry * ry) - (rx * rx * ry) + (double) (rx * rx) / 4;
    plotPoint(
        g,
        (int) ((shift * Math.cos(radian)) - (y * Math.sin(radian)) + tx),
        (int) ((shift * Math.sin(radian)) + (y * Math.cos(radian)) + ty),
        Color.red);
    plotPoint(
        g,
        (int) ((shift * Math.cos(radian)) - ((-y) * Math.sin(radian)) + tx),
        (int) ((shift * Math.sin(radian)) + ((-y) * Math.cos(radian)) + ty),
        Color.red);
    while (2 * ry * ry * shift <= 2 * rx * rx * y) {
      if (p1 < 0) {
        shift++;
        p1 = p1 + (2 * ry * ry * shift) + (ry * ry);
      } else {
        shift++;
        y--;
        p1 = p1 + (2 * ry * ry * shift) + (ry * ry) - (2 * rx * rx * y);
      }
      plotPoint(
          g,
          (int) ((shift * Math.cos(radian)) - (y * Math.sin(radian)) + tx),
          (int) ((shift * Math.sin(radian)) + (y * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) (((-shift) * Math.cos(radian)) - (y * Math.sin(radian)) + tx),
          (int) (((-shift) * Math.sin(radian)) + (y * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) ((shift * Math.cos(radian)) - ((-y) * Math.sin(radian)) + tx),
          (int) ((shift * Math.sin(radian)) + ((-y) * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) (((-shift) * Math.cos(radian)) - ((-y) * Math.sin(radian)) + tx),
          (int) (((-shift) * Math.sin(radian)) + ((-y) * Math.cos(radian)) + ty),
          Color.red);
    }

    double p2 = (ry * ry * (shift + 0.5) * (shift + 0.5)) +
        ((y - 1) * (y - 1) * rx * rx) -
        rx *
            rx *
            ry *
            ry;
    while (2 * ry * ry * shift > 2 * rx * rx * y && (y != 0)) {
      if (p2 < 0) {
        shift++;
        y--;
        p2 = p2 + (2 * ry * ry * shift) - (2 * rx * rx * y) + (rx * rx);
      } else {
        y--;
        p2 = p2 - (2 * rx * rx * y) + (rx * rx);
      }

      plotPoint(
          g,
          (int) ((shift * Math.cos(radian)) - (y * Math.sin(radian)) + tx),
          (int) ((shift * Math.sin(radian)) + (y * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) (((-shift) * Math.cos(radian)) - (y * Math.sin(radian)) + tx),
          (int) (((-shift) * Math.sin(radian)) + (y * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) ((shift * Math.cos(radian)) - ((-y) * Math.sin(radian)) + tx),
          (int) ((shift * Math.sin(radian)) + ((-y) * Math.cos(radian)) + ty),
          Color.red);
      plotPoint(
          g,
          (int) (((-shift) * Math.cos(radian)) - ((-y) * Math.sin(radian)) + tx),
          (int) (((-shift) * Math.sin(radian)) + ((-y) * Math.cos(radian)) + ty),
          Color.red);
      // plotPoint(g, shift, y, Color.red);
      // plotPoint(g, -shift, y, Color.red);
      // plotPoint(g, shift, -y, Color.red);
      // plotPoint(g, -shift, -y, Color.red);
    }
  }

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
    float shift = (float) x0;
    float y = (float) y0;

    for (int i = 0; i < step; i++) {
      plotPoint(g, Math.round(shift), Math.round(y), Color.green);
      shift += x_incr;
      y += y_incr;
    }
  }

  // func to draw triangle
  public void drawTriangle(Graphics g, int x0, int y0, int x1, int y1, int x2, int y2) {
    DDALine(g, x0, y0, x1, y1);
    DDALine(g, x1, y1, x2, y2);
    DDALine(g, x2, y2, x0, y0);
  }

  public void drawTooth(Graphics g, int shift) {
    DDALine(g, -78 + shift, 65, -78 + shift, 63);
    DDALine(g, -76 + shift, 65, -76 + shift, 63);
    DDALine(g, -74 + shift, 65, -74 + shift, 63);
    DDALine(g, -72 + shift, 65, -72 + shift, 63);
    DDALine(g, -70 + shift, 65, -70 + shift, 63);
    DDALine(g, -68 + shift, 65, -68 + shift, 63);

    DDALine(g, -78 + shift, 62, -78 + shift, 60);
    DDALine(g, -76 + shift, 62, -76 + shift, 60);
    DDALine(g, -74 + shift, 62, -74 + shift, 60);
    DDALine(g, -72 + shift, 62, -72 + shift, 60);
    DDALine(g, -70 + shift, 62, -70 + shift, 60);
    DDALine(g, -68 + shift, 62, -68 + shift, 60);
  }

  public void drawBeak(Graphics g, int shift, int toothFlag) {
    DDALine(g, -80 + shift, 65, -64 + shift, 69);
    DDALine(g, -80 + shift, 65, -64 + shift, 65);
    DDALine(g, -64 + shift, 65, -64 + shift, 69);
    DDALine(g, -80 + shift, 61, -64 + shift, 61);
    DDALine(g, -64 + shift, 61, -64 + shift, 56);
    DDALine(g, -80 + shift, 61, -64 + shift, 56);

    // drawCircle(g,6, -38, 78);
    if (toothFlag == 1)
      drawTooth(g, shift);
  }

  // func to draw ear
  public void drawEar(Graphics g, int shift, int earSizeFlag, int earShapeFlag) {
    if (earSizeFlag == 0) {
      // small ear size
      if (earShapeFlag == 0)
        drawCircle(g, 6, -38 + shift, 80);
      else
        drawTriangle(g, -38 + shift, 72, -48 + shift, 78, -30 + shift, 90);
    } else {
      // large ear size
      if (earShapeFlag == 0)
        drawCircle(g, 10, -38 + shift, 80);
      else
        drawTriangle(g, -35 + shift, 70, -52 + shift, 78, -30 + shift, 95);
    }
  }

  public void drawSpotBody(Graphics g, int shift, int bodySpotFlag) {
    if (bodySpotFlag == 1) {
      drawCircle(g, 2, 8 - 20 + shift, 50);
      drawCircle(g, 2, -2 - 20 + shift, 50);
      drawCircle(g, 2, -12 - 20 + shift, 50);

      drawCircle(g, 2, 18 - 12 + shift, 40);
      drawCircle(g, 2, 8 - 12 + shift, 40);
      drawCircle(g, 2, -2 - 12 + shift, 40);
      drawCircle(g, 2, -12 - 12 + shift, 40);
      drawCircle(g, 2, -22 - 12 + shift, 40);
      drawCircle(g, 2, -32 - 12 + shift, 40);

      drawCircle(g, 2, 18 - 12 + shift, 30);
      drawCircle(g, 2, 8 - 12 + shift, 30);
      drawCircle(g, 2, -2 - 12 + shift, 30);
      drawCircle(g, 2, -12 - 12 + shift, 30);
      drawCircle(g, 2, -22 - 12 + shift, 30);
      drawCircle(g, 2, -32 - 12 + shift, 30);

      drawCircle(g, 2, 18 - 4 + shift, 20);
      drawCircle(g, 2, 8 - 4 + shift, 20);
      drawCircle(g, 2, -2 - 4 + shift, 20);
      drawCircle(g, 2, -12 - 4 + shift, 20);
      drawCircle(g, 2, -22 - 4 + shift, 20);
      drawCircle(g, 2, -32 - 4 + shift, 20);
      drawCircle(g, 2, -42 - 4 + shift, 20);

      drawCircle(g, 2, 18 + shift, 10);
      drawCircle(g, 2, 8 + shift, 10);
      drawCircle(g, 2, -2 + shift, 10);
      drawCircle(g, 2, -12 + shift, 10);
      drawCircle(g, 2, -22 + shift, 10);
      drawCircle(g, 2, -32 + shift, 10);
      drawCircle(g, 2, -42 + shift, 10);

      drawCircle(g, 2, 8 + 4 + shift, 0);
      drawCircle(g, 2, -2 + 4 + shift, 0);
      drawCircle(g, 2, -12 + 4 + shift, 0);
      drawCircle(g, 2, -22 + 4 + shift, 0);
      drawCircle(g, 2, -32 + 4 + shift, 0);
      drawCircle(g, 2, -42 + 4 + shift, 0);

      drawCircle(g, 2, -2 + 8 + shift, -10);
      drawCircle(g, 2, -12 + 8 + shift, -10);
      drawCircle(g, 2, -22 + 8 + shift, -10);
      drawCircle(g, 2, -32 + 8 + shift, -10);
    }
  }

  public void drawHairBody(Graphics g, int shift, int bodyHairFlag) {
    if (bodyHairFlag == 1) {
      DDALine(g, -30 + shift, 39, -60 + shift, 43);
      DDALine(g, -22 + shift, 50, -19 + shift, 80);
      DDALine(g, -12 + shift, 40, -4 + shift, 70);
      DDALine(g, -9 + shift, 20, 14 + shift, 35);
      DDALine(g, 4 + shift, 35, 25 + shift, 54);
      DDALine(g, 11 + shift, 20, 35 + shift, 37);
      DDALine(g, 7 + shift, 7, 25 + shift, -15);
      DDALine(g, -11 + shift, -7, -30 + shift, -30);
      DDALine(g, -20 + shift, 2, -45 + shift, -17);
      DDALine(g, -29 + shift, 16, -49 + shift, 21);
    }
  }

  public void drawBody(Graphics g, int shift, int bodySpotFlag, int bodyHairFlag) {
    drawSpotBody(g, shift, bodySpotFlag);
    drawHairBody(g, shift, bodyHairFlag);
  }

  public void drawSpotLegArm(Graphics g, int shift, int legArmSpotFlag, int legArmSizeFlag) {
    if (legArmSpotFlag == 1 && legArmSizeFlag == 0) {

      // right thigh spots
      drawCircle(g, 1, 8 + shift, -25);
      drawCircle(g, 1, 3 + shift, -25);
      drawCircle(g, 1, 9 + shift, -30);
      drawCircle(g, 1, 4 + shift, -30);
      drawCircle(g, 1, 5 + shift, -35);
      drawCircle(g, 1, 10 + shift, -35);
      drawCircle(g, 1, 6 + shift, -40);
      drawCircle(g, 1, 11 + shift, -40);
      drawCircle(g, 1, 7 + shift, -45);
      drawCircle(g, 1, 12 + shift, -45);

      // right shin
      drawCircle(g, 1, 9 + shift, -57);
      drawCircle(g, 1, 6 + shift, -62);
      drawCircle(g, 1, 10 + shift, -62);
      drawCircle(g, 1, 5 + shift, -67);
      drawCircle(g, 1, 9 + shift, -67);
      drawCircle(g, 1, 5 + shift, -72);

      // left thigh
      drawCircle(g, 1, -9 + shift, -27);
      drawCircle(g, 1, -14 + shift, -27);
      drawCircle(g, 1, -10 + shift, -32);
      drawCircle(g, 1, -15 + shift, -32);
      drawCircle(g, 1, -11 + shift, -37);
      drawCircle(g, 1, -16 + shift, -37);
      drawCircle(g, 1, -12 + shift, -42);
      drawCircle(g, 1, -17 + shift, -42);
      drawCircle(g, 1, -14 + shift, -47);
      drawCircle(g, 1, -19 + shift, -47);

      // left shin
      drawCircle(g, 1, -26 + shift, -59);
      drawCircle(g, 1, -21 + shift, -59);
      drawCircle(g, 1, -29 + shift, -64);
      drawCircle(g, 1, -24 + shift, -64);
      drawCircle(g, 1, -33 + shift, -69);
      drawCircle(g, 1, -28 + shift, -69);

      // right upper arm
      drawCircle(g, 1, -55 + shift, 10);
      drawCircle(g, 1, -52 + shift, 7);
      drawCircle(g, 1, -58 + shift, 7);
      drawCircle(g, 1, -55 + shift, 4);
      drawCircle(g, 1, -61 + shift, 4);
      drawCircle(g, 1, -58 + shift, 1);

      // right lower arm
      drawCircle(g, 1, -68 + shift, -3);
      drawCircle(g, 1, -72 + shift, -2);
      drawCircle(g, 1, -76 + shift, -2);

      // left upper arm
      drawCircle(g, 1, -55 + shift, 19);
      drawCircle(g, 1, -59 + shift, 17);
      drawCircle(g, 1, -65 + shift, 17);
      drawCircle(g, 1, -62 + shift, 14);
      drawCircle(g, 1, -68 + shift, 14);

      // left lower arm
      drawCircle(g, 1, -78 + shift, 14);
      drawCircle(g, 1, -82 + shift, 17);

    } else if (legArmSpotFlag == 1 && legArmSizeFlag == 1) {
      // Leg Spots

      drawCircle(g, 1, 8 + shift, -25);
      drawCircle(g, 1, 3 + shift, -25);
      drawCircle(g, 1, -7 + shift, -25);
      drawCircle(g, 1, -12 + shift, -25);

      drawCircle(g, 1, 13 + shift, -30);
      drawCircle(g, 1, 8 + shift, -30);
      drawCircle(g, 1, 3 + shift, -30);
      drawCircle(g, 1, -7 + shift, -30);
      drawCircle(g, 1, -12 + shift, -30);
      drawCircle(g, 1, -17 + shift, -30);

      drawCircle(g, 1, 13 - 2 + shift, -35);
      drawCircle(g, 1, 8 - 2 + shift, -35);
      drawCircle(g, 1, 3 - 2 + shift, -35);
      drawCircle(g, 1, -7 - 2 + shift, -35);
      drawCircle(g, 1, -12 - 2 + shift, -35);
      drawCircle(g, 1, -17 - 2 + shift, -35);

      drawCircle(g, 1, 13 + shift, -40);
      drawCircle(g, 1, 8 + shift, -40);
      drawCircle(g, 1, 3 + shift, -40);
      drawCircle(g, 1, -7 + shift, -40);
      drawCircle(g, 1, -12 + shift, -40);
      drawCircle(g, 1, -17 + shift, -40);
      drawCircle(g, 1, -12 + shift, -40);

      drawCircle(g, 1, 13 + shift, -45);
      drawCircle(g, 1, 8 + shift, -45);
      drawCircle(g, 1, 3 + shift, -45);
      drawCircle(g, 1, -7 - 2 + shift, -45);
      drawCircle(g, 1, -12 - 2 + shift, -45);
      drawCircle(g, 1, -17 - 2 + shift, -45);

      drawCircle(g, 1, 13 + 2 + shift, -50);
      drawCircle(g, 1, 8 + 2 + shift, -50);
      drawCircle(g, 1, 3 + 2 + shift, -50);
      drawCircle(g, 1, -7 - 4 + shift, -50);
      drawCircle(g, 1, -12 - 4 + shift, -50);
      drawCircle(g, 1, -17 - 4 + shift, -50);

      drawCircle(g, 1, 13 + 4 + shift, -55);
      drawCircle(g, 1, 8 + 4 + shift, -55);
      drawCircle(g, 1, 3 + 4 + shift, -55);
      drawCircle(g, 1, -7 - 6 + shift, -55);
      drawCircle(g, 1, -12 - 6 + shift, -55);
      drawCircle(g, 1, -17 - 6 + shift, -55);

      drawCircle(g, 1, 13 + shift, -70);
      drawCircle(g, 1, 8 + shift, -70);
      drawCircle(g, 1, 3 + shift, -70);
      drawCircle(g, 1, -7 - 18 + shift, -70);
      drawCircle(g, 1, -12 - 18 + shift, -70);

      drawCircle(g, 1, 8 + shift, -75);
      drawCircle(g, 1, 3 + shift, -75);
      drawCircle(g, 1, -7 - 18 - 5 + shift, -75);
      drawCircle(g, 1, -12 - 18 - 5 + shift, -75);

      drawCircle(g, 1, 8 + shift, -80);
      drawCircle(g, 1, 3 + shift, -80);
      drawCircle(g, 1, -7 - 18 - 10 + shift, -80);
      drawCircle(g, 1, -12 - 18 - 10 + shift, -80);

      drawCircle(g, 1, 8 + shift, -85);
      drawCircle(g, 1, 3 + shift, -85);
      drawCircle(g, 1, -7 - 18 - 10 + shift, -85);
      drawCircle(g, 1, -12 - 18 - 10 + shift, -85);

      // arm Spots
      // right upper arm spots
      drawCircle(g, 1, -62 + shift, 7);
      drawCircle(g, 1, -56 + shift, 7);
      drawCircle(g, 1, -50 + shift, 7);

      drawCircle(g, 1, -66 + shift, 2);
      drawCircle(g, 1, -60 + shift, 2);
      drawCircle(g, 1, -54 + shift, 2);

      drawCircle(g, 1, -70 + shift, -3);
      drawCircle(g, 1, -64 + shift, -3);
      drawCircle(g, 1, -58 + shift, -3);

      drawCircle(g, 1, -68 + shift, -8);

      // right lower arm
      drawCircle(g, 1, -82 + shift, -8);
      drawCircle(g, 1, -82 + shift, -13);

      drawCircle(g, 1, -87 + shift, -8);
      drawCircle(g, 1, -87 + shift, -13);

      drawCircle(g, 1, -92 + shift, -7);
      drawCircle(g, 1, -92 + shift, -12);

      drawCircle(g, 1, -97 + shift, -6);
      drawCircle(g, 1, -97 + shift, -11);

      drawCircle(g, 1, -97 + shift, -6);
      drawCircle(g, 1, -97 + shift, -11);

      // left upper arm
      drawCircle(g, 1, -56 + shift, 20);

      drawCircle(g, 1, -62 + shift, 20);
      drawCircle(g, 1, -62 + shift, 15);

      drawCircle(g, 1, -68 + shift, 19);
      drawCircle(g, 1, -68 + shift, 13);

      drawCircle(g, 1, -74 + shift, 17);
      drawCircle(g, 1, -74 + shift, 11);

      drawCircle(g, 1, -80 + shift, 15);
      drawCircle(g, 1, -80 + shift, 9);

      // left lower Arm
      drawCircle(g, 1, -91 + shift, 16);
      drawCircle(g, 1, -94 + shift, 12);

      drawCircle(g, 1, -96 + shift, 19);
      drawCircle(g, 1, -100 + shift, 15);

      drawCircle(g, 1, -100 + shift, 22);
      drawCircle(g, 1, -104 + shift, 18);

      drawCircle(g, 1, -106 + shift, 23);

    } else if (legArmSpotFlag == 1 && legArmSizeFlag == 2) {

      // right upper arm
      drawCircle(g, 1, -53 + shift, 7);
      drawCircle(g, 1, -49 + shift, 3);

      drawCircle(g, 1, -57 + shift, 3);
      drawCircle(g, 1, -53 + shift, -1);

      drawCircle(g, 1, -61 + shift, -1);
      drawCircle(g, 1, -57 + shift, -5);

      drawCircle(g, 1, -65 + shift, -5);
      drawCircle(g, 1, -61 + shift, -9);

      drawCircle(g, 1, -69 + shift, -9);
      drawCircle(g, 1, -65 + shift, -13);

      drawCircle(g, 1, -73 + shift, -13);
      drawCircle(g, 1, -69 + shift, -17);

      drawCircle(g, 1, -73 + shift, -21);

      // right lower Arm
      drawCircle(g, 1, -88 + shift, -21);

      drawCircle(g, 1, -94 + shift, -17);
      drawCircle(g, 1, -94 + shift, -24);

      drawCircle(g, 1, -100 + shift, -16);
      drawCircle(g, 1, -100 + shift, -23);

      drawCircle(g, 1, -106 + shift, -15);
      drawCircle(g, 1, -106 + shift, -22);

      drawCircle(g, 1, -112 + shift, -18);

      // left upper arm
      drawCircle(g, 1, -58 + shift, 27);
      drawCircle(g, 1, -58 + shift, 21);

      drawCircle(g, 1, -65 + shift, 25);
      drawCircle(g, 1, -65 + shift, 19);

      drawCircle(g, 1, -72 + shift, 23);
      drawCircle(g, 1, -72 + shift, 17);

      drawCircle(g, 1, -79 + shift, 21);
      drawCircle(g, 1, -79 + shift, 15);

      drawCircle(g, 1, -86 + shift, 19);
      drawCircle(g, 1, -86 + shift, 13);

      // left upper arm
      drawCircle(g, 1, -103 + shift, 17);
      drawCircle(g, 1, -107 + shift, 13);

      drawCircle(g, 1, -110 + shift, 21);
      drawCircle(g, 1, -114 + shift, 17);

      drawCircle(g, 1, -117 + shift, 25);
      drawCircle(g, 1, -121 + shift, 20);

      // right thigh
      drawCircle(g, 1, 9 + shift, -27);
      drawCircle(g, 1, 4 + shift, -29);
      drawCircle(g, 1, -1 + shift, -31);

      drawCircle(g, 1, 11 + shift, -35);
      drawCircle(g, 1, 6 + shift, -37);
      drawCircle(g, 1, 1 + shift, -39);

      drawCircle(g, 1, 13 + shift, -43);
      drawCircle(g, 1, 8 + shift, -45);
      drawCircle(g, 1, 3 + shift, -47);

      drawCircle(g, 1, 15 + shift, -51);
      drawCircle(g, 1, 10 + shift, -53);
      drawCircle(g, 1, 5 + shift, -55);

      drawCircle(g, 1, 17 + shift, -59);
      drawCircle(g, 1, 12 + shift, -61);
      drawCircle(g, 1, 7 + shift, -63);

      drawCircle(g, 1, 19 + shift, -67);
      drawCircle(g, 1, 14 + shift, -69);
      drawCircle(g, 1, 9 + shift, -71);

      // right shin
      drawCircle(g, 1, 12 + shift, -89);
      drawCircle(g, 1, 17 + shift, -92);

      drawCircle(g, 1, 10 + shift, -94);
      drawCircle(g, 1, 15 + shift, -97);

      drawCircle(g, 1, 8 + shift, -99);
      drawCircle(g, 1, 13 + shift, -102);

      drawCircle(g, 1, 6 + shift, -104);
      drawCircle(g, 1, 11 + shift, -107);

      drawCircle(g, 1, 4 + shift, -109);
      drawCircle(g, 1, 9 + shift, -112);

      // left thigh
      drawCircle(g, 1, -21 + shift, -27);
      drawCircle(g, 1, -15 + shift, -29);
      drawCircle(g, 1, -9 + shift, -31);

      drawCircle(g, 1, -24 + shift, -34);
      drawCircle(g, 1, -18 + shift, -36);
      drawCircle(g, 1, -12 + shift, -38);

      drawCircle(g, 1, -27 + shift, -41);
      drawCircle(g, 1, -21 + shift, -43);
      drawCircle(g, 1, -15 + shift, -45);
      drawCircle(g, 1, -9 + shift, -47);

      drawCircle(g, 1, -30 + shift, -48);
      drawCircle(g, 1, -24 + shift, -50);
      drawCircle(g, 1, -18 + shift, -52);
      drawCircle(g, 1, -12 + shift, -54);

      drawCircle(g, 1, -29 + shift, -56);
      drawCircle(g, 1, -23 + shift, -58);
      drawCircle(g, 1, -17 + shift, -60);

      drawCircle(g, 1, -30 + shift, -64);
      drawCircle(g, 1, -24 + shift, -66);
      drawCircle(g, 1, -18 + shift, -68);

      // left shin
      drawCircle(g, 1, -37 + shift, -84);
      drawCircle(g, 1, -32 + shift, -88);

      drawCircle(g, 1, -42 + shift, -90);
      drawCircle(g, 1, -37 + shift, -94);

      drawCircle(g, 1, -47 + shift, -96);
      drawCircle(g, 1, -42 + shift, -100);

      drawCircle(g, 1, -49 + shift, -104);
    }
  }

  public void drawArmLeg(Graphics g, int shift, int legArmSizeFlag) {
    if (legArmSizeFlag == 0) {

      drawEllipse(g, 20, 7, 8 + shift, -36, 105); // right thigh
      drawEllipse(g, 15, 5, 8 + shift, -65, 70); // right shin
      drawCircle(g, 4, 2 + shift, -80); // right toe

      drawEllipse(g, 20, 7, -15 + shift, -37, 75); // left thigh
      drawEllipse(g, 15, 5, -27 + shift, -64, 50); // left shin
      drawCircle(g, 4, -36 + shift, -77); // left toe

      drawEllipse(g, 13, 5, -56 + shift, 6, 45); // right upper arm
      drawEllipse(g, 10, 4, -73 + shift, -2, 175); // right lower arm
      drawCircle(g, 3, -85 + shift, -2); // right hand

      drawEllipse(g, 13, 5, -63 + shift, 16, 20); // left upper arm
      drawEllipse(g, 10, 4, -80 + shift, 16, 150); // left lower arm
      drawCircle(g, 3, -91 + shift, 21); // left hand

    } else if (legArmSizeFlag == 1) {

      drawEllipse(g, 25, 10, 10 + shift, -42, 105); // right thigh
      drawEllipse(g, 25, 10, -15 + shift, -42, 75); // left thigh
      drawEllipse(g, 20, 8, -35 + shift, -78, 50); // left shin
      drawEllipse(g, 20, 8, 6 + shift, -80, 70); // right shin
      drawCircle(g, 6, -5 + shift, -98); // right toe
      drawCircle(g, 6, -52 + shift, -94); // left toe
      drawEllipse(g, 20, 8, -62 + shift, 0, 45); // right upper arm
      drawEllipse(g, 20, 8, -70 + shift, 16, 20); // left upper arm
      drawEllipse(g, 15, 7, -100 + shift, 18, 150); // left lower arm
      drawEllipse(g, 15, 7, -90 + shift, -10, 175); // right lower arm
      drawCircle(g, 6, -115 + shift, 27); // left hand
      drawCircle(g, 6, -108 + shift, -6); // right hand
    } else {
      drawEllipse(g, 35, 14, 10 + shift, -52, 105); // right thigh
      drawEllipse(g, 24, 8, 11 + shift, -100, 70); // right shin
      drawCircle(g, 6, -3 + shift, -122); // right toe

      drawEllipse(g, 35, 14, -21 + shift, -50, 75); // left thigh
      drawEllipse(g, 24, 8, -40 + shift, -92, 50); // left shin
      drawCircle(g, 6, -59 + shift, -110); // left toe

      drawEllipse(g, 26, 10, -65 + shift, -8, 45); // right upper arm
      drawEllipse(g, 20, 8, -100 + shift, -20, 175); // right lower arm
      drawCircle(g, 6, -122 + shift, -16); // right hand

      drawEllipse(g, 26, 10, -75 + shift, 19, 20); // left upper arm
      drawEllipse(g, 20, 8, -111 + shift, 18, 150); // left lower arm
      drawCircle(g, 6, -128 + shift, 30); // left hand
    }
  }

  public void drawTail(Graphics g, int shift, int tailShapeFlag) {
    if (tailShapeFlag == 1) {
      drawEllipse(g, 10, 6, 28 + shift, 0, 25);
    } else {
      if (tailShapeFlag == 2) {
        drawTriangle(g, 15 + shift, 5, 18 + shift, -5, 45 + shift, 15);
      } else {
        drawCircle(g, 5, 20 + shift, 0);
        drawCircle(g, 3, 20 + shift, 0);
        DDALine(g, 20 + shift, 4, 30 + shift, 25);
        DDALine(g, 22 + shift, 4, 36 + shift, 23);
        DDALine(g, 23 + shift, 2, 40 + shift, 18);
        DDALine(g, 24 + shift, 1, 44 + shift, 13);
        DDALine(g, 24 + shift, 0, 47 + shift, 8);
        DDALine(g, 24 + shift, -1, 49 + shift, -1);
        DDALine(g, 23 + shift, -2, 47 + shift, -8);
        DDALine(g, 22 + shift, -3, 45 + shift, -15);
        DDALine(g, 21 + shift, -4, 41 + shift, -20);
      }
    }
  }

  public void drawShape(Graphics g, int shift, int toothFlag, int earShapeFlag, int earSizeFlag, int bodySpotFlag,
      int legArmSpotFlag, int tailShapeFlag,
      int bodyHairFlag, int legArmSizeFlag) {
    // animal structure w/o variation
    drawEllipse(g, 35, 45, -15 + shift, 19, 30); // body
    drawCircle(g, 17, -50 + shift, 62); // face
    drawCircle(g, 5, -54 + shift, 65); // eyes

    drawBeak(g, shift, toothFlag);
    drawEar(g, shift, earSizeFlag, earShapeFlag);
    drawBody(g, shift, bodySpotFlag, bodyHairFlag);
    drawArmLeg(g, shift, legArmSizeFlag);
    drawSpotLegArm(g, shift, legArmSpotFlag, legArmSizeFlag);
    drawTail(g, shift, tailShapeFlag);
  }
}