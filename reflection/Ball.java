package reflection;

public class Ball
{
	public void Squish()
	{
		Ref.Return("isSquished", "True");
	}
	
	public void SetBag(Bag bag)
	{
		Ref.Call(this, "Squish", null);
		Squish();
		Ref.Return(this);
	}
}
