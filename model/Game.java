package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import interfaces.ModelAPI;

/**
 * 
 * Ez az osztály felel a robotokért, valamint a játékban lévő nehezítő tényezőkért, 
 *úgy mint napvihar, aszteroidák naptól való távolság esetén történő felrobbanása.
 */




public class Game implements java.io.Serializable, ModelAPI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public static void addNewSettler(Settler settler)
	{
		self.settlers.add(settler);
	}
	
	private ArrayList<Worker> workers;
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
	
	public ArrayList<Settler> GetAllSettler(){
		return self.settlers;
	}
	
	public ArrayList<Travelable> GetAllTravelAble(){
		Set<Travelable> set = new HashSet<Travelable>();
		for(Asteroid a : asteroids) {
			ArrayList<Travelable> neighbors = a.GetNeighbors();
			for(Travelable t : neighbors) {
				set.add(t);
			}
				
		}
		ArrayList<Travelable> list = new ArrayList<Travelable>(set);
		return list;
	}
	/**
	 * Napvihar generálása. Hatására meghalhatnak a nem elbújt állapotban lévő egységek.
	 */
	public Asteroid GetRandomAsteroid()
	{
		return asteroids.get(RNG.GetRand() % asteroids.size());
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
	
	public static Boolean Load(String filename) {
		File f = new File(filename);
		if(!f.exists())
			return false;
		try {
			FileInputStream fileStream = new FileInputStream("save.txt");
            ObjectInputStream input = new ObjectInputStream(fileStream);
            int n=input.readInt();
            for(int i=0;i<n;i++) {
            	self.asteroids.add((Asteroid)input.readObject());
            }
            input.close();
            fileStream.close();
		} catch(FileNotFoundException e) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return true;
	}
	
	public static void Save(String filename) {
		try {
			FileOutputStream file = new FileOutputStream("save.txt");

            // Creates an ObjectOutputStream
            ObjectOutputStream output = new ObjectOutputStream(file);
            
            output.writeInt(self.asteroids.size());
            for(Asteroid a: self.asteroids) {
            	output.writeObject(a);
            }
            output.close();
            file.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
