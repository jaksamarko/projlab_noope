package model;

import reflection.Ref;

public class Coal extends Resource {
	
	public void PickedUp(Inventory inventory) {
		Ref.Call(this, "PickedUp", inventory);
	}
}
