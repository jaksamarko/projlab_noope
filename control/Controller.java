package control;

import model.Game;
import model.Player;

public class Controller {

	// game init
	Game game = new Game();

	public void createPlayer(String name) {
		Game.addPlayer();
	}

	private void doaction(String action) {
		switch (action) {
		case "createplayer": {
			createPlayer("arg_cli");
		}
		}
	}

	//játékosok része
	public void playersWork() {
		game.playersStart();

		for (Player p : game.getPlayers()) {
			int unitcnt = p.getPsettlers().size();

			for (int i = 0; i < unitcnt; i++) {
				if (p.getPsettler(i).GetStepDone()) {
					i++;
				} else {
					doaction("input"); // chosen action by input, passed by cli
				}
			}

		}
	}
	// első játékos lép
	// move
	// drill
	// mine
	// place portal
	// place back material
	// craft

	// ha egy játékos minden unitaja lépett, nextplayer

	// ....

	// utolsó játékos is lépett, game step

	// JÁTÉK RÉSZE

	// robotok dolgoznak

	// körvégi dolgok meghívódnak

}
