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

		// draw Rectangle
		int rectangle_x = 2; // x coordinate of rectangle
		int rectangle_y = 1; // y coordinate of rectangle
		int rectangle_size_multiplier = 5; // change size of rectangle by changing this multiplier variable
		rectangle_x = rectangle_x * scale - (rectangle_size_multiplier * scale) / 2; // shifting x coordinate to draw
																						// shapes on applet coordinate
		rectangle_y = rectangle_y * scale + (rectangle_size_multiplier * scale) / 2; // shifting y coordinate to draw
																						// shapes on applet coordinate
		g.setColor(Color.red);
		g.drawRect(originX + rectangle_x, originY - rectangle_y, scale * rectangle_size_multiplier,
				scale * rectangle_size_multiplier);
		g.fillRect(originX + rectangle_x, originY - rectangle_y, scale * rectangle_size_multiplier,
				scale * rectangle_size_multiplier);
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
