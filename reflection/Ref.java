package reflection;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * 
 * singleton, nem kell init-elni
 *
 */
public class Ref 
{
	/**
	 * Meg kell h�vni, ha �j objektum-ot hozol l�tre. (ut�na)
	 * @param object
	 * @param name
	 */
	
	public static void Created(Object object, String name)
	{
		if(instance == null) Reset();
		instance.o_addObject(object, name);
	}
	/**
	 * Ott kell lennie minden met�dus els� sor�ban. args: (this, "met�dus neve", argumentum)
	 * @param called
	 * @param func
	 * @param arg
	 */
	
	public static void Call(Object called, String func, Object arg)
	{
		if(instance == null) Reset();
		instance.o_call(called, func, arg);
	}
	/**
	 * Meg kell h�vni minden void met�dus v�g�n, �s a teljes algirtumus v�g�n is.
	 */
	public static void Return()
	{
		if(instance == null) Reset();
		instance.o_Return();
	}
	
	/**
	 * Meg kell h�vni, ha a met�dus objektum t�pussal t�r vissza
	 * @param object
	 */
	public static void Return(Object object)
	{
		if(instance == null) Reset();
		instance.o_Return(object);
	}
	
	/**
	 * Meg kell h�vni, hogyha a met�dus primit�vvel t�r vissza
	 * @param name
	 * @param value
	 */
	public static void Return(String name, String value)
	{
		if(instance == null) Reset();
		instance.o_Return(name, value);
	}
	
	/**
	 * Az argumentumban megadott sz�veget ki�rja a konzolra �s v�r v�laszra majd azt visszadja a vissza t�r�si �rt�kk�nt
	 * figyelemben tartja az indent�l�st
	 * @param requestText
	 * @return
	 */
	public static boolean RequestBool(String requestText)
	{
		System.out.print(instance.getStackIndentation()+requestText+"\n"+instance.getStackIndentation());
		BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));   
		String re = "";
		try {
			re = obj.readLine();
		} catch (IOException e) {
			System.out.println("io problem");
			e.printStackTrace();}
		return (Boolean.parseBoolean(re));
	}
	/**
	 * Az argumentumban megadott int-et ki�rja a konzolra �s v�r v�laszra majd azt visszadja a vissza t�r�si �rt�kk�nt
	 * figyelemben tartja az indent�l�st
	 * @param requestText
	 * @return
	 */
	public static int RequestInt(String requestText)
	{
		System.out.print(instance.getStackIndentation()+requestText+"\n"+instance.getStackIndentation());
		BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));   
		String re = "";
		try {
			re = obj.readLine();
		} catch (IOException e) {
			System.out.println("io problem");
			e.printStackTrace();}
		return (Integer.parseInt(re));
	}
	
	/**
	 * �jra ind�tja az eg�sz singleton-t
	 */
	public static void Reset()
	{
		instance = new Ref(); 
	}
	
	public static Object nullObject;
	
	public static void Return(String name, boolean value)
	{
		Return(name, ""+value);
	}
	public static void Return(String name, int value)
	{
		Return(name, ""+value);
	}
	
	//Innent�l lefele a nem fontos
//----------------------------------------------
	private static Ref instance = null;
	
	private int arrowLength= 2;
	private Variable baseCaller; 
	
	private ArrayList<Variable> allVars;
	private ArrayList<Variable> callStack;
	private int indent = 0;
	
	private void addToStack(Variable v)
	{
		Variable prevTop = GetTopOfStack();
		callStack.add(v);
		indent += arrowLength + prevTop.name.length(); 
	}
	
	private void removeFromStack()
	{
		callStack.remove(callStack.size() - 1);
		indent -= (arrowLength + GetTopOfStack().name.length());
	}
	
	private Ref()
	{
		allVars = new ArrayList<Variable>();
		callStack = new ArrayList<Variable>();
		baseCaller = new Variable(new Object(), "Main");
		nullObject = new Object();
		allVars.add(new Variable(nullObject,"null"));
	}
	
	private Variable GetTopOfStack()
	{
		if(callStack.size()>0)
			return callStack.get(callStack.size() - 1);
		return baseCaller;
	}
	
	private Variable GetVar(Object o)
	{
		for(Variable v: allVars)
		{
			if(v.equals(o))
				return v;
		}
		return null;
	}
	
	private String ObjectsToArgString(Object args[])
	{
		if(args.length == 0)
			return "";
		String re = GetVar(args[0]).name;
		for(int i = 1; i < args.length; i++)
		{
			re += ", " + GetVar(args[i]).name;
		}
		return re;
	}

	private String getStackIndentation()
	{
		String re = "";
		for(int i = 0; i < indent; i++)
			re += " ";
		return re;
	}
	
	private void o_addObject(Object object, String name)
	{
		Variable var = new Variable(object, name);
		allVars.add(var);
		System.out.println(getStackIndentation()+"<Created> "+ var.GetNameAndType());
	}
	
	private void o_call(Object called, String func, Object arg)
	{
		Variable calledVar = GetVar(called);
		Variable caller = GetTopOfStack();
		System.out.println(getStackIndentation()+ caller.name + "->"+ calledVar.name +"."+func + "("+ (arg == null ? "": GetVar(arg).name) + ")");
		
		addToStack(calledVar);
	}
	
	private void o_Return()
	{
		if(callStack.size() == 0)
		{
			System.out.println("END");
			return;
		}
		System.out.println(getStackIndentation() + "Return");
		removeFromStack();
	}
	
	private void o_Return(Object object)
	{
		System.out.println(getStackIndentation() + "Return "+ GetVar(object).name);
		removeFromStack();
	}
	
	private void o_Return(String name, String value)
	{
		System.out.println(getStackIndentation() + "Return "+ name+":="+value);
		removeFromStack();
	}
}





/*private void addUnique(Object o, String name)
{
	for(Variable v: allVars)
	{
		if(v.equals(o))
			return;
	}
	allVars.add(new Variable(o, name));
}*/

/*private String getStackIndentation(int shift)
{
	String out = "";
	for(int i = 0; i < callStack.size()+shift; i++)
		out += "    ";
	return out;
}*/
