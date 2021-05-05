package model;

/**
 * A játék során segítse a telepesek közlekedését és az adott kapu párjához tartozó aszteroidára továbbítsa a játékost.
 * Illetve az õ dolga összekapcsolnia magát más kapuval, ha van már a játékban párosítatlan kapu.
 */
@SuppressWarnings("serial")
public class Portal extends ID implements Travelable, java.io.Serializable
{
	public Portal GetPair() {return pair;}
	public void SetPair(Portal other) {pair = other; other.pair = this; active = true; other.active = true;}
	
	private static Portal unpairedPortal = null;
	private Asteroid asteroid;
	private Portal pair;
	private boolean active;
	private boolean crazy;
	
	public Portal(int ID)
	{
		super(ID);
		pair = null;
		asteroid = null;
		crazy = false;
		active = false;
		ObjectStore.add(this);
	}
	
	/**
	 * Erõforrásokat felszabadító függvény, akkor hívódik meg, mikor az aszteroida megsemmisül, ezzel együtt a portálja is, errõl értesíteni kell a portál párját, mert az is felrobban.
	 */
	public void Destroyed() {
		if(asteroid != null)
			asteroid.RemovePortal();
		if(pair != null)
			pair.ProxyDestroy();
		ObjectStore.remove(this);
	}
	
	public void ProxyDestroy()
	{
		if(asteroid != null)
		{
			asteroid.RemovePortal();
		}
		ObjectStore.remove(this);
	}
	
	public void RemovePair()
	{
		if(unpairedPortal == null)
		{
			unpairedPortal = this;
			pair = null;
			return;
		}
		pair = unpairedPortal;
		unpairedPortal = null;
	}
	
	/**
	 * Fogad egy unit-ot egy másik portáltól.
	 * @param unit A fogadott unit.
	 */
	public void ReceiveUnit(Unit unit)
	{
		if(pair == null)
			asteroid.ReceiveUnit(unit);
		else
			pair.SendUnitToPair(unit);
	}
	
	/**
	 * Egy unit használatba veszi a portált.
	 * Arról, hogy ez lehetséges-e (van-e párja a portálnak) egy boolean ad igazolást,ekkor az utazás meg is fog történni.
	 * @param unit A unit ami a portált használja.
	 */
	public void SendUnitToPair(Unit unit) {
		asteroid.ReceiveUnit(unit);
	}
	
	/**
	 * Beállítja az aszeroidát 
	 * @param asteroid Az aszteroida amihez a kapu tartozik, Bekapcsolódik így, ha van pár nélküli portál ahoz kapcsolódik
	 */
	public void SetAsteroid(Asteroid asteroid)
	{
		this.asteroid=asteroid;
		if(active == false)
		{
			if(unpairedPortal == null)
			{
				unpairedPortal = this;
				return;
			}
			pair = unpairedPortal;
			unpairedPortal = null;
			active = true;
		}
	}
	
	public void EndTurnEffect()
	{
		if(crazy)
		{
			Travelable neighbor = asteroid.GetRandomNeighbor();
			if(neighbor.ReceivePortal(this))
			{
				asteroid.RemovePortal();
			}
		}
	}

	@Override
	public void Sunstorm()
	{
		crazy = true;
	}

	@Override
	public boolean ReceivePortal(Portal portal)
	{
		return false;
	}
	
	public Asteroid GetAsteroid()
	{
		return this.asteroid;
	}
}
