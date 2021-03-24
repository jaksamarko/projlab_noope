package model;

/**
 * A játék teljesítéséhez szolgáló nyersanyagok egyike. 
 * Ha egy teljesen kifúrt, napközelben lévõ aszteroidában van, felrobban.
 */
public class Uranium extends Resource {
	
	private int lives;
	public Uranium()
	{
		lives = 3;
	}
	
	/**
	 *  A felrobbanást megvalósító függvény.
	 */
	public void Exposed()
	{
		lives--;
		if(lives == 0)
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
