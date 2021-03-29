package model;

/**
 * Absztrakt õsosztály, amibõl következik a Robot és a Settler. 
 * Ez felel a mozgásukért, hogy “A” aszteroidáról eljussanak “B” aszteroidára, illetve képesek legyenek az aszteroida rétegét fúrni. 
 * Azt is figyelembe veszi, hogy egyszerre csak egy dolgot tehessenek, erre van egy változó.
 */
public abstract class Unit {
	protected Asteroid asteroid;
	protected boolean stepDone;
	
	/**
	 * Kell a szérializáláshoz, sry
	 */
	public Unit() {
		AddUnitToGame();
	}
	
	public Unit(Asteroid _asteroid)
	{
		asteroid = _asteroid;
		stepDone = false;
		
	}
	
	protected abstract void AddUnitToGame();
	
	/**
	 * Ez a függvény hívódik meg, mikor valamelyik okból az adott egység meghal.
	 */
	public abstract void Die();
	
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
		if(asteroid.IsNeighboor(target))
		{
			asteroid.RemoveUnit(this);
			target.ReceiveUnit(this);
			MakeStepDone();
		}
	}
	
	/**
	 *  Setter függvény ami arra van hogy amikor egy mûveletet sikeresen végrehajtott akkor beállítja a stepDone változót. 
	 */
	protected void MakeStepDone()
	{
		stepDone = true;
	}
	
	/**
	 *  Fügvény amely minden Settler körének az elején van meghívva
	 */
	public void Active()
	{
		stepDone = false;
	}
	
	/**
	 * Beállítja a unit Asteroida referenciáját.
	 * @param asteroid Az aszteroida, amin a unit tartózkodik.
	 */
	public void SetAsteroid(Asteroid asteroid) 
	{
		this.asteroid = asteroid;
	}
	
	public Asteroid getAsteroid() {
		return this.asteroid;
	}
	
	public boolean getStepDone()
	{
		return this.stepDone;
	}
}
