package model;

import java.util.ArrayList;

import view.DrawAbles;

/**
 * Ez az osztály felel az aszteroidán történő eseményekre.
 * Itt gondolhatunk arra, hogy az aszteroidán lehetnek robotok illetve telepesek, 
 * ezek mind-mind mozognak és fúrják a kérgét, az előbbi ki is vehet és be is tehet anyagokat, 
 * de akár portált is létrehozhat, mindezt ez az osztály kezeli.
 */

public class Asteroid implements Travelable, java.io.Serializable  {
	public static int Layers = 3;
	// getter és setterek
	public void RemoveUnit(Unit unit) {units.remove(unit);}
	public void SetResource(Resource resource) {this.resource = resource;}
	public Resource GetResource() { return resource;}
	private boolean IsNearSun() {return nearSun;}
	public void AddUnit(Unit unit) {units.add(unit);}
	public void addNeighbor(Travelable travelable) {neighbors.add(travelable);}
	public ArrayList<Travelable> GetNeighbors() {return neighbors;}
	public int GetLayers() {return layers;}
	
	private int layers;
	private boolean nearSun;
	private Portal portal;
	private Resource resource;
	private ArrayList<Unit> units;
	private ArrayList<Travelable> neighbors;
	
	public Asteroid(boolean _nearSun)
	{
		layers = Asteroid.Layers;
		nearSun = _nearSun;
		portal = null;
		resource = null;
		units = new ArrayList<Unit>();
		neighbors = new ArrayList<Travelable>();
		DrawAbles.add(this);
	}
	
	/**
	 * Elfogad egy resource anyagot és igazat ad, ha sikerült is betenni, mert ha már van, akkor nem engedi.
	 * @param resource
	 * @return true, ha sikerül betenni, egyébként false
	 */
	public boolean AcceptResource(Resource resource)
	{
		if(resource!=null)
			return false;
		this.resource = resource;
		return true;
	}
	
	/**
	 *  Felépít egy portált, ha a unitnak van lerakható portálja, illetve nincs még portál az aszteroidán. Ennek sikerességét egy boolean igazolja.
	 * @param inventory
	 * @return true, ha van a tárolóban portál, és az aszteroidán még nincs, azaz lerakható egy, false, ha nem lehet lerakni portált
	 */
	public boolean BuildPortal(Inventory inventory)
	{
		if(portal!=null) 
			return false;
		Portal newPortal = inventory.GetPortal();
		if(newPortal == null)
			return false;
		ReceivePortal(newPortal);
		return true;
	}
	
	/**
	 * Az aszteroida kitörléséért felel.
	 */
	public void DestroySelf()
	{
		Game.RemoveAsteroid(this);
		DrawAbles.remove(this);
	}
	
	/**
	 * 	Felel az aszteroida felrobbanásával járó dolgokért, mint például a hozzátartozó elemek értesítése, erőforrások felszabadítása.
	 */
	public void Explode()
	{
		for(Unit u: units)
			u.Exploded();
		DestroySelf();
	}
	
	public Travelable GetRandomNeighbor()
	{
		if(neighbors.size() == 0)
			return null;
		return neighbors.get(RNG.GetRand() % neighbors.size());	
	}
	/**
	 * Visszaadja, hogy az aszteroida napközelben van-e.
	 * @return true, ha napközelben van, egyébként false
	 */
	
	/**
	 * 
	 * @param travelable
	 * @return true, ha a választott cél szomszéd, egyébként false
	 */
	public boolean IsNeighboor(Travelable travelable) {
		for(Travelable t: neighbors)
		{
			if(t == travelable)
				return true;
		}
		return false;
	}
	
	/**
	 * Kiveszi és odaadja a tárolt resource-t az inventory-nak
	 * @return resource, ha az aszteroida nem üres, egyébként null
	 */
	public Resource MineResource()
	{
		return RemoveResource();
	}
	
	/**
	 * Elfogad egy egységet (unit-ot) az aszteroidán és ott tárolja.
	 */
	public void ReceiveUnit(Unit unit) 
	{
		AddUnit(unit);
		unit.SetAsteroid(this);
	}
	/**
	 *  Eltávolít egy réteget az aszteroidáról és ennek sikerességét visszaadja. Ha már nincs több ilyen réteg, akkor nem történik változás.
	 * @return true, ha sikerült eltávolítani egy réteget, false ha nem sikerült. 
	  */
	public boolean RemoveLayer()
	{
		if(layers == 0)
			return false;
		layers--;
		return true;
	}
	/**
	 * Eltávolítja a nyersanyagot az aszteroidából, ez akkor fontos, ha például a jég elszublimál, teljesen eltűnik és senki nem kaphatja meg.
	 * @return 
	 */
	public Resource RemoveResource()
	{
		Resource re = resource;
		resource = null;
		return re;
	}
	/**
	 * Egy függvény, amivel lejátszódik a napkitörés effektus, ellenőrzi hogy megvan a feltételei annak, hogy elbújhassanak ott. Ha nem, akkor megöl mindenkit, aki rajta tartózkodott.
	 */
	public void Sunstorm()
	{
		// if I use the orignal ArrayList it throws java.util.ConcurrentModificationException
		ArrayList<Unit> unitsCopy = new ArrayList<Unit>();
		for(Unit u: units)
			unitsCopy.add(u);
		if(layers > 0||resource!=null)
			for(Unit u: unitsCopy)
				u.Die();
	}
	public void RemoveNeighbor(Asteroid asteroid)
	{
		neighbors.remove(asteroid);
	}
	
	public boolean ReceivePortal(Portal portal)
	{
		this.portal = portal;
		neighbors.add(portal);
		portal.SetAsteroid(this);
		return true;
	}
	public void RemovePortal()
	{
		neighbors.remove(portal);
		portal = null;
	}
	/**
	 *  Egy függvény, amivel lejátszódik az az esemény, amikor az aszteroida ki van fúrva teljesen. Ez fontos a napközeli események kezelésében.
	 */
	@Override
	public void EndTurnEffect()
	{
		portal.EndTurnEffect();
		if(IsNearSun() && layers == 0) 
			resource.Exposed();
		
	}
	
	public Portal GetPortal()
	{
		return portal;
	}
	public ArrayList<Unit> GetUnits()
	{
		return units;
	}
}
