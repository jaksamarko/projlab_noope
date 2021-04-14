package view;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import interfaces.*;
import model.MapCreator;
import model.Material;

public class Cli_Input
{
	BufferedReader consoleReader;
	BufferedReader reader;
	boolean printRead = false;
	ControlerAPI control;
	
	public Cli_Input()
	{
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
		reader = consoleReader;
	}
	
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
	
	public String readln(){
		
		String re = null;
		try {
			re = reader.readLine();
		} catch (IOException e) {
			System.out.println("io problem");
			e.printStackTrace();}
		return re;
	}
	
	public void loadGame()
	{
		CLI.print("File to load from?\nPath: ");
		String input = readln();
		MapCreator mp = new MapCreator(input);
	}
	
	public void compareStringWithfile(String data)
	{
		reader = consoleReader;
		CLI.println("What file to chech output with?");
		String fname = readln();
		StringBuilder contentBuilder = new StringBuilder();
		 
        try (Stream<String> stream = Files.lines( Paths.get(fname), StandardCharsets.UTF_8)) 
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        String fileString = contentBuilder.toString();
        String[] dataP = data.split("\n");
        String[] fileP = fileString.split("\n");
        
        int dataI = 0;
        int fileI = 0;
        
        boolean check = true;
        boolean end = false;
        
        while(!end)
        {  	
        	if(!dataP[dataI].equals(fileP[fileI]))
        	{
        		CLI.println("「"+dataP[dataI]+"」 not same as 「"+fileP[fileI]+"」");
        		check = false;
        	}
        	dataI++;
        	fileI++;
        	
        	if(fileI > fileP.length - 1 || dataI > dataP.length - 1)
        	{
        		end = true;
        	}
        	
        	while(!end && dataP[dataI].trim().equals(""))
        	{
        		dataI++;
        		if(dataI > dataP.length - 1)
        			end = true;
        	}
        	while(!end && fileP[fileI].trim().equals(""))
        	{
        		fileI++;
        		if(fileI > fileP.length - 1)
        			end = true;
        	}
        	
        	if(fileI > fileP.length - 1 || dataI > dataP.length - 1)
        	{
        		if(!(fileI > fileP.length - 1 && dataI > dataP.length - 1))
        		{
        			CLI.println("files are not same length");
        			check = false;
        		}
        	}
        }
        CLI.println("test result: " + check);
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
				CLI.println("<From file> " + line);
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
				if(pieces[1].equals("setsunstorm"))
				{
					if(pieces.length == 2 )
					{
						CLI.printError("kevés argument setsunstorm-nak");
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
				else if(pieces[1].equals("setai"))
				{
					if(pieces.length == 2 )
					{
						CLI.printError("kevés argument setai-nek");
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
