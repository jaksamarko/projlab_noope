package model;

public class Portal {
	private static Portal unpairedPortal;
	public Asteroid asteroid;
	public Portal pair;
	public void Created() {
	}
	
	public void Destroyed() {
	}
	
	public boolean ReceiveUnit(Unit unit) {
		return false;
	}
	
	public boolean SendUnitToPair(Unit unit) {
		return false;
	}
}
