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
		MapCreator mp = new MapCreator("m1.txt");
		GUILogic output = new GUILogic();
		Controler controler = new Controler(g,output);
		output.ActivateInput(controler);
    }
}


