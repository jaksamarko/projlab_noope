package model;

/**
 * A játékban azok az egységek, amiket a játékosok irányítanak. 
 * Körönként egy lépést hajthatnak végre, amely lehet craftolás, mozgás, fúrás, bányászat, nyersanyag visszahelyezése. 
 * Meghalnak, ha nincsenek elbújva napviharkor, valamint ha egy felrobbanó aszteroidán tartózkodnak.
 */
public class Settler extends Unit
{
	private Inventory inventory;
	
	public Settler(Asteroid _asteroid)
	{
		super(_asteroid);
		inventory = new Inventory();
	}
	
	/**
	 * Ha van elegendõ nyersanyag náluk, portál-pár craftolása, mely a saját inventoryjába kerül.
	 */
	public void CreatePortal()
	{
		if(inventory.CraftPortal())
			this.MakeStepDone();
	}
	
	/**
	 * Ha van elegendõ nyersanyag náluk, robot craftolása, mely az adott aszteroidára kerül.
	 */
	public void CreateRobot()
	{
		Robot newRobot = inventory.CraftRobot();
		if(newRobot!=null)
		{
			asteroid.ReceiveUnit(newRobot);
			MakeStepDone();
		}
	}
	
	/**
	 * Bányászat mûvelete, amelyet azon az aszteroidán végezhet el, amelyiken éppen tartózkodik. Ha sikeres, az inventoryjába kerül a nyersanyag.
	 */
	public void Mine()
	{
		Resource minedMaterial = asteroid.MineResource();
		if(minedMaterial!=null)
		{
			this.MakeStepDone();
			minedMaterial.PickedUp(inventory);
		}
	}
	
	/**
	 * Az inventoryjából egy portál lehelyezése az adott aszteroidára, amennyiben ez lehetséges. Ha már volt páratlan portál, összekapcsolódik a kettõ.
	 */
	public void PlacePortal()
	{
		if(asteroid.BuildPortal(inventory)) 
			this.MakeStepDone();
	}
	
	/**
	 * Amennyiben üres az aszteroida magja, amin tartózkodik, visszahelyezhet egy darab nyersanyagot az aszteroidába. 
	 * Ekkor ez a nyersanyag eltûnik az inventoryjából.
	 * @param material A nyersanyag, amit visszahelyezünk.
	 */
	public void PutResourceBack(Material material)
	{
		Resource sentResource = inventory.GetItem(material);
		if(sentResource == null)
			return;
		if(asteroid.AcceptResource(sentResource))
		{
			inventory.RemoveItem(material);
			MakeStepDone();
		}
	}
	
	/**
	 * Ha az aszteroida, amin épp tartózkodik, felrobban, a telepes meghal.
	 */
	public void Exploded()
	{
		Die();
	}
}
