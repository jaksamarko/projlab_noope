package main;
import control.Controler;
import model.*;
import view.CLI;
import view.Cli_Input;
import view.Cli_Output;

public class Main
{
	/**
	 * A játék incializálásának a hívása itt történik. Van egy két console-ra történő kérés, amely késöbb ki lesz törölve, amint a prototípus fásinak vége
	 */
	public static void main(String[] args)
	{
		Game g = new Game();
		Cli_Input input = new Cli_Input();
		CLI.println("TestMode?(y/n)");
		if(input.readln().equals("y"))
		{
			CLI.println("Test Number?");
			input.loadGame(true,Integer.parseInt(input.readln()));
		}
		else
			input.loadGame(false, -1);
		Cli_Output output = new Cli_Output();
		Controler controler = new Controler(g,output);
		input.init(controler);
		input.Run();
		CLI.println("Do you want to compare the output with a file?(y/n)");
		if(input.readln().equals("y"))
		{
			input.compareStringWithfile(output.getOut());
		}
		Game.Save("save.txt");
    }
}


