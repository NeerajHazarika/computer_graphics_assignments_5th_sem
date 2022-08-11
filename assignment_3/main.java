import java.applet.*;
import java.awt.*;

public class test extends Applet
{
	// scale (distance between two points in coordinate plane
        int scale=20;

	public void init()
	{
	    	setBackground(Color.yellow);
 		Button zoom_in = new Button("Zoom In");   
		Button zoom_out = new Button("Zoom Out");
		add(zoom_in);
                add(zoom_out);
                zoom_in.addActionListener(this);
                zoom_out.addActionListener(this);
	}

        public void plotpoint(int x, int y, int r, int gr, int b, Graphics g){
                int newX = (getX()+getWidth())/2;
                int newY = (getY()+getHeight())/2;
                Color myColor = new Color(r,gr,b);
                g.setColor(myColor);
                g.fillOval(x*scale-scale/2+newX,newY-y*scale-scale/2,scale,scale); 
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
                for(int i=x+scale; i<getWidth(); i+=scale){
                	g.drawLine(i,0,i,getHeight());
                }
                
                for(int i=scale; i<getWidth(); i+=scale){
                	g.drawLine(originX-i,0,originX-i,getHeight());
                }
                
		// vertical lines
                for(int i=y+scale; i<getHeight(); i+=scale){
                	g.drawLine(0,i,getWidth(),i);
                }
                
                for(int i=scale; i<getHeight(); i+=scale){
                	g.drawLine(0,originY-i,getWidth(),originY-i);
                }

                plotpoint(10,11,192,192,192,g);
                plotpoint(-2,10,192,192,192,g);
                plotpoint(-1,3,255,192,192,g);

                // Drawing basic shapes using plotpoint
                // 1) Straight Line
                for(int i=-1; i<10; i++){
                        plotpoint(i,1,0,0,0,g);
                }
                // 2) Rectangle
                for(int i=-1; i<10; i++){
                        plotpoint(i,-1,0,0,255,g);
                        plotpoint(i,-5,0,0,255,g);
                }
                for(int i=-1; i>=-5; i--){
                        plotpoint(-1,i,0,0,255,g);
                        plotpoint(9,i,0,0,255,g);
                }
	}

        public void actionPerformed(ActionEvent e) {
                String st = e.getActionCommand();
                if(st.equals("Zoom In"))
                	scale += 4;
                else
                	scale -= 4;
                repaint();
        }
}