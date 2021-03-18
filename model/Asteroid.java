package model;

import reflection.*;

public class Asteroid implements Travelable {
	private int layers;
	public Portal portal;
	public Resource resource;
	private Unit units;
	public Game game;
	public Travelable neighbors;
	public boolean AcceptResource(Resource resource) {
		Ref.Call(resource, "AcceptResource", resource);
		boolean ret = false;
		if(this.resource==null) {
			ret=true;
		}
		this.SetResource(resource);
		Ref.Return();
		return ret;
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
		Boolean ret=false;
		if(this.portal!=null) {
			ret=false;
		} else {
			Portal newPortal = inventory.GetPortal();
			if(newPortal==null) {
				ret=false;
			} else {
				ret=true;
				this.SetPortal(newPortal);
			}
		}
		Ref.Return();
		return ret;
	}
	
	public void DestroySelf()
	{
		Ref.Call(this, "DestroySelf", null);
		Ref.Return();
	}
	
	public void Explode() {
		Ref.Call(this, "Explode", null);
		Ref.Return();
	}
	
	public void Exposure() {
		Ref.Call(this, "Exposure", null);
		Ref.Return();
	}
	
	public Travelable GetRandomNeighbor() {
		Ref.Call(this, "GetRandomNeighbor", null);
		Ref.Return();
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
		Ref.Return();
	}
	
	public void SetPortal(Portal portal) {
		Ref.Call(this, "SetPortal", portal);
		Ref.Return();
	}
	
	public void SetResource(Resource resource)
	{
		Ref.Call(this, "SetResource", resource);
		this.resource = resource;
		Ref.Return();
	}
	
	public void Sunstorm() {
		Ref.Call(this, "Sunstorm", null);
		if(this.layers>0||resource!=null) {
			//TODO a szekvencián settler van!
			this.units.Die();
		}
		Ref.Return();
	}
}
