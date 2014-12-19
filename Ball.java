import java.awt.*;

public class Ball 
{
	private int ballSize;
	private int xPos, yPos;
	private boolean down, right;
	private Color ballColor;
	
	public Ball()
	{
		ballSize = 15;
		ballColor= Color.red ;
	}
	
	public  Ball (int n)
	{
		ballSize = n;	
	}
	public Ball (int n, Color c)
	{
		ballSize = n;
		ballColor = c;
		
	}
	
	public int getBallSize()
	{
		return ballSize;	
	}
	
	public void setBallSize(int n)
	{
		ballSize = n;	
	}
	
	public Color getBallColor()
	{
		return ballColor;	
	}
	
	public void setBallColor(Color c)
	{
		ballColor=c;	
	}
	
	public int getxPos()
	{
		return xPos;	
	}
	
	public void setxPos(int x)
	{
			xPos=x;
	}
	
	public int getyPos()
	{
		return yPos;	
	}
	
	public void setyPos(int y)
	{
		yPos=y;
	}
	public void createBall (int x, int y, Graphics g)
	{
		g.setColor(ballColor);
		g.fillOval (x,y,ballSize, ballSize);
		
	}
}
