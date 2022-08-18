import java.applet.*;
import java.awt.*;

public class test extends Applet
{
	// scale (distance between two points in coordinate plane
        int scale=20;

	public void init()
	{
	    	setBackground(Color.green);
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
	}
}
