package view;

import model.*;

public class AsterNode
{
	private Asteroid realAsteroid;
	private Vec2 pos;
	public AsterNode(Asteroid _realAsteroid, Vec2 _pos)
	{
		realAsteroid = _realAsteroid;
		pos = _pos;
	}
}
