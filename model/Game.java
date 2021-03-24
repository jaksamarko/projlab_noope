package model;

import java.util.ArrayList;

/**
 * 
 * Ez az osztály felel a robotokért, valamint a játékban lévő nehezítő tényezőkért, 
 *úgy mint napvihar, aszteroidák naptól való távolság esetén történő felrobbanása.
 */

public class Game {
	private static Game self;
	public static void RemoveAsteroid(Asteroid asteroid)
	{
		self.asteroids.remove(asteroid);
		for(Asteroid a:self.asteroids)
			a.RemoveNeighbor(asteroid);
	}
	
	public static void RemoveUnit(Unit unit)
	{
		self.settlers.remove(unit);
		self.workers.remove(unit);
		for(Asteroid a:self.asteroids)
			a.RemoveUnit(unit);
	}
	
	public ArrayList<Worker> workers;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Settler> settlers;
	
	Game()
	{
		self = this;
		workers = new ArrayList<Worker>();
		asteroids = new ArrayList<Asteroid>();
		settlers = new ArrayList<Settler>();
	}
	/**
	 * A játék végigiterálja a robotokat, így az összes lép.
	 */
	public void AllWorkersWork() {
		for(Worker w: workers)
			w.Work();
	}
	
	/**
	 * Napvihar generálása. Hatására meghalhatnak a nem elbújt állapotban lévő egységek.
	 */
	public Asteroid GetRandomAsteroid()
	{
		return asteroids.get(RNG.GetRand()/asteroids.size());
	}
	
	public void CreateSunstorm() {
		Asteroid target = GetRandomAsteroid();
		ArrayList<Travelable> neighborhood = target.GetNeighbors();
		target.Sunstorm();
		for(Travelable t:neighborhood)
			t.Sunstorm();
	}
	/**
	 * A nap közelségéből adódó negatív tényezők által kiváltott hatások végbemenetele.
	 */
	public void EndTurnAsteroidEffect() {
		for(Asteroid a:asteroids)
			a.EndTurnEffect();
	}

	
}
