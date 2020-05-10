package iut.progrep.games.controller;



import java.net.URL;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import iut.progrep.games.pojo.JoueurAllumettes;
import iut.progrep.games.rmi.AllumettesInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.ButtonBase;


public class AllumetteController  implements Initializable{
			// intialisation des variables en fonction de la représentation graphique du jeu (fichier xml)
		@FXML private AnchorPane pnl_ecran; // Panel d'affichage de la fenetre
		@FXML private Button btn_1allu; // bouton pour enlever 1 allumette
		@FXML private Button btn_2allu;// bouton pour enlever 2allumette
		@FXML private Label allu_total; // nombre d'allumette total
		@FXML private Label pseudo; // pseudo du joueur
		@FXML private Pane pnl_allu; // panel d'affichage des allumettes
		@FXML private Label compt_joueur; // compteur du joueur
		@FXML private Label compt_ordi; // compteur de l'ordi
		@FXML private Label msg_instruction; // affichage des messages du jeu
		@FXML private ImageView img_allu; // image de l'allumette

		AllumettesInterface allumette;
		JoueurAllumettes joueur1;
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			this.joueur1 = new JoueurAllumettes("Sofiane");

			
			System.setProperty("java.security.policy","file:./policies/client.policy");
			System.setProperty("java.rmi.server.codebase", "file:D:/Utilisateurs/razor/Documents/Etudes/IUT/2A/ProgRep/Projet/RMIGames/RMIGamesServer/bin");
			
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			
			try {
				int port = 8000;
				
				this.allumette = (AllumettesInterface) Naming.lookup("rmi://localhost:" + port + "/allumette");
				this.allumette.rejoindrePartie(joueur1);
				this.allumette.lancerPartie();
				this.pseudo.setText(this.joueur1.getPseudo()); // affiche le pseudo dans le label
				this.allu_total.setText(Integer.toString(this.allumette.getAlluRestantes())); // affiche le nb d'allumette dans le label
				this.compt_joueur.setText(Integer.toString(this.joueur1.getCompteurAllumettes())); // affiche le compteur d'allumette du joueur
				this.compt_ordi.setText(Integer.toString(0)); // affiche le compteur de l'ordi (initialisé à 0)
				this.msg_instruction.setText("C'est à " + this.joueur1.getPseudo() + " de jouer.\n"); // indique qui joue
				int j=0;
			    for(int i=0; i<this.allumette.getAlluRestantes(); i++ ) {
			    	// affichage des allumettes en fonction du nb total
			    ImageView iv1 = new ImageView(new Image("./iut/progrep/games/views/img_allu.gif")); 
			    this.pnl_allu.getChildren().addAll(iv1);
			    iv1.relocate(j, 10);
			    j=j+10;
			    };
			    
			    
			    
			    
				  
				  
			    
			} catch(Exception e) {
				System.out.println("Erreur lors de l'initialisation du client : " + e);
				e.printStackTrace();
			}
		}	
		
	
		
		
		@FXML
		public void enlever_1allumettes() throws RemoteException {// click sur le bouton enlever 1 allumette
			
			int allu = 1;
		
				this.joueur1.setCompteurAllumettes(joueur1.getCompteurAllumettes() + allu);// actualise le compteur du joueur
				

			
				condition_ordi();
			
				
				this.compt_joueur.setText(Integer.toString(this.joueur1.getCompteurAllumettes())); // affiche le compteur du joueur dans le lable 
				allumette.actualiserAlluRestantes(allu); // actualise le nb d'allumette total
				this.allu_total.setText(Integer.toString(this.allumette.getAlluRestantes())); // affiche dans le label le nombre d'allu total
				dessiner_allumette();// dessine les allumettes

				bouton_invisible();
				
				

				
		}

				
				
				
		
		
		@FXML
		public void enlever_2allumettes() throws RemoteException { // meme methode que le 1er bouton mais pour le 2 ème bouton et avec 2 allumettes à enlever
			int allu = 2;
			this.joueur1.setCompteurAllumettes(joueur1.getCompteurAllumettes() + allu);
			System.out.println( this.joueur1.getPseudo()+ " a  " + this.joueur1.getCompteurAllumettes() + " allumettes");
			
		
			condition_ordi();
			
			this.compt_joueur.setText(Integer.toString(this.joueur1.getCompteurAllumettes()));
			allumette.actualiserAlluRestantes(allu);
			this.allu_total.setText(Integer.toString(this.allumette.getAlluRestantes()));
			dessiner_allumette();
			System.out.println( "L'ordinateur a  " + this.allumette.getCompt_ordi() + " allumettes");
			bouton_invisible();
			
			
			    
			    
		}
			
			
		public int actuOrdi() throws RemoteException { // fonction qui retourne le compteur de l'ordi
			// action automatisé réalisé par l'ordi pour jouer
			
			int Min = 1;
			 int Max = 2;
			 int n=0;
	
			  n = Min + (int)(Math.random() * ((Max - Min) + 1));
			 
			
				// génére un nombre aléatoire d'allu a enlever entre 1 et 2  
				  
			  if (n==1) {
				  int allu=1;
				  this.allumette.actualiserAlluRestantes(allu); 
				  this.allumette.setCompt_ordi(allumette.getCompt_ordi() + allu); // incrémente le compteur d'allumette de l'ordi
				  this.msg_instruction.setText("L'Ordinateur a enlever "+ allu + " allumettes.\n"); // affiche le nombre d'allu enlever par l'ordi 
				   return this.allumette.getCompt_ordi(); 
				
				  
				}else {  int allu=2;
				  this.allumette.actualiserAlluRestantes(allu);
				  this.allumette.setCompt_ordi(allumette.getCompt_ordi() + allu);
				  this.msg_instruction.setText("L'Ordinateur a enlever "+ allu + " allumettes.\n");
				   return this.allumette.getCompt_ordi();
				}
			  
			  }
			  
		public void bouton_invisible() throws RemoteException { // condition d'affichage des boutons
			
			if(this.allumette.getAlluRestantes() == 1) {
				// si il le reste plus qu'une allumette on rend invisible le bouton 2 allumette
		    	btn_2allu.setVisible(false);
		    } else if(this.allumette.getAlluRestantes() ==0 || this.allumette.getAlluRestantes() <0 ) {
		    	// si il e reste plus d'allumette on rend invisible les 2 bouton 
		    	btn_1allu.setVisible(false);
		    	btn_2allu.setVisible(false);
		    	this.msg_instruction.setText(allumette.estGagnant()); // affiche le gagnant
			   
		    };
		    
		    
		}
		
		public void condition_ordi() throws RemoteException{ // condition de jeu de l'ordi
			
			if (this.allumette.getAlluRestantes()>=0) {
				// si il y a des allumettes l'ordi peut joueur
				this.compt_ordi.setText(Integer.toString(actuOrdi()));
				} else if(this.allumette.getAlluRestantes()==1) {
					// si il reste 1 allumettes l'ordi n'en enleve qu'1

					int allu=1;
				this.allumette.actualiserAlluRestantes(allu);
			   this.allumette.setCompt_ordi(allumette.getCompt_ordi() + allu);
			   this.msg_instruction.setText("L'Ordinateur a enlever "+ allu + " allumettes.\n");
				}
					   
				
		}
		
		public void dessiner_allumette() throws RemoteException {
			// a chaque tour on efface le contenu du panel pour réafficher les allumettes en fonction du nombre actualisé
			this.pnl_allu.getChildren().clear();
			int j=0;
			 for(int i=0; i<this.allumette.getAlluRestantes(); i++ ) {
				    ImageView iv1 = new ImageView(new Image("./iut/progrep/games/views/img_allu.gif")); 
				    this.pnl_allu.getChildren().addAll(iv1);
				    iv1.relocate(j, 10);
				    j=j+10;
				    };
				    
				   
		}
			 
		}
			
		
		
			
			
		
		
		

			
		

			       


		    
		        

		
		
		



