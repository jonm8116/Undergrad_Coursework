package chp14;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class ButtonInPane extends Application {
	
	@Override
	public void start(Stage primaryStage){
		//In this program a button object is not made explicitly 
		//A stackpane object is made as the first object 
		StackPane pane = new StackPane();
		//The getChildren method returns an Observable List 
		//This Observable List is similar to an ArrayList
		pane.getChildren().add(new Button("OK"));
		Scene scene = new Scene(pane, 400, 50); //These int parameters determine dimensions of window
		primaryStage.setTitle("Button in a pane");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	//You don't need a main method to have the window pop up 
	
	
//	public static void main(String[] args) {
//		Application.launch(args);
//	}

}
