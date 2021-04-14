package view;
import java.util.ArrayList;

import interfaces.ViewAPI;
import model.*;

public class Cli_Output implements ViewAPI
{
	String storedOut = "";

	private String getAsteroidSettlers(Asteroid a)
	{
		String re = "";
		boolean started = false;
		for(Settler s: ObjectStore.getInstance().settlers)
		{
			if(a.GetUnits().contains(s))
			{
				if(started == false)
				{
					started = true;
					re = re + s.GetID();
				}
				else
				{
					re = re + ", " + s.GetID();
				}
			}
		}
		return re;
	}
	
	private String getAsteroidRobots(Asteroid a)
	{
		String re = "";
		boolean started = false;
		for(Robot r: ObjectStore.getInstance().robots)
		{
			if(a.GetUnits().contains(r))
			{
				if(started == false)
				{
					started = true;
					re = re + r.GetID();
				}
				else
				{
					re = re + ", " + r.GetID();
				}
			}
		}
		return re;
	}
	
	private String getAsteroidUfos(Asteroid a)
	{
		String re = "";
		boolean started = false;
		for(Ufo u: ObjectStore.getInstance().ufos)
		{
			if(a.GetUnits().contains(u))
			{
				if(started == false)
				{
					started = true;
					re = re + u.GetID();
				}
				else
				{
					re = re + ", " + u.GetID();
				}
			}
		}
		return re;
	}
	
	public void println(String line)
	{
		storedOut+= line + "\n";
		CLI.println(line);
	}
	
	public void print(String text)
	{
		storedOut+= text;
		CLI.print(text);
	}
	
	public String getOut()
	{
		return storedOut;
	}
	
	@Override
	public void printStatus()
	{
		for(Asteroid a:ObjectStore.getInstance().asteroids)
		{
			String resource = "None";
			if(a.GetResource() != null)
				resource = a.GetResource().toString();
			println("Asteroid("+a.GetID()+") material: " + resource+"; layers: "+a.GetLayers()+ "; near sun: "+a.GetNearSun());
			print("\tNeighbors: ");
			boolean started = false;
			for(Travelable t:a.GetNeighbors())
			{
				if(t == a.GetPortal())
					continue;
				
				if(started == false)
				{
					print(""+t.GetID());
					started = true;
				}
				else
				{
					print(", "+t.GetID());
				}
			}
			println("");
			if(a.GetPortal() != null)
			{
				println("\tHas Portal: "+a.GetPortal().GetID()+ " (pair: "+ a.GetPortal().GetPair().GetID() +")");
			}
			
			String settlers = getAsteroidSettlers(a);
			if(settlers.length()>0)
				println("\tSettlers: "+ settlers);
			
			String robots = getAsteroidRobots(a);
			if(robots.length()>0)
				println("\tSettlers: "+ robots);
			
			String ufos = getAsteroidUfos(a);
			if(ufos.length()>0)
				println("\tSettlers: "+ ufos);
			println("");
		}
		for(Settler s:ObjectStore.getInstance().settlers)
		{
			println("Player ("+ s.GetID() + ") inventory");
			println("\tCoal count: "+s.GetInvetory().GetCoalBox().GetCount());
			println("\tIron count: "+s.GetInvetory().GetIronBox().GetCount());
			println("\tIce count: "+s.GetInvetory().GetIceBox().GetCount());
			println("\tUranium count: "+s.GetInvetory().GetUraniumBox().GetCount());
			println("\tPortal count: "+s.GetInvetory().GetPortalCount());
		}
		println("");
	}

	@Override
	public void printLost() {
		println("Game Lost");
		
	}

	@Override
	public void printWon() {
		println("Game won");
	}

	@Override
	public void printCurrentPlayer(int playerID) {
		println("Current Player: "+ playerID);
	}

	@Override
	public void printEndTurn() {
		println("End Turn");
	}

	@Override
	public void log(String text) {
		println(text);
		
	}
}
