package reflection;

import model.*;

public class TestPrograms {
	private static void printInit() {
		System.out.println("-----INIT-----");
	}
	private static void printInteraction() {
		System.out.println("-----INTERACTION-----");
	}
	// 1.
	public static void SettlerTravelAsteroid()
	{		
		//printInit();
		//Init
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		Coal aResource = new Coal();
		Ref.Created(aResource, "aResource");
		
		asteroid.SetResource(aResource);
		
		Asteroid target = new Asteroid();
		Ref.Created(target, "target");
		
		Coal tResource = new Coal();
		Ref.Created(tResource, "tResource");
		
		target.SetResource(tResource);
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		asteroid.ReceiveUnit(settler);
		
		asteroid.addNeighbor(target);
		
		//printInteraction();
		//Interaction
		settler.Move(target);
		
		Ref.Return();
	}
	// 2.
	public static void SettlerTravelPortal()
	{
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		Coal aResource = new Coal();
		Ref.Created(aResource, "aResource");
		
		asteroid.SetResource(aResource);
		
		Portal target = new Portal();
		Ref.Created(target, "target");
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		asteroid.ReceiveUnit(settler);
		
		asteroid.addNeighbor(target);
		
		Portal pair = new Portal();
		Ref.Created(pair, "pair");
		
		target.SetPair(pair);
		
		Asteroid pairAsteroid = new Asteroid();
		Ref.Created(pairAsteroid, "pairAsteroid");
		
		Coal tResource = new Coal();
		Ref.Created(tResource, "tResource");
		
		pairAsteroid.SetResource(tResource);
		
		pair.SetAsteroid(pairAsteroid);
		
		//Interaction
		
		settler.Move(target);
		
		Ref.Return();
	}
	// 3.
	public static void SettlerDrill()
	{
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		Coal aResource = new Coal();
		Ref.Created(aResource, "aResource");
		
		asteroid.SetResource(aResource);
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		asteroid.ReceiveUnit(settler);
		
		//Interaction
		
		settler.Drill();
		Ref.Return();
	}
	// 4.
	public static void SettlerMine()
	{
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		asteroid.ReceiveUnit(settler);
		
		Coal aResource = new Coal();
		Ref.Created(aResource, "aResource");
		
		asteroid.SetResource(aResource);
		
		//Interaction
		
		settler.Mine();
	}
	// 5.
	public static void SettlerPutBack()
	{
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		
		Inventory inventory = new Inventory();
		Ref.Created(inventory, "inventory");
		
		inventory.InsertItem(Material.Coal);
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		asteroid.ReceiveUnit(settler);
		
		//Interaction
		
		settler.PutResourceBack(Material.Coal);
	}
	// 6.
	public static void SettlerBuildPortal()
	{
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		
		Inventory inventory = new Inventory();
		Ref.Created(inventory, "inventory");
		
		settler.SetInventory(inventory);
		//Interaction
		settler.CreatePortal();
	}
	// 7.
	public static void SettlerPlacePortal()
	{
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		asteroid.ReceiveUnit(settler);
		
		Inventory inventory = new Inventory();
		Ref.Created(inventory, "inventory");
		settler.SetInventory(inventory);
		
		//Interaction
		settler.PlacePortal();
	}
	// 8.
	public static void SettlerBuildAndPlaceRobot()
	{
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		Resource resource = new Coal();
		Ref.Created(resource, "resource");
		
		asteroid.SetResource(resource);
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		asteroid.ReceiveUnit(settler);
		
		Inventory inventory = new Inventory();
		Ref.Created(inventory, "inventory");
		settler.SetInventory(inventory);
		
		settler.CreateRobot();
	}
	// 9.
	public static void RobotWorkAsteroid()
	{
		
	}
	// 10.
	public static void RobotWorkPortal()
	{
		
	}
	// 11.
	public static void Sunstorm()
	{
		
	}
	// 12.
	public static void ExposeCoal()
	{
		
	}
	// 13.
	public static void ExposeIce()
	{
		
	}
	// 14.
	public static void ExposeUraniumSettler()
	{
		
	}
	// 15.
	public static void ExposeUraniumRobot()
	{
		
	}
}
