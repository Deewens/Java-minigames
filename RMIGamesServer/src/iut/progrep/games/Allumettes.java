package iut.progrep.games;


import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.pojo.JoueurAllumettes;

import java.awt.Color;
import java.awt.Graphics;

public class Allumettes  {
	private	String joueurquicommence; // variable qui initialise le joueur de départ
	public int compt_ordi; // entier qui correspond au nombre d'allumette retirer par l'ordi
	private JoueurAllumettes j1; 
	public int nbAllumettesRestantes; //nombre d'allumette total restante dans le jeu
	
	public Allumettes(JoueurAllumettes j1) { 
		// initialise les variables , génere un joueur qui débute
		this.j1 = j1;
		this.nbAllumettesRestantes = this.nb_allumette();
		int Min = 1;
		 int Max = 2;
		 int n=0;
		  n = Min + (int)(Math.random() * ((Max - Min) + 1));
		if (n==1) { joueurquicommence = j1.getPseudo();
		}else joueurquicommence = "Ordinateur";
		

		System.out.println("Jeu initialisé");
		
	}
	
	private int nb_allumette() { // génére un nombre impair d'allumette total entre 10 et 20
		 int Min = 10;
		 int Max = 20;
		 int n=0;
		 do {
		  n = Min + (int)(Math.random() * ((Max - Min) + 1));
		}while(n % 2  == 0);
		return n;}
	
	public void jeuAllu(int allu) { //actualise le nombre d'allumette retirer par le joueur
		j1.setCompteurAllumettes(j1.getCompteurAllumettes() + allu);
		}
	
	
	public void actualiserAlluRestantes(int allu) {
		// actualise le nombre d'allumette total par rapport a ceux retiré par le joueur et l'ordinateur
			
			nbAllumettesRestantes=nbAllumettesRestantes-allu;
			
	}	
	
	
	
	
	
	
	/* 
	 * V�rification pour voir si quelqu'un a gagn�.
	 */
	
	public boolean verifGagnant(int compt) {
		// si y a plus d'allumette et le compteur du joueur est impair
		if((nbAllumettesRestantes == 0) && (j1.getCompteurAllumettes() % 2  == 1) ) {
			
			return true;
		
		}else if((nbAllumettesRestantes == 0)&& (compt % 2  == 1)  ) {
			// si y a plus d'allumette et le compteur de l'ordi est impair

			return true;
		
		}else  return false;
	}
	
	public String estGagnant() { // retourne le gagnant du jeu
		
		  if(j1.getCompteurAllumettes() % 2 == 1) {
		    String gagnant = j1.getPseudo()+ "a gagné" ;
		    return gagnant;
		  } else {String gagnant = "L'ordinateur a gagné" ;
		  return gagnant;
		  }
		}
	

	



	
	public JoueurAllumettes getJ1() {
		return j1;
	}

	public void setJ1(JoueurAllumettes j1) {
		this.j1 = j1;
	}

	
	public int getAlluRestantes() {
		return nbAllumettesRestantes;
	}

	public String getJoueurquicommence() {
		return joueurquicommence;
	}

	public void setJoueurquicommence(String joueurquicommence) {
		this.joueurquicommence = " ";
	}

	public int getCompt_ordi() {
		return compt_ordi;
	}

	public void setCompt_ordi(int compt_ordi) {
		this.compt_ordi = compt_ordi;
	}

	

}
