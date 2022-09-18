import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class test extends Applet implements ActionListener {
	// scale (distance between two points in coordinate plane
	int scale = 20;
	int flameState = 0;

	public void init() {
		setBackground(Color.black);
		Button zoom_in = new Button("Zoom In");
		Button zoom_out = new Button("Zoom Out");
		Button light_candle = new Button("Light Candle");
		Button put_out_candle = new Button("Put Out Candle");
		add(zoom_in);
		add(zoom_out);
		add(light_candle);
		add(put_out_candle);
		zoom_in.addActionListener(this);
		zoom_out.addActionListener(this);
		light_candle.addActionListener(this);
		put_out_candle.addActionListener(this);
	}

	public void plotpoint(Graphics g, int x, int y, Color C) {
		int plotpoint_x = x - scale / 2; // shifting x coordinate to appletcoordinate
		int plotpoint_y = y - scale / 2; // shifting y coordinate to appletcoordinate
		g.setColor(C);
		g.fillOval(plotpoint_x, plotpoint_y, scale, scale);
	}

	public void bresenhamlow(Graphics g, int originX, int originY, int dy, int dx, int x1, int y1, int x2, int y2,
			int colorFlag) {

		// dy is always positive to make it compatible with algorithm (pk value)
		if (dy < 0) {
			dy = Math.abs(dy);
		}

		// pk is initial decision making parameter
		int pk = 2 * dy - dx;

		int y_color = y1;

		x1 = originX + x1 * scale;
		y1 = originY - y1 * scale;
		x2 = originX + x2 * scale;
		y2 = originY - y2 * scale;

		int r = 255, gr = 255, b = 255;

		Color c = new Color(r, gr, b);

		plotpoint(g, x1, y1, c);

		for (int i = 0; i < Math.abs(dx); i++) {

			x1 += scale;

			if (pk < 0) {
				c = new Color(r, gr, b);
				plotpoint(g, x1, y1, c);
				pk = pk + 2 * dy;
			} else {
				if (y1 < y2) {
					y1 += scale;
				} else {
					y1 -= scale;
				}

				// assuming y1<y2 always for fire plot
				y_color += 1;

				if (colorFlag == 1) {
					if (y_color <= 8) {
						r = 255;
						gr = 255;
						b = 255 - (y_color * 31);
					} else if (y_color > 8 && y_color <= 16) {
						r = 255;
						gr = 255 - ((y_color - 8) * 31);
						b = 0;
					} else {
						r = 255;
						gr = 0;
						b = 0;
					}
				}

				c = new Color(r, gr, b);

				plotpoint(g, x1, y1, c);
				pk = pk + 2 * dy - 2 * dx;
			}
		}
	}

	public void bresenhamhigh(Graphics g, int originX, int originY, int dy, int dx, int x1, int y1, int x2, int y2,
			int colorFlag) {

		// dy is always positive to make it compatible with algorithm (pk value)
		if (dx < 0) {
			dx = Math.abs(dx);
		}

		// pk is initial decision making parameter
		int pk = 2 * dx - dy;

		int y_color = y1;

		x1 = originX + x1 * scale;
		y1 = originY - y1 * scale;
		x2 = originX + x2 * scale;
		y2 = originY - y2 * scale;

		int r = 255, gr = 255, b = 255;

		Color c = new Color(r, gr, b);

		plotpoint(g, x1, y1, c);

		for (int i = 0; i < Math.abs(dy); i++) {

			y1 -= scale; // subtracting scale to increase plotpoint along applet coordinate

			// assuming y1<y2 always for fire
			y_color += 1;

			if (colorFlag == 1) {
				if (y_color <= 8) {
					r = 255;
					gr = 255;
					b = 255 - (y_color * 31);
				} else if (y_color > 8 && y_color <= 16) {
					r = 255;
					gr = 255 - ((y_color - 8) * 31);
					b = 0;
				} else {
					r = 255;
					gr = 0;
					b = 0;
				}
			}

			if (pk < 0) {
				c = new Color(r, gr, b);
				plotpoint(g, x1, y1, c);
				pk = pk + 2 * dx;
			} else {
				if (x1 < x2) {
					x1 += scale;
				} else {
					x1 -= scale;
				}

				c = new Color(r, gr, b);

				plotpoint(g, x1, y1, c);
				pk = pk + 2 * dx - 2 * dy;
			}
		}
	}

	public void plotLine(Graphics g, int originX, int originY, int x0, int y0, int x1, int y1, int colorFlag) {

		// bresenham line drawing algo call
		if (Math.abs(x1 - x0) >= Math.abs(y1 - y0)) {
			if (x0 <= x1)
				bresenhamlow(g, originX, originY, y1 - y0, x1 - x0, x0, y0, x1, y1, colorFlag);
			else
				bresenhamlow(g, originX, originY, y0 - y1, x0 - x1, x1, y1, x0, y0, colorFlag); // switching coordinates
																								// and
			// switching
			// slope coordinates
		} else {
			// originX and originY variables arent switched because only theoritically we
			// switched coordinates for algorithms to plot points on right position on
			// applet coordinate we need to use the originX for y coordinates and originX
			// for x coordinates
			if (y0 <= y1)
				bresenhamhigh(g, originX, originY, y1 - y0, x1 - x0, x0, y0, x1, y1, colorFlag);
			else
				bresenhamhigh(g, originX, originY, y0 - y1, x0 - x1, x1, y1, x0, y0, colorFlag); // switching
																									// coordinates and
			// switching
			// slope coordinates
		}
	}

	public void plotRect(Graphics g, int originX, int originY, int rtlxc, int rtlyc, int rtrxc, int rtryc, int rblxc,
			int rblyc, int rbrxc, int rbryc) {

		plotLine(g, originX, originY, rtlxc, rtlyc, rtrxc, rtryc, 0);
		plotLine(g, originX, originY, rtrxc, rtryc, rbrxc, rbryc, 0);
		plotLine(g, originX, originY, rbrxc, rbryc, rblxc, rblyc, 0);
		plotLine(g, originX, originY, rblxc, rblyc, rtlxc, rtlyc, 0);
	}

	/*
	 * Fire algorithm
	 * - plot points in 100 directions, -50 to 0, 0 to 50.
	 * - based on the value of x, we choose y for plotting the line.
	 * - Points close to the source are different in color than points far away.
	 * - color is changed intenally in plotline while plotting the points
	 */

	public void fire(Graphics g, int originX, int originY) {

		for (int i = -25; i <= 25; i++) {
			int y = 50;
			if (Math.abs(y) >= Math.abs(i)) {
				y = y - Math.abs(i) + (int) (Math.random() * 10);
				plotLine(g, originX, originY, 0, 0, i, y, 1);
			}
		}

	}

	public void plotcandle(Graphics g, int originX, int originY) {
		plotRect(g, originX, originY, -5, 0, 5, 0, -5, -40, 5, -40);
	}

	public void infiniteLoop() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {

		}
		repaint();
	}

	public void paint(Graphics g) {
		// shift the origin and put the coordinates in new variables
		int originX = (getX() + getWidth()) / 2;
		int originY = (getY() + getHeight()) / 2;

		// drawing coordinates lines
		g.setColor(Color.red);
		g.drawLine(originX, 0, originX, getHeight());
		g.drawLine(0, originY, getWidth(), originY);

		// drawing Grid
		// vertical lines
		g.setColor(Color.black);
		// right half vertical lines
		for (int i = originX + scale; i < getWidth(); i += scale) {
			g.drawLine(i, 0, i, getHeight());
		}
		// left half vertical lines
		for (int i = scale; originX - i >= 0; i += scale) {
			g.drawLine(originX - i, 0, originX - i, getHeight());
		}

		// horizontal lines
		// right half horizontal lines
		for (int i = originY + scale; i < getHeight(); i += scale) {
			g.drawLine(0, i, getWidth(), i);
		}
		// left half horizontal lines
		for (int i = scale; originY - i >= 0; i += scale) {
			g.drawLine(0, originY - i, getWidth(), originY - i);
		}

		// plot candle
		// plotcandle(g, originX, originY);

		// plot fire lines
		if (flameState == 1) {
			fire(g, originX, originY);
			// infiniteLoop();
		}
	}

	public void light_candle() {
		flameState = 1;
		repaint(); // calls paint() function
	}

	public void put_out_candle() {
		flameState = 0;
		repaint(); // calls paint() function
	}

	public void actionPerformed(ActionEvent e) {
		String st = e.getActionCommand();
		if (st.equals("Zoom In")) {
			scale += 4;
			repaint();
		} else if (st.equals("Zoom Out")) {
			scale -= 4;
			repaint();
		} else if (st.equals("Light Candle")) {
			light_candle();
		} else {
			put_out_candle();
		}
	}
}
