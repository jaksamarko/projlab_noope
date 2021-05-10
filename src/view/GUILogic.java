package view;

import java.util.ArrayList;

import interfaces.ControlerAPI;
import interfaces.ViewAPI;
import javafx.animation.AnimationTimer;
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
	private float zoomTo = 1.0f;
	
	//For some spicy look
	private AnimationTimer aTimer;
	private double portalFloatY;
	private double tick;
	
	public void moveCamera(Vec2 amount)
	{
		pos = pos.add(amount);
	}
	public void zoomIn(float amount)
	{
		zoomTo = zoomTo / amount;
	}
	
	public float getZoomTo() {
		return zoomTo;
	}
	
	
	public Vec2 getPos() {
		return pos;
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
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		window.setGUILogic(this);
		
		//spicy stuff
		portalFloatY=0;
		aTimer = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				portalFloatY = Math.sin((float)arg0/100000000.0f)*5;
				zoom+=(zoomTo-zoom)/16.0f;
				printStatus();
			}
		};
		aTimer.start();
		
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
					Asteroid aster2 = ObjectStore.TravelableToAsteroid(aster);
					if(aster2 == null)
						continue;
					if(aster.GetNeighbors().indexOf(node2.realAsteroid)!=-1)
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
		window.drawAsteroid(AsteroidCenter(asteroidPos),node.realAsteroid == selected);
		if(node.realAsteroid.GetLayers()>0)
			window.drawText(asteroidPos.add(new Vec2(10,10)), ""+node.realAsteroid.GetLayers());
		else
		{
			Resource resource = node.realAsteroid.GetResource();
			if(resource == null)
				window.drawText(asteroidPos.add(new Vec2(10,10)), "no");
			else if(resource instanceof Coal)
			{
				window.drawCoal(asteroidPos.add(new Vec2(-20,-20)));
			}
			else if(resource instanceof Iron)
			{
				window.drawIron(asteroidPos.add(new Vec2(-20,-20)));
			}
			else if(resource instanceof Ice)
			{
				window.drawIce(asteroidPos.add(new Vec2(-20,-20)));
			}
			else if(resource instanceof Uranium)
			{
				window.drawUranium(asteroidPos.add(new Vec2(-20,-20)));
			}
		}
		if(node.realAsteroid.GetPortal()!= null)
		{
			window.drawPortal(asteroidPos.add(new Vec2(60, -110+portalFloatY)),node.realAsteroid.GetPortal() == selected);
		}
		
		
		ArrayList<Unit> units = node.realAsteroid.GetUnits();
		int radius = 60;
		int N = units.size();
		
		
		for(int i =0; i<N;i++)
		{
			Vec2 pos = new Vec2(Math.cos(360/(float)N*i/180*3.14)*radius,Math.sin(360/(float)N*i/180*3.14)*radius);
			
			if(units.get(i) instanceof Settler)
			{
				window.drawSettler(asteroidPos.add(pos));
				window.drawTextS(asteroidPos.add(pos),Integer.toString(units.get(i).GetID()));
			}
			else if(units.get(i) instanceof Robot)
				window.drawRobot(asteroidPos.add(pos));
			else if(units.get(i) instanceof Ufo)
				window.drawUfo(asteroidPos.add(pos));
		}
		
		//asteroidPos
		
	}
	
	
	public void DrawPlayerInventoryUI()
	{
		window.erasePlayerInfo();		
		ObjectStore os = ObjectStore.getInstance();
		for(Settler s: os.settlers)
		{
			int[] counts = s.GetInvetory().getCounts();
			int portalCount = s.GetInvetory().GetPortalCount();
			window.printPlayerInfo(String.format("Player: %d\n", s.GetID()));
			String inventoryInfo = String.format("Inventory:\n Coal: %d\n Iron: %d\n Ice: %d\n Uranium: %d\n Portals: %d\n", counts[0], counts[1], counts[2], counts[3], portalCount);
			window.printPlayerInfo(inventoryInfo);		
		}	
	}

	@Override
	public void printStatus()
	{	
		window.clearCanvas();
		UpdateDrawData();
		window.drawBG();
		DrawLines();
		for(AsterNode node: asterNodes)
			DrawAsteroid(node);
		
		DrawPlayerInventoryUI();
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
		double minDistance = 10000000.0;
		for(AsterNode a: asterNodes)
		{
			double distance = transformed.distance(a.pos);
			if(distance < minDistance)
			{
				selected = a.realAsteroid;
				minDistance = distance;
				
			}
			if(a.realAsteroid.GetPortal()!= null)
				distance = transformed.distance(a.pos.add(new Vec2(60, -110)));
				if(distance < minDistance)
				{
					selected = a.realAsteroid.GetPortal();
					minDistance = distance;
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
		window.printlnLog(text);
		
	}
	
}
