
import java.applet.*;
import java.awt.*;
import java.awt.event .*;
public class Paddle 
{
       private int x, y;// Used for location of paddle
       private int paddleSize;
       private Color color;
       private int paddleWidth;
       private boolean left, right;
       private int paddleHeight;
       
       public Paddle() {
             paddleWidth = 60;
             paddleHeight = 25;
             color = Color.GREEN;
             x = 120;
             y = 420;
       }
       
       public Paddle(int xPos, int yPos, int w, int h, Color c) {
             paddleWidth = w;
             paddleHeight = h;
             color = c;
             x = xPos;
             y = yPos;
       }
       
       public int getxPos() {
             return x;
       }
       
       public int getyPos() {
             return y;
       }
       
       public int getPaddleSize() {
             return paddleWidth;
       }
       
       //probably not necessary, but...
       public int getHeight() {
             return paddleHeight;
       }
       
       public void setX(int xPos) {
             x = xPos;
       }
       public void setY(int yPos) {
             y = yPos;
       }
       
       public boolean getLeft(){
             return left;
       }
       
       public boolean getRight(){
             return right;
       }
       
       public void setLeft(boolean isLeft){
             left = isLeft;
       }
       public void setRight(boolean isRight){
             right = isRight;
       }
       
       public void toggleLeft(boolean isLeft){
             left = !left;
       }
       public void toggleRight(boolean isRight){
             right = !right;
       }
       public void setColor(Color co){
             color = co;
       }
       
       //for powerups
       
       public void setWidth(int w) {
             paddleWidth = w;
       }
       
       public void drawPaddle(Graphics g) {
             g.setColor(color);
             g.fillRect(x, y, paddleWidth, paddleHeight);
       }
       
}