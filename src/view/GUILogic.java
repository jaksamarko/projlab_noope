package view;

import java.util.ArrayList;

import interfaces.ControlerAPI;
import interfaces.ViewAPI;
import model.*;

public class GUILogic implements ViewAPI
{
	public Window window;
	
	public ArrayList<AsterNode> asterNodes;
	public ArrayList<PortalNode> portalNodes;
	
	public int activePlayer = 1;
	public Travelable selected = null;
	
	public GUILogic()
	{
		window = new Window(this);
		asterNodes = new ArrayList<AsterNode>();
		portalNodes = new ArrayList<PortalNode>();
	}
	
	public void ActivateInput(ControlerAPI control)
	{
		window.ActivateInput(control);
	}
	
	public void UpdateDrawData() // kettő arrayList firssítése
	{
		//TODO
	}
	
	public void DrawLines()
	{
		//TODO
	}
	
	public void DrawAsteroid(AsterNode node)
	{
		float x = 1.0f;
		WindowJavaFX.instance.gc.drawImage(WindowJavaFX.instance.imgs.get("asteroid"), 0, 0);
	}
	
	public void DrawPortal(PortalNode node)
	{
		//TODO
	}
	
	public void DrawPlayerInventoryUI(int activePlayer)
	{
		//TODO
	}

	@Override
	public void printStatus()
	{
		WindowJavaFX.instance.gc.fillOval(22.5, 22.5, 221.0, 221.0);
		/*window.ClearCanvas();
		UpdateDrawData();
		DrawLines();
		for(AsterNode node: asterNodes)
			DrawAsteroid(node);
		for(PortalNode node: portalNodes)
			DrawPortal(node);
		
		DrawPlayerInventoryUI(activePlayer);*/
		//TODO
	}
	
	@Override
	public void printCurrentPlayer(int playerID)
	{
		activePlayer = playerID;
		printStatus();
	}

	@Override
	public void printLost()
	{
		//TODO
	}

	@Override
	public void printWon()
	{
		//TODO
	}
	
	public void clickSelect(Vec2 clickPos)
	{
		//TODO
	}
	
	public void move()
	{
		if(selected!=null)
		{
			if(selected.getClass() == Asteroid.class)
			{
				window.moveA(((Asteroid) selected).GetID());
			}
			else
			{
				window.moveG(((Portal) selected).GetID());
			}
		}
	}

	@Override
	public void logEvent(String text) {
		// TODO 
		
	}
	
}