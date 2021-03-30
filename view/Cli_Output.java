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
			CLI.println("Asteroid("+getID(a, destinations)+")");
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
				CLI.println("\tHas Portal: "+getID(a.GetPortal(), destinations));
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

}
