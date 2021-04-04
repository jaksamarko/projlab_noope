package view;

import java.io.*;

import interfaces.*;
import model.Material;

public class Cli_Input
{
	BufferedReader consoleReader;
	BufferedReader reader;
	boolean printRead = false;
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
	
	
	ControlerAPI control;
	
	public void init(ControlerAPI _control)
	{
		control = _control;
		CLI.println("Read from file or console?(c/f)?");
		String input = "";
		while(!(input.equals("c")||input.equals("f")))
			input = readln();
		if(input.equals("f"))
		{
			printRead = true;
			CLI.print("Path:");
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
			if(printRead)
			{
				if(line == null)
					break;
				CLI.println(line);
			}
			String[] pieces = line.split(" ");
			if(pieces.length == 0)
				continue;
			String cmd = pieces[0];
			if(cmd.equals("move"))
			{
				if(pieces.length == 1)
				{
					CLI.printError("move-nak 2 argument kell");
					continue;
				}
				String[] pieces2 = pieces[1].split("\\.");
				if(pieces2[0].equals("A"))
					control.moveA(Integer.parseInt(pieces2[1]));
				if(pieces2[0].equals("G"))
					control.moveG(Integer.parseInt(pieces2[1]));
				
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
					continue;
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
			else if(cmd.equals("admin"))
			{
				if(pieces.length == 1 )
				{
					CLI.printError("kevés argument admin-nak");
					continue;
				}
				if(pieces[1].equals("setsun"))
				{
					if(pieces.length == 2 )
					{
						CLI.printError("kevés argument setsun-nak");
						continue;
					}
					boolean state = false;
					if(pieces[2].equals("on"))
						state = true;
					control.admin_setSunstorm(state);
				}
				else if(pieces[1].equals("setnearsun"))
				{
					if(pieces.length < 4 )
					{
						CLI.printError("kevés argument setnearsun-nak");
						continue;
					}
					int id = Integer.parseInt(pieces[2]);
					boolean state = false;
					if(pieces[3].equals("on"))
						state = true;
					control.admin_setNearSun(id, state);
				}
				else if(pieces[1].equals("setwork"))
				{
					if(pieces.length == 2 )
					{
						CLI.printError("kevés argument setwork-nek");
						continue;
					}
					boolean state = false;
					if(pieces[2].equals("on"))
						state = true;
					control.admin_setWorkers(state);
				}
			}
		}
	}
}
