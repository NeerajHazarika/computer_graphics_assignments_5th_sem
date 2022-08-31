import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class test extends Applet implements ActionListener {
	// scale (distance between two points in coordinate plane
	int scale = 20;

	public void init() {
		setBackground(Color.white);
		Button zoom_in = new Button("Zoom In");
		Button zoom_out = new Button("Zoom Out");
		add(zoom_in);
		add(zoom_out);
		zoom_in.addActionListener(this);
		zoom_out.addActionListener(this);
	}

	public void plotpoint(Graphics g, int x, int y, Color C) {
		int plotpoint_x = x - scale / 2; // shifting x coordinate to appletcoordinate
		int plotpoint_y = y - scale / 2; // shifting y coordinate to appletcoordinate
		g.setColor(C);
		g.fillOval(plotpoint_x, plotpoint_y, scale, scale);
	}

	// Brehensam line drawing algorithm implementation
	/*
	 * - This algorithm is an extension to mid point algorithm for using integer
	 * arithmetic only
	 * - acc. to it, D (Difference b/w f(x0,y0+1/2) and f(x0,y0)) = dy - 1/2 dx =
	 * 2dy - dx (since only sign matters for considering case)
	 * - if D is +ve => choose next point as (x0+1,y0+1) => change in D will be => D
	 * + 2dy - 2dx
	 * - otherwise => choose next point as (x0+1,y0) => change in D will be => D +
	 * 2dy
	 * - for it to work with negative slop we just need to switch the x0, y0 with
	 * x1,y1
	 * - for it to work with slope > 1 we need to switch x and y coordinates
	 */
	public void bresenhamlow(Graphics g, int originX, int originY, int dy, int dx, int x1, int y1, int x2, int y2) {

		// dy is always positive to make it compatible with algorithm (pk value)
		if (dy < 0) {
			dy = Math.abs(dy);
		}

		// pk is initial decision making parameter
		int pk = 2 * dy - dx;

		x1 = originX + x1 * scale;
		y1 = originY - y1 * scale;
		x2 = originX + x2 * scale;
		y2 = originY - y2 * scale;

		plotpoint(g, x1, y1, Color.red);

		for (int i = 0; i < Math.abs(dx); i++) {

			x1 += scale;

			if (pk < 0) {
				plotpoint(g, x1, y1, Color.red);
				pk = pk + 2 * dy;
			} else {
				if (y1 < y2) {
					y1 += scale;
				} else {
					y1 -= scale;
				}

				plotpoint(g, x1, y1, Color.red);
				pk = pk + 2 * dy - 2 * dx;
			}
		}
	}

	public void bresenhamhigh(Graphics g, int originX, int originY, int dy, int dx, int x1, int y1, int x2, int y2) {

		// dy is always positive to make it compatible with algorithm (pk value)
		if (dx < 0) {
			dx = Math.abs(dx);
		}

		// pk is initial decision making parameter
		int pk = 2 * dx - dy;

		x1 = originX + x1 * scale;
		y1 = originY - y1 * scale;
		x2 = originX + x2 * scale;
		y2 = originY - y2 * scale;

		plotpoint(g, x1, y1, Color.red);

		for (int i = 0; i < Math.abs(dy); i++) {

			y1 -= scale; // subtracting scale to increase plotpoint along applet coordinate

			if (pk < 0) {
				plotpoint(g, x1, y1, Color.red);
				pk = pk + 2 * dx;
			} else {
				if (x1 < x2) {
					x1 += scale;
				} else {
					x1 -= scale;
				}

				plotpoint(g, x1, y1, Color.red);
				pk = pk + 2 * dx - 2 * dy;
			}
		}
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

		// coordinates to plot line with Bresenham LDA

		// m=1 && x0<x1 && y0<y1
		// int x0 = 1;
		// int y0 = 1;
		// int x1 = 3;
		// int y1 = 3;

		// m=1 && x0>x1 && y0>y1
		// int x0 = 3;
		// int y0 = 3;
		// int x1 = 1;
		// int y1 = 1;

		// m<1 && x0<x1 && y0>y1
		// int x0 = -3;
		// int y0 = 3;
		// int x1 = 3;
		// int y1 = -1;

		// m<1 && x0>x1 && y0>y1
		// int x0 = 3;
		// int y0 = 3;
		// int x1 = -3;
		// int y1 = -1;

		// m>1 && x0<x1 && y0>y1
		// int x0 = -1;
		// int y0 = -1;
		// int x1 = 2;
		// int y1 = 4;

		// m>1 && x0>x1 && y0>y1
		int x0 = 3;
		int y0 = 4;
		int x1 = -1;
		int y1 = -1;

		/*
		 * 4 cases :
		 * |m|>1 => y+=1, x+=0 or 1/2 (in case of y0>y1 switch coordinates x0,x1 &
		 * y0,y1)
		 * |m|<1 => x+=1, y+=0 or 1/2 (in case of x0>x1 switch coordinates x0,x1 &
		 * y0,y1)
		 * we will only make the algo for |m|<1, it can also work for |m|>1 by just
		 * switch x and y axis
		 */

		// DDA line drawing algo call
		if (Math.abs(x1 - x0) >= Math.abs(y1 - y0)) {
			if (x0 <= x1)
				bresenhamlow(g, originX, originY, y1 - y0, x1 - x0, x0, y0, x1, y1);
			else
				bresenhamlow(g, originX, originY, y0 - y1, x0 - x1, x1, y1, x0, y0); // switching coordinates and
																						// switching
																						// slope coordinates
		} else {
			// originX and originY variables arent switched because only theoritically we
			// switched coordinates for algorithms to plot points on right position on
			// applet coordinate we need to use the originX for y coordinates and originX
			// for x coordinates
			if (y0 <= y1)
				bresenhamhigh(g, originX, originY, y1 - y0, x1 - x0, x0, y0, x1, y1);
			else
				bresenhamhigh(g, originX, originY, y0 - y1, x0 - x1, x1, y1, x0, y0); // switching coordinates and
																						// switching
																						// slope coordinates
		}
	}

	public void actionPerformed(ActionEvent e) {
		String st = e.getActionCommand();
		if (st.equals("Zoom In"))
			scale += 4;
		else
			scale -= 4;
		repaint();
	}
}
