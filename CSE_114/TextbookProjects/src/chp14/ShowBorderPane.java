package chp14;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ShowBorderPane extends Application{

	@Override
	public void start(Stage primaryStage){
		//A borderpane can place a node in five regions
		//top, right, bottom, left, center
		BorderPane pane = new BorderPane();
		
		pane.setTop(new CustomPane("top"));
		pane.setRight(new CustomPane("right"));
		pane.setLeft(new CustomPane("left"));
		pane.setBottom(new CustomPane("bottom"));
		pane.setCenter(new CustomPane("center"));
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("ShowBorderPane");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
		
	}
	
	class CustomPane extends StackPane {
		public CustomPane(String title){
			getChildren().add(new Label(title));
			setStyle("-fx-border-color: blue");
			setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		}
	}
}
