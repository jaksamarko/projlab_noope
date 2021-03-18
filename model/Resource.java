package model;

import reflection.Ref;

public abstract class Resource {
	public Asteroid asteroid;
	public void Exposed() {
		Ref.Call(this, "Exposed", null);
		Ref.Return();
	}
	
	public abstract void PickedUp(Inventory inventory);
}
