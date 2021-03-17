package model;

import reflection.Ref;

public class Game {
	public Robot robots;
	private Asteroid asteroids;
	private Settler settlers;
	public void AllRobotsWork() {
		Ref.Call(this, "AllRobotsWork", null);
	}
	
	public void CreateSunstorm() {
		Ref.Call(this, "CreateSunstorm", null);
	}
	
	public void EndTurnAsteroidEffect() {
		Ref.Call(this, "EndTurnAsteroidEffect", null);
	}
}
