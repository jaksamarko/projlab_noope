package control;

import java.util.ArrayList;
import interfaces.*;
import model.*;
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
	private ArrayList<Settler> settlers;
	private int settlerIndex;
	private ArrayList<Item<Settler>> players;
	private ArrayList<Item<Travelable>> destinations;
	public Controler(ModelAPI _model)
	{
		model = _model;
		settlers = model.GetAllSettler();
		settlerIndex = 0;
		players = new ArrayList<Item<Settler>>();
		for(int i = 0; i< settlers.size(); i++)
		{
			Item<Settler> it = new Item<Settler>(i+1, settlers.get(i));
			players.add(it);
		}
			
		destinations = new ArrayList<Item<Travelable>>();
		ArrayList<Travelable> aList = model.GetAllTravelAble();
		for(int i = 0; i< aList.size(); i++)
		{
			Item<Travelable> it = new Item<Travelable>(i+1, aList.get(i));
			destinations.add(it);
		}
	}
	
	public ArrayList<Settler> getSettlers(){
		return this.settlers;
	}
	
	public ArrayList<Item<Travelable>> getDestinations(){
		return this.destinations;
	}
	
	private void endTurn()
	{
		model.AllWorkersWork();
		model.EndTurnAsteroidEffect();
		model.CreateSunstorm(); 
	}
	
	private void endPhase()
	{
		settlerIndex++;
		if(settlerIndex>settlers.size())
		{
			endTurn();
			settlerIndex = 0;
			settlers = model.GetAllSettler();
		}
	}
	
	public boolean checkwin() {
		for(Asteroid a : DrawAbles.getInstance().asteroids) {
			ArrayList<Settler> settlers = new ArrayList<Settler>();
			ArrayList<Unit> units = new ArrayList<Unit>();
			for(Unit u : )
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