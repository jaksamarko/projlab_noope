package reflection;

public class Main
{
	public static void main(String[] args)
	{
		Ref.Reset();
		
		String startText = "";
		startText+="Válasz teszt programot:"
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
		
		int inp = Ref.RequestInt(startText);
		switch(inp) {
		  case 1: TestPrograms.SettlerTravelAsteroid(); break;
		  case 2: TestPrograms.SettlerTravelPortal(); break;
		  case 3: TestPrograms.SettlerDrill(); break;
		  case 4: TestPrograms.SettlerMine(); break;
		  case 5: TestPrograms.SettlerPutBack(); break;
		  case 6: TestPrograms.SettlerBuildPortal(); break;
		  case 7: TestPrograms.SettlerPlacePortal(); break;
		  case 8: TestPrograms.SettlerBuildAndPlaceRobot(); break;
		  case 9: TestPrograms.RobotWorkAsteroid(); break;
		  case 10: TestPrograms.RobotWorkPortal(); break;
		  case 11: TestPrograms.Sunstorm(); break;
		  case 12: TestPrograms.ExposeCoal(); break;
		  case 13: TestPrograms.ExposeIce(); break;
		  case 14: TestPrograms.ExposeUraniumSettler(); break;
		  case 15: TestPrograms.ExposeUraniumRobot(); break;
		}
    }
}


