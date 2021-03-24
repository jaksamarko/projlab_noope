package model;

public abstract class DrillUnit extends Unit
{
	public DrillUnit(Asteroid _asteroid) {super(_asteroid);}

	/**
	 * Fúrás tevékenység, szól az aszteroidának, hogy egy réteget le akar fúrni.
	 */
	public void Drill() {
		if(asteroid.RemoveLayer()) 
			MakeStepDone();
	}
}
