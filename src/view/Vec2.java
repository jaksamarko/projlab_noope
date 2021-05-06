package view;
import java.lang.Math;

public class Vec2 
{
	public double x;
	public double y;
	
	public Vec2()
	{
		x = 0;
		y = 0;
	}
	
	public Vec2(double _x, double _y)
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
		double productX = f * x;
		double loadIntX = productX;
		double productY = f * y;
		double loadIntY = productY;
		return new Vec2(loadIntX,loadIntY);
	}
	
	public double distance(Vec2 o)
	{
		double vx = o.x - x;
		double vy = o.y -y;
		return Math.sqrt(vx * vx + vy * vy);
	}
	
}
