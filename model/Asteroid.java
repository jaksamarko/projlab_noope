package model;
///FUCK YOU TESZT
import reflection.*;

public class Asteroid implements Travelable {
	private int layers;
	public Portal portal;
	public Resource resource;
	private Unit units;
	public Game game;
	public Travelable neighbors;
	public boolean AcceptResource(Resource resource) {
		return false;
	}
	
	public void AddUnit(Unit unit)
	{
		Ref.Call(this, "AddUnit", unit);
		units = unit;
		//TODO: Miért volt két unit 5.3.2-ön?
		unit.SetAsteroid(this);
		Ref.Return();
	}
	
	public void addNeighbor(Travelable travelable)
	{
		Ref.Call(this, "addNeighbor", travelable);
		this.neighbors = travelable;
		Ref.Return();
	}
	
	public boolean BuildPortal(Inventory inventory) {
		Ref.Call(this, "BuildPortal", inventory);
		return false;
	}
	
	public void DestroySelf()
	{
		Ref.Call(this, "DestroySelf", null);
	}
	
	public void Explode() {
		Ref.Call(this, "Explode", null);
	}
	
	public void Exposure() {
		Ref.Call(this, "Exposure", null);
	}
	
	public Travelable GetRandomNeighbor() {
		Ref.Call(this, "GetRandomNeighbor", null);
		return neighbors;
	}
	
	private boolean IsNearSun() {
		Ref.Call(this, "IsNearSun", null);
		return false;
	}
	
	public boolean IsNeighboor(Travelable travelable) {
		Ref.Call(this, "IsNeighboor", travelable);
		
		if(travelable == neighbors)
		{
			Ref.Return("result", "True");
			return true;
		}
		Ref.Return("result", "False");
		return false;
	}
	
	public Resource MineResource() {
		Ref.Call(this, "MineResource", null);
		if(resource!=null) {
			Resource removedMaterial = this.RemoveResource();
		}
		Ref.Return();
		return resource;
	}
	
	public void ReceiveUnit(Unit unit) 
	{
		Ref.Call(this, "ReceiveUnit", unit);
		AddUnit(unit);
		
		unit.SetAsteroid(this);
		
		Ref.Return();
	}
	
	public boolean RemoveLayer() {
		Ref.Call(this, "RemoveLayer", null);
		return false;
	}
	
	public Resource RemoveResource() {
		Ref.Call(this, "RemoveResource", null);
		return resource;
	}
	
	public void RemoveUnit(Unit unit) {
		Ref.Call(this, "RemoveUnit", unit);
	}
	
	public void SetPortal(Portal portal) {
		Ref.Call(this, "SetPortal", portal);
	}
	
	public void SetResource(Resource resource)
	{
		Ref.Call(this, "SetResource", resource);
		this.resource = resource;
		Ref.Return();
	}
	
	public void Sunstorm() {
		Ref.Call(this, "Sunstorm", null);
	}
}
