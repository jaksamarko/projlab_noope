package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * Ez az osztály felel a robotokért, valamint a játékban lévő nehezítő tényezőkért, 
 *úgy mint napvihar, aszteroidák naptól való távolság esetén történő felrobbanása.
 */

public class Game implements java.io.Serializable {
	private static transient Game self;
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
	
	public static void createAsteroid(Asteroid asteroid) {
		self.asteroids.add(asteroid);
	}
	
	
	public static void addNewWorker(Worker worker)
	{
		self.workers.add(worker);
	}
	
	public ArrayList<Worker> workers;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Settler> settlers;
	
	public Game()
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
	
	public Boolean Load(String filename) {
		File f = new File(filename);
		if(!f.exists())
			return false;
		
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(filename);
			SequentialObjectInputStream stream = new SequentialObjectInputStream(inputStream);
			Object[] objs = stream.readObject();
			for(int i=0;i<objs.length;i++) {
				asteroids.add((Asteroid) objs[i]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void Save(String filename) {
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(filename);
			SequentialObjectOutputStream stream = new SequentialObjectOutputStream(outputStream);
			for(Asteroid a:asteroids) {
				stream.writeObject(a);
			}
			
			stream.close();
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NotSerializableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
