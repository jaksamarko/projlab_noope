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
		Ref.Call(this, "CreatePortal", null);
	}
	
	public void CreateRobot() {
		Ref.Call(this, "CreateRobot", null);
	}
	
	public void Mine() {
		Ref.Call(this, "Mine", null);
		Resource minedMaterial = asteroid.MineResource();
		if(minedMaterial!=null) {
			this.MakeStepDone();
			minedMaterial.PickedUp(inventory);
		}
		Ref.Return();
	}
	
	public void PlacePortal() {
		Ref.Call(this, "PlacePortal", null);
	}
	
	public void PutResourceBack(Material material) {
		Ref.Call(this, "PutResourceBack", material);
	}
	
	public void Exploded()
	{
		Ref.Call(this, "Exploded", null);
	}
}
