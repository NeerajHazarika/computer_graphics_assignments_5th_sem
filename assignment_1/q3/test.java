import java.applet.*;
import java.awt.*;
import java.awt.event.*;

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

		// Plot point on origin
		int plotpoint_x_origin = 0;
		int plotpoint_y_origin = 0;
		plotpoint_x_origin = plotpoint_x_origin + originX; // shifting point to cartesian x coordinate
		plotpoint_y_origin = plotpoint_y_origin + originY; // shifting point to cartesian y coordinate
		plotpoint(g, plotpoint_x_origin, plotpoint_y_origin, Color.blue);

		// drawing line using plotpoint
		for (int line_iterator = originX + 0; line_iterator <= originX + 5 * scale; line_iterator += scale) {
			plotpoint(g, line_iterator, originY - 3 * scale, Color.blue);
		}

		// draw rectangle using plotpoint()
		int rtlxc = -3; // rtlxc = rectangle_top_left_x_coordinate
		int rtlyc = -1; // rtlyc = rectangle_top_left_y_coordinate
		int rtrxc = 3; // rtrxc = rectangle_top_right_x_coordinate
		int rtryc = -1; // rtryc = rectangle_top_right_y_coordinate
		int rblxc = -3; // rblxc = rectangle_bottom_left_x_coordinate
		int rblyc = -4; // rblyc = rectangle_botton_left_y_coordinate
		int rbrxc = 3; // rbrxc = rectangle_botton_right_x_coordinate
		int rbryc = -4; // rbryc = rectangle_bottom_right_y_coordinate

		// Shifting coordinate to represent in cartesian coordinate of applet
		rtlxc = originX + rtlxc * scale;
		rtlyc = originY - rtlyc * scale;
		rtrxc = originX + rtrxc * scale;
		rtryc = originY - rtryc * scale;
		rblxc = originX + rblxc * scale;
		rblyc = originY - rblyc * scale;
		rbrxc = originX + rbrxc * scale;
		rbryc = originY - rbryc * scale;

		// plotting top and bottom breadth of rectangle by iterating over the width
		for (int breadth_x_coordinate_iterator = rtlxc; breadth_x_coordinate_iterator <= rtrxc; breadth_x_coordinate_iterator += scale) {
			plotpoint(g, breadth_x_coordinate_iterator, rtlyc, Color.blue);
			plotpoint(g, breadth_x_coordinate_iterator, rblyc, Color.blue);
		}

		// plotting left and right length of rectangle by iterating over the length
		for (int length_y_coordinate_iterator = rtlyc; length_y_coordinate_iterator <= rblyc; length_y_coordinate_iterator += scale) {
			plotpoint(g, rtlxc, length_y_coordinate_iterator, Color.blue);
			plotpoint(g, rtrxc, length_y_coordinate_iterator, Color.blue);
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
