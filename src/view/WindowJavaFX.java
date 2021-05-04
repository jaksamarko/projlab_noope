package view;

import java.util.HashMap;

import com.sun.marlin.DCollinearSimplifier;

import interfaces.ControlerAPI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WindowJavaFX extends Application {
	
	private final String[][] btnNames = {{"Move","PutBack","craftPortal"},{"Dig","createRobot","placePortal"}};
	public Button[][] btn; //2*3-as elrendezés,btnNames-ből kitalálod melyik, melyik
	public TextArea playerInfo, log;
	public GraphicsContext gc;
	public Scene scene;
	
	private final String[] imgNames = {"asteroid","bg","btn","coal","cursor","iron","portal","robot","settler","ufo","uranium"};
	public HashMap<String,Image> imgs;
	
	private double WindowW = 1280, WindowH = 720;
	private double SidebarSize = 192;
	
	private GUILogic logic = null;
	void setGUILogic(GUILogic _logic)
	{logic = _logic;}
	
	
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
        
        //instance=this;
        imgs = new HashMap<String,Image>();
        for(String str:imgNames) {
        	imgs.put(str, new Image("file:Textures/"+str+".png"));
        }
        
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key)->{
        	if(key.getCode()==KeyCode.W) 
        	{
        		logic.moveCamera(new Vec2(0,10));
        		logic.printStatus();
        	}
        	if(key.getCode()==KeyCode.S) 
        	{
        		logic.moveCamera(new Vec2(0,-10));
        		logic.printStatus();
        	}
        	if(key.getCode()==KeyCode.D) 
        	{
        		logic.moveCamera(new Vec2(-10,0));
        		logic.printStatus();
        	}
        	if(key.getCode()==KeyCode.A) 
        	{
        		logic.moveCamera(new Vec2(10,0));
        		logic.printStatus();
        	}
        	if(key.getCode()==KeyCode.F) 
        	{
        		logic.zoomIn(2.0f);
        		logic.printStatus();
        	}
        	if(key.getCode()==KeyCode.G) 
        	{
        		logic.zoomIn(0.5f);
        		logic.printStatus();
        	}
        });
		
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        //System.out.println("mouse click detected! " + mouseEvent.getX() +" "+mouseEvent.getY());
		    	logic.clickSelect(new Vec2((int)mouseEvent.getX(),(int) mouseEvent.getY()));
		    }
		});
		
		
        
        btn[0][0].setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                logic.move();
            }
        });
        
        btn[0][1].setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	
            }
        });
        btn[1][0].setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                
            }
        });
        btn[1][1].setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                
            }
        });
        btn[2][0].setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                
            }
        });
        btn[2][1].setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                
            }
        });
  
		
		GUILogic.setWindow(this);
	}
	
	private ControlerAPI control = null;
	void ActivateInput(ControlerAPI _control)
	{
		control = _control;
	}
	
	public void moveA(int ID){control.moveA(ID);}
	public void moveG(int ID){control.moveG(ID);}
	
	public void clearCanvas()
	{
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}
	
	public void drawAsteroid(Vec2 pos)
	{
		gc.drawImage(imgs.get("asteroid"), pos.x, pos.y);
	}
	public void drawCoal(Vec2 pos)
	{
		gc.drawImage(imgs.get("coal"), pos.x, pos.y);
	}
	public void drawIron(Vec2 pos)
	{
		gc.drawImage(imgs.get("iron"), pos.x, pos.y);
	}
	public void drawIce(Vec2 pos)
	{
		gc.drawImage(imgs.get("ice"), pos.x, pos.y);
	}
	public void drawUranium(Vec2 pos)
	{
		gc.drawImage(imgs.get("uranium"), pos.x, pos.y);
	}
	
	//{"asteroid","bg","btn","coal","cursor","iron","portal","robot","settler","ufo","uranium"};
	
	public void drawLine(Vec2 start, Vec2 end)
	{
		gc.setFill(Color.RED);
	    gc.setLineWidth(3.0);
	    
	    gc.strokeLine(start.x, start.y, end.x, end.y);
	}
	
	public void drawCircle(Vec2 pos, double rad)
	{
		gc.setFill(Color.RED);
		gc.fillOval(pos.x, pos.y, rad, rad);
	}
	public void drawText(Vec2 pos, String text)
	{
		gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(gc.getFont().getName(), 60));
        gc.fillText(text, pos.x, pos.y);
	}
	public void drawBG()
	{
		gc.drawImage(imgs.get("bg"), 0,0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}
	
}
