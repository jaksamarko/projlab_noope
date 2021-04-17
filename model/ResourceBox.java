package model;

import java.util.ArrayList;

/**
 * Játékbeli resource-ok tárolását úgy végzi el, hogy betartatja az ahhoz támasztott szabályokat.
 */
public class ResourceBox<T extends Resource> implements java.io.Serializable
{
	private static transient int StorageCapacityPerType= 10; 
	private ArrayList<T> store;
	public ResourceBox()
	{
		store = new ArrayList<T>();
	}
	/**
	 * Új resource belerakása a tárolóba, csak akkor lehet, ha nincs tele
	 */
	public void Add(T newItem)
	{
		if(store.size()<ResourceBox.StorageCapacityPerType)
			store.add(newItem);
	}
	/**
	 * Visza ad egy resource-ot, ha nem üres egyébként null
	 */
	public T Get()
	{
		if(store.size() == 0)
			return null;
		return store.get(store.size()-1);
	}
	/**
	 * Megadja, hogy van-e ennyi darab benne
	 */
	public boolean Has(int thatMany)
	{
		return store.size()>=thatMany;
	}
	
	/**
	 * Bizonyos számú nyersanyagot felhasznál
	 */
	public void UseUp(int i)
	{
		for(;i>0;i--)
			Remove();
	}
	/**
	 * Egy nyersanyagot töröl, ha még van.
	 */
	public void Remove()
	{
		if(store.size()>0)
			store.remove(store.size() - 1);
	}
	/**
	 * Megadja, mennyi nyersanyag van még benne.
	 */
	public int GetCount()
	{
		return store.size();
	}
}