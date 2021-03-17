package reflection;

public class Main
{
	public static void main(String[] args)
	{
		Ball ball = new Ball();
		Reflector.addObject(ball, "ball");
		Bag bag = new Bag();
		Reflector.addObject(bag, "bag");
	
		
		Reflector.call(bag, "addBall", new Object[]{ball});
		bag.addBall(ball);
    }
}


