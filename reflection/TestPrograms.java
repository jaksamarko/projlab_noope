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
	}
	// 4.
	public static void SettlerMine()
	{
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		
		Inventory inventory = new Inventory();
		Ref.Created(inventory, "inventory");
		settler.SetInventory(inventory);
		
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
		Ref.Created(Material.Coal, "material");
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		
		Inventory inventory = new Inventory();
		Ref.Created(inventory, "inventory");
		settler.SetInventory(inventory);
		
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
		//init begin
		//printInit();
		
		Game game = new Game();
		Ref.Created(game, "game");

		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		Asteroid neighbor = new Asteroid();
		Ref.Created(neighbor, "neighbor");
		
		asteroid.addNeighbor(neighbor);
		
		Robot robot = new Robot();
		Ref.Created(robot, "robot");

		game.SetRobots(robot);
		asteroid.ReceiveUnit(robot);

		
		//interact begin
		//printInteraction();
		
		game.AllRobotsWork();
		
	}
	// 10.
	public static void RobotWorkPortal()
	{
		Game game = new Game();
		Ref.Created(game, "game");
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		Asteroid pairAsteroid = new Asteroid();
		Ref.Created(pairAsteroid, "pairAsteroid");
		
		Portal portal = new Portal();
		Ref.Created(portal, "portal");
		portal.SetAsteroid(asteroid);
		
		Portal pair = new Portal();
		Ref.Created(pair, "pair");
		portal.SetPair(pair);
		pair.SetAsteroid(pairAsteroid);
		
		//asteroid.SetPortal(portal);
		asteroid.addNeighbor(portal);
		
		Robot robot = new Robot();
		Ref.Created(robot, "robot");

		game.SetRobots(robot);
		asteroid.ReceiveUnit(robot);
		
		game.AllRobotsWork();
	}
	// 11.
	public static void Sunstorm()
	{
		Game game = new Game();
		Ref.Created(game, "game");
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		asteroid.ReceiveUnit(settler);
		
		game.SetAsteroids(asteroid);
		
		game.CreateSunstorm();
		
	}
	// 12.
	public static void ExposeCoal()
	{
		Game game = new Game();
		Ref.Created(game, "game");
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		game.SetAsteroids(asteroid);
		
		Resource resource = new Coal();
		Ref.Created(resource, "resource");
		
		asteroid.SetResource(resource);
		
		game.EndTurnAsteroidEffect();
	}
	// 13.
	public static void ExposeIce()
	{
		Game game = new Game();
		Ref.Created(game, "game");
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		game.SetAsteroids(asteroid);
		
		Resource resource = new Ice();
		Ref.Created(resource, "resource");
		
		asteroid.SetResource(resource);
		
		game.EndTurnAsteroidEffect();
	}
	// 14.
	public static void ExposeUraniumSettler()
	{
		//init begin
		printInit();
				
		Game game = new Game();
		Ref.Created(game, "game");

		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		game.SetAsteroids(asteroid);
				
		Uranium aResource = new Uranium();
		Ref.Created(aResource, "aResource");
				
		asteroid.SetResource(aResource);
				
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		asteroid.ReceiveUnit(settler);
				
		//interact begin
		printInteraction();
						
		game.EndTurnAsteroidEffect();
		
	}
	// 15.
	public static void ExposeUraniumRobot()
	{
		//init begin
		printInit();
		
		Game game = new Game();
		Ref.Created(game, "game");

		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		game.SetAsteroids(asteroid);
		
		
		Asteroid neighbor = new Asteroid();
		Ref.Created(neighbor, "neighbor");
		
		asteroid.addNeighbor(neighbor);
		
		Uranium aResource = new Uranium();
		Ref.Created(aResource, "aResource");
		
		asteroid.SetResource(aResource);
		
		Robot robot = new Robot();
		Ref.Created(robot, "robot");
		
		asteroid.ReceiveUnit(robot);
		//interact begin
		printInteraction();
		
		game.EndTurnAsteroidEffect();
		
	}
}
