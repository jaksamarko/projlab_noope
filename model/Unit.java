package model;

import reflection.*;

public abstract class Unit {
	private Asteroid asteroid;

	public void Die() {
	}
	
	public void Drill() {
	}
	
	public abstract void Exploded();
	
	public void Move(Asteroid target)
	{
		Ref.Call(asteroid, "IsNeighboor", target);
		asteroid.IsNeighboor(target);
		Ref.Return();
		return;
	}
	
	private void MakeStepDone() {
	}
	
	public void SetAsteroid(Asteroid asteroid) 
	{
		this.asteroid = asteroid;
		Ref.Return();
	}
}
