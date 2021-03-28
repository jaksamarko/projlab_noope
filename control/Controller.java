package control;

import java.util.ArrayList;
import interfaces.*;
import model.*;

public class Controller implements ControllerAPI
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
	public Controller(ModelAPI _model)
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
	
	private void endTurn()
	{
		
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