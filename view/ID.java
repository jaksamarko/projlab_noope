package view;

public class ID
{
	private static int tempIDcounter = 0;
	public static int tempGetID() {tempIDcounter++; return tempIDcounter;}
	
	private int id;
	public int GetID() {return id;}
	public void SetID(int _id) { id = _id;}
	public ID(int _id)
	{
		id = _id;
		ID.tempGetID();
	}
}
