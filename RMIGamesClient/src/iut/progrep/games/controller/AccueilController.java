package iut.progrep.games.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AccueilController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void ticTacToeClicked() {
		try {
	        Stage stage = new Stage();
	        stage.initModality(Modality.APPLICATION_MODAL);
	        
	        URL fxmlURL = getClass().getResource("/iut/progrep/games/views/tictactoe.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Parent root = (Parent) fxmlLoader.load();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
			stage.setTitle("TicTacToe");
			
			TicTacToeController controller = fxmlLoader.getController();
			controller.setParentStage(stage);
			
			stage.setOnCloseRequest(e -> controller.onShutdown());
			/*primaryStage.setOnShowing(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					System.out.println("Ok.");
					controller.onWindowShown();
				}
			});*/
			stage.show();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void allumettesClicked() {
		try {
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/iut/progrep/games/views/allumette.fxml"));
	
			stage.setTitle("Allumettes");
			stage.setScene(new Scene(root));
			stage.show();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
