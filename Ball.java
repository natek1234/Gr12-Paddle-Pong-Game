import java.awt.Color;
import java.awt.Graphics;

public class Ball{

	//The position of the ball.
	private static double positionX;
	private static double positionY;
	
	//The size of the ball.
	private static double sizeWidth = 32;
	private static double sizeHeight = 32;
	
	//The speed variables of the ball, its current speed, default speed, the speed acceleration rate, and the maximum speed, as well as a minimum horizontal speed, to keep the ball from standing completely still.
	private static double speed = 2;
	private static double speedBase = 2;
	private static double speedIncrease = 0.25;
	private static double speedMax = 6;
	private static double speedMinX = 1 ;
	
	//The velocity of the ball.
	private static double velocityX=speed;
	private static double velocityY=0;
	
	//The borders of the top and bottom of the screen, stopping the balls movement.
	private static double borderSize = 1;
	private static double borderTop = borderSize;
	private static double borderBottom = Main.appletHeight-borderSize;
	
	//The goals on both sides of the screen, when the ball enters either of these regions, the respective player gains a point.
	private static double goalLeft = -sizeWidth;
	private static double goalRight = Main.appletWidth+sizeWidth;
	
	//Collision data, so the ball remembers which paddle collided with it last.
	private static int collisionLast =0;
	private static int collisionLeftPaddle =1;
	private static int collisionRightPaddle =2;
	
	//------------------------------------------------------------------------------------------------
	
	public Ball(){
		
		//Reset the ball, instancing it at its default stance.
		resetBall();
		
	}
	
	public static void tick(){
		
		//Check for collision with right paddle.
		if(collidingWith(PaddleComputer.getX(),PaddleComputer.getY(),PaddleComputer.getWidth(),PaddleComputer.getHeight())){
			
			//Only increase the speed if the last collision was not the right paddle.
			if(collisionLast!=collisionRightPaddle){
				collisionLast=collisionRightPaddle;
				increaseSpeed();
			}
			
			//Change the velocity of the ball as if it was bouncing against the right paddle.
			bounceAgainstObject(PaddleComputer.getX(),PaddleComputer.getY(),PaddleComputer.getWidth(),PaddleComputer.getHeight());
			
		}
		
		//Check for collision with left paddle.
		if(collidingWith(PaddleHuman.getX(),PaddleHuman.getY(),PaddleHuman.getWidth(),PaddleHuman.getHeight())){
			
			//Only increase the speed if the last collision was not the left paddle.
			if(collisionLast!=collisionLeftPaddle){
				collisionLast=collisionLeftPaddle;
				increaseSpeed();
			}
			
			//Change the velocity of the ball as if it was bouncing against the left paddle.
			bounceAgainstObject(PaddleHuman.getX(),PaddleHuman.getY(),PaddleHuman.getWidth(),PaddleHuman.getHeight());
			
		}
		
		//Move the ball.
		positionY+=velocityY;
		positionX+=velocityX;
		
		//Make sure the ball is inside of the borders.
		if(positionY<borderTop){
			positionY=borderTop;
			velocityY=-velocityY;
		}else if(positionY+sizeHeight>borderBottom){
			positionY=borderBottom-sizeHeight;
			velocityY=-velocityY;
		}
		
		//Check if we've passed any of the paddles goalLines. If so, add a point to them and reset the ball.
		if(positionX<goalLeft){
			Main.goalRight();
			resetBall();
		}else if(positionX+sizeWidth>goalRight){
			Main.goalLeft();
			resetBall();
		}
		
	}
	
	private static void increaseSpeed(){
		
		//Increase the speed, but if we exceed the max, set speed to the maximum.
		speed+=speedIncrease;
		if(speed>speedMax){
			speed = speedMax;
		}
		
	}
	
	private static void bounceAgainstObject(double x, double y, double w, double h){
		
		//Calculate the horizontal and vertical difference of the ball and the object, using their center points.
		double diffX = (positionX+sizeWidth/2)-(x+w/2);
		double diffY = (positionY+sizeHeight/2)-(y+h/2);
		
		//Calculate the angle at which the ball is compared to the object 
		double angle = Math.atan2(diffY, diffX);
		
		//Set the horizontal velocity the angle and speed, as well as following a few rules to make sure the ball does not stop its horizontal movement.
		if(diffX!=0){
			velocityX = makeSureVelocityIsAboveMinimum(Math.cos(angle)*speed);
		}else{
			if(positionX+sizeWidth/2<x+w/2){
				velocityX = -speedMinX;
			}else{
				velocityX = speedMinX;
			}
		}
		
		//Set the vertical velocity using the angle and speed.
		if(diffY!=0){
			velocityY = Math.sin(angle)*speed;
		}else{
			velocityY = 0;
		}
		
	}
	
	private static double makeSureVelocityIsAboveMinimum(double speedX){
		
		//If the velocity is less than the minimum, set it to the minimum, keeping the direction it was in. (If no direction is existing, the default direction is right).
		if(speedX>=0 && speedX<speedMinX){
			return speedX=speedMinX;
		}else if (speedX<0 && speedX>-speedMinX){
			return speedX=-speedMinX;
		}else{
			return speedX;
		}
		
	}
	
	private static boolean collidingWith(double x, double y, double w, double h){
		return ((positionX+sizeWidth>x) && positionX<x+w && positionY+sizeHeight>y && positionY<y+h);
	}
	
	public static void resetBall(){
		
		//Reset the paddles last collision.
		collisionLast =0;
		
		//Reset ball position to center.
		positionX = (Main.appletWidth/2)-(sizeWidth/2);
		positionY = (Main.appletHeight/2)-(sizeHeight/2);
		
		//Reset speed
		speed=speedBase;
		
		//Calculate which horizontal direction the ball last had.
		int direction;
		if(velocityX>=0){
			direction=1;
		}else{
			direction=-1;
		}
		
		//Reset the velocitySpeed, but keep the direction.
		velocityX = direction*speed;
		velocityY = 0;
		
	};
	
	public static void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect((int)positionX, (int)positionY, (int)sizeWidth, (int)sizeHeight);
	}
	
	public static double getX() {
		return positionX;
	}
	
	public static double getY() {
		return positionY;
	}
	
	public static double getHeight() {
		return sizeHeight;
	}
	
	public static double getWidth() {
		return sizeWidth;
	}
	
	public static double getVelocityY() {
		return velocityY;
	}
	
	public static double getVelocityX() {
		return velocityX;
	}
	
}
