package chp14;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

//Remember you had to go and download e(fx)clipse before this stuff could work
//You downloaded it then and only then the libraries didn't have any errors
public class MyJavaFX extends Application{
	
	@Override 
	public void start(Stage primaryStage){
		//Create scene and place button in scene
		Button btOK = new Button("OK");
		Scene scene = new Scene(btOK, 200, 250);
		primaryStage.setTitle("MyJavaFX, stupid eclipse"); //set stage title-- stupid
		primaryStage.setScene(scene); 
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		//The launch method is a static method 
		Application.launch(args);
	}

}
