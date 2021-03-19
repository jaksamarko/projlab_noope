package model;

import reflection.Ref;

public class Robot extends Unit {
	public Game game;
	public void Exploded() {
		Ref.Call(this, "Exploded", null);
		Travelable neighbor = asteroid.GetRandomNeighbor();
		asteroid.RemoveUnit(this);
		neighbor.ReceiveUnit(this);
		
	}
	
	public void Work() {
		Ref.Call(this, "Work", null);
		boolean result = asteroid.RemoveLayer();
		if(result) {
			this.MakeStepDone();
		}
		if(!this.getStepDone()) {
			Travelable neighbor = asteroid.GetRandomNeighbor();
			asteroid.RemoveUnit(this);
			neighbor.ReceiveUnit(this);
			
		}
		Ref.Return();
	}

	
}
