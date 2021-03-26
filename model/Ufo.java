package model;

public class Ufo extends Unit implements Worker
{
	public Ufo() {super(null);}
	public Ufo(Asteroid _asteroid) {super(_asteroid);}

	public void Exploded() {Die();}
	
	public void Work()
	{
		if (asteroid.MineResource()!=null)
		{
			MakeStepDone();
			return;
		}
		Travelable neighbor = asteroid.GetRandomNeighbor();
		if(neighbor == null)
			return;
		asteroid.RemoveUnit(this);
		neighbor.ReceiveUnit(this);
	}
}
