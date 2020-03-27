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
		URL fxmlURL = getClass().getResource("views/accueil.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
		Parent root = (Parent) fxmlLoader.load();
		primaryStage.setTitle("Accueil");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}