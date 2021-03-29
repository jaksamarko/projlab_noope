package view;

public class CLI 
{
	public static void print(String text) {System.out.print(text);}
	public static void println(String text) {System.out.println(text);}
	public static void printError(String text) {println("\n!!!!!!!\nHiba: "+ text +"\\n!!!!!!!\\n");}
}
