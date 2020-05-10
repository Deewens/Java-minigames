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
		// Insertion de chaque cases dans une observableList afin de lui assigner un Listener
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
		
		
		// Si le joueur ne peut rejoindre la partie avec le premier symbole, on change son symboel et on r�essaie. (Ce qui veut dire que le symbole �tait d�j� utilis�)
		try {
			if(!this.tictactoe.rejoindrePartie(joueur)) {
				this.joueur.setSymbole('O');
				this.tictactoe.rejoindrePartie(joueur);
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// On initialise le jeu en lancant des threads.
		this.initJeu();
		
		// Algorithme du jeu qui se lance d�s qu'un client clique sur une case, ajout d'un listener sur l'observableList
		cases.forEach((c) -> {
			c.setOnMouseClicked(actionEvent -> {
				System.out.println(GridPane.getColumnIndex(c) + " - " + GridPane.getRowIndex(c));	
				try {
					if(this.tictactoe.isPartieLance()) {
						if(this.joueur.getSymbole() == this.tictactoe.getJoueurActuel().getSymbole()) { // On v�rifie si c'est au tour du client de jouer
							boolean insertion = tictactoe.insererSymbole(GridPane.getRowIndex(c), GridPane.getColumnIndex(c));
							System.out.println(Boolean.toString(insertion));
							if(insertion == false) { // La case est d�j� remplit
								Alert alert = new Alert(AlertType.ERROR);
								alert.initOwner(pStage);
								alert.setTitle("Erreur");
								alert.setHeaderText("Impossible d'ins�rer dans cette case");
								alert.setContentText("Cette case est d�j� remplit.");
								alert.showAndWait();
							}
							else { // La case peut se remplir
								tictactoe.setClick(true); // On consid�re donc que le client � cliqu�, ce qui permet d'effectuer la suite du jeu dans la boucle infini du thread "jeu" � la ligne 150
								tictactoe.afficherGrilleCmd(); // Affiche la grille dans la ligen de commande pour v�rifier
								tictactoe.changerJoueur(); // Comme le joueur vient de jouer, on change le tour pour passer � l'autre
							}
						}
						else { // Si ce n'est pas au tour du client, message d'erreur
							Alert alert = new Alert(AlertType.ERROR);
							alert.initOwner(pStage);
							alert.setTitle("Erreur");
							alert.setHeaderText("Vous ne pouvez pas jouer");
							alert.setContentText("Ce n'est pas � votre tour de jouer !");
							alert.showAndWait();
						}
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		});
		
		// Lancement du thread qui s'occupe d'effectuer l'algorithme du jeu en fonction du retour des fonctions faites dans le Listener
		// un "Listener artificiel"
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
						while(true) { // boucle infini v�rifiant continuellement si le joueur a cliqu� pour pouvoir syncroniser les deux interfaces dans ce cas
							if(tictactoe.isPartieLance()) { // On effectue la suite seulement si la partie est lanc�
								if(tictactoe.isClick()) { // Si le joueur a cliqu�, on peut effectuer la suite
									System.out.println("Click�");
									tictactoe.setClick(false); // On peut remettre false au boolean "click" du serveur

									/*
									 *  Cette partie de code r�cup�re l'array � deux dimensions qui a �t� mis � jour dans le serveur 
									 *  pour ensuite mettre � jour l'interface des deux clients en fonction de cet Array
									 */
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
									
									if(tictactoe.verifGagnant()) { // Ensuite, on v�rifie s'il y a un gagnant
										if(tictactoe.getJoueurActuel().getSymbole() == joueur.getSymbole()) { // Si ce client est le joueur gagnant, on affiche qu'il a gagn�
											System.out.println("Le joueur " + tictactoe.getJoueurActuel().getPseudo() + " a gagn� ! Bravo !");
											Platform.runLater(new Runnable() {

												@Override
												public void run() {
													Alert alert = new Alert(AlertType.INFORMATION);
													alert.initOwner(pStage);
													alert.setTitle("Fin du jeu");
													alert.setHeaderText("La partie est termin�");
													alert.setContentText("F�licitation ! Vous avez gagn� !");
													alert.showAndWait();
													pStage.close();
												}
												
											});

										}
										else {
											System.out.println("Le joueur " + tictactoe.getJoueurActuel().getPseudo() + " a perdu ! Nul !"); // Si ce client est le joueur perdant, on affiche qu'il a perdu
											Platform.runLater(new Runnable() {

												@Override
												public void run() {
													Alert alert = new Alert(AlertType.INFORMATION);
													alert.initOwner(pStage);
													alert.setTitle("Fin du jeu");
													alert.setHeaderText("La partie est termin�");
													alert.setContentText("Malheureusement, vous avez perdu !");
													alert.showAndWait();
													pStage.close();
												}
												
											});
										}
									}
									else { // Si onb ne trouve pas de gangant, on v�rifie s'il y a �galit�
										if(tictactoe.verifGrilleNulle()) { // Si c'est le cas, on affiche un message affichant �galit� pour les deux clients
											System.out.println("Egalit� !");
											
											Platform.runLater(new Runnable() {

												@Override
												public void run() {
													Alert alert = new Alert(AlertType.INFORMATION);
													alert.initOwner(pStage);
													alert.setTitle("Fin du jeu");
													alert.setHeaderText("La partie est termin�");
													alert.setContentText("Egalit� !");
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
                	System.out.println("Le service 'jeu' n'� pas r�ussit � se lancer.");
                	jeu.getException().printStackTrace();
                	break;
                case CANCELLED:
                case SUCCEEDED:
                	System.out.println("Le service jeu a �t� lanc� avec succ�s.");
                	//System.out.println(lancerService.getValue());
                    break;
            }
        });
		
		// Lancement du thread
		jeu.start();
	}
	
	public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) { // Permet de r�cuperer l'enfant contenu dans l'une des cases du GridPane
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

	public void dessinerCase(Pane p, char symbole) { // Dessine simplement dans l'interface graphique le symbole sur le Pane identifi�
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
		
		// Thread qui lance la partie
		final Service<Void> lancerService = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				// TODO Auto-generated method stub
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						// Fonction bloquante qui ne lancera pas la partie tant que les joueurs ne seront pas deux.
						tictactoe.lancerPartie();

						return null;
					}
					
				};
			}
			
		};
		
		lancerService.stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            switch (newValue) {
                case FAILED:
                	System.out.println("Le service a n'a pas r�ussit � se lancer.");
                	lancerService.getException().printStackTrace();
                	break;
                case CANCELLED:
                case SUCCEEDED:
                	System.out.println("Le service a �t� lanc� avec succ�s.");
                	//System.out.println(lancerService.getValue());
                    break;
            }
        });
		
		// D�marrage du thread
		lancerService.start();
		
		
		// Thread qui s'occupe d'afficher le message "En attente d'un autre joueur" tant que la partie n'est pas lanc�
		final Service<Void> init = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				// TODO Auto-generated method stub
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						
						// tant que la partie n'est pas lanc�, on affiche le message
						while(tictactoe.isPartieLance() == false) {
							if(!sp.getChildren().contains(attenteMsg)) {
								sp.getChildren().add(attenteMsg);
							}
						}
						
						// D�s que la partie se lance, on sort de la boucle et on effectue le code en dessous
						
						Platform.runLater(new Runnable() {
						    @Override
						    public void run() {
								try {
									if(tictactoe.isPartieLance() == true) { // La partie est lanc�, on enl�ve le message d'attente
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
                	System.out.println("Le service init ne s'est pas effectu� correctement.");
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
                	System.out.println("Le service init s'est effectu� correctement.");
                    break;
            }
        });
		// D�marrage du thread
		init.start();
	}
	
	// D�s qu'un des clients ferme la fen�tre du jeu, on consid�re qu'il a quitt�, cet �v�nement est appel� dans "AccueilController"
	public void onShutdown() {
		try {
			System.out.println(joueur.getPseudo() + " a quitt� la partie.");
			this.tictactoe.quitterPartie(joueur); // Si un joueur quitte, on consid�re que la partie n'est plus lanc�
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}