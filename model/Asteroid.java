package model;

import reflection.*;

/**
 * Ez az osztály felel az aszteroidán történő eseményekre.
 * Itt gondolhatunk arra, hogy az aszteroidán lehetnek robotok illetve telepesek, 
 * ezek mind-mind mozognak és fúrják a kérgét, az előbbi ki is vehet és be is tehet anyagokat, 
 * de akár portált is létrehozhat, mindezt ez az osztály kezeli.
 */

public class Asteroid implements Travelable {
	
	public Portal portal;
	public Resource resource;
	private Unit units;
	public Game game;
	public Travelable neighbors;
	
	public int GetLayers() {
		return Ref.RequestInt("Mekkora a kéreg?");
	}
	//TODO ezt az 5. teszt hibás mûködése miatt változtattam. pls check h így jó-e also, ehhez van egy hosszabb kommentem, csoportba ment
	
	/**
	 * Elfogad egy resource anyagot és igazat ad, ha sikerült is betenni, mert ha már van, akkor nem engedi.
	 * @param resource
	 * @return true, ha sikerül betenni, egyébként false
	 */
	public boolean AcceptResource(Resource resource) {
		Ref.Call(this, "AcceptResource", resource);
		boolean ret = false;
		if(resource!=null) {
			boolean noResource = Ref.RequestBool("Üres?");
			if(noResource) {
				ret=true;
				this.SetResource(resource);
			}
		}
		Ref.Return("taken",ret);
		return ret;
	}
	

	public void AddUnit(Unit unit)
	{
		Ref.Call(this, "AddUnit", unit);
		units = unit;
		Ref.Return();
	}
	
	
	
	public void addNeighbor(Travelable travelable)
	{
		Ref.Call(this, "addNeighbor", travelable);
		this.neighbors = travelable;
		Ref.Return();
	}
	
	/**
	 *  Felépít egy portált, ha a unitnak van lerakható portálja, illetve nincs még portál az aszteroidán. Ennek sikerességét egy boolean igazolja.
	 * @param inventory
	 * @return true, ha van a tárolóban portál, és az aszteroidán még nincs, azaz lerakható egy, false, ha nem lehet lerakni portált
	 */
	public boolean BuildPortal(Inventory inventory) {
		Ref.Call(this, "BuildPortal", inventory);
		Boolean ret=false;
		if(this.portal!=null) {
			ret=false;
		} else {
			Portal newPortal = inventory.GetPortal();
			if(newPortal==null) {
				ret=false;
			} else {
				ret=true;
				this.SetPortal(newPortal);
			}
		}
		Ref.Return("built",ret);
		return ret;
	}
	
	/**
	 * Az aszteroida kitörléséért felel.
	 */
	public void DestroySelf()
	{
		Ref.Call(this, "DestroySelf", null);
		Ref.Return();
	}
	
	/**
	 * 	Felel az aszteroida felrobbanásával járó dolgokért, mint például a hozzátartozó elemek értesítése, erőforrások felszabadítása.
	 */
	public void Explode() {
		Ref.Call(this, "Explode", null);
		units.Exploded();
		this.DestroySelf();
		Ref.Return();
	}
	
	/**
	 *  Egy függvény, amivel lejátszódik az az esemény, amikor az aszteroida ki van fúrva teljesen. Ez fontos a napközeli események kezelésében.
	 */
	public void Exposure() {
		Ref.Call(this, "Exposure", null);
		boolean result = this.IsNearSun();
		if(result && GetLayers() == 0) {
			resource.Exposed();
		}
		Ref.Return();
	}
	
	public Travelable GetRandomNeighbor() {
		Ref.Call(this, "GetRandomNeighbor", null);
		Ref.Return(neighbors);
		return neighbors;
		
	}
	/**
	 * Visszaadja, hogy az aszteroida napközelben van-e.
	 * @return true, ha napközelben van, egyébként false
	 */
	private boolean IsNearSun() {
		Ref.Call(this, "IsNearSun", null);
		Boolean in = Ref.RequestBool("Napközelben van?");
		Ref.Return("result",in);
		return in;
	}
	
	/**
	 * 
	 * @param travelable
	 * @return true, ha a választott cél szomszéd, egyébként false
	 */
	public boolean IsNeighboor(Travelable travelable) {
		Ref.Call(this, "IsNeighboor", travelable);
		
		if(travelable == neighbors)
		{
			Ref.Return("result", "True");
			return true;
		}
		Ref.Return("result", "False");
		return false;
	}
	
	/**
	 * Kiveszi és odaadja a tárolt resource-t az inventory-nak
	 * @return resource, ha az aszteroida nem üres, egyébként null
	 */
	public Resource MineResource() {
		Ref.Call(this, "MineResource", null);
		Resource mat = this.RemoveResource();
		if(mat!=null) {
			Resource minedMaterial = new Coal(); 
			Ref.Created(minedMaterial, "minedMaterial");
			Ref.Return(minedMaterial);
			return minedMaterial;
		}
		Ref.Return(Ref.nullObject);
		return null;
	}
	
	/**
	 * Elfogad egy egységet (unit-ot) az aszteroidán és ott tárolja.
	 */
	public void ReceiveUnit(Unit unit) 
	{
		Ref.Call(this, "ReceiveUnit", unit);
		AddUnit(unit);
		
		unit.SetAsteroid(this);
		
		Ref.Return();
	}
	/**
	 *  Eltávolít egy réteget az aszteroidáról és ennek sikerességét visszaadja. Ha már nincs több ilyen réteg, akkor nem történik változás.
	 * @return true, ha sikerült eltávolítani egy réteget, false ha nem sikerült. 
	  */
	public boolean RemoveLayer() {
		Ref.Call(this, "RemoveLayer", null);
		Boolean result = Ref.RequestBool("Lehet fúrni?");
		Ref.Return("result",result);
		return result;
	}
	/**
	 * Eltávolítja a nyersanyagot az aszteroidából, ez akkor fontos, ha például a jég elszublimál, teljesen eltűnik és senki nem kaphatja meg.
	 * @return 
	 */
	public Resource RemoveResource() {
		Ref.Call(this, "RemoveResource", null);
		Boolean result = Ref.RequestBool("Van resource?");
		if(result) {
			Coal res = new Coal();
			Ref.Created(res, "removedMaterial");
			Ref.Return(res);
			return res;
		}
		Ref.Return(Ref.nullObject);
		return null;
	}
	/**
	 * eltávolít egy unitot az aszteroidáról
	 * @param unit
	 */
	public void RemoveUnit(Unit unit) {
		Ref.Call(this, "RemoveUnit", unit);
		Ref.Return();
	}
	/**
	 * Hozzákapcsol egy portált az aszteroidához és visszaadja, hogy ez sikeres volt-e, mert csak 1 portál tartozhat hozzá.
	 * @param portal
	 */
	public void SetPortal(Portal portal) {
		Ref.Call(this, "SetPortal", portal);
		Ref.Return();
	}
	
	public void SetResource(Resource resource)
	{
		Ref.Call(this, "SetResource", resource);
		this.resource = resource;
		resource.asteroid = this;
		Ref.Return();
	}
	/**
	 * Egy függvény, amivel lejátszódik a napkitörés effektus, ellenőrzi hogy megvan a feltételei annak, hogy elbújhassanak ott. Ha nem, akkor megöl mindenkit, aki rajta tartózkodott.
	 */
	public void Sunstorm() {
		Ref.Call(this, "Sunstorm", null);
		if(this.GetLayers()>0||resource!=null) {
			//TODO a szekvencián settler van!
			this.units.Die();
		}
		Ref.Return();
	}
}
