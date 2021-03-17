package model;

import reflection.Ref;

public class Ice extends Resource {
	public void Exposed() {
		Ref.Call(this, "Exposed", null);
	}
	
	public void PickedUp(Inventory inventory) {
		Ref.Call(this, "PickedUp", inventory);
	}
	
}
