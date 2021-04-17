package control;

import java.util.ArrayList;
import interfaces.*;
import model.*;


/**
 * Ez maga a control modul, mivel keveset csinál jelenleg ezért csak egy osztályban van megoldva.
 */
public class Controler implements ControlerAPI
{
	/**
	 * Attribútumok a különböző más komponensekhez
	 */
	private ModelAPI model;
	private ViewAPI view;
	/**
	 * Külen el van tárolva az összes settler, hogy nyomon lehessen követni a játékosoknak a sorrenjét.
	 */
	private ArrayList<Settler> settlers;
	private int settlerIndex;
	/**
	 * Játék állapotát tároloó boolean-ek
	 */
	private boolean won = false;
	private boolean lost = false;
	private boolean sunStormActive = true;
	private boolean workersActive = true;
	
	/**
	 *Inicializálódnak a komponens re mutató attribútumok és a modelltől lekérjük a játékosokáltal irányított settler űrhajókat. 
	 */
	public Controler(ModelAPI _model, ViewAPI _view)
	{
		model = _model;
		view = _view;
		settlers = model.GetAllSettler();
		settlerIndex = 0;
		
		view.printStatus();
		view.printCurrentPlayer(settlers.get(settlerIndex).GetID());
	}
	
	public ArrayList<Settler> getSettlers(){
		return this.settlers;
	}
	
	/**
	 * A játékbeli egy körnek a végén a model-ben a szükséges váloztatások lefutatását végző metódus
	 */
	private void endTurn()
	{
		view.printEndTurn();
		if(workersActive)
			model.AllWorkersWork();
		model.EndTurnAsteroidEffect();
		if(sunStormActive)
			model.CreateSunstorm();
		if(lost = checklose())
			view.printLost();
		if(won = checkwin())
			view.printWon();
	}
	/**
	 * Amikor egy játékos elvégez egy action-t akkor ez lefut (phase a kisebb egység, ami többször is lefut egy turn-ben)
	 */
	private void endPhase()
	{
		if(settlers.get(settlerIndex).getStepDone()) {
			settlerIndex++;
			if(settlerIndex > settlers.size()-1)
			{
				endTurn();
				settlerIndex = 0;
				settlers = model.GetAllSettler();
			}
			if(won || lost)
			{
				view.printStatus();
				return;
			}
			settlers.get(settlerIndex).Active();
		}
		view.printStatus();
		view.printCurrentPlayer(settlers.get(settlerIndex).GetID());
	}
	
	/**
	 * Algoritmus ami ellenörzi, hogy a játék nyert állapotban van-e
	 */
	public boolean checkwin() {
		for(Asteroid a : ObjectStore.getInstance().asteroids) {
			ArrayList<Unit> units = new ArrayList<Unit>();
			for(Unit u : a.GetUnits())
			{
				units.add(u);
			}
			ArrayList<Settler> settlers= ObjectStore.unitToSettler(units);
			int[] array = new int[4];
			for(int i = 0; i < 4; i++)
				array[i] = 0;
			for(Settler s: settlers)
			{
				int[] unitArray = s.GetInvetory().getCounts();
				for(int i = 0; i < 4; i++)
					array[i] += unitArray[i];
			}
			boolean hasEnough = true;
			for(int i = 0; i < 4; i++)
				if(array[i]< 3)
					hasEnough = false;
			if(hasEnough == true)
				return true;
		}	
		return false;
	}
	
	
	/**
	 * Algoritmus ami ellenörzi, hogy a játék elvesztett állapotban van-e. (jelenleg ez ekvivalens azzal, hogy nem maradt több settler)
	 */
	public boolean checklose() {
		if(settlers.size()==0)
			return true;
		return false;
	}
	
	/**
	 * Játékos aszeroidára mozog parancs
	 */
	@Override
	public void moveA(int ID)
	{
		Settler settler = settlers.get(settlerIndex);
		Asteroid destination = ObjectStore.getAsteroid(ID);
		settler.Move(destination);
		endPhase();
	}
	
	/**
	 * Játékos kapún megy keresztül parancs
	 */
	public void moveG(int ID)
	{
		Settler settler = settlers.get(settlerIndex);
		Portal destination = ObjectStore.getPortal(ID);
		settler.Move(destination);
		endPhase();
	}

	/**
	 * Játékos fúr parancs
	 */
	@Override
	public void drill() {
		Settler settler = settlers.get(settlerIndex);
		settler.Drill();
		endPhase();
	}
	
	/**
	 * 
	 */
	@Override
	public void mine() {
		Settler settler = settlers.get(settlerIndex);
		settler.Mine();
		endPhase();
	}
	
	/**
	 * játékos nyersanyagot visszatesz parancs
	 */
	@Override
	public void putback(Material material) {
		Settler settler = settlers.get(settlerIndex);
		settler.PutResourceBack(material);
		endPhase();
	}
	
	/**
	 * Játékos portált épít parancs
	 */
	@Override
	public void createportal() {
		Settler settler = settlers.get(settlerIndex);
		settler.CreatePortal();
		endPhase();
	}
	
	/**
	 * Játékos robot épít parancs
	 */
	@Override
	public void createrobot() {
		Settler settler = settlers.get(settlerIndex);
		settler.CreateRobot();
		endPhase();
	}
	/**
	 * Játékos portált letesz parancs
	 */
	@Override
	public void placeportal() {
		Settler settler = settlers.get(settlerIndex);
		settler.PlacePortal();
		endPhase();
	}
	/**
	 * Nem játékosok által kiadható legális parancs: Játékban a napvhiar kikapcsolása
	 */
	@Override
	public void admin_setSunstorm(boolean state) {
		sunStormActive = state;
		view.log("sunStorm: "+ sunStormActive);
	}
	/**
	 * Nem játékosok által kiadható legális parancs: Játékban egy aszteroida naphoz közel/távol mozgatása
	 */
	@Override
	public void admin_setNearSun(int asteroidID, boolean state) {
		ObjectStore.getAsteroid(asteroidID).SetNearSun(state);
		view.printStatus();
	}
	/**
	 * Nem játékosok által kiadható legális parancs: játékban az ai kikapcsolása parancs. Terminológia az ilyen unit-okra "worker"
	 */
	@Override
	public void admin_setWorkers(boolean status) {
		workersActive = status;
		view.log("workers active: " + status);
		
	}
	/**
	 * A játék kör végének a kényszerítése teszthez.
	 */
	@Override
	public void admin_forceEndTurn() {
		settlerIndex = 0;
		endTurn();
		view.printStatus();
		
	}
	
}