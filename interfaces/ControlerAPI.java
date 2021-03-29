package interfaces;

import model.Material;

public interface ControlerAPI 
{
	public void move(int asterID);
	public void drill();
	public void mine();
	public void putback(Material material);
	public void createportal();
	public void createrobot();
	public void placeportal();
}
