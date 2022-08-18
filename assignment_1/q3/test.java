import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class test extends Applet implements ActionListener
{
	// scale (distance between two points in coordinate plane
        int scale=20;

	public void init()
	{
	    	setBackground(Color.white);
		Button zoom_in = new Button("Zoom In");
		Button zoom_out = new Button("Zoom Out");
		add(zoom_in);
		add(zoom_out);
		zoom_in.addActionListener(this);
		zoom_out.addActionListener(this);
	}

	public void plotpoint(Graphics g, int x, int y, Color C){
		int plotpoint_x = x-scale/2; // shifting x coordinate to appletcoordinate
		int plotpoint_y = y-scale/2; // shifting y coordinate to appletcoordinate
		g.setColor(C); 
		g.fillOval(plotpoint_x,plotpoint_y,scale,scale);
	}	

	public void paint(Graphics g)
	{  
		// shift the origin and put the coordinates in new variables
                int originX = (getX()+getWidth())/2;
                int originY = (getY()+getHeight())/2;

		// drawing coordinates lines
                g.setColor(Color.red);
                g.drawLine(originX,0,originX,getHeight());
                g.drawLine(0,originY,getWidth(),originY);
                
		// drawing Grid
		// horizontal lines
                g.setColor(Color.black); 
                for(int i=originX+scale; i<getWidth(); i+=scale){
                	g.drawLine(i,0,i,getHeight());
                }
                
                for(int i=scale; i<getWidth(); i+=scale){
                	g.drawLine(originX-i,0,originX-i,getHeight());
                }
                
		// vertical lines
                for(int i=originY+scale; i<getHeight(); i+=scale){
                	g.drawLine(0,i,getWidth(),i);
                }
                
                for(int i=scale; i<getHeight(); i+=scale){
                	g.drawLine(0,originY-i,getWidth(),originY-i);
                }

		// Plot point on origin
		int plotpoint_x_origin = 0;
		int plotpoint_y_origin = 0;
		plotpoint_x_origin = plotpoint_x_origin + originX; // shifting point to cartesian x coordinate
		plotpoint_y_origin = plotpoint_y_origin + originY; // shifting point to cartesian y coordinate
		plotpoint(g,plotpoint_x_origin,plotpoint_y_origin,Color.blue);

		// drawing line using plotpoint
		for(int line_iterator=originX+0; line_iterator<=originX+5*scale; line_iterator+=scale){
			plotpoint(g,line_iterator,originY-3*scale,Color.blue);
		}

		// draw rectangle using plotpoint()
		int rectangle_top_left_x_coordinate = -3; 
		int rectangle_top_left_y_coordinate = -1; 
		int rectangle_top_right_x_coordinate = 3; 
		int rectangle_top_right_y_coordinate = -1; 
		int rectangle_bottom_left_x_coordinate = -3; 
                int rectangle_botton_left_y_coordinate = -4; 
                int rectangle_botton_right_x_coordinate = 3; 
                int rectangle_bottom_right_y_coordinate = -4; 

		// Shifting coordinate to represent in cartesian coordinate of applet		
		rectangle_top_left_x_coordinate = originX + rectangle_top_left_x_coordinate*scale;
		rectangle_top_left_y_coordinate = originY - rectangle_top_left_y_coordinate*scale;
		rectangle_top_right_x_coordinate = originX + rectangle_top_right_x_coordinate*scale;
		rectangle_top_right_y_coordinate = originY - rectangle_top_right_y_coordinate*scale;
		rectangle_bottom_left_x_coordinate = originX + rectangle_bottom_left_x_coordinate*scale;
		rectangle_botton_left_y_coordinate = originY - rectangle_botton_left_y_coordinate*scale;
		rectangle_botton_right_x_coordinate = originX + rectangle_botton_right_x_coordinate*scale;
		rectangle_bottom_right_y_coordinate = originY - rectangle_bottom_right_y_coordinate*scale;	

		for(int width_x_coordinate_iterator = rectangle_top_left_x_coordinate; width_x_coordinate_iterator <= rectangle_top_right_x_coordinate; width_x_coordinate_iterator+=scale){
			plotpoint(g, width_x_coordinate_iterator, rectangle_top_left_y_coordinate, Color.blue);
			plotpoint(g, width_x_coordinate_iterator, rectangle_botton_left_y_coordinate, Color.blue);
		}

		for(int length_y_coordinate_iterator = rectangle_botton_left_y_coordinate; length_y_coordinate_iterator <= rectangle_top_left_y_coordinate; length_y_coordinate_iterator+=scale){
			plotpoint(g, rectangle_bottom_left_x_coordinate, length_y_coordinate_iterator, Color.blue);
			plotpoint(g, rectangle_botton_right_x_coordinate, length_y_coordinate_iterator, Color.blue); 
		}
	}

	public void actionPerformed(ActionEvent e){
		String st = e.getActionCommand();
		if(st.equals("Zoom In"))
			scale += 4;
		else
			scale -= 4;
		repaint();
	}
}
