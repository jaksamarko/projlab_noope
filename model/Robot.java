package model;

import reflection.Ref;

public class Robot extends Unit {
	public Game game;
	public void Exploded() {
		Ref.Call(this, "Exploded", null);
	}
	
	public void Work() {
		Ref.Call(this, "Work", null);
		boolean result = asteroid.RemoveLayer();
		if(result) {
			this.MakeStepDone();
		}
		if(!this.getStepDone()) {
			Asteroid neighbor = (Asteroid) asteroid.GetRandomNeighbor();
			asteroid.RemoveUnit(this);
			neighbor.ReceiveUnit(this);
			
		}
		Ref.Return();
	}

	
}
