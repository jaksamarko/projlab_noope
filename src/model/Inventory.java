package model;

import java.util.ArrayList;
/**
 * 
 * Ez az osztály felel a nyersanyagok tárolásáért, telepesenként külön-külön.
 *
 */

@SuppressWarnings("serial")
public class Inventory implements java.io.Serializable
{
	private static transient int PortalStorageCapacity = 3; 
	
	public ResourceBox<Coal> GetCoalBox() {return coalBox;}
	public ResourceBox<Iron> GetIronBox() {return ironBox;}
	public ResourceBox<Ice> GetIceBox() {return iceBox;}
	public ResourceBox<Uranium> GetUraniumBox() {return uraniumBox;}
	public int GetPortalCount() {return portals.size();}
	
	private ResourceBox<Coal> coalBox;
	private ResourceBox<Iron> ironBox;
	private ResourceBox<Ice> iceBox;
	private ResourceBox<Uranium> uraniumBox;
	private ArrayList<Portal> portals;
	
	public int[] getCounts()
	{
		int[] array = new int[4];
		array[0] = coalBox.GetCount();
		array[1] = iceBox.GetCount();
		array[2] = ironBox.GetCount();
		array[3] = uraniumBox.GetCount();
		return array;
	}
	
	public Inventory()
	{
		coalBox = new ResourceBox<Coal>(); 
		ironBox = new ResourceBox<Iron>(); 
		iceBox = new ResourceBox<Ice>(); 
		uraniumBox = new ResourceBox<Uranium>(); 
		portals = new ArrayList<Portal>();
	}
	
	/**
	 * mennyiben van elég az adott nyersanyagból,
	 * eltölti az inventory tartalmát maximum 2 db portál mennyiségére
	 *  és ennek a készítésnek a sikerességét visszaadja.
	 * @return true, ha sikeres volt a gyártás, egyébként false
	 */
	public boolean CraftPortal() {
		if(portals.size()<PortalStorageCapacity && ironBox.Has(2) && iceBox.Has(1) && uraniumBox.Has(1))
		{
			ironBox.UseUp(2); iceBox.UseUp(1); uraniumBox.UseUp(1);
			portals.add(new Portal(ObjectStore.getUniquePortalID()));
			return true;
		}
		return false;
	}
	
	/**
	 * Amennyiben van elég nyersanyag, létrehoz egy Robot-ot és visszaadja, vagy null-t ha nem sikerült.
	 * @return robot, ha sikeres volt a gyártás, egyébként null
	 */
	public Robot CraftRobot() {
		if(coalBox.Has(1) && ironBox.Has(1) && uraniumBox.Has(1))
		{
			coalBox.UseUp(1); ironBox.UseUp(1); uraniumBox.UseUp(1);
			return new Robot(ObjectStore.getUniqueRobotID(),null);
		}
		return null;
	}
	
	/**
	 *  Csökkentjük a számát az adott nyersanyagnak és visszakapjuk ezt, a material indexeléshez kell.
	 * @param material
	 * @return resource, egyébként null
	 */
	public Resource GetItem(Material material) {
		if(material == Material.Coal)
			return coalBox.Get();
		if(material == Material.Iron)
			return ironBox.Get();
		if(material == Material.Ice)
			return iceBox.Get();
		if(material == Material.Uranium)
			return uraniumBox.Get();
		return null;
	}
	
	/**
	 * Amennyiben van az inventory-ban portál (tehát a mennyisége nagyobb, mint 0) akkor visszaad egy Portal objektumot, vagy null-t, ha nem volt.
	 * @return portal, egyéblént null
	 */
	public Portal GetPortal() {
		if(portals.size() == 0)
			return null;
		Portal re = portals.get(portals.size()-1);
		portals.remove(re);
		return re;
	}
	
	/**
	 * Felveszünk egy item-et a tárolóba, erről kapunk egy visszaigazolást, hogy sikeres volt-e. Az enumeration itt is csak indexeléshez kell.
	 * @param material
	 */
	public void InsertItem(Coal coal) {coalBox.Add(coal);}
	public void InsertItem(Iron iron) {ironBox.Add(iron);}
	public void InsertItem(Ice ice) {iceBox.Add(ice);}
	public void InsertItem(Uranium uranium) {uraniumBox.Add(uranium);}
	
	/**
	 *  Kitörlünk egy nyersanyagot a tárolóból, más szóval elhasználunk egyet, a mennyiségét az inventoryban csökkentjük.
	 * @param material
	 */
	public void RemoveItem(Material material)
	{
		if(material == Material.Coal)
			coalBox.Remove();
		if(material == Material.Iron)
			ironBox.Remove();
		if(material == Material.Ice)
			iceBox.Remove();
		if(material == Material.Uranium)
			uraniumBox.Remove();
	}
	
	public void InsertPortal() {
		this.portals.add(new Portal(ObjectStore.getUniquePortalID()));
	}
	
	public void Die()
	{
		for(Portal p: portals)
		{
			p.Destroyed();
		}
	}
}
