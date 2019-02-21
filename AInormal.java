public class AInormal extends AI{

	//!!!WORK HERE!!!

	public double tick() {
		
		if(Ball.getY() > PaddleComputer.getY())
		{
			return PaddleComputer.getY() + 1.5;
		}
		else if(Ball.getY() < PaddleComputer.getY()) 
		{
			return PaddleComputer.getY() - 1.5;
		}
		else
		{
			return PaddleComputer.getY();
		}
	}

	
}