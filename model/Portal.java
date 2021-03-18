package model;

import reflection.Ref;

public class Portal implements Travelable {
	private static Portal unpairedPortal;
	public Asteroid asteroid;
	public Portal pair;
	public void Created() {
		Ref.Call(this, "Created", null);
	}
	
	public void Destroyed() {
		Ref.Call(this, "Destroyed", null);
	}
	
	public void ReceiveUnit(Unit unit) {
		Ref.Call(this, "ReceiveUnit", unit);
		pair.SendUnitToPair(unit);
		Ref.Return();
	}
	
	public boolean SendUnitToPair(Unit unit) {
		Ref.Call(this, "SendUnitToPair", unit);
		asteroid.AddUnit(unit);
		Ref.Return();
		return false;
	}
	
	public void SetAsteroid(Asteroid asteroid) {
		Ref.Call(this, "SetAsteroid", asteroid);
		this.asteroid=asteroid;
		Ref.Return();
	}
	
	public void SetPair(Portal portal) {
		Ref.Call(this, "SetPair", portal);
		this.pair = portal;
		Ref.Return();
	}
}
