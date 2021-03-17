package reflection;

public class Variable
{
	public Object object;
	public String name;
	
	public Variable(Object _object, String _name)
	{
		object = _object;
		name = _name;
	}
	
	public String GetTypeString()
	{
		String name = object.getClass().getName();
		String[] pieces = name.split("\\.");
		String out = "";
		for(int i = 1; i < pieces.length; i++)
			out += pieces[i];
		return out;
	}
	
	public String GetNameAndType()
	{
		return name + ": "+ GetTypeString();
	}
	
	public boolean equals(Variable other)
	{
		return other.object == object;
	}
	
	public boolean equals(Object other)
	{
		return other == object;
	}
}
