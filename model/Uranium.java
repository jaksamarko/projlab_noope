package model;

import reflection.Ref;

public class Uranium extends Resource {
	public void Exposed() {
		Ref.Call(this, "Exposed", null);
		asteroid.Explode();
		Ref.Return();
	}
	
	public void PickedUp(Inventory inventory) {
		Ref.Call(this, "PickedUp", inventory);
		Ref.Return();
	}
	
}
