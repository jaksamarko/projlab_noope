package model;

public abstract class DrillUnit extends Unit implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DrillUnit(int ID, Asteroid _asteroid) {
		super(ID, _asteroid);
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
