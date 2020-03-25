package iut.progrep.games.controller;

import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import iut.progrep.games.pojo.JoueurTicTacToe;
import iut.progrep.games.rmi.TicTacToeInterface;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TicTacToeController implements Initializable {
	@FXML private StackPane sp;
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
	JoueurTicTacToe joueur;

	public TicTacToeController() {
		this.joueur = new JoueurTicTacToe("Sofiane", 'O');
		
		System.setProperty("java.security.policy","file:./policies/client.policy");
		System.setProperty("java.rmi.server.codebase", "file:D:/Utilisateurs/razor/Documents/Etudes/IUT/2A/ProgRep/Projet/RMIGames/RMIGamesServer/bin");
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		
		try {
			int port = 8000;

			this.tictactoe = (TicTacToeInterface) Naming.lookup("rmi://localhost:" + port + "/tictactoe");
			this.tictactoe.rejoindrePartie(joueur);
			this.initJeu();
		} catch(Exception e) {
			System.out.println("Erreur lors de l'initialisation du client : " + e);
			e.printStackTrace();
		}
		
		
		cases.forEach((c) -> {
			c.setOnMouseClicked(actionEvent -> {
				System.out.println(GridPane.getColumnIndex(c) + " - " + GridPane.getRowIndex(c));
				if(this.tictactoe.isPartieLance()) {
					System.out.println("C'est à " + joueur.getPseudo() + " de jouer.");
					try {
						this.tictactoe.insererSymbole(GridPane.getRowIndex(c), GridPane.getColumnIndex(c));
						this.tictactoe.afficherGrilleCmd();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dessinerCase(c);
				}
				else {
					System.out.println("La partie n'a pas débuté.");
				}
			});
		});
	}
	
	public void jeu() {
		while(this.tictactoe.isPartieLance()) {
			System.out.println("C'est à " + joueur.getPseudo() + " de jouer.");
			//this.tictactoe.insererSymbole(ligne, colonne)
		}
	}

	public void dessinerCase(Pane p) {
		try {
			if(this.tictactoe.getJoueurActuel().getSymbole() == 'O') {
				Circle cercle = new Circle(0, 0, 50);
				cercle.setFill(Color.TRANSPARENT);
				cercle.setStroke(Color.BLACK);
				cercle.setStrokeWidth(5);
				cercle.setLayoutX(p.getWidth()/2);
				System.out.println(p.getWidth());
				cercle.setLayoutY(p.getHeight()/2);
				p.getChildren().add(cercle);
			}
			else if(this.tictactoe.getJoueurActuel().getSymbole() == 'X') {
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
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void initJeu() {
		final Service<Boolean> lancerService = new Service<Boolean>() {
			@Override
			protected Task<Boolean> createTask() {
				// TODO Auto-generated method stub
				return new Task<Boolean>() {
					@Override
					protected Boolean call() throws Exception {
						tictactoe.lancerPartie();

						return true;
					}
					
				};
			}
			
		};
		
		lancerService.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            switch (newValue) {
                case FAILED:
                	System.out.println("Le service a n'a pas réussit à se lancer.");
                	break;
                case CANCELLED:
                case SUCCEEDED:
                	System.out.println("Le service a été lancé avec succès.");
                	//System.out.println(lancerService.getValue());
                    break;
            }
        });
		
		lancerService.start();
		
		final Service<Void> init = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				// TODO Auto-generated method stub
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						Label attenteMsg = new Label("En attente d'un autre joueur.");
						while(!tictactoe.isPartieLance()) {
							if(!sp.getChildren().contains(attenteMsg)) {
								sp.getChildren().add(attenteMsg);
							}
						}
						if(tictactoe.isPartieLance()) {
							if(sp.getChildren().contains(attenteMsg)) {
								sp.getChildren().remove(attenteMsg);
							}
							
						}
						return null;
					}
					
				};
			}
			
		};
		
		init.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            switch (newValue) {
                case FAILED:
                	System.out.println("Le service init ne s'est pas effectué correctement.");
                	System.out.println(init.getException());
                	break;
                case CANCELLED:
                case SUCCEEDED:
                	System.out.println("Le service init s'est effectué correctement.");
                    break;
            }
        });
		init.start();
	}
	
	public void onShutdown() {
		try {
			System.out.println(joueur.getPseudo() + " a quitté la partie.");
			this.tictactoe.quitterPartie(joueur);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}