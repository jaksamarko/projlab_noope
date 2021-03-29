package interfaces;
import java.util.ArrayList;
import control.*;
import model.Settler;
import model.Travelable;


public interface ViewAPI
{
	public ArrayList<Settler> getSettlers();
	public ArrayList<Item<Travelable>> getDestinations();
}
