package model;

import reflection.Ref;

/**
 * A j�t�k sor�n seg�tse a telepesek k�zleked�s�t �s az adott kapu p�rj�hoz tartoz� aszteroid�ra tov�bb�tsa a j�t�kost.
 * Illetve az � dolga �sszekapcsolnia mag�t m�s kapuval, ha van m�r a j�t�kban p�ros�tatlan kapu.
 */
public class Portal implements Travelable {
	private static Portal unpairedPortal;
	public Asteroid asteroid;
	public Portal pair;
	
	/**
	 * Ez l�nyeg�ben egy konstruktor f�ggv�ny, v�gbe viszi a port�l �sszek�t�s logik�j�t.
	 */
	public void Created() {
		Ref.Call(this, "Created", null);
		Ref.Return();
	}
	
	/**
	 * Er�forr�sokat felszabad�t� f�ggv�ny, akkor h�v�dik meg, mikor az aszteroida megsemmis�l, ezzel egy�tt a port�lja is, err�l �rtes�teni kell a port�l p�rj�t, mert az is felrobban.
	 */
	public void Destroyed() {
		Ref.Call(this, "Destroyed", null);
		Ref.Return();
	}
	
	/**
	 * Fogad egy unit-ot egy m�sik port�lt�l.
	 * @param unit A fogadott unit.
	 */
	public void ReceiveUnit(Unit unit) {
		Ref.Call(this, "ReceiveUnit", unit);
		pair.SendUnitToPair(unit);
		Ref.Return();
	}
	
	/**
	 * Egy unit haszn�latba veszi a port�lt.
	 * Arr�l, hogy ez lehets�ges-e (van-e p�rja a port�lnak) egy boolean ad igazol�st,ekkor az utaz�s meg is fog t�rt�nni.
	 * @param unit A unit ami a port�lt haszn�lja.
	 * @return Igaz, ha az utaz�s lehets�ges.
	 */
	public boolean SendUnitToPair(Unit unit) {
		Ref.Call(this, "SendUnitToPair", unit);
		asteroid.ReceiveUnit(unit);
		Ref.Return();
		return true;
	}
	
	/**
	 * Be�ll�tja az aszeroid�t 
	 * @param asteroid Az aszteroida amihez a kapu tartozik.
	 */
	public void SetAsteroid(Asteroid asteroid) {
		Ref.Call(this, "SetAsteroid", asteroid);
		this.asteroid=asteroid;
		Ref.Return();
	}
	
	/**
	 * Be�ll�tja a kapu p�rj�t.
	 * @param portal A p�r, amihez a kaput k�tni akarjuk.
	 */
	public void SetPair(Portal portal) {
		Ref.Call(this, "SetPair", portal);
		this.pair = portal;
		Ref.Return();
	}
}
