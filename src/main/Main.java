package main;
import control.Controler;
import model.*;
import view.GUILogic;

public class Main
{
	/**
	 * A játék incializálásának a hívása itt történik. Van egy két console-ra történő kérés, amely késöbb ki lesz törölve, amint a prototípus fásinak vége
	 */
	public static void main(String[] args)
	{
		Game g = new Game();
		GUILogic output = new GUILogic();
		MapCreator mp = new MapCreator("m1.txt",output);
		Controler controler = new Controler(g,output);
		output.ActivateInput(controler);
    }

	
}


