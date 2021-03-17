package reflection;

public class Ball
{
	public void Squish()
	{
		Reflector.Return("isSquished", "True");
	}
	
	public void SetBag(Bag bag)
	{
		Reflector.call(this, "Squish", new Object[0]);
		Squish();
		Reflector.Return(this);
	}
}
