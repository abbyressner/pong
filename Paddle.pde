class Paddle{
  
  float x;
  float y;
  float w;
  float h;
  float speedY;
  float speedX;
  color c;
  
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
  void move(){
    y += speedY;
    x += speedX;
  }
  
  //movement for opponent's paddle only
  void moveUp(){
    y -= speedY;
  }
  
  void moveDown(){
    y += speedY;
  }

  void display(){
    fill(c);
    rect(x - w/2, y - h/2, w, h);
  } 
  
  float left(){
    return x - w/2;
  }
  
  float right(){
    return x + w/2;
  }
  
  float top(){
    return y - h/2;
  }
  
  float bottom(){
    return y + h/2;
  }
  
  float middle(){
    return y;
  }
}
