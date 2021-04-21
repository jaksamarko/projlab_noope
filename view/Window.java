package view;

import java.util.ArrayList;

public class Window
{
	public void DrawAll(ArrayList<AsterNode> nodes)
	{
		DrawLines(nodes);
		for(AsterNode node: nodes)
			DrawAsteroid(node);
		DrawPlayerInventoryUI();
	}
	
	public void DrawLines(ArrayList<AsterNode> nodes)
	{
		
	}
	
	public void DrawAsteroid(AsterNode node)
	{
		
	}
	
	public void DrawPlayerInventoryUI()
	{
		
	}
}
