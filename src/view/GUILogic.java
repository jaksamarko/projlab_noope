package view;

import java.util.ArrayList;

import interfaces.ControlerAPI;
import interfaces.ViewAPI;
import javafx.application.Application;
import model.*;

public class GUILogic implements ViewAPI
{
	private static WindowJavaFX window;
	public static void setWindow(WindowJavaFX _window) {window = _window;}
	
	public ArrayList<AsterNode> asterNodes;
	
	public int activePlayer = 1;
	public Travelable selected = null;
	
	private Vec2 pos = new Vec2(0,0);
	private float zoom = 1.0f;
	
	public void moveCamera(Vec2 amount)
	{
		pos = pos.add(amount);
	}
	public void zoomIn(float amount)
	{
		zoom = zoom / amount;
	}
	
	public GUILogic()
	{
		window = null;
		Thread thread = new Thread("New Thread") {
		      public void run(){
		    	  Application.launch(WindowJavaFX.class);
		      }
		   };
		   thread.start();
		while(window == null)
		{
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		window.setGUILogic(this);
		
		asterNodes = new ArrayList<AsterNode>();
	}
	
	public void ActivateInput(ControlerAPI control)
	{
		window.ActivateInput(control);
	}
	
	public void UpdateDrawData() 
	{
		ArrayList<AsterNode> toRemove = new ArrayList<AsterNode>();
		for(AsterNode node : asterNodes)
		{
			if(!ObjectStore.isExists(node.realAsteroid))
			{
				toRemove.add(node);
			}
		}
		
		for(AsterNode node:toRemove)
		{
			asterNodes.remove(node);
		}
	}
	
	public void DrawLines()
	{
		for(AsterNode node: asterNodes)
		{
			for(Travelable neigh:node.realAsteroid.GetNeighbors())
			{
				Asteroid aster = ObjectStore.TravelableToAsteroid(neigh);
				if(aster == null)
					continue;
				for(AsterNode node2: asterNodes)
				{
					if(aster == node2.realAsteroid)
					{
						window.drawLine(PosTransform(node.pos), PosTransform(node2.pos));
					}
				}
			}
		}
	}
	
	public Vec2 AsteroidCenter(Vec2 toCenter)
	{
		return toCenter.add(new Vec2(-50,-50));
	}
	
	public Vec2 PosTransform(Vec2 toTransform)
	{
		Vec2 toDrawPos = toTransform.add(pos);
		toDrawPos = toDrawPos.times(zoom);
		return toDrawPos;
	}
	
	public Vec2 InvPosTransform(Vec2 o)
	{
		Vec2 re = o.times(1.0f/zoom);
		re = re.add(pos.times(-1.0f));
		return re;
	}
	
	public void DrawAsteroid(AsterNode node)
	{
		Vec2 asteroidPos = PosTransform(node.pos);
		if(node.realAsteroid == selected)
			window.drawCircle(AsteroidCenter(asteroidPos), 150);
		window.drawAsteroid(AsteroidCenter(asteroidPos));
		if(node.realAsteroid.GetLayers()>0)
			window.drawText(asteroidPos, ""+node.realAsteroid.GetLayers());
		else
		{
			Resource resource = node.realAsteroid.GetResource();
			if(resource == null)
				window.drawText(asteroidPos, "no"+node.realAsteroid.GetLayers());
			else if(resource instanceof Coal)
			{
				window.drawCoal(asteroidPos);
			}
			else if(resource instanceof Iron)
			{
				window.drawIron(asteroidPos);
			}
			else if(resource instanceof Ice)
			{
				window.drawIce(asteroidPos);
			}
			else if(resource instanceof Uranium)
			{
				window.drawUranium(asteroidPos);
			}
		}
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
		window.clearCanvas();
		UpdateDrawData();
		DrawLines();
		for(AsterNode node: asterNodes)
			DrawAsteroid(node);
		
		DrawPlayerInventoryUI(activePlayer);
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
		Vec2 transformed = InvPosTransform(clickPos);
		//System.out.println(transformed.x + ", "+transformed.y);
		for(AsterNode a: asterNodes)
		{
			if(transformed.distance(a.pos) < 50.0)
			{
				selected = a.realAsteroid;
				break;
			}
		}
		printStatus();
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