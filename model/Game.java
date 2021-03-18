package model;

import reflection.Ref;

public class Game {
	public Robot robots;
	private Asteroid asteroids;
	private Settler settlers;
	
	public void SetRobots(Robot robot) {
		Ref.Call(this, "SetRobots", null);
		this.robots = robot;
		Ref.Return();
	}
	
	public void SetAsteroids(Asteroid asteroid) {
		Ref.Call(this, "SetAsteroids", null);
		this.asteroids = asteroid;
		Ref.Return();
	}
	
	public void AllRobotsWork() {
		Ref.Call(this, "AllRobotsWork", null);
		robots.Work();
		Ref.Return();
	}
	
	public void CreateSunstorm() {
		Ref.Call(this, "CreateSunstorm", null);
		asteroids.Sunstorm();
		Ref.Return();
	}
	
	public void EndTurnAsteroidEffect() {
		Ref.Call(this, "EndTurnAsteroidEffect", null);
		asteroids.Exposure();
		Ref.Return();
	}
}
