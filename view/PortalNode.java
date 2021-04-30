package view;

import model.Portal;

public class PortalNode 
{
	public Portal realPortal;
	public Vec2 pos;
	public PortalNode(Portal _realPortal, Vec2 _pos)
	{
		realPortal = _realPortal;
		pos = _pos;
	}
}
