package chp14;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.stage.Stage;

public class ShowHBoxVBox extends Application {

	@Override
	public void start(Stage primaryStage){
		BorderPane pane = new BorderPane();
		//Nodes
		pane.setTop(getHBox());
		pane.setLeft(getVBox());
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("ShowHBoxVBox");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
		
	}
	private HBox getHBox(){
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(15, 15, 15, 15));
		hBox.setStyle("-fx-background-color: gold");
		hBox.getChildren().add(new Button("Computer Science"));
		hBox.getChildren().add(new Button("Chemistry"));
		ImageView imageView = new ImageView("http://www.cs.armstrong.edu/liang/image/us.gif");
		hBox.getChildren().add(imageView);
		return hBox;
	}
	private VBox getVBox(){
		VBox vBox = new VBox(15);
		vBox.setPadding(new Insets(15, 5, 5, 5));
		
		Label[] courses = {new Label("CSCI 1301"), new Label("CSCI 1302"),
						   new Label("CSCI 2410"), new Label("CSCI 3242")};
		for(Label course: courses){
			VBox.setMargin(course, new Insets(0, 0, 0, 15));
			vBox.getChildren().add(course);
		}
		return vBox;
	}

}
