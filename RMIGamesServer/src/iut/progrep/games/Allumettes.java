package iut.progrep.games;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.pojo.JoueurAllumettes;

import java.util.Arrays;

public class Allumettes {
	private JoueurAllumettes joueurActuel;
	private JoueurAllumettes j1;
	private JoueurAllumettes j2;
	public int nballu;
	
	public Allumettes(JoueurAllumettes j1, JoueurAllumettes j2) {
		this.j1 = j1;
		this.j2 = j2;
		this.nballu = nb_allumette();
		int Min = 1;
		int Max = 2;
		int n = Min + (int)(Math.random() * ((Max - Min) + 1));
	
		if (n==1) {
			joueurActuel = j1;
		} else this.joueurActuel = j2;
		

		System.out.println("Jeu initialisé");
		System.out.println("C'est à " + this.joueurActuel.getPseudo() + " de jouer.\n");
	}
	
	public int nb_allumette() {
		 int Min = 10;
		 int Max = 20;
		 int n=0;
		 do {
		  n = Min + (int)(Math.random() * ((Max - Min) + 1));
		}while(n % 2  == 0);
		return n;}
	
	public boolean ajouterAllu(int allu) {
		if(allu ==1 || allu ==2 ) {
			return true;
			}else
		return false;
	}
	
	public void jeuAllu(int allu) {
		
		if(this.joueurActuel == j1 ) {
			j1.setJoue(j1.getJoue() + allu);
		}else j2.setJoue(j2.getJoue() + allu);	
		}
	
	public void nbAllu(int allu) {
			
			nballu=nballu-allu;
				
	}	
	
	
	public void changerJoueur() {
		if(this.joueurActuel == j1 ) {
			this.joueurActuel = j2;
		}
		else {
			this.joueurActuel = j1;
		}
	}
	
	
	
	/* 
	 * V�rification pour voir si quelqu'un a gagn�.
	 */
	
	public boolean verifGagnant() {
		
		if((nballu == 0) && (j1.getJoue() % 2  == 1) ) {
			
			return true;
		
		}else if((nballu == 0) && (j2.getJoue() % 2  == 1) ) {
			
			return true;
		
		}else  return false;
	}
	
	public JoueurAllumettes estGagnant() {
		  if(nballu == 0 && j1.getJoue() % 2 == 1) {
		    return j1;
		  }
		  else {
		    return j2;
		  }
		}
	

	public JoueurAllumettes getJoueurActuel() {
		return joueurActuel;
	}

	public void setJoueurActuel(JoueurAllumettes joueurActuel) {
		this.joueurActuel = joueurActuel;
	}

	
	public JoueurAllumettes getJ1() {
		return j1;
	}

	public void setJ1(JoueurAllumettes j1) {
		this.j1 = j1;
	}

	public JoueurAllumettes getJ2() {
		return j2;
	}

	public void setJ2(JoueurAllumettes j2) {
		this.j2 = j2;
	}

	public int getNballu() {
		return nballu;
	}

	public void setNballu(int nballu) {
		this.nballu =nballu;	}

}
