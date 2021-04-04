package view;
import interfaces.ViewAPI;
import model.*;

public class Cli_Output implements ViewAPI
{
	
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
	
	@Override
	public void printStatus()
	{
		for(Asteroid a:ObjectStore.getInstance().asteroids)
		{
			String resource = "None";
			if(a.GetResource() != null)
				resource = a.GetResource().toString();
			CLI.println("Asteroid("+a.GetID()+") material: " + resource+"; layers: "+a.GetLayers()+ "; near sun: "+a.GetNearSun());
			CLI.print("\tNeighbors: ");
			boolean started = false;
			for(Travelable t:a.GetNeighbors())
			{
				if(t == a.GetPortal())
					continue;
				
				if(started == false)
				{
					CLI.print(""+t.GetID());
					started = true;
				}
				else
				{
					CLI.print(", "+t.GetID());
				}
			}
			CLI.println("");
			if(a.GetPortal() != null)
			{
				CLI.println("\tHas Portal: "+a.GetPortal().GetID()+ " (pair: "+ a.GetPortal().GetPair().GetID() +")");
			}
			
			String settlers = getAsteroidSettlers(a);
			if(settlers.length()>0)
				CLI.println("\tSettlers: "+ settlers);
			
			String robots = getAsteroidRobots(a);
			if(robots.length()>0)
				CLI.println("\tSettlers: "+ robots);
			
			String ufos = getAsteroidUfos(a);
			if(ufos.length()>0)
				CLI.println("\tSettlers: "+ ufos);
			CLI.println("");
		}
		for(Settler s:ObjectStore.getInstance().settlers)
		{
			CLI.println("Player ("+ s.GetID() + ") inventory");
			CLI.println("\tCoal count: "+s.GetInvetory().GetCoalBox().GetCount());
			CLI.println("\tIron count: "+s.GetInvetory().GetIronBox().GetCount());
			CLI.println("\tIce count: "+s.GetInvetory().GetIceBox().GetCount());
			CLI.println("\tUranium count: "+s.GetInvetory().GetUraniumBox().GetCount());
			CLI.println("\tPortal count: "+s.GetInvetory().GetPortalCount());
		}
		CLI.println("");
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
		CLI.println("Current Player: "+ playerID);
	}

	@Override
	public void printEndTurn() {
		CLI.println("End Turn");
		
	}

}
