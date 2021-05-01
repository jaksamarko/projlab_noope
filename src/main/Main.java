package main;
import control.Controler;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import view.GUILogic;

public class Main extends Application
{
	/**
	 * A játék incializálásának a hívása itt történik. Van egy két console-ra történő kérés, amely késöbb ki lesz törölve, amint a prototípus fásinak vége
	 */
	public static void main(String[] args)
	{
		Application.launch(args);
		Game g = new Game();
		MapCreator mp = new MapCreator("m1.txt");
		GUILogic output = new GUILogic();
		Controler controler = new Controler(g,output);
		output.ActivateInput(controler);
    }

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(new Group(), Color.AQUA));
        stage.setMinWidth(640);
        stage.setMinHeight(480);
        
        stage.show();
	}
}


