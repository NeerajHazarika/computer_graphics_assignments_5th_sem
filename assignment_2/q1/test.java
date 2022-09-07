import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

import org.omg.CosNaming._BindingIteratorImplBase;

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

	public int round(float coordinate, int axis) {

		if (axis == 0) {
			int prev = (int) (coordinate / scale) * scale;
			int next = prev + scale;

			if (coordinate - prev < next - coordinate)
				return prev;
			else
				return next;
		} else {
			int prev = (int) (coordinate / scale) * scale;
			int next = prev + scale;

			if (coordinate - prev < next - coordinate)
				return prev;
			else
				return next;
		}
	}

	// DDA line drawing algorithm implementation
	/*
	 * Calculate dx and dy, based on who difference is larger, that coordinate will
	 * increment by 1 each time while other will increase by slope
	 */
	public void DDALine(Graphics g, int originX, int originY, int x0, int y0, int x1, int y1) {

		// calculate dx and dy
		int dx = x1 - x0;
		int dy = y1 - y0;

		int step;

		// if dx > dy we will take step as dx
		// else we will take step as dy to draw the complete line
		if (Math.abs(dx) > Math.abs(dy))
			step = Math.abs(dx);
		else
			step = Math.abs(dy);

		// calculate x-increment and y-increment for each step
		float x_incr = (float) dx / step;
		float y_incr = (float) dy / step;

		// take the initial points as x and y
		float x = originX + x0 * scale;
		float y = originY - y0 * scale;

		for (int i = 0; i < step; i++) {
			plotpoint(g, round(x, 0), round(y, 1), Color.red);
			x += x_incr * scale;
			y -= y_incr * scale;
		}
	}

	/*
	 * Debugging
	 * scale = 20, x_incr = 1, y_incr = 3/2 = 1.5
	 * x = 0, y = 0
	 * x = 1*20 = 20, y = 1.5*20 = 30
	 */

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

		// coordinates to plot line with DDA
		// 1 < m < 0 && x0>x1 && y0<y1
		// int x0 = 7;
		// int y0 = 0;
		// int x1 = -2;
		// int y1 = 3;

		// 0 < m < 1 && x0<x1 && y0<y1
		// int x0 = -1;
		// int y0 = -1;
		// int x1 = 4;
		// int y1 = 2;

		// 1 < m && x0<x1 && y0<y1
		// int x0 = -1;
		// int y0 = -1;
		// int x1 = 2;
		// int y1 = 4;

		// m < -1 && x0>x1 && y0>y1
		int x0 = -5;
		int y0 = 3;
		int x1 = 5;
		int y1 = -2;

		// DDA line drawing algo call
		DDALine(g, originX, originY, x0, y0, x1, y1);
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
