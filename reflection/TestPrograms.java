package reflection;

import model.*;

public class TestPrograms {
	// 1.
	public static void SettlerTravelAsteroid()
	{		
		System.out.println("-----INIT-----");
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
		
		System.out.println("-----INTERACTION-----");
		
		settler.Move(target);
		
		System.out.println("!!!!!!!!!!!!!!!!TODO: Befjezni!!!!!!!!!!!!!!!!");
		
		Ref.Return();
	}
	// 2.
	public static void SettlerTravelPortal()
	{
		
	}
	// 3.
	public static void SettlerDrill()
	{
		
	}
	// 4.
	public static void SettlerMine()
	{
		
	}
	// 5.
	public static void SettlerPutBack()
	{
		
	}
	// 6.
	public static void SettlerBuildPortal()
	{
		
	}
	// 7.
	public static void SettlerPlacePortal()
	{
		
	}
	// 8.
	public static void SettlerBuildAndPlaceRobot()
	{
		
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
