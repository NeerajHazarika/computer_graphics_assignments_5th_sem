package assignment_1;

import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class test extends Applet {

	// scale (distance between two points in coordinate plane
	int scale = 10;

	public void init() {
		this.setSize(new Dimension(800, 800));
		Button zoom_in = new Button("zoom_in");
		Button zoom_out = new Button("zoom_out");
		add(zoom_in);
		add(zoom_out);
		zoom_in.addActionListener(this);
		zoom_out.addActionListener(this);
	}

	public void paint(Graphics g) {

		// shift the origin and put the coordinates in new variables
		int originX = (getX() + getWidth()) / 2;
		int originY = (getY() + getHeight()) / 2;

		// Variables to change size of rectangle
		int width = 2;
		int height = 3;

		// drawing coordinates lines
		g.setColor(Color.green);
		g.drawLine(0, originY, getWidth(), originY); // X axis
		g.drawLine(originX, 0, originX, getHeight()); // Y axis

		// drawing Grid
		// horizontal lines
		g.setColor(Color.black);
		for (int y = originY - scale; y >= 0; y -= scale) {
			g.drawLine(0, y, getWidth(), y);
		}

		for (int y = originY + scale; y <= getHeight(); y += scale) {
			g.drawLine(0, y, getWidth(), y);
		}

		// vertical lines
		for (int x = originX - scale; x >= 0; x -= scale) {
			g.drawLine(x, 0, x, getHeight());
		}

		for (int x = originX + scale; x <= getWidth(); x += scale) {
			g.drawLine(x, 0, x, getHeight());
		}

		// draw rectangle at (2,1) with variable size to find optimum size
		g.drawRect(originX + 2 * scale, originY - 1 * scale, width, height);
	}

	public void actionPerformed(ActionEvent e) {
		// If zoom in is pressed, increase scale of grid else decrease scale of grid
		if (e.getSource() == "zoom_in") {
			scale += 4;
		} else {
			scale -= 4;
		}
		repaint();
	}
}
