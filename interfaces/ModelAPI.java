 package interfaces;

import java.util.ArrayList;

import model.*;

public interface ModelAPI
{
	public ArrayList<Settler> GetAllSettler();
	public ArrayList<Travelable> GetAllTravelAble(); // Nem csak aszteroida, kell osszes neighbor
	public void EndTurnAsteroidEffect();
	public void CreateSunstorm();
	public void AllWorkersWork();
}