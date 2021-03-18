package model;

import reflection.*;

public class Settler extends Unit {
	private Inventory inventory;
	
	public Settler()
	{
		
	}
	
	public void CreatePortal() {
		Ref.Call(this, "CreatePortal", null);
		Boolean isCrafted = inventory.CraftPortal();
		if(isCrafted) {
			this.MakeStepDone();
		}
		Ref.Return();
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
		Resource sentResorce = inventory.GetItem(material);
		Boolean taken = asteroid.AcceptResource(sentResorce);
		if(taken) {
			inventory.RemoveItem(material);
			this.MakeStepDone();
		}
		Ref.Return();
	}
	
	public void Exploded()
	{
		Ref.Call(this, "Exploded", null);
	}
	
	public void SetInventory(Inventory inventory) {
		Ref.Call(this, "SetInventory", inventory);
		this.inventory=inventory;
		Ref.Return();
	}
}
