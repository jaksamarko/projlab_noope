package interfaces;


public interface ViewAPI
{
	public void printStatus();
	public void printLost();
	public void printWon();
	public void printCurrentPlayer(int playerID);
	public void printEndTurn();
}
