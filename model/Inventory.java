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
		return null;
	}
	
	public Portal GetPortal() {
		Ref.Call(this, "GetPortal", null);
		return null;
	}
	
	public boolean InsertItem(Material material) {
		Ref.Call(this, "InsertItem", material);
		return false;
	}
	
	public void RemoveItem(Material material) {
		Ref.Call(this, "RemoveItem", material);
	}
}
