package model;

import reflection.*;

public class Settler extends Unit {
	private Inventory inventory;
	
	public Settler()
	{
		inventory = new Inventory();
		Ref.Created(inventory, "inventory");
	}
	
	public void CreatePortal() {
	}
	
	public void CreateRobot() {
	}
	
	
	public void Mine() {
	}
	
	public void PlacePortal() {
	}
	
	public void PutResourceBack(Material material) {
		
		
	}
	
	public void Exploded()
	{
	}
}
