package view;

import java.util.ArrayList;

import interfaces.ControlerAPI;
import interfaces.ViewAPI;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import model.*;

/**
 * Ez az osztály felelős a grafikus megjelenítéshez szükséges logika működetéséhez
 */
public class GUILogic implements ViewAPI
{
	/**
	 * Az ablak amire rajzol
	 */
	private static WindowJavaFX window;
	public static void setWindow(WindowJavaFX _window) {window = _window;}
	
	/**
	 * Aszteroidák pozicionális adatok
	 */
	public ArrayList<AsterNode> asterNodes;
	public int activePlayer = 1;
	
	/**
	 * Felületen kiválasztott célpont itt van eltárolva. 
	 */
	public Travelable selected = null;
	
	/**
	 * View transzformációk
	 */
	private Vec2 pos = new Vec2(0,0);
	private Vec2 posTo = new Vec2(0,0);
	private float zoom = 1.0f;
	private float zoomTo = 1.0f;
	
	/**
	 * Animációs adatok.
	 */
	private AnimationTimer aTimer;
	private double portalFloatY;
	
	/**
	 * Camera elmozgatása megadott mennyiséggel.
	 */
	public void moveCamera(Vec2 amount)
	{
		posTo = posTo.add(amount);
	}
	/**
	 * Camera ránagyítása megadott mennyíséggel.
	 */
	public void zoomIn(float amount)
	{
		float zoomNew = zoomTo * amount;
		if(zoomNew>.2&&zoomNew<4)
			zoomTo = zoomNew;
	}
	
	public float getZoomTo() {
		return zoomTo;
	}
	
	
	public Vec2 getPos() {
		return pos;
	}
	
	/**
	 * Konstrukor, amelyben inicializálódik a vizuális megjelenítő felület
	 */
	
	public GUILogic()
	{
		window = null;
		// Ablak típus amit használtunk igényeli a külön szállon indítást
		Thread thread = new Thread("New Thread") {
			public void run(){
				Application.launch(WindowJavaFX.class);
			}
		};
		thread.start();
		while(window == null) //Nem teljesesn busy wait
		{
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		window.setGUILogic(this); //kész ablak beállítása
		
		//Animáció információ beállítása
		portalFloatY=0;
		aTimer = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				portalFloatY = Math.sin((float)arg0/100000000.0f)*5;
				zoom+=(zoomTo-zoom)/16.0f;
				
				pos.x+=(posTo.x-pos.x)/8.0f;
				pos.y+=(posTo.y-pos.y)/8.0f;
				printStatus();
			}
		};
		aTimer.start();
		
		asterNodes = new ArrayList<AsterNode>();
	}
	
	/**
	 * Control megadása a tárolt ablaknak
	 */
	public void ActivateInput(ControlerAPI control)
	{
		window.ActivateInput(control);
	}
	
	/**
	 * Model alapján adatok összegyüjtése
	 */
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
	
	/**
	 * Aszteroidák közötti vonolak kirajzolása
	 */
	
	public void DrawLines()
	{
		for(AsterNode node: asterNodes)
		{
			Asteroid aster = node.realAsteroid;
			for(Travelable neigh:aster.GetNeighbors())
			{	
				for(AsterNode node2: asterNodes)
				{
					if(node2.realAsteroid==(Asteroid)neigh)
						window.drawLine(PosTransform(node.pos), PosTransform(node2.pos));
				}
			}
		}
	}
	
	/**
	 * Aszteroida középpontják kiszámoló metódus
	 */
	
	public Vec2 AsteroidCenter(Vec2 toCenter)
	{
		return toCenter.add(new Vec2(-50,-50));
	}
	
	/**
	 * View transzformot megcsináló metódus
	 */
	public Vec2 PosTransform(Vec2 toTransform)
	{
		Vec2 toDrawPos = toTransform.add(pos);
		toDrawPos = toDrawPos.times(zoom);
		return toDrawPos;
	}
	
	/**
	 * inverese View transzformot megcsináló metódus
	 */
	
	public Vec2 InvPosTransform(Vec2 o)
	{
		Vec2 re = o.times(1.0f/zoom);
		re = re.add(pos.times(-1.0f));
		return re;
	}
	
	/**
	 * Aszteroida és hozzá tartozó dolgok kirajzolása
	 */
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
	
	/**
	 * Jákos inventory kirajzolása ablakra
	 */
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
	
	/**
	 * Minden kirajzolása
	 */
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
	
	/**
	 * Játékos változása kirajzolása
	 */
	@Override
	public void printCurrentPlayer(int playerID)
	{
		activePlayer = playerID;
		printStatus();
	}

	@Override
	public void printLost() {}

	@Override
	public void printWon() {}
	
	/**
	 * Kattintás hatására kiválasztás változása
	 */
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
	
	/**
	 * Mozgás történsének a megvalósitása
	 */
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

	/**
	 * Esemény kiírása ablakra
	 */
	@Override
	public void logEvent(String text) {
		window.printlnLog(text);
		
	}
	
}
