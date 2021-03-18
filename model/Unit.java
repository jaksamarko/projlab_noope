package model;

import reflection.*;

public abstract class Unit {
	protected Asteroid asteroid;

	public void Die() {
		Ref.Call(this, "Die", null);
	}
	
	public void Drill() {
		Ref.Call(this, "Drill", null);
		boolean result = asteroid.RemoveLayer();
		if(result) {
			this.MakeStepDone();
		}
		Ref.Return();
	}
	
	public abstract void Exploded();
	
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
	
	protected void MakeStepDone() {
		Ref.Call(this, "MakeStepDone", null);
	}
	protected boolean getStepDone() {
		return false;
	}
	public void SetAsteroid(Asteroid asteroid) 
	{
		Ref.Call(this, "SetAsteroid", asteroid);
		this.asteroid = asteroid;
		Ref.Return();
	}
}
