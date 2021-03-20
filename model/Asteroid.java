package model;

import reflection.*;

public class Asteroid implements Travelable {
	
	public Portal portal;
	public Resource resource;
	private Unit units;
	public Game game;
	public Travelable neighbors;
	
	public int GetLayers() {
		return Ref.RequestInt("Mekkora a kéreg?");
	}
	//TODO ezt az 5. teszt hibás mûködése miatt változtattam. pls check h így jó-e also, ehhez van egy hosszabb kommentem, csoportba ment
	public boolean AcceptResource(Resource resource) {
		Ref.Call(this, "AcceptResource", resource);
		boolean ret = false;
		if(resource!=null) {
			if(this.resource==null) {
				ret=true;
				this.SetResource(resource);
			}
		}
		Ref.Return("taken",ret);
		return ret;
	}
	
	public void AddUnit(Unit unit)
	{
		Ref.Call(this, "AddUnit", unit);
		units = unit;
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
		Ref.Return("built",ret);
		return ret;
	}
	
	public void DestroySelf()
	{
		Ref.Call(this, "DestroySelf", null);
		Ref.Return();
	}
	
	public void Explode() {
		Ref.Call(this, "Explode", null);
		units.Exploded();
		this.DestroySelf();
		Ref.Return();
	}
	
	public void Exposure() {
		Ref.Call(this, "Exposure", null);
		boolean result = this.IsNearSun();
		if(result && GetLayers() == 0) {
			resource.Exposed();
			this.DestroySelf();
		}
		Ref.Return();
	}
	
	public Travelable GetRandomNeighbor() {
		Ref.Call(this, "GetRandomNeighbor", null);
		Ref.Return(neighbors);
		return neighbors;
		
	}
	
	private boolean IsNearSun() {
		Ref.Call(this, "IsNearSun", null);
		Boolean in = Ref.RequestBool("Napközelben van?");
		Ref.Return("result",in);
		return in;
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
			Ref.Created(removedMaterial, "minedMaterial");
			Ref.Return(removedMaterial);
			return removedMaterial;
		}
		Ref.Return(Ref.nullObject);
		return null;
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
		Boolean result = Ref.RequestBool("Lehet fúrni?");
		Ref.Return("result",result);
		return result;
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
		resource.asteroid = this;
		Ref.Return();
	}
	
	public void Sunstorm() {
		Ref.Call(this, "Sunstorm", null);
		if(this.GetLayers()>0||resource!=null) {
			//TODO a szekvencián settler van!
			this.units.Die();
		}
		Ref.Return();
	}
}
