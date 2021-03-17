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
		units = unit;
		Ref.Return();
	}
	
	public void addNeighbor(Travelable travelable)
	{
		this.neighbors = travelable;
		Ref.Return();
	}
	
	public boolean BuildPortal(Inventory inventory) {
		return false;
	}
	
	public void DestroySelf() {
	}
	
	public void Explode() {
	}
	
	public void Exposure() {
	}
	
	public Travelable GetRandomNeighbor() {
		return neighbors;
	}
	
	private boolean IsNearSun() {
		return false;
	}
	
	public boolean IsNeighboor(Travelable travelable) {
		
		if(travelable == neighbors)
		{
			Ref.Return("result", "true");
			return true;
		}
		
		Ref.Return("result", "false");
		return false;
	}
	
	public Resource MineResource() {
		return resource;
	}
	
	public void ReceiveUnit(Unit unit) 
	{
		Ref.Call(this, "AddUnit", unit);
		AddUnit(unit);
		
		Ref.Call(unit, "SetAsteroid", this);
		unit.SetAsteroid(this);
		
		Ref.Return();
	}
	
	public boolean RemoveLayer() {
		return false;
	}
	
	public Resource RemoveResource() {
		return resource;
	}
	
	public void RemoveUnit(Unit unit) {
	}
	
	public void SetPortal(Portal portal) {
	}
	
	public void SetResource(Resource resource)
	{
		this.resource = resource;
		Ref.Return();
	}
	
	public void Sunstorm() {
	}
}
