package iut.progrep.games.controller;

import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.rmi.TicTacToeInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.WindowEvent;

public class TicTacToeController implements Initializable {
	@FXML private GridPane grille;
	@FXML private ObservableList<Pane> cases;
	@FXML private Pane case0_0;
	@FXML private Pane case1_0;
	@FXML private Pane case2_0;
	@FXML private Pane case0_1;
	@FXML private Pane case1_1;
	@FXML private Pane case2_1;
	@FXML private Pane case0_2;
	@FXML private Pane case1_2;
	@FXML private Pane case2_2;
	
	TicTacToeInterface tictactoe;
	Joueur joueur;

	public TicTacToeController() {
		this.joueur = new Joueur("Deewens");
		
		System.setProperty("java.security.policy","file:./policies/client.policy");
		System.setProperty("java.rmi.server.codebase", "file:D:/Utilisateurs/razor/Documents/Etudes/IUT/2A/ProgRep/Projet/RMIGames/RMIGamesServer/bin");
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			int port = 8000;

			this.tictactoe = (TicTacToeInterface) Naming.lookup("rmi://localhost:" + port + "/tictactoe");
		} catch(Exception e) {
			System.out.println("Erreur lors de l'initialisation du client : " + e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		/*Task<Integer> attenteJoueurService = new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
				try {
					this.tictactoe.rejoindrePartie(joueur);
					while(!this.tictactoe.lancerPartie()) {
						System.out.println("En attente de l'autre joueur.");
						Thread.sleep(1000);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return null;
			}
		};*/
			
		this.cases = FXCollections.observableList(new ArrayList<>());
		cases.add(case0_0);
		cases.add(case1_0);
		cases.add(case2_0);
		cases.add(case0_1);
		cases.add(case1_1);
		cases.add(case2_1);
		cases.add(case0_2);
		cases.add(case1_2);
		cases.add(case2_2);
		
		System.out.println(cases.get(0).getWidth());
		
		cases.forEach((c) -> {
			c.setOnMouseClicked(actionEvent -> {
				System.out.println(GridPane.getColumnIndex(c) + " - " + GridPane.getRowIndex(c));
				dessinerCase(c);
			});
		});
		System.out.println("eh oui test");
	}

	
	public void dessinerCase(Pane p) {
		try {
			if(this.tictactoe.getSymbole() == 'O') {
				Circle cercle = new Circle(0, 0, 50);
				cercle.setFill(Color.TRANSPARENT);
				cercle.setStroke(Color.BLACK);
				cercle.setStrokeWidth(5);
				cercle.setLayoutX(p.getWidth()/2);
				System.out.println(p.getWidth());
				cercle.setLayoutY(p.getHeight()/2);
				p.getChildren().add(cercle);
			}
			else if(this.tictactoe.getSymbole() == 'X') {
				Line line1 = new Line(10, 10, 10, 10);
				line1.endXProperty().bind(p.widthProperty().subtract(10));
				line1.endYProperty().bind(p.heightProperty().subtract(10));
				line1.setStrokeWidth(5);
				
				Line line2 = new Line(10, 10, 10, 10);
				line2.startXProperty().bind(p.widthProperty().subtract(10));
				line2.endYProperty().bind(p.heightProperty().subtract(10));
				line2.setStrokeWidth(5);
				p.getChildren().addAll(line1, line2);
			}
			this.tictactoe.ajouterSymbole(GridPane.getRowIndex(p), GridPane.getColumnIndex(p));
			this.tictactoe.changerJoueur();
			System.out.println("C'est à : " +this.tictactoe.getJoueurActuel().getPseudo());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void onWindowShown() {
		try {
			this.tictactoe.rejoindrePartie(joueur);
			while(!this.tictactoe.lancerPartie()) {
				System.out.println("En attente de l'autre joueur.");
				Thread.sleep(1000);
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}