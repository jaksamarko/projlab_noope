package view;

import java.io.*;
import java.util.*;

public class Cli {

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
}
