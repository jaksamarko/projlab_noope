package model;

import reflection.Ref;

public class Iron extends Resource {
	public void PickedUp(Inventory inventory) {
		Ref.Call(this, "PickedUp", inventory);
		Ref.Return();
	}
	
}
