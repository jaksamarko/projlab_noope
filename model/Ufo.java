package model;

public class Ufo extends Unit implements Worker, java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ufo(Asteroid _asteroid) {
		super(_asteroid);
	}

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
	
	/**
	 * Sry ez muszáj volt loadinghoz
	 */
	@Override
	protected void AddUnitToGame() {
		Game.addNewWorker(this);
	}
}
