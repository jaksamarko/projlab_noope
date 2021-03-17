package model;

public abstract class Resource {
	public Asteroid asteroid;
	public void Exposed() {
	}
	
	public abstract void PickedUp(Inventory inventory);
}
