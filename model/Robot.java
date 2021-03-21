package model;

import reflection.Ref;

/**
 * A j�t�kban mozg�, AI �ltal vez�relt, f�rni �s mozogni k�pes egys�gek.
 */
public class Robot extends Unit {
	public Game game;
	
	/**
	 * Ha az aszteroida felrobbant, amin tart�zkodott, a robot egy szomsz�dos aszteroid�ra sodr�dik.
	 */
	public void Exploded() {
		Ref.Call(this, "Exploded", null);
		Travelable neighbor = asteroid.GetRandomNeighbor();
		asteroid.RemoveUnit(this);
		neighbor.ReceiveUnit(this);
		
	}
	
	/**
	 * Ez alapj�n hajtja v�gre a l�p�s�t a robot: f�r vagy mozog.
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
