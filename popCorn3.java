import java.applet.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class popCorn3 extends Applet implements KeyListener
{
	private int x,y; //Used for location of ball
	private boolean goingRight, goingDown;
	//create object pongBall based off the Ball class
	Ball pongBall= new Ball (15,Color.red);
	private Paddle myPaddle = new Paddle();
	private String msg = "";
	private String lose = "You Lose";
	private String win = "You Win";
	private String points = "You Points ";
	private Brick myBrick = new Brick(30, 30, Color.blue);
	private Brick[] myBricks = new Brick[30];
	//private Color brickColor;
	private int lives = 5;
	private int score = 0;
	
	
	public void start()
	{
		initialValues();
		addKeyListener(this);
	}//end of start method
	
	public void paint(Graphics g)
	{
		
		if(lives == 0)
		{
			g.drawString(lose, 100, 100);
		}
		else if(score == 30)
		{
			g.drawString(win, 100, 100);
			g.drawString(points + score, 200, 200);
		}
		if(lives != 0 && score != 30)
		{
			g.setColor (Color.black);
			g.drawRect (0,0,getSize().width-1 , getSize().height -1);
		
			//Draw the ball
			
			g.setColor(pongBall.getBallColor ());
			g.fillOval(x,y,pongBall.getBallSize (),pongBall.getBallSize());
			
			
			//Draw the paddle
		    //myPaddle.setxPos(10);
	        myPaddle.drawPaddle(g);
			
			g.setColor(Color.black);
			//g.drawString(msg, 55, 55);
			
			//for each brick in the array
			//if brick is alive, draw brick
			
			for (int i = 0; i < 30; i++)
			{
				myBrick = myBricks[i];
				if (myBrick.isAlive() ) {
				myBrick.createBrick(g);
				}
			}

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
		 
			updatePaddle();
			updateXCoordinate();
			updateYCoordinate();
			checkPaddleCollision();
			//for each brick in the array
			//if brick is alive, check collision
			for (int i = 0; i < 30; i++)
			{
				myBrick = myBricks[i];
				if (myBrick.isAlive() ) {
					checkBrickCollision(myBrick);
				}
			}
			
			int scoreCount = 0;
			for (int i = 0; i < 30; i++)
			{
				myBrick = myBricks[i];
				if (!myBrick.isAlive() ) {
					scoreCount++;
					score = scoreCount;
				}
			}
		}
			
	
			//repaint();
		
				
	}//end of paint method
	
	private void checkBrickCollision(Brick aBrick){
        //top collision            
        
        //rs of ball to the right of ls of paddle   
		if(lives != 0 && score != 30)
		{
        if(
        ((x + (pongBall.getBallSize()    * .75)                                                            //right side of ball (rather, at least 25% of the right side ball)
                         >=                      // is to the RIGHT OF
                      aBrick.getX()
                      )                                                           //left side of the 
        &&           
        //ls of ball to the left of rs of brick
        (x + (pongBall.getBallSize() * .25)
                      <=
                      aBrick.getX() + aBrick.getBrickSize()
                      )
        &&
        //top of the ball is above the top of brick    
        y <= aBrick.getY()
        )
        &&
        (y+pongBall.getBallSize()) //bottom of the ball
        >=                                             // is at or below
        aBrick.getY()
        
                      )
        {      
               //bounce
               goingDown = !goingDown;
               //'hit()' the brick
               aBrick.hit();
            
        }
        else if(
        ((x + (pongBall.getBallSize()    * .75)                                                            //right side of ball (rather, at least 25% of the right side ball)
                         >=                      // is to the RIGHT OF
                      aBrick.getX()
                      )                                                           //left side of the 
        &&           
        //ls of ball to the left of rs of brick
        (x + (pongBall.getBallSize() * .25)
                      <=
                      aBrick.getX() + aBrick.getBrickSize()
                      )
        &&
        //top of the ball is above the bottom of brick 
        y <= aBrick.getY() + aBrick.getBrickHeight()
        )
        &&
        (y+pongBall.getBallSize()) //bottom of the ball
        >=                                             // is at or below
        aBrick.getY() + aBrick.getBrickHeight()              
                      ) {
               
               //hit the bottom
               goingDown = !goingDown;
               //'hit()' the brick
               aBrick.hit();
           
        }
        else if (
        (x + pongBall.getBallSize()                                                            //right side of ball (rather, at least 25% of the right side ball)
           >=                      // is to the RIGHT OF
        aBrick.getX()
        )                                                           //left side of the 
        &&           
        //ls of ball to the left of rs of brick
        (x + (pongBall.getBallSize())
        <=
        aBrick.getX()
        )
        &&
        //top of the ball is above the bottom of brick 
        (y + (pongBall.getBallSize() * .25) 
        <= 
        aBrick.getY() + aBrick.getBrickHeight()
        )
        &&
        (y+ (pongBall.getBallSize()*.75)) //bottom of the ball
        >=                                             // is at or below
        aBrick.getY()
        
                      ){
               //hit the left
               goingRight = !goingRight;
               //'hit()' the brick
               aBrick.hit();
              
        }
        else if (
               (x + pongBall.getBallSize()                                                            //right side of ball (rather, at least 25% of the right side ball)
                  >=                      // is to the RIGHT OF
               aBrick.getX() + aBrick.getBrickSize()
               )                                //right side of the 
               &&           
               //ls of ball to the left of rs of brick
               (x + (pongBall.getBallSize())
               <=
               aBrick.getX() + aBrick.getBrickSize()
               )
               &&
               //top of the ball is above the bottom of brick 
               (y + (pongBall.getBallSize() * .25) 
               <= 
               aBrick.getY() + aBrick.getBrickHeight()
               )
               &&
               (y+ (pongBall.getBallSize()*.75)) //bottom of the ball
               >=                                             // is at or below
               aBrick.getY()
               
                                                ){
               //hit the right
               goingRight = !goingRight;
               //'hit()' the brick
               aBrick.hit();
               
        }
		}
  }

	private void checkPaddleCollision() {
		//when does the ball hit the paddle?
		if(lives != 0 && score != 30)
		{
		//if the bottom of the ball is beyond the top of the paddle
		if ( 	y+pongBall.getBallSize()	//bottom of the ball
				>=							// is at or below
				myPaddle.getyPos()			// top of the paddle
					) {
			
			//if we've gone past the top of the paddle
			
			//either we BOUNCE
			if (	(
					x + (pongBall.getBallSize()	* .75) 
										//right side of ball (rather, at least 25% of the right side ball)
					   >=				// is to the RIGHT OF
					myPaddle.getxPos()	//left side of the paddle
					)
					
					&&
					
					(
					x + (pongBall.getBallSize() * .25)
										//left side of ball (rather, at least 25% of the left side)
						<=				//is to the LEFT OF
					myPaddle.getxPos() + myPaddle.getPaddleSize()	
										//right side of paddle
					)
					
				)
			{
				goingDown = false;
				y = myPaddle.getyPos() - pongBall.getBallSize(); //y + getBallsize() = myPaddle.getyPos
			}
			//or we DIE
			else {
				y = 400;
				x = 100;
				goingDown = false;
				goingRight = true;
				lives --;
			}
			
		}
		}
		// AND at least a quarter of the ball is over the paddle itself.
	}
	
	private void updatePaddle() {
		if(lives != 0 && score != 30)
		{
		if (myPaddle.getLeft()) {
			int tmpX = myPaddle.getxPos();
			tmpX -= 5;
			if (tmpX < 0) {
				tmpX = 0;
			}
			myPaddle.setX(tmpX);
		}
		if (myPaddle.getRight()) {
			int tmpX = myPaddle.getxPos();
			tmpX += 5;
			if (tmpX+myPaddle.getPaddleSize () > getSize().width) {
				tmpX = getSize().width - myPaddle.getPaddleSize();
			}
			myPaddle.setX(tmpX);
		}
		}
	}
	
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
		goingDown = false;
		x = 100;
		y = 400;
		setBackground(Color.white );
		myPaddle.setX(10);
		
		int y = 30;
		 Color[] colors =  new Color[5];
		 colors[0] = Color.blue;
		 colors[1] = Color.red;
		 colors[2] = Color.green;
		 colors[3] = Color.yellow;
		 colors[4] = Color.orange;
		 
		 for (int i = 0; i < 30; ++i) {
			 myBricks[i] = new Brick(((i%6) * 60), y, colors[i%5]);
			 if (i % 6 == 5) {
				 y += 50;
			 }
		 }
	}
	
	/** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
		
    }

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
		//msg = c + " was pressed";
		if (c == 'a') {
			myPaddle.setLeft(true);
		}
		else if (c == 'd') {
			myPaddle.setRight(true);
		}
    }

    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
	//	msg = c + " was released";
		if (c == 'a') {
			myPaddle.setLeft(false);
		}
		else if (c == 'd') {
			myPaddle.setRight(false);
		}
    }
	
	
}// End of popCorn Class