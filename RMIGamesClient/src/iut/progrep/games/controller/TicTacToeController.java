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
	Joueur joueur1;
	Joueur joueur2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.joueur1 = new Joueur("Deewens");
		this.joueur2 = new Joueur("Sofiane");
		
		System.setProperty("java.security.policy","file:./policies/client.policy");
		System.setProperty("java.rmi.server.codebase", "file:D:/Utilisateurs/razor/Documents/Etudes/IUT/2A/ProgRep/Projet/RMIGames/RMIGamesServer/bin");
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			int port = 8000;
			
			// rmi://localhost:port/nomService

			this.tictactoe = (TicTacToeInterface) Naming.lookup("rmi://localhost:" + port + "/tictactoe");
			this.tictactoe.rejoindrePartie(joueur1);
			this.tictactoe.rejoindrePartie(joueur2);
			this.tictactoe.lancerPartie();
		} catch(Exception e) {
			System.out.println("Erreur lors de l'initialisation du client : " + e);
			e.printStackTrace();
		}
		
		
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
		
		cases.forEach((c) -> {
			c.setOnMouseClicked(actionEvent -> {
				System.out.println(GridPane.getColumnIndex(c) + " - " + GridPane.getRowIndex(c));
				dessinerCase(c);
			});
		});
		
	}
	
	public void dessinerCase(Pane p) {
		try {
			if(this.tictactoe.getSymbole() == 'O') {
				Circle cercle = new Circle(0, 0, 50);
				cercle.setFill(Color.TRANSPARENT);
				cercle.setStroke(Color.BLACK);
				cercle.setStrokeWidth(5);
				cercle.setLayoutX(p.getWidth()/2);
				cercle.setLayoutY(p.getHeight()/2);
				p.getChildren().add(cercle);
			}
			else if(this.tictactoe.getSymbole() == 'X') {
				/*Line line1 = new Line(10, 10, 100, 50);
				Line line2 = new Line(p.getHeight(), p.getHeight(), 100, 50);
				line1.setStrokeWidth(5);
				line2.setStrokeWidth(5);*/
				
				Line line1 = new Line(10, 10, 10, 10);
				line1.endXProperty().bind(line1.widthProperty().subtract(10));
				p.getChildren().addAll(line1, line2);
			}
			
			this.tictactoe.ajouterSymbole(GridPane.getRowIndex(p), GridPane.getColumnIndex(p));
			this.tictactoe.changerJoueur();
			System.out.println("C'est à : " +this.tictactoe.getJoueurActuel().getPseudo());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
