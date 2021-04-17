 package interfaces;

import java.util.ArrayList;

import model.*;

/**
 * A modell által megvalósítandó interface. Ez az interface csak Controller-nek van. View-nak külön interface nincs.
 */
public interface ModelAPI
{
	public ArrayList<Settler> GetAllSettler();
	public ArrayList<Travelable> GetAllTravelAble(); // Nem csak aszteroida, kell osszes neighbor
	public void EndTurnAsteroidEffect();
	public void CreateSunstorm();
	public void AllWorkersWork();
}