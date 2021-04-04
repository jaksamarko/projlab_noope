package main;
import control.Controler;
import model.*;
import view.Cli_Input;
import view.Cli_Output;

public class Main
{
	public static void main(String[] args)
	{
		MapCreator mp;
		Game g = new Game();
		if(/*!Game.Load("save.txt")*/true) {
			mp = new MapCreator("MapCreate.txt");
		}
		
		Cli_Input input = new Cli_Input();
		Cli_Output output = new Cli_Output(); 
		Controler controler = new Controler(g,output);
		input.init(controler);
		input.Run();
		
		Game.Save("save.txt");
    }
}


