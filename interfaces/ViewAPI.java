package interfaces;
import java.util.ArrayList;
import control.*;
import model.Settler;
import model.Travelable;


public interface ViewAPI
{
	public void printStatus(ArrayList<Item<Settler>> players, ArrayList<Item<Travelable>> destinations);
	public void printLost();
	public void printWon();
	public void printCurrentPlayer(int playerID);
}
