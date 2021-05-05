package view;

import interfaces.ControlerAPI;
import model.Material;

public class Window
{	
	ControlerAPI control;
	GUILogic gui;
	
	public Window(GUILogic _gui)
	{
		
	}
	
	public void ActivateInput(ControlerAPI _control)
	{
		control = _control;
	}
	
	public void ClearCanvas()
	{
		
	}
	
	//TODO: Összes esemény meghívása innentől lefelé ablak eseményekkel
	public void clickEvent()
	{
		Vec2 clickPos = new Vec2(0,0); //getting actual click position from window
		gui.clickSelect(clickPos);
	}
	
	public void moveEvent()
	{
		gui.move();
	}
	public void moveA(int ID){control.moveA(ID);}
	public void moveG(int ID){control.moveG(ID);}
	
	public void drillEvent()
	{
		control.drill();
	}
	
	public void mineEvent()
	{
		control.mine();
	}
	
	public void putbackEvent(Material material)
	{
		control.putback(material);
	}
	
	public void createportalEvent()
	{
		control.createportal();
	}
	
	public void createrobotEvent()
	{
		control.createrobot();
	}
	
	public void placeportalEvent()
	{
		control.placeportal();
	}
	
}
