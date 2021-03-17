package model;

public abstract class Unit {
	private Asteroid asteroid;

	public void Die() {
	}
	
	public void Drill() {
	}
	
	public abstract void Exploded();
	
	public void Move(Travelable target) {
	}
	
	private void MakeStepDone() {
	}
	
	public void SetAsteroid(Asteroid asteroid) {
	}
}
