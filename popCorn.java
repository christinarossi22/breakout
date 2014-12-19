import java.applet.*;
import java.awt.*;
public class popCorn extends Applet 
{
	private int x,y; //Used for location of ball
	private boolean goingRight, goingDown;
	//create obejct pongBall based off the Ball class
	Ball pongBall= new Ball (15,Color.red);
		
	public void start()
	{
		initialValues();
	}//end of start method
	
	public void paint(Graphics g)
	{
		
			g.setColor (Color.black );
			g.drawRect (0,0,getSize().width-1 , getSize().height -1);
		
			
			g.setColor(pongBall.getBallColor ());
			g.fillOval(x,y,pongBall.getBallSize (),pongBall.getBallSize());
			

			//Used for error control Chapter 13, page 621
			try
			{
				//sleep() method page 720
				//Thread.sleep(speed[level]);
				Thread.sleep(40);
			}
			catch(InterruptedException e)
			{
				//Put error control here
			}
		 
			updateXCoordinate();
			updateYCoordinate();
		 
		
				
	}//end of paint method
	
	
	private void updateXCoordinate()
	{
			if(goingRight)
			{
				x = x + 5;
				if(x+pongBall.getBallSize () > getSize().width)
				{
					x = getSize().width - pongBall.getBallSize ();
					goingRight = false;
				}
			}
			else
			{
				x = x - 10;
				if(x < 0)
				{
					x = 0;
					goingRight = true;
				}
			}
	
		
	}// end of updateXCoordinate method
	private void updateYCoordinate()
	{
			if(goingDown)
			{
				y = y + 5;
				if(y+pongBall.getBallSize () > getSize().height)
				{
					y = getSize().height - pongBall.getBallSize ();
					goingDown = false;
				}
			}
			else
			{
				y = y - 10;
				if(y < 0)
				{
				y = 0;
				goingDown = true;
				}
			}
	
		repaint();
	
	}//end of updateYCoordinate mehtod
	
	
	public void initialValues()
	{
		
		goingRight = true;
		goingDown = true;
		setBackground(Color.white );		
	}
	
	
}// End of popCorn Class