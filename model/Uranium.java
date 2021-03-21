package model;

import reflection.Ref;

/**
 * A j�t�k teljes�t�s�hez szolg�l� nyersanyagok egyike. 
 * Ha egy teljesen kif�rt, napk�zelben l�v� aszteroid�ban van, felrobban.
 */
public class Uranium extends Resource {
	
	/**
	 *  A felrobban�st megval�s�t� f�ggv�ny.
	 */
	public void Exposed() {
		Ref.Call(this, "Exposed", null);
		asteroid.Explode();
		Ref.Return();
	}
	/**
	 * A nyersanyag kib�ny�sz�sa. 
	 * A sikeress�g�r�l a boolean visszat�r�si �rt�k inform�l.
	 * @param inventory Az inventory, amibe a kib�ny�szott nyersanyagot tessz�k.
	 */
	public void PickedUp(Inventory inventory) {
		Ref.Call(this, "PickedUp", inventory);
		Ref.Return();
	}
	
}
