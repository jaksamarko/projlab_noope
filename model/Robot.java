package model;

import view.DrawAbles;

/**
 * A játékban mozgó, AI által vezérelt, fúrni és mozogni képes egységek.
 */
public class Robot extends DrillUnit implements Worker
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Robot(Asteroid _asteroid) 
	{
		super(_asteroid);
		DrawAbles.add(this);
	}
	
	
	/**
	 * Ha az aszteroida felrobbant, amin tartózkodott, a robot egy szomszédos aszteroidára sodródik.
	 */
	public void Exploded() {
		Travelable neighbor = asteroid.GetRandomNeighbor();
		if(neighbor == null)
			Die();
		asteroid.RemoveUnit(this);
		neighbor.ReceiveUnit(this);
		
	}
	
	/**
	 * Ez alapján hajtja végre a lépését a robot: fúr vagy mozog.
	 */
	public void Work()
	{
		Drill();
		if(stepDone == true)
			return;
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
		DrawAbles.remove(this);
		
	}
}
