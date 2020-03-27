package iut.progrep.games.controller;

import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import iut.progrep.games.pojo.JoueurTicTacToe;
import iut.progrep.games.rmi.TicTacToeInterface;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

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
	
	Label attenteMsg = new Label("En attente d'un autre joueur.");
	
	TicTacToeInterface tictactoe;
	JoueurTicTacToe joueur;
	
	Stage pStage;

	public TicTacToeController() {
		this.joueur = new JoueurTicTacToe("Sofiane", 'X');
		
		System.setProperty("java.security.policy","file:./policies/client.policy");
		System.setProperty("java.rmi.server.codebase", "file:D:/Utilisateurs/razor/Documents/Etudes/IUT/2A/ProgRep/Projet/RMIGames/RMIGamesServer/bin");
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
	}
	
	public void setParentStage(Stage pStage) {
		this.pStage = pStage;
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
		
		try {
			int port = 8000;

			this.tictactoe = (TicTacToeInterface) Naming.lookup("rmi://localhost:" + port + "/tictactoe");
		} catch(Exception e) {
			System.out.println("Erreur lors de l'initialisation du client : " + e);
			e.printStackTrace();
		}
		
		try {
			if(!this.tictactoe.rejoindrePartie(joueur)) {
				this.joueur.setSymbole('O');
				this.tictactoe.rejoindrePartie(joueur);
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.initJeu();
		
		cases.forEach((c) -> {
			c.setOnMouseClicked(actionEvent -> {
				System.out.println(GridPane.getColumnIndex(c) + " - " + GridPane.getRowIndex(c));	
				try {
					if(this.tictactoe.isPartieLance()) {
						if(this.joueur.getSymbole() == this.tictactoe.getJoueurActuel().getSymbole()) {
							boolean insertion = tictactoe.insererSymbole(GridPane.getRowIndex(c), GridPane.getColumnIndex(c));
							System.out.println(Boolean.toString(insertion));
							if(insertion == false) {
								Alert alert = new Alert(AlertType.ERROR);
								alert.initOwner(pStage);
								alert.setTitle("Erreur");
								alert.setHeaderText("Impossible d'insérer dans cette case");
								alert.setContentText("Cette case est déjà remplit.");
								alert.showAndWait();
							}
							else {
								tictactoe.setClick(true);
								tictactoe.afficherGrilleCmd();
								tictactoe.changerJoueur();
							}
						}
						else {
							Alert alert = new Alert(AlertType.ERROR);
							alert.initOwner(pStage);
							alert.setTitle("Erreur");
							alert.setHeaderText("Vous ne pouvez pas jouer");
							alert.setContentText("Ce n'est pas à votre tour de jouer !");
							alert.showAndWait();
						}
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		});
		
		this.jeu();
	}
	
	public void jeu() {
		final Service<Void> jeu = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				// TODO Auto-generated method stub
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {						
						while(true) {
							if(tictactoe.isPartieLance()) {
								if(tictactoe.isClick()) {
									System.out.println("Clické");
									tictactoe.setClick(false);

									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											try {
												char[][] grilleServeur = tictactoe.getGrille();
												for(int i = 0; i<grilleServeur.length;i++) {
													for(int j = 0; j<grilleServeur[i].length;j++) {
														if(grilleServeur[i][j] == 'X') {
															Pane p = (Pane) getNodeByRowColumnIndex(i, j, grille);
															dessinerCase(p, 'X');
														}
														
														if(grilleServeur[i][j] == 'O') {
															Pane p = (Pane) getNodeByRowColumnIndex(i, j, grille);
															dessinerCase(p, 'O');
														}
													}
												}
											} catch (RemoteException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
										}
										
									});
									
									if(tictactoe.verifGagnant()) {
										if(tictactoe.getJoueurActuel().getSymbole() == joueur.getSymbole()) {
											System.out.println("Le joueur " + tictactoe.getJoueurActuel().getPseudo() + " a gagné ! Bravo !");
											Platform.runLater(new Runnable() {

												@Override
												public void run() {
													Alert alert = new Alert(AlertType.INFORMATION);
													alert.initOwner(pStage);
													alert.setTitle("Fin du jeu");
													alert.setHeaderText("La partie est terminé");
													alert.setContentText("Félicitation ! Vous avez gagné !");
													alert.showAndWait();
													pStage.close();
												}
												
											});

										}
										else {
											System.out.println("Le joueur " + tictactoe.getJoueurActuel().getPseudo() + " a perdu ! Nul !");
											Platform.runLater(new Runnable() {

												@Override
												public void run() {
													Alert alert = new Alert(AlertType.INFORMATION);
													alert.initOwner(pStage);
													alert.setTitle("Fin du jeu");
													alert.setHeaderText("La partie est terminé");
													alert.setContentText("Malheureusement, vous avez perdu !");
													alert.showAndWait();
													pStage.close();
												}
												
											});
										}
									}
									else {
										if(tictactoe.verifGrilleNulle()) {
											System.out.println("Egalité !");
											
											Platform.runLater(new Runnable() {

												@Override
												public void run() {
													Alert alert = new Alert(AlertType.INFORMATION);
													alert.initOwner(pStage);
													alert.setTitle("Fin du jeu");
													alert.setHeaderText("La partie est terminé");
													alert.setContentText("Egalité !");
													alert.showAndWait();
													pStage.close();
												}
												
											});
											
										}
									}
								}
							}
						}
					}
				};
			}
		};
		
		jeu.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            switch (newValue) {
                case FAILED:
                	System.out.println("Le service 'jeu' n'à pas réussit à se lancer.");
                	jeu.getException().printStackTrace();
                	break;
                case CANCELLED:
                case SUCCEEDED:
                	System.out.println("Le service jeu a été lancé avec succès.");
                	//System.out.println(lancerService.getValue());
                    break;
            }
        });
		
		jeu.start();
	}
	
	public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
	    Node result = null;
	    ObservableList<Node> childrens = gridPane.getChildren();

	    for (Node node : childrens) {
	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
	            result = node;
	            break;
	        }
	    }
	    return result;
	}

	public void dessinerCase(Pane p, char symbole) {
		if(symbole == 'O') {
			Circle cercle = new Circle(0, 0, 50);
			cercle.setFill(Color.TRANSPARENT);
			cercle.setStroke(Color.BLACK);
			cercle.setStrokeWidth(5);
			cercle.setLayoutX(p.getWidth()/2);
			System.out.println(p.getWidth());
			cercle.setLayoutY(p.getHeight()/2);
			p.getChildren().add(cercle);
		}
		else if(symbole == 'X') {
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
	}
	
	public void initJeu() {
		final Service<Boolean> lancerService = new Service<Boolean>() {
			@Override
			protected Task<Boolean> createTask() {
				// TODO Auto-generated method stub
				return new Task<Boolean>() {
					@Override
					protected Boolean call() throws Exception {
						System.out.println("Avant lancer " + tictactoe.isPartieLance());
						tictactoe.lancerPartie();
						System.out.println("Après lancé " + tictactoe.isPartieLance());

						return true;
					}
					
				};
			}
			
		};
		
		lancerService.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            switch (newValue) {
                case FAILED:
                	System.out.println("Le service a n'a pas réussit à se lancer.");
                	lancerService.getException().printStackTrace();
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
						
						while(tictactoe.isPartieLance() == false) {
							if(!sp.getChildren().contains(attenteMsg)) {
								sp.getChildren().add(attenteMsg);
							}
						}
						
						Platform.runLater(new Runnable() {
						    @Override
						    public void run() {
								try {
									if(tictactoe.isPartieLance() == true) {
										System.out.println("Remove");
										sp.getChildren().remove(attenteMsg);
									}
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						    }
						});

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
				try {
					System.out.println(this.tictactoe.isPartieLance());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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