package model;
///FUCK YOU TESZT

public class Asteroid {
	private int layers;
	public Portal portal;
	public Resource resource;
	private Unit units;
	public Game game;
	public Travelable neighbors;
	public boolean AcceptResource(Resource resource) {
		return false;
	}
	
	private void AddUnit(Unit unit) {
	}
	
	public boolean BuildPortal(Inventory inventory) {
		return false;
	}
	
	public void DestroySelf() {
	}
	
	public void Explode() {
	}
	
	public void Exposure() {
	}
	
	public Travelable GetRandomNeighbor() {
		return neighbors;
	}
	
	private boolean IsNearSun() {
		return false;
	}
	
	public boolean IsNeighboor(Travelable travelable) {
		return false;
	}
	
	public Resource MineResource() {
		return resource;
	}
	
	public boolean ReceiveUnit(Unit unit) {
		return false;
	}
	
	public boolean RemoveLayer() {
		return false;
	}
	
	public Resource RemoveResource() {
		return resource;
	}
	
	public void RemoveUnit(Unit unit) {
	}
	
	public void SetPortal(Portal portal) {
	}
	
	private void SetResource(Resource resource) {
	}
	
	public void Sunstorm() {
	}
}
