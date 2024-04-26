/* autogenerated by Processing revision 1282 on 2022-11-06 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class pong extends PApplet {


//global objects
Ball ball; 
Paddle paddleLeft;
Paddle paddleRight;

int scoreLeft = 0;
int scoreRight = 0;

 public void setup(){
  /* size commented out by preprocessor */;
  ball = new Ball(width / 2, height / 2, 50); 
  ball.speedX = 5; 
  ball.speedY = random(-3, 3);
  paddleLeft = new Paddle(15, height / 2, 30, 200, 4);
  paddleRight = new Paddle(width - 15, height / 2, 30, 200);
}

 public void draw(){
  background(16, 97, 36);
  ball.move();
  ball.display();
  
  textSize(40);
  textAlign(CENTER);
  text(scoreRight, width/2 + 30, 30);
  text(scoreLeft, width/2 - 30, 30);
  
  //border detection
  if (ball.right() > width) {
    scoreLeft = scoreLeft + 1;
    ball.x = width/2;
    ball.y = height/2;
  }
  if (ball.left() < 0) {
    scoreRight = scoreRight + 1;
    ball.x = width/2;
    ball.y = height/2;
  }
  if (ball.bottom() > height) {
    ball.speedY = -ball.speedY;
  }
  if (ball.top() < 0) {
    ball.speedY = -ball.speedY;
  }
  paddleRight.move();
  paddleRight.display();
  
  paddleLeft.display();
  
  while(ball.bottom() < paddleLeft.middle()){
    paddleLeft.moveUp();
  }
  while(ball.top() > paddleLeft.middle()){
    paddleLeft.moveDown();
  }
  
  paddleRight.move();
  paddleRight.display();
  
  if (paddleLeft.bottom() > height) {
    paddleLeft.y = height - paddleLeft.h / 2;
  }
  if (paddleLeft.top() < 0) {
    paddleLeft.y = paddleLeft.h/2;
  }
  if (paddleRight.bottom() > height) {
    paddleRight.y = height - paddleRight.h / 2;
  }
  if (paddleRight.top() < 0) {
    paddleRight.y = paddleRight.h / 2;
  }

  if (ball.left() < paddleLeft.right() && ball.y > paddleLeft.top() && ball.y < paddleLeft.bottom()){
    ball.speedX = -ball.speedX;
  }
  
  if (ball.right() > paddleRight.left() && ball.y > paddleRight.top() && ball.y < paddleRight.bottom()) {
    ball.speedX = -ball.speedX;
    ball.speedY = map(ball.y - paddleRight.y, -paddleRight.h/2, paddleRight.h/2, -10, 10);
  }

}

//key detection
 public void keyPressed(){
  if(keyCode == UP){
    paddleRight.speedY = -3;
  }
  if(keyCode == DOWN){
    paddleRight.speedY = 3;
  }
}

 public void keyReleased(){
  if(keyCode == UP){
    paddleRight.speedY = 0;
  }
  if(keyCode == DOWN){
    paddleRight.speedY = 0;
  }
}
class Ball {
  float x;
  float y;
  float speedX;
  float speedY;
  float diameter;
  int c;
  
  // constructor method
  Ball(float tempX, float tempY, float tempDiameter) {
    x = tempX;
    y = tempY;
    diameter = tempDiameter;
    speedX = 0;
    speedY = 0;
    c = (225); 
  }
  
    public void move() {
    y = y + speedY;
    x = x + speedX;
  }
  
   public void display() {
    fill(c);
    ellipse(x, y, diameter, diameter);
  }
  
  
   public float left(){
    return x - diameter/2;
  }
  
   public float right(){
    return x + diameter/2;
  }
  
   public float top(){
    return y - diameter/2;
  }
  
   public float bottom(){
    return y + diameter/2;
  }
  
}
class Paddle{
  
  float x;
  float y;
  float w;
  float h;
  float speedY;
  float speedX;
  int c;
  
  //paddle object constructor
  Paddle(float tempX, float tempY, float tempW, float tempH, float speedY){
    x = tempX;
    y = tempY;
    w = tempW;
    h = tempH;
    speedX = 0;
    this.speedY = speedY;
    c = (255);
  }  
  Paddle(float tempX, float tempY, float tempW, float tempH){
    x = tempX;
    y = tempY;
    w = tempW;
    h = tempH;
    speedY = 0;
    speedX = 0;
    c = (255);
  }    
   public void move(){
    y += speedY;
    x += speedX;
  }
  
  //movement for opponent's paddle only
   public void moveUp(){
    y -= speedY;
  }
  
   public void moveDown(){
    y += speedY;
  }

   public void display(){
    fill(c);
    rect(x - w/2, y - h/2, w, h);
  } 
  
   public float left(){
    return x - w/2;
  }
  
   public float right(){
    return x + w/2;
  }
  
   public float top(){
    return y - h/2;
  }
  
   public float bottom(){
    return y + h/2;
  }
  
   public float middle(){
    return y;
  }
}


  public void settings() { size(800, 600); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "pong" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
