package model;

import reflection.Ref;

public class Uranium extends Resource {
	public void Exposed() {
		Ref.Call(this, "Exposed", null);
<<<<<<< Updated upstream
		Ref.Return();
=======
		asteroid.Explode();
>>>>>>> Stashed changes
	}
	
	public void PickedUp(Inventory inventory) {
		Ref.Call(this, "PickedUp", inventory);
		Ref.Return();
	}
	
}
