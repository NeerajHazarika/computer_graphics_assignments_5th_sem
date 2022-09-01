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

	void midPoint(Graphics g, int originX, int originY, int X1, int Y1, int X2, int Y2) {
		// calculate dx & dy

		int dx = X2 - X1;
		int dy = Y2 - Y1;

		// shifting coordinates to applet coordinate
		X2 = originX + X2 * scale;
		Y2 = originY - Y2 * scale;

		if (dy <= dx) {

			// keeping dy always positive for algorithm to be compatible to work with Y1>Y2
			if (dy < 0)
				dy = -dy;

			// initial value of decision parameter d
			int d = dy - (dx / 2);
			int x = originX + X1 * scale, y = originY - Y1 * scale;

			Y1 = originY - Y1 * scale; // shifting Y1 coordinate to applet coordinate as it is being used in later part
										// of code to compare with Y2 applet coordinate

			// Plot initial given point
			// putpixel(x,y) can be used to print pixel
			// of line in graphics
			plotpoint(g, x, y, Color.red);

			// iterate through value of X
			while (x < X2) {
				x += scale;

				// E or East is chosen
				if (d < 0)
					d = d + dy;

				// NE or North East is chosen
				else {
					d += (dy - dx);

					if (Y1 > Y2) // Y1 & Y2 have already been shifted to applet coordinate, so Y1>Y2 means Y1 is
									// below Y2
						y -= scale; // decrease y with scale make y go down in applet coordinate
					else
						y += scale;
				}

				// Plot intermediate points
				// putpixel(x,y) is used to print pixel
				// of line in graphics
				plotpoint(g, x, y, Color.red);
			}
		}

		else if (dx < dy) {
			// initial value of decision parameter d
			int d = dx - (dy / 2);
			int x = originX + X1 * scale, y = originY - Y1 * scale;

			X1 = originY + X1 * scale; // shifting X1 coordinate to applet coordinate as it is being used in later part
										// of code to compare with X2 applet coordinate

			// Plot initial given point
			// putpixel(x,y) can be used to print pixel
			// of line in graphics
			plotpoint(g, x, y, Color.red);

			// iterate through value of X
			// y > Y2 means y is below Y2
			while (y > Y2) {
				y -= scale;

				// E or East is chosen
				if (d < 0)
					d = d + dx;

				// NE or North East is chosen
				else {
					d += (dx - dy);

					if (X1 < X2)
						x += scale;
					else
						x -= scale;
				}

				// Plot intermediate points
				// putpixel(x,y) is used to print pixel
				// of line in graphics
				plotpoint(g, x, y, Color.red);
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

		// coordinates to plot line with mid point LDA

		// origin
		// int x0 = 0;
		// int y0 = 0;
		// int x1 = 0;
		// int y1 = 0;

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
		int x0 = 3;
		int y0 = 3;
		int x1 = -3;
		int y1 = -1;

		// m>1 && x0<x1 && y0<y1
		// int x0 = -1;
		// int y0 = -1;
		// int x1 = 2;
		// int y1 = 4;

		// m>1 && x0>x1 && y0>y1
		// int x0 = 3;
		// int y0 = 4;
		// int x1 = -1;
		// int y1 = -1;

		/*
		 * 4 cases :
		 * |m|>1 => y+=1, x+=0 or 1/2 (in case of y0>y1 switch coordinates x0,x1 &
		 * y0,y1)
		 * |m|<1 => x+=1, y+=0 or 1/2 (in case of x0>x1 switch coordinates x0,x1 &
		 * y0,y1)
		 * we will only make the algo for |m|<1, it can also work for |m|>1 by just
		 * switch x and y axis
		 */

		// midpoint line drawing algo call
		if (Math.abs(x1 - x0) >= Math.abs(y1 - y0)) {
			if (x0 <= x1)
				midPoint(g, originX, originY, x0, y0, x1, y1);
			else
				midPoint(g, originX, originY, x1, y1, x0, y0); // switching coordinates and
																// switching
																// slope coordinates
		} else {
			// originX and originY variables arent switched because only theoritically we
			// switched coordinates for algorithms to plot points on right position on
			// applet coordinate we need to use the originX for y coordinates and originX
			// for x coordinates
			if (y0 <= y1)
				midPoint(g, originX, originY, x0, y0, x1, y1);
			else
				midPoint(g, originX, originY, x1, y1, x0, y0); // switching coordinates and
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
