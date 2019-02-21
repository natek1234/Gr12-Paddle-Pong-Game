import java.awt.Color;
import java.awt.Graphics;

public class PaddleComputer{

	//The position of the paddle.
	private static double positionX;
	private static double positionY;
	
	//The size of the paddle.
	private static double sizeWidth=32;
	private static double sizeHeight=96;
	
	//The borders of the top and bottom of the screen, stopping the paddles movement.
	private static double borderSize = 1;
	private static double borderTop = borderSize;
	private static double borderBottom = Main.appletHeight-borderSize;
	
	//The AI controlling the paddle.
	private static AI AI;
	
	//------------------------------------------------------------------------------------------------
	
	public PaddleComputer(int x, int y, int level){
		
		//SetPosition
		positionX=x;
		positionY=y;
		
		//Set AI
		if(level==1){
			AI = new AIeasy();
		}else if(level==2){
			AI = new AInormal();
		}else{
			AI = new AIhard();
		}
		
	}
	
	public static void tick(){
		
		//Move the paddle using the AI.
		positionY = AI.tick();	
		
		//Make sure the paddle is inside of the borders.
		if(positionY<borderTop){
			positionY=borderTop;
		}else if(positionY+sizeHeight>borderBottom){
			positionY=borderBottom-sizeHeight;
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
	
}