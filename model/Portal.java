package model;

import reflection.Ref;

/**
 * A játék során segítse a telepesek közlekedését és az adott kapu párjához tartozó aszteroidára továbbítsa a játékost.
 * Illetve az õ dolga összekapcsolnia magát más kapuval, ha van már a játékban párosítatlan kapu.
 */
public class Portal implements Travelable {
	private static Portal unpairedPortal;
	public Asteroid asteroid;
	public Portal pair;
	
	/**
	 * Ez lényegében egy konstruktor függvény, végbe viszi a portál összekötés logikáját.
	 */
	public void Created() {
		Ref.Call(this, "Created", null);
		Ref.Return();
	}
	
	/**
	 * Erõforrásokat felszabadító függvény, akkor hívódik meg, mikor az aszteroida megsemmisül, ezzel együtt a portálja is, errõl értesíteni kell a portál párját, mert az is felrobban.
	 */
	public void Destroyed() {
		Ref.Call(this, "Destroyed", null);
		Ref.Return();
	}
	
	/**
	 * Fogad egy unit-ot egy másik portáltól.
	 * @param unit A fogadott unit.
	 */
	public void ReceiveUnit(Unit unit) {
		Ref.Call(this, "ReceiveUnit", unit);
		pair.SendUnitToPair(unit);
		Ref.Return();
	}
	
	/**
	 * Egy unit használatba veszi a portált.
	 * Arról, hogy ez lehetséges-e (van-e párja a portálnak) egy boolean ad igazolást,ekkor az utazás meg is fog történni.
	 * @param unit A unit ami a portált használja.
	 * @return Igaz, ha az utazás lehetséges.
	 */
	public boolean SendUnitToPair(Unit unit) {
		Ref.Call(this, "SendUnitToPair", unit);
		asteroid.ReceiveUnit(unit);
		Ref.Return();
		return true;
	}
	
	/**
	 * Beállítja az aszeroidát 
	 * @param asteroid Az aszteroida amihez a kapu tartozik.
	 */
	public void SetAsteroid(Asteroid asteroid) {
		Ref.Call(this, "SetAsteroid", asteroid);
		this.asteroid=asteroid;
		Ref.Return();
	}
	
	/**
	 * Beállítja a kapu párját.
	 * @param portal A pár, amihez a kaput kötni akarjuk.
	 */
	public void SetPair(Portal portal) {
		Ref.Call(this, "SetPair", portal);
		this.pair = portal;
		Ref.Return();
	}
}
