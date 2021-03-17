package reflection;

public class Main
{
	public static void main(String[] args)
	{
		Ref.Reset();
		
		String startText = "";
		startText+="V�lasz teszt programot:"
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


