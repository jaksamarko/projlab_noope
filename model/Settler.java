package model;

import reflection.*;

/**
 * A j�t�kban azok az egys�gek, amiket a j�t�kosok ir�ny�tanak. 
 * K�r�nk�nt egy l�p�st hajthatnak v�gre, amely lehet craftol�s, mozg�s, f�r�s, b�ny�szat, nyersanyag visszahelyez�se. 
 * Meghalnak, ha nincsenek elb�jva napviharkor, valamint ha egy felrobban� aszteroid�n tart�zkodnak.
 */
public class Settler extends Unit {
	private Inventory inventory;
	
	public Settler()
	{
		
	}
	
	/**
	 * Ha van elegend� nyersanyag n�luk, port�l-p�r craftol�sa, mely a saj�t inventoryj�ba ker�l.
	 */
	public void CreatePortal() {
		Ref.Call(this, "CreatePortal", null);
		Boolean isCrafted = inventory.CraftPortal();
		if(isCrafted) {
			this.MakeStepDone();
		}
		Ref.Return();
	}
	
	/**
	 * Ha van elegend� nyersanyag n�luk, robot craftol�sa, mely az adott aszteroid�ra ker�l.
	 */
	public void CreateRobot() {
		Ref.Call(this, "CreateRobot", null);
		Robot newRobot = inventory.CraftRobot();
		if(newRobot!=null) {
			asteroid.ReceiveUnit(newRobot);
			this.MakeStepDone();
		}
		
		Ref.Return();
	}
	
	/**
	 * B�ny�szat m�velete, amelyet azon az aszteroid�n v�gezhet el, amelyiken �ppen tart�zkodik. Ha sikeres, az inventoryj�ba ker�l a nyersanyag.
	 */
	public void Mine() {
		Ref.Call(this, "Mine", null);
		Resource minedMaterial = asteroid.MineResource();
		if(minedMaterial!=null) {
			this.MakeStepDone();
			minedMaterial.PickedUp(inventory);
		}
		Ref.Return();
	}
	
	/**
	 * Az inventoryj�b�l egy port�l lehelyez�se az adott aszteroid�ra, amennyiben ez lehets�ges. Ha m�r volt p�ratlan port�l, �sszekapcsol�dik a kett�.
	 */
	public void PlacePortal() {
		Ref.Call(this, "PlacePortal", null);
		Boolean built = asteroid.BuildPortal(inventory);
		if(built) {
			this.MakeStepDone();
		}
		Ref.Return();
	}
	
	/**
	 * Amennyiben �res az aszteroida magja, amin tart�zkodik, visszahelyezhet egy darab nyersanyagot az aszteroid�ba. 
	 * Ekkor ez a nyersanyag elt�nik az inventoryj�b�l.
	 * @param material A nyersanyag, amit visszahelyez�nk.
	 */
	public void PutResourceBack(Material material) {
		Ref.Call(this, "PutResourceBack", material);
		Resource sentResource = inventory.GetItem(material);
		if(sentResource == null)
		{
			Ref.Return();
			return;
		}
		Boolean taken = asteroid.AcceptResource(sentResource);
		if(taken) {
			inventory.RemoveItem(material);
			this.MakeStepDone();
		}
		Ref.Return();
	}
	
	/**
	 * Ha az aszteroida, amin �pp tart�zkodik, felrobban, a telepes meghal.
	 */
	public void Exploded()
	{
		Ref.Call(this, "Exploded", null);
		this.Die();
		Ref.Return();
	}
	
	/**
	 * Be�ll�tja a setterhez tartoz� inventoryt.
	 * @param inventory Az inventory, amit a settlerhez rendel�nk.
	 */
	public void SetInventory(Inventory inventory) {
		Ref.Call(this, "SetInventory", inventory);
		this.inventory=inventory;
		Ref.Return();
	}
}
