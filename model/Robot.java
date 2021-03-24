package model;

import reflection.Ref;

/**
 * A játékban mozgó, AI által vezérelt, fúrni és mozogni képes egységek.
 */
public class Robot extends Unit {
	public Game game;
	
	/**
	 * Ha az aszteroida felrobbant, amin tartózkodott, a robot egy szomszédos aszteroidára sodródik.
	 */
	public void Exploded() {
		Ref.Call(this, "Exploded", null);
		Travelable neighbor = asteroid.GetRandomNeighbor();
		asteroid.RemoveUnit(this);
		neighbor.ReceiveUnit(this);
		
	}
	
	/**
	 * Ez alapján hajtja végre a lépését a robot: fúr vagy mozog.
	 */
	public void Work() {
		Ref.Call(this, "Work", null);
		boolean result = asteroid.RemoveLayer();
		if(result) {
			this.MakeStepDone();
		}
		if(!result) {
			Travelable neighbor = asteroid.GetRandomNeighbor();
			asteroid.RemoveUnit(this);
			neighbor.ReceiveUnit(this);
			
		}
		Ref.Return();
	}

	
}
