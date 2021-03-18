package model;

import reflection.Ref;

public class Uranium extends Resource {
	public void Exposed() {
		Ref.Call(this, "Exposed", null);
		Ref.Return();
	}
	
	public void PickedUp(Inventory inventory) {
		Ref.Call(this, "PickedUp", inventory);
		Ref.Return();
	}
	
}
