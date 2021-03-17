package reflection;

public class Main
{
	public static void main(String[] args)
	{
		
		
		Reflector.Reset();
		
		String startText = "";
		startText+="Válasz teszt programot:"
		+ "\n0. TEMP tesztProgram"		
		+ "\n1. Settler utazik Aszteroidára"
		+ "\n2. Settler utazik Aszteroidára portálon keresztül"
		+ "\n3. Settler Fúr"
		+ "\n4. Settler bányászik"
		+ "\n5. Settler visszateszi az alapanyagot"
		+ "\n6. Settler portált épít"
		+ "\n7. Settler lehelyezi a portált"
		+ "\n8. Settler robotot épít és lehelyez"
		+ "\n9. Robotok dolgoztatva vannak"
		+ "\n10. Robotok dolgoztatva vannak, de portálba megy bele egy robot"
		+ "\n11. Napvihar történik"
		+ "\n12. Körvégi napsütés hatása Szénre (megegyezik a vaséval)"
		+ "\n13. Körvégi napsütés hatása Jégre"
		+ "\n14. Körvégi napsütés hatása Urániumra, úgy ,hogy Settler van az aszteroidán"
		+ "\n15. Körvégi napsütés hatása Urániumra, úgy, hogy Robot van az aszteroidán";
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


