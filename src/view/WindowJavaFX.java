package view;

import java.util.HashMap;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WindowJavaFX extends Application {
	public static WindowJavaFX instance;
	
	private final String[][] btnNames = {{"Move","PutBack","craftPortal"},{"Dig","createRobot","placePortal"}};
	public Button[][] btn; //2*3-as elrendezés,btnNames-ből kitalálod melyik, melyik
	public TextArea playerInfo, log;
	public GraphicsContext gc;
	public Scene scene;
	
	private final String[] imgNames = {"asteroid","bg","btn","coal","cursor","iron","portal","robot","settler","ufo","uranium"};
	public HashMap<String,Image> imgs;
	
	private double WindowW = 1280, WindowH = 720;
	private double SidebarSize = 192;
	
	private Window window = null;
	void setWindow(Window _window) {window = _window;}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setMinWidth(WindowW);
        stage.setMinHeight(WindowH);
        stage.setWidth(WindowW);
        stage.setHeight(WindowH);
        
        stage.setForceIntegerRenderScale(true);
        stage.setResizable(false);
        Rectangle2D scrR = Screen.getPrimary().getBounds();
        stage.setX((scrR.getWidth()-WindowW)/2);
        stage.setY((scrR.getHeight()-WindowH)/2);
        stage.setTitle("Asteroid mining");
        
		SplitPane splitPane = new SplitPane();
		Canvas canv = new Canvas();
		gc = canv.getGraphicsContext2D();
		
        Pane leftControl  = new Pane(canv);
        canv.widthProperty().bind(leftControl.widthProperty());
        canv.heightProperty().bind(leftControl.heightProperty());
        
        playerInfo = new TextArea();
        playerInfo.setMaxHeight(250);
        playerInfo.setMinHeight(250);
        
        btn = new Button[2][3];
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
        
        log = new TextArea();
        VBox.setVgrow(log, Priority.ALWAYS);
        
        VBox rightControl = new VBox(playerInfo,btnField,log);
        splitPane.getItems().addAll(leftControl, rightControl);
        leftControl.setMinWidth(1280-SidebarSize);
        rightControl.setMinWidth(SidebarSize);
        
        scene = new Scene(splitPane, Color.AQUA);
        
		stage.setScene(scene);
        stage.show();
        
        instance=this;
        imgs = new HashMap<String,Image>();
        for(String str:imgNames) {
        	imgs.put(str, new Image("file:Textures/"+str+".png"));
        }
        
		instance.gc.drawImage(instance.imgs.get("asteroid"), 0, 0);
		instance.scene.addEventHandler(KeyEvent.KEY_PRESSED, (key)->{
        	if(key.getCode()==KeyCode.W) {
        		
        	}
        });
	}
}
