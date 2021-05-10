package view;

import model.*;

/**
 *  Ez az osztály felelős az aszteroidák grafikus megjelentéshez szükséges adatok tárolásához
 */
public class AsterNode
{
	//Pointer a tárolt objektumra
	public Asteroid realAsteroid;
	//Pozíciója a képernyőn
	public Vec2 pos;
	public AsterNode(Asteroid _realAsteroid, Vec2 _pos)
	{
		realAsteroid = _realAsteroid;
		pos = _pos;
	}
}
