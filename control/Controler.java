package control;

import java.util.ArrayList;
import interfaces.*;
import model.*;

public class Controler implements ControlerAPI
{
	private ModelAPI model;
	private ViewAPI view;
	private ArrayList<Settler> settlers;
	private int settlerIndex;
	private boolean won = false;
	private boolean lost = false;
	private boolean sunStormActive = true;
	private boolean workersActive = true;
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
	
	
	public boolean checklose() {
		if(settlers.size()==0)
			return true;
		return false;
	}
	
	@Override
	public void moveA(int ID)
	{
		Settler settler = settlers.get(settlerIndex);
		Asteroid destination = ObjectStore.getAsteroid(ID);
		settler.Move(destination);
		endPhase();
	}
	
	public void moveG(int ID)
	{
		Settler settler = settlers.get(settlerIndex);
		Portal destination = ObjectStore.getPortal(ID);
		settler.Move(destination);
		endPhase();
	}

	@Override
	public void drill() {
		Settler settler = settlers.get(settlerIndex);
		settler.Drill();
		endPhase();
	}
	
	
	@Override
	public void mine() {
		Settler settler = settlers.get(settlerIndex);
		settler.Mine();
		endPhase();
	}
	

	@Override
	public void putback(Material material) {
		Settler settler = settlers.get(settlerIndex);
		settler.PutResourceBack(material);
		endPhase();
	}
	

	@Override
	public void createportal() {
		Settler settler = settlers.get(settlerIndex);
		settler.CreatePortal();
		endPhase();
	}
	

	@Override
	public void createrobot() {
		Settler settler = settlers.get(settlerIndex);
		settler.CreateRobot();
		endPhase();
	}

	@Override
	public void placeportal() {
		Settler settler = settlers.get(settlerIndex);
		settler.PlacePortal();
		endPhase();
	}

	@Override
	public void admin_setSunstorm(boolean state) {
		sunStormActive = state;
		view.log("sunStorm: "+ sunStormActive);
	}

	@Override
	public void admin_setNearSun(int asteroidID, boolean state) {
		ObjectStore.getAsteroid(asteroidID).SetNearSun(state);
		view.printStatus();
	}

	@Override
	public void admin_setWorkers(boolean status) {
		workersActive = status;
		view.log("workers active: " + status);
		
	}
	
}