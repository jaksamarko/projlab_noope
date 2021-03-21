package model;

import reflection.*;

/**
 * Absztrakt õsosztály, amibõl következik a Robot és a Settler. 
 * Ez felel a mozgásukért, hogy “A” aszteroidáról eljussanak “B” aszteroidára, illetve képesek legyenek az aszteroida rétegét fúrni. 
 * Azt is figyelembe veszi, hogy egyszerre csak egy dolgot tehessenek, erre van egy változó.
 */
public abstract class Unit {
	protected Asteroid asteroid;
	
	/**
	 * Ez a függvény hívódik meg, mikor valamelyik okból az adott egység meghal.
	 */
	public void Die() {
		Ref.Call(this, "Die", null);
		Ref.Return();
	}
	
	/**
	 * Fúrás tevékenység, szól az aszteroidának, hogy egy réteget le akar fúrni.
	 */
	public void Drill() {
		Ref.Call(this, "Drill", null);
		boolean result = asteroid.RemoveLayer();
		if(result) {
			this.MakeStepDone();
		}
		Ref.Return();
	}
	
	/**
	 * Ez egy virtuális függvény, ami akkor hívódik meg, mikor az aszteroida (amin tartózkodott) felrobbant.
	 */
	public abstract void Exploded();
	
	/**
	 * Egyik aszteroidáról a másikra való közlekedés.
	 * @param target Célaszteroida, amire megyünk.
	 */
	public void Move(Travelable target)
	{
		Ref.Call(this, "Move", target);
		
		Boolean result = asteroid.IsNeighboor(target);
		if(result) {
			asteroid.RemoveUnit(this);
			target.ReceiveUnit(this);
			this.MakeStepDone();
		}
		Ref.Return();
		return;
	}
	
	/**
	 *  Setter függvény ami arra van hogy amikor egy mûveletet sikeresen végrehajtott akkor beállítja a stepDone változót. 
	 */
	protected void MakeStepDone() {
		Ref.Call(this, "MakeStepDone", null);
		Ref.Return();
	}
	
	/**
	 * Beállítja a unit Asteroida referenciáját.
	 * @param asteroid Az aszteroida, amin a unit tartózkodik.
	 */
	public void SetAsteroid(Asteroid asteroid) 
	{
		Ref.Call(this, "SetAsteroid", asteroid);
		this.asteroid = asteroid;
		Ref.Return();
	}
}
