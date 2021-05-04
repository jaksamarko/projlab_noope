package view;
import java.lang.Math;

public class Vec2 
{
	public int x;
	public int y;
	
	public Vec2()
	{
		x = 0;
		y = 0;
	}
	
	public Vec2(int _x, int _y)
	{
		x = _x;
		y = _y;
	}
	
	public Vec2 add(Vec2 o)
	{
		return new Vec2(x+o.x, y+o.y);
	}
	
	public Vec2 times(float f)
	{
		float productX = f * x;
		int loadIntX = (int) productX;
		float productY = f * y;
		int loadIntY = (int) productY;
		return new Vec2(loadIntX,loadIntY);
	}
	
	public double distance(Vec2 o)
	{
		double vx = o.x - x;
		double vy = o.y -y;
		return Math.sqrt(vx * vx + vy * vy);
	}
	
}
