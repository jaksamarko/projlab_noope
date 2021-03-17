package reflection;

public class Main
{
	public static void main(String[] args)
	{
		
		
		Reflector.Reset();
		
		String startText = "";
		startText+="V�lasz teszt programot:"
		+ "\n0. TEMP tesztProgram"		
		+ "\n1. Settler utazik Aszteroid�ra"
		+ "\n2. Settler utazik Aszteroid�ra port�lon kereszt�l"
		+ "\n3. Settler F�r"
		+ "\n4. Settler b�ny�szik"
		+ "\n5. Settler visszateszi az alapanyagot"
		+ "\n6. Settler port�lt �p�t"
		+ "\n7. Settler lehelyezi a port�lt"
		+ "\n8. Settler robotot �p�t �s lehelyez"
		+ "\n9. Robotok dolgoztatva vannak"
		+ "\n10. Robotok dolgoztatva vannak, de port�lba megy bele egy robot"
		+ "\n11. Napvihar t�rt�nik"
		+ "\n12. K�rv�gi naps�t�s hat�sa Sz�nre (megegyezik a vas�val)"
		+ "\n13. K�rv�gi naps�t�s hat�sa J�gre"
		+ "\n14. K�rv�gi naps�t�s hat�sa Ur�niumra, �gy ,hogy Settler van az aszteroid�n"
		+ "\n15. K�rv�gi naps�t�s hat�sa Ur�niumra, �gy, hogy Robot van az aszteroid�n";
		int inp = Reflector.RequestInt(startText);
		if(inp == 0)
		{
			Ball ball = new Ball();
			Reflector.addObject(ball, "ball");
			Bag bag = new Bag();
			Reflector.addObject(bag, "bag");
		
			
			Reflector.call(bag, "addBall", new Object[]{ball});
			bag.addBall(ball);
		}
    }
}


