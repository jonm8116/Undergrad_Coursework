package chp14;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;

public class ShowText extends Application {
	
	@Override
	public void start(Stage primaryStage){
		Pane pane = new Pane();
		pane.setPadding(new Insets(5, 5, 5, 5));
		//Text Object
		Text text1 = new Text(20, 20, "Programming is... I'm not saying that");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
		pane.getChildren().add(text1);
		
		Text text2 = new Text(60, 60, "Programming is... \n Display Text");
		pane.getChildren().add(text2);
		
		Text text3 = new Text(10, 100, "Programming is.... \n OH HECK NA");
		text3.setFill(Color.AQUA);
		text3.setUnderline(true);
		text3.setStrikethrough(true);
		pane.getChildren().add(text3);
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Show Text");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {		
		Application.launch(args);
		
	}

}
