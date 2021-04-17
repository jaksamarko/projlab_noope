package model;

import java.util.ArrayList;

import model.*;
/**
 * Játékbeli össze objektum itt össze van gyüjtve. Főkép kirajzolás miatt létezik
 * Singleton módon van megvalósítva
 */
public class ObjectStore
{
	/**
	 * Singleton objektum lekérése
	 */
	public static ObjectStore getInstance()
	{
		NullReset();
		return instance;
	}
	/**
	 * Singleton objektum törlése és újra létrehozása
	 */
	private static void Reset()
	{
		instance = new ObjectStore();
	}
	
	/**
	 * Egy metódus, ami létrehoz egy objektum-ot, ha még eddig nem volt.
	 */
	private static void NullReset()
	{
		if(instance == null)
			Reset();
	}
	
	/**
	 * Ha van egy referenciánk egy unitra. Akkor ezzel ellenőrizni tudjuk, hogy settler-e és ha igen akkor megkapjuk azt (kirajzoláshoz kell.)
	 */
	public static Settler unitToSettler(Unit unit){
		for(Settler s : getInstance().settlers) {
			if(s==unit)
				return s;
		}
		return null;
	}
	
	/**
	 * Ha van ArrayList-ünk unit-okra, akkor vissza ad egy listát, amely megadja azok kezül melyi settler (Kirajzoláshoz kell)
	 */

	public static ArrayList<Settler> unitToSettler(ArrayList<Unit> units){
		ArrayList<Settler> re = new ArrayList<Settler>();
		for(Unit u:units)
		{
			Settler s = unitToSettler(u);
			if(s != null)
				re.add(s);
		}
		return re;
	}
	
	private static ObjectStore instance = null;
	
	/**
	 * Getter-ek és remover-ek a tárolt objektum-okhoz
	 */
	public static void add(Asteroid asteroid) {NullReset(); instance.asteroids.add(asteroid);}
	public static void add(Portal portal) {NullReset(); instance.portals.add(portal);}
	public static void add(Settler settler) {NullReset(); instance.settlers.add(settler);}
	public static void add(Robot robot) {NullReset(); instance.robots.add(robot);}
	public static void add(Ufo ufo) {NullReset(); instance.ufos.add(ufo);}
	public static void remove(Asteroid asteroid) {NullReset(); instance.asteroids.remove(asteroid);}
	public static void remove(Portal portal) {NullReset(); instance.portals.remove(portal);}
	public static void remove(Settler settler) {NullReset(); instance.settlers.remove(settler);}
	public static void remove(Robot robot) {NullReset(); instance.robots.remove(robot);}
	public static void remove(Ufo ufo) {NullReset(); instance.ufos.remove(ufo);}
	
	/**
	 * Amikor a játékba új robot jön létre, akkor ezzel lehet biztosítani, hogy egydi ID-t kap
	 */
	public static int getUniqueRobotID()
	{
		boolean unique = false;
		int id = 1;
		while(unique == false)
		{
			unique = true;
			for(Robot r: getInstance().robots)
			{
				if(r.GetID() == id)
				{
					unique = false;
					id++;
					break;
				}
			}
		}
		return id;
	}
	
	/**
	 * Amikor a játékba új portal jön létre, akkor ezzel lehet biztosítani, hogy egydi ID-t kap
	 */
	public static int getUniquePortalID()
	{
		boolean unique = false;
		int id = 1;
		while(unique == false)
		{
			unique = true;
			for(Portal p: getInstance().portals)
			{
				if(p.GetID() == id)
				{
					unique = false;
					id++;
					break;
				}
			}
		}
		return id;
	}
	
	public ArrayList<Asteroid> asteroids;
	public ArrayList<Portal> portals;
	public ArrayList<Settler> settlers;
	public ArrayList<Robot> robots;
	public ArrayList<Ufo> ufos;
	
	private ObjectStore()
	{
		asteroids = new ArrayList<Asteroid>();
		portals = new ArrayList<Portal>();
		settlers = new ArrayList<Settler>();
		robots = new ArrayList<Robot>();
		ufos = new ArrayList<Ufo>();
	}
	
	/**
	 * ID alapján meg lehet kapni, hogy van-e ilyen aszteroid
	 */
	public static Asteroid getAsteroid(int ID) 
	{
		NullReset(); 
		for(Asteroid a: instance.asteroids)
			if(a.GetID() == ID)
				return a;
		return null;
	}
	
	/**
	 * ID alapján meg lehet kapni, hogy van-e ilyen portal
	 */
	public static Portal getPortal(int ID) 
	{
		NullReset(); 
		for(Portal p: instance.portals)
			if(p.GetID() == ID)
				return p;
		return null;
	}
}
