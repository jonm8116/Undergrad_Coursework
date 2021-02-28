package chp15;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HandleEvent extends Application {
	
	@Override
	public void start(Stage primaryStage){
		HBox pane = new HBox(10);
		pane.setAlignment(Pos.CENTER);
		Button btOK = new Button("OK");
		Button btCancel = new Button("Cancel");
		OKHandlerClass handler1 = new OKHandlerClass();
		//The setOnAction method triggers the fire method when 
		//the particular button is pushed 
		//the fire method invokes the method within the class
		//that is the parameter of the setOnAction method
		btOK.setOnAction(handler1);
		CancelHandlerClass handler2 = new CancelHandlerClass();
		btCancel.setOnAction(handler2);
		pane.getChildren().addAll(btOK, btCancel);
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("HandleEvent");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	class OKHandlerClass implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e){
			System.out.println("OK BUTTON");
		}
	}

	class CancelHandlerClass implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e){
			System.out.println("CANCEL BUTTON");
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
		
	}

}
