package model;

public abstract class DrillUnit extends Unit implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DrillUnit(Asteroid _asteroid) {
		super(_asteroid);
		AddUnitToGame();
	}

	/**
	 * Fúrás tevékenység, szól az aszteroidának, hogy egy réteget le akar fúrni.
	 */
	public void Drill() {
		if(asteroid.RemoveLayer()) 
			MakeStepDone();
	}
}
