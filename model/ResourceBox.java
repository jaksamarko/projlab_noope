package model;

import java.util.ArrayList;

public class ResourceBox<T extends Resource> implements java.io.Serializable
{
	private static transient int StorageCapacityPerType= 10; 
	private ArrayList<T> store;
	public ResourceBox()
	{
		store = new ArrayList<T>();
	}
	public void Add(T newItem)
	{
		if(store.size()<ResourceBox.StorageCapacityPerType)
			store.add(newItem);
	}
	public T Get()
	{
		if(store.size() == 0)
			return null;
		return store.get(store.size()-1);
	}
	public boolean Has(int thatMany)
	{
		return store.size()>=thatMany;
	}
	public void UseUp(int i)
	{
		for(;i>0;i--)
			Remove();
	}
	public void Remove()
	{
		if(store.size()>0)
			store.remove(store.size() - 1);
	}
	public int GetCount()
	{
		return store.size();
	}
}