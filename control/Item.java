package control;

import model.*;

public class Item<T> {
	public int ID;
	public T object;

	public Item(int _ID, T _object) {
		ID = _ID;
		object = _object;
	}
}
