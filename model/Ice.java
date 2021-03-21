package model;

import reflection.Ref;

/**
 * 
 * A játék teljesítéséhez szolgáló nyersanyagok egyike. Ha egy teljesen kifúrt, napközelben lévő aszteroidában van, szublimál.
 *
 */
public class Ice extends Resource {
	
	/**
	 * A szublimálást megvalósító függvény.
	 */
	public void Exposed() {
		Ref.Call(this, "Exposed", null);
		asteroid.RemoveResource();
		Ref.Return();
	}
	
	/**
	 * A nyersanyag kibányászása. A sikerességéről a boolean visszatérési érték informál.

	 */
	public void PickedUp(Inventory inventory) {
		Ref.Call(this, "PickedUp", inventory);
		Ref.Return();
	}
	
}
