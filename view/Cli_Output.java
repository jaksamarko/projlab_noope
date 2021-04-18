package view;
import java.util.ArrayList;

import interfaces.ViewAPI;
import model.*;

/**
 * Ez egy CLI-re kiíró ideiglenes view modul
 */
public class Cli_Output implements ViewAPI
{
	/**
	 * Ide menti az output-ot, késöbbi teszt céljából
	 */
	String storedOut = "";

	/**
	 * Argumentumban megaddott aszteroidához összegyüjti a rajta lévő settlereket és megadja az ID-jaikat stringként
	 */
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
	
	/**
	 * Argumentumban megaddott aszteroidához összegyüjti a rajta lévő robotokat és megadja az ID-jaikat stringként
	 */
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
	/**
	 * Argumentumban megaddott aszteroidához összegyüjti a rajta lévő ufo-kat és megadja az ID-jaikat stringként
	 */
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
	
	/**
	 * Egy sor kiírása a console-ra. Adat gyüjtés is
	 */
	public void println(String line)
	{
		storedOut+= line + "\n";
		CLI.println(line);
	}
	
	/**
	 * Kiírása a console-ra. Adat gyüjtés is
	 */
	public void print(String text)
	{
		storedOut+= text;
		CLI.print(text);
	}
	
	/**
	 * Megadja az összegyüjtött kiírási adatot
	 */
	public String getOut()
	{
		return storedOut;
	}
	
	/**
	 * kiírja a játék állapotát a console-ra
	 */
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
				if(a.GetPortal().GetPair() != null)
					println("\tHas Portal: "+a.GetPortal().GetID()+ "(pair: "+ a.GetPortal().GetPair().GetID() +")");
				else
					println("\tHas Portal: "+a.GetPortal().GetID());
			}
			
			String settlers = getAsteroidSettlers(a);
			if(settlers.length()>0)
				println("\tSettlers: "+ settlers);
			
			String robots = getAsteroidRobots(a);
			if(robots.length()>0)
				println("\tRobots: "+ robots);
			
			String ufos = getAsteroidUfos(a);
			if(ufos.length()>0)
				println("\tUfos: "+ ufos);
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

	/**
	 * kiírja, hogy el lett veszítve a játék
	 */
	@Override
	public void printLost() {
		println("Game Lost");
		
	}
	/**
	 * kiírja, hogy meg lett nyerve a játék
	 */
	@Override
	public void printWon() {
		println("Game won");
	}
	/**
	 * kiírja, hogy ki a jelenlegi játékos
	 */
	@Override
	public void printCurrentPlayer(int playerID) {
		println("Current Player: "+ playerID);
	}
	/**
	 * kiírja, hogy vége van a körnek
	 */
	@Override
	public void printEndTurn() {
		println("End Turn");
	}
	/**
	 * kiírja ami a argumentumban van megadva. Teszteklés miatt fontos
	 */
	@Override
	public void log(String text) {
		println(text);
		
	}
}
