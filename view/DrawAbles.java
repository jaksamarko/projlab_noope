package view;

import java.util.ArrayList;

import model.*;

public class DrawAbles
{
	public static DrawAbles getInstance()
	{
		NullReset();
		return instance;
	}
	private static void Reset()
	{
		instance = new DrawAbles();
	}
	private static void NullReset()
	{
		if(instance == null)
			Reset();
	}
	
	public static Settler unitToSettler(Unit unit){
		for(Settler s : getInstance().settlers) {
			if(s==unit)
				return s;
		}
		return null;
	}

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
	
	private static DrawAbles instance = null;
	
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
	
	private DrawAbles()
	{
		asteroids = new ArrayList<Asteroid>();
		portals = new ArrayList<Portal>();
		settlers = new ArrayList<Settler>();
		robots = new ArrayList<Robot>();
		ufos = new ArrayList<Ufo>();
	}
	
	public static Asteroid getAsteroid(int ID) 
	{
		NullReset(); 
		for(Asteroid a: instance.asteroids)
			if(a.GetID() == ID)
				return a;
		return null;
	}
	
	public static Portal getPortal(int ID) 
	{
		NullReset(); 
		for(Portal p: instance.portals)
			if(p.GetID() == ID)
				return p;
		return null;
	}
}
