package view;

import model.*;

public class AsterNode
{
	public Asteroid realAsteroid;
	public Vec2 pos;
	public AsterNode(Asteroid _realAsteroid, Vec2 _pos)
	{
		realAsteroid = _realAsteroid;
		pos = _pos;
	}
}
