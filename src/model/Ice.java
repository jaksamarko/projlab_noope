package model;

/**
 * 
 * A játék teljesítéséhez szolgáló nyersanyagok egyike. Ha egy teljesen kifúrt, napközelben lévő aszteroidában van, szublimál.
 *
 */
@SuppressWarnings("serial")
public class Ice extends Resource {
	/**
	 * A szublimálást megvalósító függvény.
	 */
	public void Exposed() {
		asteroid.RemoveResource();
	}
	
	/**
	 * A nyersanyag kibányászása. A sikerességéről a boolean visszatérési érték informál.

	 */
	public void PickedUp(Inventory inventory) {
		inventory.InsertItem(this);
	}
	
	public String toString() {
        return "Ice";
    } 
}
