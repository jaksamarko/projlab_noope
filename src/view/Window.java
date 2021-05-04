package view;

import interfaces.ControlerAPI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Material;

public class Window
{	
	ControlerAPI control;
	GUILogic gui;
	
	public Window(GUILogic _gui)
	{
		gui = _gui;
		Thread thread = new Thread("New Thread") {
	      public void run(){
	    	  Application.launch(WindowJavaFX.class);
	      }
	   };
	   thread.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//WindowJavaFX.instance.setWindow(this);
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
