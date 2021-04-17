package model;

public class ID
{
	/**
	 * Minden játékban mechanikailag lényeges objekutm ennek a leszármazottja és így, minden objektumnak van egy egyedi ID-ja.
	 * ID ütközés elleni lépések a pálya betöltési szinten van.
	 * 
	 */
	private int id;
	public int GetID() {return id;}
	public void SetID(int _id) { id = _id;}
	public ID(int _id)
	{
		id = _id;
	}
}
