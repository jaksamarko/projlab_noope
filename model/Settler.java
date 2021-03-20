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
		Robot newRobot = inventory.CraftRobot();
		if(newRobot!=null) {
			asteroid.ReceiveUnit(newRobot);
			this.MakeStepDone();
		}
		
		Ref.Return();
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
		Boolean built = asteroid.BuildPortal(inventory);
		if(built) {
			this.MakeStepDone();
		}
		Ref.Return();
	}
	
	public void PutResourceBack(Material material) {
		Ref.Call(this, "PutResourceBack", material);
		Resource sentResource = inventory.GetItem(material);
		Boolean taken = asteroid.AcceptResource(sentResource);
		if(taken) {
			inventory.RemoveItem(material);
			this.MakeStepDone();
		}
		Ref.Return();
		
	}
	
	public void Exploded()
	{
		Ref.Call(this, "Exploded", null);
		this.Die();
		Ref.Return();
	}
	
	public void SetInventory(Inventory inventory) {
		Ref.Call(this, "SetInventory", inventory);
		this.inventory=inventory;
		Ref.Return();
	}
}
