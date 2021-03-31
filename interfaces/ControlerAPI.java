package interfaces;

import model.Asteroid;
import model.Material;

public interface ControlerAPI 
{
	public void moveA(int ID);
	public void moveG(int ID);
	public void drill();
	public void mine();
	public void putback(Material material);
	public void createportal();
	public void createrobot();
	public void placeportal();
	public void admin_setSunstorm(boolean state);
	public void admin_setNearSun(int asteroidID, boolean state);
	public void admin_setWorkers (boolean status);
}
