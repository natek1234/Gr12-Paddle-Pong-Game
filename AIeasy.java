public class AIeasy extends AI{
	
	//!!!WORK HERE!!!
	
	public double tick() {
		
		if(Ball.getY() > PaddleComputer.getY())
		{
			return PaddleComputer.getY() + 1;
		}
		else if(Ball.getY() < PaddleComputer.getY()) 
		{
			return PaddleComputer.getY() - 1;
		}
		else
		{
			return PaddleComputer.getY();
		}
	}
}