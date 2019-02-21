import java.awt.Color;
import java.awt.Graphics;

public class PaddleHuman {

	//The position of the paddle.
	private static double positionX;
	private static double positionY;
	
	//The size of the paddle.
	private static double sizeWidth=32;
	private static double sizeHeight=96;
	
	//The velocity of the paddle, and a maximum velocity that it can not be faster then, and the rate at wich the velocity will accelerate when continously going in one direction.
	private static double velocityY=0;
	private static double velocityAcc=0.5;
	private static double velocityMax=3;
	
	//The borders of the top and bottom of the screen, stopping the paddles movement.
	private static double borderSize = 1;
	private static double borderTop = borderSize;
	private static double borderBottom = Main.appletHeight-borderSize;
	
	//------------------------------------------------------------------------------------------------
	
	public PaddleHuman(int x, int y){
		
		//SetPosition
		positionX=x;
		positionY=y;
		
	}
	
	public static void tick(boolean up, boolean down){
		
		//Use the "up" and "down" variables to calculate our velocity.
		if(up && !down && velocityY>-velocityMax){
			velocityY-=velocityAcc;
		}else if(down && !up && velocityY<velocityMax){
			velocityY+=velocityAcc;
		}else if((!down && !up) || (up && down)){
			velocityY=0;
		}
		
		//Move the paddle.
		positionY+=velocityY;
		
		//Make sure the paddle is inside of the borders.
		if(positionY<borderTop){
			positionY=borderTop;
			velocityY=0;
		}else if(positionY+sizeHeight>borderBottom){
			positionY=borderBottom-sizeHeight;
			velocityY=0;
		}
		
	}
	
	public static void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect((int)positionX, (int)positionY, (int)sizeWidth, (int)sizeHeight);
	}
	
	public static double getX(){
		return positionX;
	}
	
	public static double getY(){
		return positionY;
	}
	
	public static double getWidth(){
		return sizeWidth;
	}
	
	public static double getHeight(){
		return sizeHeight;
	}
	
	public static double getVelocityY() {
		return velocityY;
	}
	
}