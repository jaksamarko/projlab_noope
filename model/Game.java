package model;

import reflection.Ref;

/**
 * 
 * Ez az osztály felel a robotokért, valamint a játékban lévő nehezítő tényezőkért, 
 *úgy mint napvihar, aszteroidák naptól való távolság esetén történő felrobbanása.
 */

public class Game {
	public Robot robots;
	private Asteroid asteroids;
	private Settler settlers;
	
	public void SetRobots(Robot robot) {
		Ref.Call(this, "SetRobots", robot);
		this.robots = robot;
		Ref.Return();
	}
	
	public void SetAsteroids(Asteroid asteroid) {
		Ref.Call(this, "SetAsteroids", asteroid);
		this.asteroids = asteroid;
		Ref.Return();
	}
	
	/**
	 * A játék végigiterálja a robotokat, így az összes lép.
	 */
	
	public void AllRobotsWork() {
		Ref.Call(this, "AllRobotsWork", null);
		robots.Work();
		Ref.Return();
	}
	
	/**
	 * Napvihar generálása. Hatására meghalhatnak a nem elbújt állapotban lévő egységek.
	 */
	public void CreateSunstorm() {
		Ref.Call(this, "CreateSunstorm", null);
		asteroids.Sunstorm();
		Ref.Return();
	}
	/**
	 * A nap közelségéből adódó negatív tényezők által kiváltott hatások végbemenetele.
	 */
	public void EndTurnAsteroidEffect() {
		Ref.Call(this, "EndTurnAsteroidEffect", null);
		asteroids.Exposure();
		Ref.Return();
	}
}
