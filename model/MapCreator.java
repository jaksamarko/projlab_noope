package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MapCreator {
	private final String types[] = {"Asteroid","Settler","Ufo","Portal","Robot"};
	
	private final String params[] = {
			//For asteroid
			"layers",
			"res","neigh",
			//For settler/robot
			"aster",
			//settler
			"inv",
			//Resource
			"lives"};
	
	
	private HashMap<String,HashMap<Integer, Object>> objects = new HashMap<String, HashMap<Integer, Object>>();
	private String ln="";
	BufferedReader bf;
	public MapCreator(String filename) {
		for(String type: types) {
			objects.put(type,new HashMap<Integer,Object>());
		}
		
		try {
			for(int i=0;i<2;i++) {
				bf = new BufferedReader(new FileReader(filename));
				readLine();
				while(ln!=null) {
					String type = selectFromKeys(types);
					if(i==0) {
						switch(type) {
							case "Asteroid":
								objects.get(type).put(readId(),createAsteroid());
							break;
							case "Settler":
								objects.get(type).put(readId(),createSettler());
							break;
							case "Ufo":
								objects.get(type).put(readId(),createUfo());
							break;
							case "Robot":
								objects.get(type).put(readId(),createRobot());
								break;
							case "Portal":
								objects.get(type).put(readId(),createPortal());
								break;
							case "":
								System.out.println("Wrong line: "+ln);
							break;
						}
					} else {
						switch(type) {
							case "Asteroid":
								configAsteroid((Asteroid)objects.get(type).get(readId()));
							break;
							case "Ufo":
							case "Robot":
							case "Settler":
								configUnit((Unit)objects.get(type).get(readId()));
							break;
							case "Portal":
								configPortal((Portal)objects.get(type).get(readId()));
							break;
						}
					}
				};
				bf.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private String readData() {
		String words[] = ln.split(" ");
		return words[words.length-1];
	}
	
	private Integer readId() {
		return Integer.parseInt(readData());
	}
	
	private void readLine()  throws IOException{
		ln=bf.readLine();
	}
	
	private Boolean readLineUntil(String str) throws IOException {
		readLine();
		if(ln==null)
			return false;
		return !findKey(str);
	}
	
	private Boolean findKey(String key) {
		return ln.indexOf(key)!=-1;
	}
	
	private String selectFromKeys(final String keys[]) {
		for(String key : keys) {
			if(findKey(key))
				return key;
		}
		return "";
	}
	
	private Asteroid createAsteroid() throws IOException {
		Asteroid aster = new Asteroid(false);
		while(readLineUntil("new")) {
			switch(selectFromKeys(params)) {
				case "res":
					Resource res = null;
					switch(readData()) {
						case "Coal":
							res = new Coal();
						break;
						case "Iron":
							res = new Iron();
						break;
						case "Uranium":
							res = new Uranium();
						break;
						case "Ice":
							res = new Ice();
						break;
						default:
							System.out.println("Wrong material: "+ readData());
					}
					if(res!=null)
						res.asteroid = aster;
					aster.SetResource(res);
				break;
				case "layers":
					for(int i=Integer.parseInt(readData());i<Asteroid.Layers;i++)
						aster.RemoveLayer();
				break;
			}
		}
		return aster;
	}
	
	private Settler createSettler() throws IOException {
		Settler sett = new Settler(null);
		Inventory inv = sett.GetInvetory();
		while(readLineUntil("new")) {
			switch(selectFromKeys(params)) {
				case "inv":
					switch(readData()) {
						case "Portal":
							//TODO ha a zsebében van
								inv.InsertPortal();
							break;
					}
				break;
			}
		}
		return sett;
	}
	
	private Ufo createUfo() throws IOException {
		Ufo ufo = new Ufo(null);
		while(readLineUntil("new")) {
			/*switch(selectFromKeys(params)) {
			}*/
		}
		return ufo;
	}
	
	private Portal createPortal() throws IOException {
		Portal portal = new Portal();
		while(readLineUntil("new")) {
			
		}
		return portal;
	}
	
	private Robot createRobot() throws IOException {
		Robot robot = new Robot(null);
		while(readLineUntil("new")) {
			
		}
		return robot;
	}
	
	private void configAsteroid(Asteroid aster) throws IOException {
		Game.createAsteroid(aster);
		while(readLineUntil("new")) {
			switch(selectFromKeys(params)) {
				case "neigh":
					for(String dat:readData().split(",")) {
						aster.addNeighbor((Asteroid)objects.get("Asteroid").get(Integer.parseInt(dat)));
					}
				break;
				case "lives":
					Resource res = aster.RemoveResource();
					for(int i=Integer.parseInt(readData());i<Uranium.MaxLives;i++) {
						res.Exposed();
					}
					aster.SetResource(res);
				break;
			}
		}
	}
	
	private void configUnit(Unit unit) throws IOException {
		while(readLineUntil("new")) {
			if(findKey("aster")) {
				((Asteroid)objects.get("Asteroid").get(readId())).ReceiveUnit(unit);
			}
		}
	}
	
	private void configPortal(Portal portal) throws IOException {
		while(readLineUntil("new")) {
			switch(selectFromKeys(params)) {
				case "aster":
					Asteroid aster = (Asteroid)objects.get("Asteroid").get(readId());
					//portal.SetAsteroid(aster);
					aster.ReceivePortal(portal);
					
				break;
				case "neigh":
					portal.SetPair((Portal)objects.get("Portal").get(readId()));
				break;
			}
		}
	}
}
