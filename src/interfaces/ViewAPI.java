package interfaces;

/**
 * A view által megvalósítandó metódusok. Itt minden a megjelinítési hívásokkal kapcsolatos. Controller feladata ezek meghívása
 */

public interface ViewAPI
{
	public void printStatus();
	public void printCurrentPlayer(int playerID);
	public void printLost();
	public void printWon();
	public void logEvent(String text);
}
