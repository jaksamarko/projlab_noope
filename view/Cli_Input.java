package view;

import java.io.*;

import interfaces.*;
import model.Material;

public class Cli_Input
{
	BufferedReader consoleReader;
	BufferedReader reader;
	
	public Cli_Input()
	{
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
		reader = consoleReader;
	}
	
	public String readln(){
		
		String re = "";
		try {
			re = reader.readLine();
		} catch (IOException e) {
			System.out.println("io problem");
			e.printStackTrace();}
		return re;
	}
	
	
	ControllerAPI control;
	
	public void init(ControllerAPI _control)
	{
		control = _control;
		CLI.println("Read from file or console?(c/f)?");
		String input = "";
		while(input.equals("c")||input.equals("f"))
			input = readln();
		if(input.equals("f"))
		{
			try {
				reader = new BufferedReader(new FileReader(readln()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void Run()
	{
		while(true)
		{
			String line = readln();
			String[] pieces = line.split(" ");
			if(pieces.length == 0)
				continue;
			String cmd = pieces[1];
			if(cmd.equals("move"))
			{
				if(pieces.length == 1)
				{
					CLI.printError("move-nak 2 argument kell");
					break;
				}
				control.move(Integer.parseInt(pieces[0]));
			}
			else if(cmd.equals("drill"))
			{
				control.drill();
			}
			else if(cmd.equals("mine"))
			{
				control.mine();
			}
			else if(cmd.equals("putback"))
			{
				if(pieces.length == 1)
				{
					CLI.printError("putback-nek 2 argument kell");
					break;
				}
				if(pieces[1].equals("coal"))
					control.putback(Material.Coal);
				else if(pieces[1].equals("iron"))
					control.putback(Material.Iron);
				else if(pieces[1].equals("ice"))
					control.putback(Material.Ice);
				else if(pieces[1].equals("uranium"))
					control.putback(Material.Uranium);
			}
			else if(cmd.equals("createportal"))
			{
				control.createportal();
			}
			else if(cmd.equals("placeportal"))
			{
				control.placeportal();
			}
			else if(cmd.equals("createrobot"))
			{
				control.createrobot();
			}
			else if(cmd.equals("quit"))
			{
				break;
			}
		}
	}
}


/*public class Cli {

	public static void run(Scanner in)
	{
		try {
			while(true) {
				String input = in.nextLine();
				menu(input);
			}
		} catch (NoSuchElementException e){
			return;
		}
	}
	
	public static void menu(String input) 
	{	
		String[] cmd = input.split("\\s+");
		
		switch (cmd[0]) {
			case "move": {
				
			}break;
			
			case "drill": {
				
			}break;
			
			case "mine": {
				
			}break;
			
			case "putback": {
				
			}break;
			
			case "craftportal": {
				
			}break;
			
			case "craftrobot": {
				
			}break;
			
			case "placeportal": {
				
			}break;
		
			case "save": {
				
			}break;
			
			case "load": {
				//map.txt
				//loadgame"map.txt"
			}break;
		
		
		}
	}
}*/
