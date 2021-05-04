package main;
import control.Controler;
import javafx.application.Application;
import javafx.application.Platform;
import model.*;
import view.GUILogic;
import view.WindowJavaFX;

public class Main
{
	/**
	 * A játék incializálásának a hívása itt történik. Van egy két console-ra történő kérés, amely késöbb ki lesz törölve, amint a prototípus fásinak vége
	 */
	public static void main(String[] args)
	{
		
		
		   GUILogic output = new GUILogic();
		   
		Game g = new Game();
		
		MapCreator mp = new MapCreator("m1.txt",output);
		Controler controler = new Controler(g,output);
		output.ActivateInput(controler);
    }

	
}


