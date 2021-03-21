package model;

import reflection.Ref;

/**
 * A j�t�kban l�v� nyersanyagok, amelyek a j�t�k megnyer�s�hez sz�ks�gesek. 
 * Vannak k�zt�k, amelyek egy teljesen kif�rt, napk�zelben l�v� aszteroid�n bizonyos viselked�st hajtanak v�gre. 
 * A j�g szublim�l, az ur�n felrobban.
 */
public abstract class Resource {
	public Asteroid asteroid;
	
	/**
	 * Ennek a f�ggv�nynek a fel�ldefini�l�s�val val�s�tj�k meg az egyes nyersanyagok a napk�zelben l�v�, teljesen kif�rt aszteroid�n val� viselked�s�ket.
	 */
	public void Exposed() {
		Ref.Call(this, "Exposed", null);
		Ref.Return();
	}
	
	/**
	 * Ha kib�ny�sznak egy adott nyersanyagot, akkor ennek a f�ggv�nynek az adott nyersanyag �ltal megval�s�tott v�ltozata fut le.
	 * @param inventory
	 */
	public abstract void PickedUp(Inventory inventory);
}
