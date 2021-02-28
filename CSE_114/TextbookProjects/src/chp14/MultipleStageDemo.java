package chp14;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MultipleStageDemo extends Application {
	
	@Override
	public void start(Stage primaryStage){
		//Create scene and place a button in the scene
		Scene scene = new Scene(new Button("OK"), 200, 250);
		primaryStage.setTitle("MyJavaFX finally starting to work");
		primaryStage.setScene(scene);
		//The show method, shows the window
		//It sets the visibility property to true
		primaryStage.show();
		
		Stage stage = new Stage();
		stage.setTitle("Second Stage");
		stage.setScene(new Scene(new Button("New Stage"), 100, 100));
		stage.show();
	}	
	public static void main(String[] args) {
		Application.launch(args);
		
	}

}
