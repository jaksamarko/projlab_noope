package reflection;

public class Bag 
{
	public void addBall(Ball b)
	{
		
		Ref.Call(b, "setBag", this);
		b.SetBag(this);
		
		Ref.Call(this, "setBall", b);
		setBall(b);
		
		Ref.RequestBool("kerlek írj valamit");
		
		Ref.Return();
	}
	
	public void setBall(Ball b)
	{
		Ref.Return();
	}
}
