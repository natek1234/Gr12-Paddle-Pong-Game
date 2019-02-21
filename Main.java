import java.applet.Applet;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;

public class Main extends Applet implements Runnable, KeyListener{
	
	//Used to prevent some warning message.
	private static final long serialVersionUID = 1L;
	
	//These things are used to define the size of the window.
	public static final int appletWidth=640;
	public static final int appletHeight=480;
	
	//Used for controls.
	private boolean keyDown = false;
	private boolean keyUp = false;
	private boolean keyEnter = false;
	private boolean keyEscape = false;
	private boolean enterCanClick = true;
	
	//These are used for accessing the image that gets rendered onto the screen, and the graphics used to manipulate it.
	private Image screenImage;
	private Graphics screenGraphics;

	private boolean menuCanClick = true;
	private int menuCursor = 1;
	private int menuCursorMax = 3;
	private int menuCursorMin = 1;
	
	//Here are the objects used in the program.
	public static PaddleHuman objectPaddleHUM = new PaddleHuman(32,(appletHeight/2)-48);
	public static PaddleComputer objectPaddleCOM = new PaddleComputer(appletWidth-32-32,(appletHeight/2)-48,1);
	public static Ball objectBall = new Ball();
	
	//Scene
	private static int screenId = 0;
	private static int screenTitle = 0;
	private static int screenGame= 1;
	private static int screenScoreLimit= 2;
	private static int screenEnd= 3;
	
	//Score
	private static int scoreLeft = 0;
	private static int scoreRight = 0;
	private static int scoreMin = 1;
	private static int scoreLimit = 5;
	private static int scoreMax = 99;
	private static int scoreOffsetX = 128;
	private static int scoreOffsetY = 32;
	private static int scoreBase = 10;
	private static int scoreBlockSize = 8;
	private static int scoreRenderWidth = 4;
	
	//Numbers used for printing out title and menu.
	private static int [][] number0 = {{1,1,1},{1,0,1},{1,0,1},{1,0,1},{1,0,1},{1,1,1}};
	private static int [][] number1 = {{0,1,0},{1,1,0},{0,1,0},{0,1,0},{0,1,0},{1,1,1}};
	private static int [][] number2 = {{1,1,1},{0,0,1},{0,1,0},{1,0,0},{1,0,0},{1,1,1}};
	private static int [][] number3 = {{1,1,1},{0,0,1},{1,1,1},{0,0,1},{0,0,1},{1,1,1}};
	private static int [][] number4 = {{1,0,1},{1,0,1},{1,0,1},{1,1,1},{0,0,1},{0,0,1}};
	private static int [][] number5 = {{1,1,1},{1,0,0},{1,0,0},{0,1,0},{0,0,1},{1,1,1}};
	private static int [][] number6 = {{1,1,1},{1,0,0},{1,1,1},{1,0,1},{1,0,1},{1,1,1}};
	private static int [][] number7 = {{1,1,1},{0,0,1},{0,0,1},{0,0,1},{0,0,1},{0,0,1}};
	private static int [][] number8 = {{1,1,1},{1,0,1},{1,1,1},{1,0,1},{1,0,1},{1,1,1}};
	private static int [][] number9 = {{1,1,1},{1,0,1},{1,0,1},{1,1,1},{0,0,1},{1,1,1}};
	private static int [][][] numbers = {number0,number1,number2,number3,number4,number5,number6,number7,number8,number9};
	
	//Letters used for printing out title and menu.
	private static int [][] letterP = {{1,1,1,1},{1,0,0,1},{1,0,0,1},{1,1,1,1},{1,0,0,0},{1,0,0,0}};
	private static int [][] letterO = {{1,1,1,1},{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,1,1,1}};
	private static int [][] letterN = {{1,0,0,1},{1,1,0,1},{1,1,0,1},{1,0,1,1},{1,0,1,1},{1,0,0,1}};
	private static int [][] letterG = {{1,1,1,1},{1,0,0,1},{1,0,0,0},{1,0,1,1},{1,0,0,1},{1,1,1,1}};
	private static int [][] letterE = {{1,1,1,1},{1,0,0,0},{1,1,1,0},{1,0,0,0},{1,0,0,0},{1,1,1,1}};
	private static int [][] letterA = {{1,1,1,1},{1,0,0,1},{1,0,0,1},{1,1,1,1},{1,0,0,1},{1,0,0,1}};
	private static int [][] letterS = {{1,1,1,1},{1,0,0,0},{1,1,1,1},{0,0,0,1},{0,0,0,1},{1,1,1,1}};
	private static int [][] letterY = {{1,0,1,0},{1,0,1,0},{1,0,1,0},{1,1,1,0},{0,1,0,0},{0,1,0,0}};
	private static int [][] letterR = {{1,1,1,1},{1,0,0,1},{1,1,1,1},{1,0,1,0},{1,0,0,1},{1,0,0,1}};
	private static int [][] letterM = {{1,0,0,1},{1,1,1,1},{1,1,1,1},{1,0,1,1},{1,0,0,1},{1,0,0,1}};
	private static int [][] letterL = {{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,1,1,1}};
	private static int [][] letterH = {{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,1,1,1},{1,0,0,1},{1,0,0,1}};
	private static int [][] letterD = {{1,1,1,0},{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,1,1,0}};
	private static int [][] letterI = {{0,1,1,1},{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,1,1,1}};
	private static int [][] letterT = {{1,1,1,1},{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,1,0}};
	private static int [][] letterU = {{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,0,0,1},{1,1,1,1}};
	private static int [][] letterW = {{1,0,0,1},{1,0,0,1},{1,0,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}};
	private static int [][] letterSpace = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
	
	//Words used to more easily print out words.
	private static int[][][] wordPong = {letterP,letterO,letterN,letterG};
	private static int[][][] wordEasy = {letterE,letterA,letterS,letterY};
	private static int[][][] wordNormal = {letterN,letterO,letterR,letterM,letterA,letterL};
	private static int[][][] wordHard = {letterH,letterA,letterR,letterD};
	private static int[][][] wordYou = {letterY,letterO,letterU};
	private static int[][][] wordWin = {letterW,letterI,letterN};
	private static int[][][] wordLose = {letterL,letterO,letterS,letterE};
	private static int[][][] wordEndAt = {letterE,letterN,letterD,letterSpace,letterA,letterT};
	
	//------------------------------------------------------------------------------------------------
	
	//Main function.
	public static void main (String[] args){
		JFrame frame = new JFrame("Pong");
		Main main = new Main();
		frame.add(main);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frame.getInsets().left+appletWidth+frame.getInsets().right,frame.getInsets().top+appletHeight+frame.getInsets().bottom);
		main.start();
	}

	//Constructor.
	public Main(){
		this.addKeyListener(this);
	}

	public void start (){
		new Thread(this).start();
	}
	
	//The main loop, this is where the main loop is, making the game work.
	public void run(){
		while(true){
			if(screenId==screenTitle){
				
				//Reset menu cursor cool down
				if(!keyDown && !keyUp && !menuCanClick){
					menuCanClick = true;
				}
				
				//Move menu cursor
				if(keyDown && !keyUp && menuCanClick && menuCursor<menuCursorMax){
					menuCursor+=1;
					menuCanClick = false;
				}else if(keyUp && !keyDown && menuCanClick && menuCursor>menuCursorMin){
					menuCanClick = false;
					menuCursor-=1;
				}
				
				//Switch to score selection screen
				if(keyEnter && enterCanClick){
					screenId=screenScoreLimit;
					enterCanClick=false;
				}
				
				//Reset enter button cool down
				if(!keyEnter){
					enterCanClick = true;
				}
				
			}else if(screenId==screenScoreLimit){
				
				//Reset menu cursor cool down
				if(!keyDown && !keyUp && !menuCanClick){
					menuCanClick = true;
				}
				
				//Move menu cursor
				if(keyDown && !keyUp && menuCanClick && scoreLimit>scoreMin){
					scoreLimit -=1;
					menuCanClick = false;
				}else if(keyUp && !keyDown && menuCanClick && scoreLimit<scoreMax){
					menuCanClick = false;
					scoreLimit +=1;
				}
				
				//Switch to game
				if(keyEnter && enterCanClick){
					enterCanClick=false;
					screenId=screenGame;
					Ball.resetBall();
					scoreLeft=0;
					scoreRight=0;
					objectPaddleCOM = new PaddleComputer(appletWidth-32-32,(appletHeight/2)-48,menuCursor);
					objectPaddleHUM = new PaddleHuman(32,(appletHeight/2)-48);
				}
				
				//Reset enter button cool down
				if(!keyEnter){
					enterCanClick = true;
				}
				
				//Go back
				if(keyEscape){
					screenId=screenTitle;
				}
				
			}else if(screenId==screenEnd){
				
				//Go back
				if(keyEscape || (keyEnter && enterCanClick)){
					scoreLeft=0;
					scoreRight=0;
					screenId=screenTitle;
					enterCanClick=false;
				}
				
				//Reset enter button cool down
				if(!keyEnter){
					enterCanClick = true;
				}
				
			}else if(screenId==screenGame){
			
				//Tick
				Ball.tick();
				PaddleHuman.tick(keyUp, keyDown);
				PaddleComputer.tick();
				
				if(keyEscape){
					scoreLeft=0;
					scoreRight=0;
					screenId=screenTitle;
				}
				
				if(scoreLeft>=scoreLimit || scoreRight>=scoreLimit){
					screenId=screenEnd;
				}
			
			}
			
			//Paint
			repaint();
			
			//DelayThread
			try{Thread.sleep(5);}
			catch (InterruptedException ex){}
			
		}
	}
	
	public void update (Graphics g){
		if (screenImage == null){
			screenImage = createImage(appletWidth,appletHeight);
			screenGraphics = screenImage.getGraphics();
		}
		paint (screenGraphics);
		g.drawImage(screenImage, 0, 0, appletWidth,appletHeight,0,0,appletWidth,appletHeight, this);
	}

	public void paint(Graphics g){
		
		//Paint the Background
		g.setColor(Color.black);
		g.fillRect(0, 0, appletWidth, appletHeight);
		
		if(screenId==screenTitle){
			
			//Paint the Border Lines
			g.setColor(Color.gray);
			for(int i=8;i<appletWidth;i+=32){
				g.fillRect(i, 8, 16, 16);
				g.fillRect(i, appletHeight-8-16, 16, 16);
			}
			
			//Paint arrow
			g.setColor(Color.white);
			g.fillRect(48, 208+56*menuCursor, 16, 48);
			g.fillRect(48+24, 208+56*menuCursor+16, 8, 16);
			g.fillRect(48+16, 208+56*menuCursor+8, 8, 32);
			
			//Paint Menu
			g.setColor(Color.white);
			printWord(wordEasy,88,208+56,8,g);
			printWord(wordNormal,88,208+56*2,8,g);
			printWord(wordHard,88,208+56*3,8,g);
			
			//Paint the Title
			g.setColor(Color.white);
			printWord(wordPong,16,32,32,g);
			
		}else if(screenId==screenScoreLimit){
			
			//Paint the Border Lines
			g.setColor(Color.gray);
			for(int i=8;i<appletWidth;i+=32){
				g.fillRect(i, 8, 16, 16);
				g.fillRect(i, appletHeight-8-16, 16, 16);
			}
			
			//ArrowDown
			int x = 264;
			int y = 256+112;
			g.setColor(Color.gray);
			g.fillRect(x, y, 96, 16);
			g.fillRect(x+16, y+16, 64, 16);
			g.fillRect(x+32, y+32, 32, 16);
			
			//ArrowUp
			x = 264;
			y = 256-64;
			g.setColor(Color.gray);
			g.fillRect(x, y+32, 96, 16);
			g.fillRect(x+16, y+16, 64, 16);
			g.fillRect(x+32, y, 32, 16);
			
			//Words and Score
			g.setColor(Color.white);
			printWord(wordEndAt,88,64,16,g);
			printScore(scoreLimit,256,256,16,g);
		
		}else if(screenId==screenEnd){
			
			//Paint the Border Lines
			g.setColor(Color.gray);
			for(int i=8;i<appletWidth;i+=32){
				g.fillRect(i, 8, 16, 16);
				g.fillRect(i, appletHeight-8-16, 16, 16);
			}
			
			int x = 16;
			int y = 256-64;
			//Paint words
			g.setColor(Color.white);
			if(scoreLeft>scoreRight){
				printWord(wordYou,48+x,y,16,g);
				printWord(wordWin,48+x+18*16,y,16,g);
			}else{
				printWord(wordYou,x,y,16,g);
				printWord(wordLose,x+18*16,y,16,g);
			}
			
		}else if(screenId==screenGame){
			
			//Paint the Middle Line
			g.setColor(Color.gray);
			for(int i=8;i<appletHeight;i+=32){
				g.fillRect((appletWidth/2)-8, i, 16, 16);
			}
			
			//Paint the scores
			printScore(scoreLeft,scoreOffsetX,scoreOffsetY,scoreBlockSize,g);
			printScore(scoreRight,appletWidth-64-scoreOffsetX,scoreOffsetY,scoreBlockSize,g);
			
			//Paint the Ball
			Ball.render(g);
			
			//Paint the Paddles
			PaddleHuman.render(g);
			PaddleComputer.render(g);
			
		}
			
	}
	
	private void printScore(int score, int x, int y, int s, Graphics g){
		int	tempIntOne = score%scoreBase;
		int	tempIntTwo = (score-tempIntOne)/scoreBase;
		printLetter(numbers[tempIntTwo],x,y,s,g);
		printLetter(numbers[tempIntOne],x+(s*scoreRenderWidth),y,s,g);
	}
	
	public static void goalLeft(){
		scoreLeft = increaseUpTo(scoreLeft,scoreMax);
	}
	
	public static void goalRight(){
		scoreRight = increaseUpTo(scoreRight,scoreMax);
	}
	
	private static int increaseUpTo(int num, int max){
		int increaseBy = 1;
		if(num<max){
			return num+=increaseBy;
		}else{
			return max;
		}
	}
	
	private void printLetter(int[][] arr, int x, int y ,int s, Graphics g){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[0].length;j++){
				if(arr[i][j]==1){
					g.fillRect(x+j*s, y+i*s, s, s);
				}
			}
		}
	}
	
	private void printWord(int[][][] arr, int x, int y ,int s, Graphics g){
		for(int i=0;i<arr.length;i++){
			printLetter(arr[i],x+i*s*5,y,s,g);
		}
	}
	
	//Function for when a key is pressed.
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key==KeyEvent.VK_DOWN){
			keyDown=true;
		}else if(key==KeyEvent.VK_UP){
			keyUp=true;
		}else if(key==KeyEvent.VK_ENTER){
			keyEnter=true;
		}else if(key==KeyEvent.VK_ESCAPE){
			keyEscape=true;
		}
	}
	
	//Function for when a key is released.
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if (key==KeyEvent.VK_DOWN){
			keyDown=false;
		}else if(key==KeyEvent.VK_UP){
			keyUp=false;
		}else if(key==KeyEvent.VK_ENTER){
			keyEnter=false;
		}else if(key==KeyEvent.VK_ESCAPE){
			keyEscape=false;
		}
	}

	//Function that's required but not used.
	public void keyTyped(KeyEvent e){}
	
	//Returns the players score.
	public static int getScoreHuman(){
		return scoreLeft;
	};
	
	//Returns the computers score.
	public static int getScoreComputer(){
		return scoreRight;
	};
	
	//Returns the score limit.
	public static int getScoreLimit(){
		return scoreLimit;
	};
	
	//Returns the score limit.
	public static int getFieldWidth(){
		return appletWidth;
	};
	
	//Returns the score limit.
	public static int getFieldHeight(){
		return appletHeight;
	};
	
}
