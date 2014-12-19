import java.awt.*;

public class Brick 
{
       private final int brickSize = 50;
       private final int brickHeight = 25;
       private int xPos;
       private int yPos;
       private Color brickColor;
       private int lives = 1;
       
       public Brick()
       {
             xPos = 30;
             yPos = 30;
             brickColor= Color.green;
       }
       

       public Brick (int x, int y, Color c)
       {
             xPos = x;
             yPos = y;
             brickColor = c;
             
       }
       
       public int getBrickSize()
       {
             return brickSize;   
       }
       public int getBrickHeight(){
             return brickHeight;
       }
       
       
       public Color getBrickColor()
       {
             return brickColor;  
       }
       
       public void setBrickColor(Color c)
       {
             brickColor=c; 
       }
       
       public int getX()
       {
             return xPos; 
       }
       
       public void setX(int x)
       {
                    xPos=x;
       }

       public int getY()
       {
             return yPos; 
       }
       
       public void setY(int y)
       {
             yPos = y;
       }
       
       public void hit() {
             lives--;
       }
       
       public boolean isAlive() {
             boolean ret = true;
             if (lives == 0) {
                    ret = false;
             }
             return ret;
       }
       
       public void createBrick (Graphics g)
       {
             g.setColor(brickColor);
             g.fillRect (xPos,yPos, brickSize, brickHeight);
             
       }
}

