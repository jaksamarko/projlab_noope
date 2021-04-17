package view;

/**
 * console-ra kiírást megvalósító osztály
 */
public class CLI 
{
	/**
	 * Console-ra kiír függvények
	 */
	public static void print(String text) {System.out.print(text);}
	public static void println(String text) {System.out.println(text);}
	public static void printError(String text) {println("\n!!!!!!!\nHiba: "+ text +"\\n!!!!!!!\\n");}
}
