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
		//init begin
		printInit();
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");

		Asteroid target = new Asteroid();
		Ref.Created(target, "target");
		
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		asteroid.ReceiveUnit(settler);
		
		asteroid.addNeighbor(target);
		
		//interact begin
		printInteraction();
		
		settler.Move(target);
	}
	// 2.
	public static void SettlerTravelPortal()
	{
		//init begin
		printInit();
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
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
		
		pair.SetAsteroid(pairAsteroid);
		
		//interact begin
		printInteraction();
		
		settler.Move(target);
	}
	// 3.
	public static void SettlerDrill()
	{
		//init begin
		printInit();
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		Coal aResource = new Coal();
		Ref.Created(aResource, "aResource");
		
		asteroid.SetResource(aResource);
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		asteroid.ReceiveUnit(settler);
		
		//interact begin
		printInteraction();
		
		settler.Drill();
	}

	public static void SettlerMine()
	{
		//init begin
		printInit();
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		
		Inventory inventory = new Inventory();
		Ref.Created(inventory, "inventory");
		settler.SetInventory(inventory);
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		asteroid.ReceiveUnit(settler);
		
		//interact begin
		printInteraction();
		
		settler.Mine();
	}
	// 5.
	public static void SettlerPutBack()
	{
		//init begin
		printInit();
		
		Ref.Created(Material.Coal, "material");
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		
		Inventory inventory = new Inventory();
		Ref.Created(inventory, "inventory");
		settler.SetInventory(inventory);
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		asteroid.ReceiveUnit(settler);
		
		//interact begin
		printInteraction();
		
		settler.PutResourceBack(Material.Coal);
	}
	// 6.
	public static void SettlerBuildPortal()
	{
		//init begin
		printInit();
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		
		Inventory inventory = new Inventory();
		Ref.Created(inventory, "inventory");
		
		settler.SetInventory(inventory);

		//interact begin
		printInteraction();
		
		settler.CreatePortal();
	}
	// 7.
	public static void SettlerPlacePortal()
	{
		//init begin
		printInit();
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		asteroid.ReceiveUnit(settler);
		
		Inventory inventory = new Inventory();
		Ref.Created(inventory, "inventory");
		settler.SetInventory(inventory);
		
		//interact begin
		printInteraction();
		
		settler.PlacePortal();
	}
	// 8.
	public static void SettlerBuildAndPlaceRobot()
	{
		//init begin
		printInit();
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		asteroid.ReceiveUnit(settler);
		
		Inventory inventory = new Inventory();
		Ref.Created(inventory, "inventory");
		settler.SetInventory(inventory);
		
		//interact begin
		printInteraction();		
		
		settler.CreateRobot();
	}
	// 9.
	public static void RobotWorkAsteroid()
	{
		//init begin
		printInit();
		
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
		printInteraction();
		
		game.AllRobotsWork();
		
	}
	// 10.
	public static void RobotWorkPortal()
	{
		//init begin
		printInit();
		
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
		
		
		asteroid.addNeighbor(portal);
		
		Robot robot = new Robot();
		Ref.Created(robot, "robot");

		game.SetRobots(robot);
		asteroid.ReceiveUnit(robot);
		
		//interact begin
		printInteraction();	
		
		game.AllRobotsWork();
		
	}
	// 11.
	public static void Sunstorm()
	{
		//init begin
		printInit();
		
		Game game = new Game();
		Ref.Created(game, "game");
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		
		Settler settler = new Settler();
		Ref.Created(settler, "settler");
		asteroid.ReceiveUnit(settler);
		
		game.SetAsteroids(asteroid);
		
		//interact begin
		printInteraction();	
		
		game.CreateSunstorm();
		
	}
	// 12.
	public static void ExposeCoal()
	{
		
		//init begin
		printInit();
		
		Game game = new Game();
		Ref.Created(game, "game");
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		game.SetAsteroids(asteroid);
		
		Resource resource = new Coal();
		Ref.Created(resource, "resource");
		
		asteroid.SetResource(resource);
		
		//interact begin
		printInteraction();	
		
		game.EndTurnAsteroidEffect();
	}
	// 13.
	public static void ExposeIce()
	{
		
		//init begin
		printInit();
		
		Game game = new Game();
		Ref.Created(game, "game");
		
		Asteroid asteroid = new Asteroid();
		Ref.Created(asteroid, "asteroid");
		game.SetAsteroids(asteroid);
		
		Resource resource = new Ice();
		Ref.Created(resource, "resource");
		
		asteroid.SetResource(resource);
		
		//interact begin
		printInteraction();	
		
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
