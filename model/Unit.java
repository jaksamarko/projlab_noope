package model;

import reflection.*;

public abstract class Unit {
	private Asteroid asteroid;

	public void Die() {
		Ref.Call(this, "Die", null);
	}
	
	public void Drill() {
		Ref.Call(this, "Drill", null);
	}
	
	public abstract void Exploded();
	
	public void Move(Asteroid target)
	{
		Ref.Call(this, "Move", target);
		
		asteroid.IsNeighboor(target);
		Ref.Return();
		return;
	}
	
	private void MakeStepDone() {
		Ref.Call(this, "MakeStepDone", null);
	}
	
	public void SetAsteroid(Asteroid asteroid) 
	{
		Ref.Call(this, "SetAsteroid", asteroid);
		this.asteroid = asteroid;
		Ref.Return();
	}
}
