//global objects
Ball ball; 
Paddle paddleLeft;
Paddle paddleRight;

int scoreLeft = 0;
int scoreRight = 0;

void setup(){
  size(800, 600);
  ball = new Ball(width / 2, height / 2, 50); 
  ball.speedX = 5; 
  ball.speedY = random(-3, 3);
  paddleLeft = new Paddle(15, height / 2, 30, 200, 4);
  paddleRight = new Paddle(width - 15, height / 2, 30, 200);
}

void draw(){
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
void keyPressed(){
  if(keyCode == UP){
    paddleRight.speedY = -3;
  }
  if(keyCode == DOWN){
    paddleRight.speedY = 3;
  }
}

void keyReleased(){
  if(keyCode == UP){
    paddleRight.speedY = 0;
  }
  if(keyCode == DOWN){
    paddleRight.speedY = 0;
  }
}
