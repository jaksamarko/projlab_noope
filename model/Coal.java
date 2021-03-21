package model;

import reflection.Ref;

/**
 * 
 * A játék teljesítéséhez szolgáló nyersanyagok egyike. 
 *
 */

public class Coal extends Resource {
	
	/**
	 * A nyersanyag kibányászása. A sikerességéről a boolean visszatérési érték informál.
	 */
	public void PickedUp(Inventory inventory) {
		Ref.Call(this, "PickedUp", inventory);
		Ref.Return();
	}
}
