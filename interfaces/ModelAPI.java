package interfaces;

import java.util.ArrayList;

import model.*;

public interface ModelAPI
{
	public ArrayList<Settler> GetAllSettler();
	public ArrayList<Travelable> GetAllTravelAble(); // Nem csak aszteroida, kell osszes neighbor
}