package model;

import reflection.*;

/**
 * A játékban azok az egységek, amiket a játékosok irányítanak. 
 * Körönként egy lépést hajthatnak végre, amely lehet craftolás, mozgás, fúrás, bányászat, nyersanyag visszahelyezése. 
 * Meghalnak, ha nincsenek elbújva napviharkor, valamint ha egy felrobbanó aszteroidán tartózkodnak.
 */
public class Settler extends Unit {
	private Inventory inventory;
	
	public Settler()
	{
		
	}
	
	/**
	 * Ha van elegendõ nyersanyag náluk, portál-pár craftolása, mely a saját inventoryjába kerül.
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
	 * Ha van elegendõ nyersanyag náluk, robot craftolása, mely az adott aszteroidára kerül.
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
	 * Bányászat mûvelete, amelyet azon az aszteroidán végezhet el, amelyiken éppen tartózkodik. Ha sikeres, az inventoryjába kerül a nyersanyag.
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
	 * Az inventoryjából egy portál lehelyezése az adott aszteroidára, amennyiben ez lehetséges. Ha már volt páratlan portál, összekapcsolódik a kettõ.
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
	 * Amennyiben üres az aszteroida magja, amin tartózkodik, visszahelyezhet egy darab nyersanyagot az aszteroidába. 
	 * Ekkor ez a nyersanyag eltûnik az inventoryjából.
	 * @param material A nyersanyag, amit visszahelyezünk.
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
	 * Ha az aszteroida, amin épp tartózkodik, felrobban, a telepes meghal.
	 */
	public void Exploded()
	{
		Ref.Call(this, "Exploded", null);
		this.Die();
		Ref.Return();
	}
	
	/**
	 * Beállítja a setterhez tartozó inventoryt.
	 * @param inventory Az inventory, amit a settlerhez rendelünk.
	 */
	public void SetInventory(Inventory inventory) {
		Ref.Call(this, "SetInventory", inventory);
		this.inventory=inventory;
		Ref.Return();
	}
}
