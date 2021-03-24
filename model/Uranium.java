package model;

/**
 * A játék teljesítéséhez szolgáló nyersanyagok egyike. 
 * Ha egy teljesen kifúrt, napközelben lévõ aszteroidában van, felrobban.
 */
public class Uranium extends Resource {
	
	/**
	 *  A felrobbanást megvalósító függvény.
	 */
	public void Exposed() {
		asteroid.Explode();
	}
	/**
	 * A nyersanyag kibányászása. 
	 * A sikerességérõl a boolean visszatérési érték informál.
	 * @param inventory Az inventory, amibe a kibányászott nyersanyagot tesszük.
	 */
	public void PickedUp(Inventory inventory) {
		inventory.InsertItem(this);
	}
	
}
