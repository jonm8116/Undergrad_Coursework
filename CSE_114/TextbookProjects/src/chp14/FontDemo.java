package chp14;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;


public class FontDemo extends Application {
	
	//I just realized that the start method isn't static
	//Should it be??
	@Override
	public void start(Stage primaryStage){
		Pane pane = new StackPane();
		
		Circle circle = new Circle();
		circle.setRadius(50);
		circle.setStroke(Color.BLACK);
		circle.setFill(new Color(0.5, 0.5, 0.5, 0.1));
		pane.getChildren().add(circle); //add circle to pane
		
		//Label
		Label label = new Label("JavaFX");
		label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		pane.getChildren().add(label); //This getChildren() and add() methods are needed
									   //in order to add properties to the fx image
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("FontDemo");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
		
	}

}
