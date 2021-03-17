package reflection;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Reflector
{
	public static void Reset()
	{
		instance = new Reflector(); 
	}
	
	public static void addObject(Object object, String name)
	{
		if(instance == null) Reset();
		instance.o_addObject(object, name);
	}
	
	public static void call(Object called, String func, Object args[])
	{
		if(instance == null) Reset();
		instance.o_call(called, func, args);
	}
	
	public static void Return()
	{
		if(instance == null) Reset();
		instance.o_Return();
	}
	
	public static void Return(Object object)
	{
		if(instance == null) Reset();
		instance.o_Return(object);
	}
	
	public static void Return(String name, String value)
	{
		if(instance == null) Reset();
		instance.o_Return(name, value);
	}
	
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
	
//----------------------------------------------
	private static Reflector instance = null;
	
	
	private ArrayList<Variable> allVars;
	private ArrayList<Variable> callStack;
	
	private Reflector()
	{
		allVars = new ArrayList<Variable>();
		callStack = new ArrayList<Variable>();
	}
	
	private Variable GetTopOfStack()
	{
		if(callStack.size()>0)
			return callStack.get(callStack.size() - 1);
		return null;
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
	
	private String getStackIndentation(int shift)
	{
		String out = "";
		for(int i = 0; i < callStack.size()+shift; i++)
			out += "\t";
		return out;
	}

	private String getStackIndentation()
	{
		return getStackIndentation(0);
	}
	
	private void addUnique(Object o, String name)
	{
		for(Variable v: allVars)
		{
			if(v.equals(o))
				return;
		}
		allVars.add(new Variable(o, name));
	}
	
	private void o_addObject(Object object, String name)
	{
		Variable var = new Variable(object, name);
		allVars.add(var);
		
		System.out.println("Object Created: "+ var.GetNameAndType());
	}
	
	private void o_call(Object called, String func, Object args[])
	{
		Variable calledVar = GetVar(called);
		Variable caller = GetTopOfStack();
		System.out.println(getStackIndentation()+(caller == null ? "Main": caller.name) + " calls " +func + "("+ ObjectsToArgString(args) + ")"+ " on "+ calledVar.name);
		
		callStack.add(calledVar);
	}
	
	private void o_Return()
	{
		callStack.remove(callStack.size() - 1);
		System.out.println(getStackIndentation() + "Return");
	}
	
	private void o_Return(Object object)
	{
		callStack.remove(callStack.size() - 1);
		System.out.println(getStackIndentation() + "Return "+ GetVar(object).name);
	}
	
	private void o_Return(String name, String value)
	{
		callStack.remove(callStack.size() - 1);
		System.out.println(getStackIndentation() + "Return "+ name+":="+value);
	}
}
