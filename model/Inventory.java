package model;

import reflection.Ref;

public class Inventory {
	public boolean CraftPortal() {
		Ref.Call(this, "CraftPortal", null);
		return false;
	}
	
	public Robot CraftRobot() {
		Ref.Call(this, "CraftRobot", null);
		return null;
	}
	
	public Resource GetItem(Material material) {
		Ref.Call(this, "GetItem", material);
		Boolean ret = Ref.RequestBool("Van e szén?");
		if(ret) {
			Resource sentResource = new Coal();
			Ref.Created(sentResource, "sentResource");
			Ref.Return(sentResource);
			return sentResource;
		}
		Ref.Return(Ref.nullObject);
		return null;
	}
	
	public Portal GetPortal() {
		Ref.Call(this, "GetPortal", null);
		Boolean ret = Ref.RequestBool("Van-e portál?");
		if(ret) {
			Portal portal = new Portal();
			Ref.Created(portal, "newPortal");
			Ref.Return(portal);
			return portal;
		}
		Ref.Return(Ref.nullObject);
		return null;
	}
	
	public boolean InsertItem(Material material) {
		Ref.Call(this, "InsertItem", material);
		Ref.Return();
		return false;
	}
	
	public void RemoveItem(Material material) {
		Ref.Call(this, "RemoveItem", material);
	}
}
