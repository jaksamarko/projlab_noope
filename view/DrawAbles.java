package view;

import java.util.ArrayList;

import model.*;

public class DrawAbles
{
	public static DrawAbles getInstance()
	{
		if(instance == null)
			Reset();
		return instance;
	}
	private static void Reset()
	{
		instance = new DrawAbles();
	}
	
	public Settler unitToSettler(Unit unit){
		for(Settler s : settlers) {
			if(s==unit)
				return s;
		}
		return null;
	}

	public ArrayList<Settler> unitToSettler(ArrayList<Unit> units){
		
		return null;
	}
	
	private static DrawAbles instance = null;
	
	public static void add(Asteroid asteroid) {if(instance == null)Reset(); instance.asteroids.add(asteroid);}
	public static void add(Portal portal) {if(instance == null)Reset(); instance.portals.add(portal);}
	public static void add(Settler settler) {if(instance == null)Reset(); instance.settlers.add(settler);}
	public static void add(Robot robot) {if(instance == null)Reset(); instance.robots.add(robot);}
	public static void add(Ufo ufo) {if(instance == null)Reset(); instance.ufos.add(ufo);}
	public static void remove(Asteroid asteroid) {if(instance == null)Reset(); instance.asteroids.remove(asteroid);}
	public static void remove(Portal portal) {if(instance == null)Reset(); instance.portals.remove(portal);}
	public static void remove(Settler settler) {if(instance == null)Reset(); instance.settlers.remove(settler);}
	public static void remove(Robot robot) {if(instance == null)Reset(); instance.robots.remove(robot);}
	public static void remove(Ufo ufo) {if(instance == null)Reset(); instance.ufos.remove(ufo);}
	
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
}
