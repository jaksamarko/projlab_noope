package reflection;

import model.*;
public class TestPrograms2 {
	private static void printInit() {
		System.out.println("-----INIT-----");
	}
	private static void printInteraction() {
		System.out.println("-----INTERACTION-----");
	}
	//9
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
}
