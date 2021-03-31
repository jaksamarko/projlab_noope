package interfaces;
import java.util.ArrayList;
import control.*;
import model.Settler;
import model.Travelable;


public interface ViewAPI
{
	public void printStatus();
	public void printLost();
	public void printWon();
	public void printCurrentPlayer(int playerID);
	public void printEndTurn();
}
