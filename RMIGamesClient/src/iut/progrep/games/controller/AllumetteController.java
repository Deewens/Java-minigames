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
			
		@FXML private AnchorPane pnl_ecran;
		@FXML private Button btn_1allu;
		@FXML private Button btn_2allu;
		@FXML private Label allu_total;
		@FXML private Label pseudo;
		@FXML private Pane pnl_allu;
		@FXML private Label compt_joueur;
		@FXML private Label compt_ordi;
		@FXML private Label msg_instruction;
		@FXML private ImageView img_allu;

		AllumettesInterface allumette;
		JoueurAllumettes joueur1;
		JoueurAllumettes joueur2;

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
				this.pseudo.setText(this.joueur1.getPseudo());
				this.allu_total.setText(Integer.toString(this.allumette.getAlluRestantes()));
				this.compt_joueur.setText(Integer.toString(this.joueur1.getCompteurAllumettes()));
				this.compt_ordi.setText(Integer.toString(0));
				this.msg_instruction.setText("C'est à " + this.joueur1.getPseudo() + " de jouer.\n");
				int j=0;
			    for(int i=0; i<this.allumette.getAlluRestantes(); i++ ) {
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
		public void enlever_1allumettes() throws RemoteException {
			
			int allu = 1;
		
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

				
				
				
		
		
		@FXML
		public void enlever_2allumettes() throws RemoteException {
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
			
			
		public int actuOrdi() throws RemoteException {
			
			int Min = 1;
			 int Max = 2;
			 int n=0;
	
			  n = Min + (int)(Math.random() * ((Max - Min) + 1));
			 
			
				  
				  
			  if (n==1) {
				  int allu=1;
				  this.allumette.actualiserAlluRestantes(allu);
				  this.allumette.setCompt_ordi(allumette.getCompt_ordi() + allu);
				  this.msg_instruction.setText("L'Ordinateur a enlever "+ allu + " allumettes.\n");
				   return this.allumette.getCompt_ordi();
				
				  
				}else {  int allu=2;
				  this.allumette.actualiserAlluRestantes(allu);
				  this.allumette.setCompt_ordi(allumette.getCompt_ordi() + allu);
				  this.msg_instruction.setText("L'Ordinateur a enlever "+ allu + " allumettes.\n");
				   return this.allumette.getCompt_ordi();
				}
			  
			  }
			  
		public void bouton_invisible() throws RemoteException {
			
			if(this.allumette.getAlluRestantes() == 1) {
		    	btn_2allu.setVisible(false);
		    } else if(this.allumette.getAlluRestantes() ==0 || this.allumette.getAlluRestantes() <0 ) {
		    	btn_1allu.setVisible(false);
		    	btn_2allu.setVisible(false);
		    	this.msg_instruction.setText(allumette.estGagnant());
			   
		    };
		    
		    
		}
		
		public void condition_ordi() throws RemoteException{
			
			if (this.allumette.getAlluRestantes()>=0) {
				this.compt_ordi.setText(Integer.toString(actuOrdi()));
				} else if(this.allumette.getAlluRestantes()==1) {
					int allu=1;
				this.allumette.actualiserAlluRestantes(allu);
			   this.allumette.setCompt_ordi(allumette.getCompt_ordi() + allu);
			   this.msg_instruction.setText("L'Ordinateur a enlever "+ allu + " allumettes.\n");
				}
					   
				
		}
		
		public void dessiner_allumette() throws RemoteException {
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
			
		
		
			
			
		
		
		

			
		

			       


		    
		        

		
		
		



