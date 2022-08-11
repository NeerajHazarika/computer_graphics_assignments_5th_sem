package assignment_1;

import java.applet.*;
import java.awt.*;

public class test extends Applet {
	public void init() {
		this.setSize(new Dimension(800, 800));

	}

	public void paint(Graphics g) {
		// scale (distance between two points in coordinate plane
		int scale = 10;

		// shift the origin and put the coordinates in new variables
		int originX = (getX() + getWidth()) / 2;
		int originY = (getY() + getHeight()) / 2;

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
	}
}
