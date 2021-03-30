package view;

import java.util.ArrayList;

import control.Item;
import interfaces.ViewAPI;
import model.*;

public class Cli_Output implements ViewAPI
{
	
	private int getID(Travelable a, ArrayList<Item<Travelable>> ta)
	{
		for(Item<Travelable> i: ta)
		{
			if(i.object == a)
				return i.ID;
		}
		return -1;
	}
	
	private String getSettlers(Asteroid a, ArrayList<Item<Settler>> players)
	{
		String re = "";
		boolean started = false;
		for(Item<Settler> s: players)
		{
			if(a.GetUnits().contains(s.object))
			{
				if(started == false)
				{
					started = true;
					re = re + s.ID;
				}
				else
				{
					re = re + ", " + s.ID;
				}
			}
		}
		return re;
	}
	
	private int robotCount(Asteroid a, ArrayList<Robot> robots)
	{
		int count = 0;
		for(Robot r: robots)
		{
			if(a.GetUnits().contains(r))
			count++;
		}
		return count;
	}
	
	private int ufoCount(Asteroid a, ArrayList<Ufo> ufos)
	{
		int count = 0;
		for(Ufo u: ufos)
		{
			if(a.GetUnits().contains(u))
			count++;
		}
		return count;
	}
	
	@Override
	public void printStatus(ArrayList<Item<Settler>> players, ArrayList<Item<Travelable>> destinations)
	{
		for(Asteroid a:DrawAbles.getInstance().asteroids)
		{
			String resource = "None";
			if(a.GetResource() != null)
				resource = a.GetResource().toString();
			CLI.println("Asteroid("+getID(a, destinations)+") material: " + resource+" layers: "+a.GetLayers());
			CLI.print("\tNeighbors: ");
			boolean started = false;
			for(Travelable t:a.GetNeighbors())
			{
				if(started == false)
				{
					CLI.print(""+getID(t, destinations));
					started = true;
				}
				else
				{
					CLI.print(", "+getID(t, destinations));
				}
			}
			CLI.println("");
			if(a.GetPortal() != null)
			{
				Portal pair = a.GetPortal().GetPair();
				CLI.println("\tHas Portal: "+getID(a.GetPortal(), destinations) + " (pair: "+ getID(a.GetPortal().GetPair(), destinations) +")");
			}
			String units = getSettlers(a, players);
			if(units.length()>0)
				CLI.println("\tSettlers: "+ units);
			CLI.println("\tRobot count: "+ robotCount(a,DrawAbles.getInstance().robots));
			CLI.println("\tUfo count: "+ ufoCount(a,DrawAbles.getInstance().ufos));
			CLI.println("");
		}
	}

	@Override
	public void printLost() {
		CLI.println("Game Lost");
		
	}

	@Override
	public void printWon() {
		CLI.println("Game won");
	}

	@Override
	public void printCurrentPlayer(int playerID) {
		CLI.println("Current Player: "+playerID);
		
	}

}
