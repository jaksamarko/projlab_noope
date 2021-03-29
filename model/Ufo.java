package model;

import view.DrawAbles;

public class Ufo extends Unit implements Worker, java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ufo(Asteroid _asteroid)
	{
		super(_asteroid);
		DrawAbles.add(this);
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

	@Override
	public void Die() {
		Game.RemoveUnit(this);
		DrawAbles.add(this);
	}
}
