package model;

import reflection.*;

/**
 * Absztrakt �soszt�ly, amib�l k�vetkezik a Robot �s a Settler. 
 * Ez felel a mozg�suk�rt, hogy �A� aszteroid�r�l eljussanak �B� aszteroid�ra, illetve k�pesek legyenek az aszteroida r�teg�t f�rni. 
 * Azt is figyelembe veszi, hogy egyszerre csak egy dolgot tehessenek, erre van egy v�ltoz�.
 */
public abstract class Unit {
	protected Asteroid asteroid;
	
	/**
	 * Ez a f�ggv�ny h�v�dik meg, mikor valamelyik okb�l az adott egys�g meghal.
	 */
	public void Die() {
		Ref.Call(this, "Die", null);
		Ref.Return();
	}
	
	/**
	 * F�r�s tev�kenys�g, sz�l az aszteroid�nak, hogy egy r�teget le akar f�rni.
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
	 * Ez egy virtu�lis f�ggv�ny, ami akkor h�v�dik meg, mikor az aszteroida (amin tart�zkodott) felrobbant.
	 */
	public abstract void Exploded();
	
	/**
	 * Egyik aszteroid�r�l a m�sikra val� k�zleked�s.
	 * @param target C�laszteroida, amire megy�nk.
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
	 *  Setter f�ggv�ny ami arra van hogy amikor egy m�veletet sikeresen v�grehajtott akkor be�ll�tja a stepDone v�ltoz�t. 
	 */
	protected void MakeStepDone() {
		Ref.Call(this, "MakeStepDone", null);
		Ref.Return();
	}
	
	/**
	 * Be�ll�tja a unit Asteroida referenci�j�t.
	 * @param asteroid Az aszteroida, amin a unit tart�zkodik.
	 */
	public void SetAsteroid(Asteroid asteroid) 
	{
		Ref.Call(this, "SetAsteroid", asteroid);
		this.asteroid = asteroid;
		Ref.Return();
	}
}
