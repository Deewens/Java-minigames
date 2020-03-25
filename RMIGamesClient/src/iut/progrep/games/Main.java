package iut.progrep.games;

import java.net.URL;

import iut.progrep.games.controller.TicTacToeController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		URL fxmlURL = getClass().getResource("views/tictactoe.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
		Parent root = (Parent) fxmlLoader.load();
		TicTacToeController controller = fxmlLoader.getController();
		primaryStage.setTitle("TicTacToe");
		primaryStage.setScene(new Scene(root));
		primaryStage.setOnCloseRequest(e -> controller.onShutdown());
		/*primaryStage.setOnShowing(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				System.out.println("Ok.");
				controller.onWindowShown();
			}
			
		});*/
		primaryStage.show();
	}
}