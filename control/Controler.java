package control;

import java.util.ArrayList;
import interfaces.*;
import model.*;
import view.CLI;
import view.DrawAbles;

public class Controler implements ControlerAPI
{
	private Travelable getTravelable(int ID)
	{
		for(Item<Travelable> o: destinations)
			if(o.ID == ID)
			{
				if(model.GetAllTravelAble().contains(o.object))
					return o.object;
				else
					return null;
			}
		return null;
	}
	
	private ModelAPI model;
	private ViewAPI view;
	private ArrayList<Settler> settlers;
	private int settlerIndex;
	private ArrayList<Item<Settler>> players;
	private ArrayList<Item<Travelable>> destinations;
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
		players = new ArrayList<Item<Settler>>();
		for(int i = 0; i< settlers.size(); i++)
		{
			Item<Settler> it = new Item<Settler>(i+1, settlers.get(i));
			players.add(it);
		}
			
		destinations = new ArrayList<Item<Travelable>>();
		int index = 1;
		for(Asteroid a:DrawAbles.getInstance().asteroids)
		{
			destinations.add(new Item<Travelable>(index, a));
			index++;
		}
		
		for(Portal p : DrawAbles.getInstance().portals)
		{
			destinations.add(new Item<Travelable>(index, p));
			index++;
		}
		
		//Optimize later
		for(Portal p:DrawAbles.getInstance().portals)
		{
			if(p.GetPair() == null)
				for(Portal q:DrawAbles.getInstance().portals)
				{
					if(!DrawAbles.getInstance().portals.contains(q.GetPair()))
					{
						p.SetPair(q);
						break;
					}
				}
		}
		view.printStatus(players, destinations);
		view.printCurrentPlayer(players.get(settlerIndex).ID);
	}
	
	public ArrayList<Settler> getSettlers(){
		return this.settlers;
	}
	
	public ArrayList<Item<Travelable>> getDestinations(){
		return this.destinations;
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
				view.printStatus(players, destinations);
				return;
			}
			settlers.get(settlerIndex).Active();
		}
		view.printStatus(players, destinations);
		view.printCurrentPlayer(players.get(settlerIndex).ID);
	}
	
	public boolean checkwin() {
		for(Asteroid a : DrawAbles.getInstance().asteroids) {
			ArrayList<Unit> units = new ArrayList<Unit>();
			for(Unit u : a.GetUnits())
			{
				units.add(u);
			}
			ArrayList<Settler> settlers= DrawAbles.unitToSettler(units);
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
	public void move(int destinationID)
	{
		Settler settler = settlers.get(settlerIndex);
		Travelable destination = this.getTravelable(destinationID);
		if(destination == null)
			return;
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
		CLI.println("sunStorm: "+ sunStormActive);
	}

	@Override
	public void admin_setNearSun(int asteroidID, boolean state) {
		Travelable ta = getTravelable(asteroidID);
		for(Asteroid a: DrawAbles.getInstance().asteroids)
			if(a == ta)
			{
				a.SetNearSun(state);
				break;
			}
		view.printStatus(players, destinations);
	}

	@Override
	public void admin_setWorkers(boolean status) {
		workersActive = status;
		CLI.println("workers active: " + status);
		
	}
	
}

// első játékos lép
	// move
	// drill
	// mine
	// place portal
	// place back material
	// craft

	// ha egy játékos minden unitaja lépett, nextplayer

	// ....

	// utolsó játékos is lépett, game step

	// JÁTÉK RÉSZE

	// robotok dolgoznak

	// körvégi dolgok meghívódnak