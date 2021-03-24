package model;

import reflection.Ref;

/**
 * A játékban lévõ nyersanyagok, amelyek a játék megnyeréséhez szükségesek. 
 * Vannak köztük, amelyek egy teljesen kifúrt, napközelben lévõ aszteroidán bizonyos viselkedést hajtanak végre. 
 * A jég szublimál, az urán felrobban.
 */
public abstract class Resource {
	public Asteroid asteroid;
	
	/**
	 * Ennek a függvénynek a felüldefiniálásával valósítják meg az egyes nyersanyagok a napközelben lévõ, teljesen kifúrt aszteroidán való viselkedésüket.
	 */
	public void Exposed() {}
	
	/**
	 * Ha kibányásznak egy adott nyersanyagot, akkor ennek a függvénynek az adott nyersanyag által megvalósított változata fut le.
	 * @param inventory
	 */
	public abstract void PickedUp(Inventory inventory);
}
