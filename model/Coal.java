package model;

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
		inventory.InsertItem(this);
	}
}
