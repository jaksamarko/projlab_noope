package model;

/**
 * Interface olyan objektumok számára, amire utazni lehet.
 */
public interface Travelable {
	public void ReceiveUnit(Unit unit);
	public void Sunstorm();
	public void EndTurnEffect();
	public boolean ReceivePortal(Portal portal);
}
