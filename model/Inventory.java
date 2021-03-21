package model;

import reflection.Ref;

/**
 * 
 * Ez az osztály felel a nyersanyagok tárolásáért, telepesenként külön-külön.
 *
 */

public class Inventory {
	/**
	 * mennyiben van elég az adott nyersanyagból,
	 * eltölti az inventory tartalmát maximum 2 db portál mennyiségére
	 *  és ennek a készítésnek a sikerességét visszaadja.
	 * @return true, ha sikeres volt a gyártás, egyébként false
	 */
	public boolean CraftPortal() {
		Ref.Call(this, "CraftPortal", null);
		Boolean ret = Ref.RequestBool("Tud portált készíteni?");
		Ref.Return("isCrafted",ret);
		return ret;
	}
	
	/**
	 * Amennyiben van elég nyersanyag, létrehoz egy Robot-ot és visszaadja, vagy null-t ha nem sikerült.
	 * @return robot, ha sikeres volt a gyártás, egyébként null
	 */
	public Robot CraftRobot() {
		Ref.Call(this, "CraftRobot", null);
		Boolean ret = Ref.RequestBool("Tud robotot készíteni?");
		if(ret) {
			Robot r = new Robot();
			Ref.Created(r, "newRobot");
			Ref.Return(r);
			return r;
		}
		
		Ref.Return(Ref.nullObject);
		return null;
	}
	
	/**
	 *  Csökkentjük a számát az adott nyersanyagnak és visszakapjuk ezt, a material indexeléshez kell.
	 * @param material
	 * @return resource, egyébként null
	 */
	public Resource GetItem(Material material) {
		Ref.Call(this, "GetItem", material);
		Boolean ret = Ref.RequestBool("Van e szén?");
		if(ret) {
			Resource sentResource = new Coal();
			Ref.Created(sentResource, "sentResource");
			Ref.Return(sentResource);
			return sentResource;
		}
		Ref.Return(Ref.nullObject);
		return null;
	}
	
	/**
	 * Amennyiben van az inventory-ban portál (tehát a mennyisége nagyobb, mint 0) akkor visszaad egy Portal objektumot, vagy null-t, ha nem volt.
	 * @return portal, egyéblént null
	 */
	public Portal GetPortal() {
		Ref.Call(this, "GetPortal", null);
		Boolean ret = Ref.RequestBool("Van-e portál?");
		if(ret) {
			Portal portal = new Portal();
			Ref.Created(portal, "newPortal");
			Ref.Return(portal);
			return portal;
		}
		Ref.Return(Ref.nullObject);
		return null;
	}
	
	/**
	 * Felveszünk egy item-et a tárolóba, erről kapunk egy visszaigazolást, hogy sikeres volt-e. Az enumeration itt is csak indexeléshez kell.
	 * @param material
	 */
	public void InsertItem(Material material) {
		Ref.Call(this, "InsertItem", material);
		Ref.Return();
	}
	
	/**
	 *  Kitörlünk egy nyersanyagot a tárolóból, más szóval elhasználunk egyet, a mennyiségét az inventoryban csökkentjük.
	 * @param material
	 */
	public void RemoveItem(Material material) {
		Ref.Call(this, "RemoveItem", material);
		Ref.Return();
	}
}
