package model;

import reflection.Ref;

public class Portal {
	private static Portal unpairedPortal;
	public Asteroid asteroid;
	public Portal pair;
	public void Created() {
		Ref.Call(this, "Created", null);
	}
	
	public void Destroyed() {
		Ref.Call(this, "Destroyed", null);
	}
	
	public boolean ReceiveUnit(Unit unit) {
		Ref.Call(this, "ReceiveUnit", unit);
		return false;
	}
	
	public boolean SendUnitToPair(Unit unit) {
		Ref.Call(this, "SendUnitToPair", unit);
		return false;
	}
}
