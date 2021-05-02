package view;

import interfaces.ControlerAPI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Material;

public class Window extends Application
{
	private String[][] btnNames = {{"Move","PutBack","craftPortal"},{"Dig","createRobot","placePortal"}};
	
	ControlerAPI control;
	GUILogic gui;
	
	public Window(GUILogic _gui)
	{
		gui = _gui;
		Application.launch(Window.class);
	}
	
	public Window() {
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.setWidth(1280);
        stage.setHeight(720);
        
        stage.setTitle("Asteroid mining");
        
		SplitPane splitPane = new SplitPane();
		Canvas canv = new Canvas();
		
        Pane leftControl  = new Pane(canv);
        canv.widthProperty().bind(leftControl.widthProperty());
        canv.heightProperty().bind(leftControl.heightProperty());
        
        TextArea playerInfo = new TextArea();
        playerInfo.setMaxHeight(250);
        playerInfo.setMinHeight(250);
        
        Button[][] btn = new Button[2][3];
        for(int i=0;i<2;i++) {
        	for(int j=0;j<3;j++) {
        		btn[i][j] = new Button(btnNames[i][j]);
        		HBox.setHgrow(btn[i][j], Priority.ALWAYS);
        		btn[i][j].setMaxWidth(Double.MAX_VALUE);
        	}
        }
        
        VBox btnList1 = new VBox(btn[0]);
        VBox btnList2 = new VBox(btn[1]);
        HBox.setHgrow(btnList1, Priority.ALWAYS);
        HBox.setHgrow(btnList1, Priority.ALWAYS);
        
        HBox btnField = new HBox(btnList1,btnList2);
        
        TextArea log = new TextArea();
        VBox.setVgrow(log, Priority.ALWAYS);
        
        VBox rightControl = new VBox(playerInfo,btnField,log);
        splitPane.getItems().addAll(leftControl, rightControl);
        leftControl.setMinWidth(1088);
        rightControl.setMinWidth(1280-1088);
        
		stage.setScene(new Scene(splitPane, Color.AQUA));
        
        stage.show();
	}
	
	public void ActivateInput(ControlerAPI _control)
	{
		control = _control;
	}
	
	public void ClearCanvas()
	{
		
	}
	
	//TODO: Összes esemény meghívása innentől lefelé ablak eseményekkel
	public void clickEvent()
	{
		Vec2 clickPos = new Vec2(0,0); //getting actual click position from window
		gui.clickSelect(clickPos);
	}
	
	public void moveEvent()
	{
		gui.move();
	}
	public void moveA(int ID){control.moveA(ID);}
	public void moveG(int ID){control.moveG(ID);}
	
	public void drillEvent()
	{
		control.drill();
	}
	
	public void mineEvent()
	{
		control.mine();
	}
	
	public void putbackEvent(Material material)
	{
		control.putback(material);
	}
	
	public void createportalEvent()
	{
		control.createportal();
	}
	
	public void createrobotEvent()
	{
		control.createrobot();
	}
	
	public void placeportalEvent()
	{
		control.placeportal();
	}
	
}
