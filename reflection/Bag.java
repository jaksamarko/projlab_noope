package reflection;

public class Bag 
{
	public void addBall(Ball b)
	{
		
		Reflector.call(b, "setBag", new Object[]{this});
		b.SetBag(this);
		
		Reflector.call(this, "setBall", new Object[]{b});
		setBall(b);
		
		Boolean done = true;
		
		Reflector.RequestBool("kerlek írj valamit");
		
		Reflector.Return();
	}
	
	public void setBall(Ball b)
	{
		Reflector.Return();
	}
}
