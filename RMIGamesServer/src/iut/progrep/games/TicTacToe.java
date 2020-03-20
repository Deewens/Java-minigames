package iut.progrep.games;

import java.util.Arrays;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.pojo.JoueurTicTacToe;

public class TicTacToe {
	private char[][] grille;
	private JoueurTicTacToe joueurActuel; // Tour du joueur
	private JoueurTicTacToe j1;
	private JoueurTicTacToe j2;
	
	public TicTacToe(JoueurTicTacToe j1, JoueurTicTacToe j2) {
		this.j1 = j1;
		this.j2 = j2;
		
		this.grille = new char[][] {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
		
		System.out.println("TicTacToe initialisé.");
		System.out.println("C'est à " + this.joueurActuel.getPseudo() + " de joué.");
	}
	
	public boolean ajouterSymbole(int ligne, int colonne) {
		if(this.grille[ligne][colonne] == ' ') {
			this.grille[ligne][colonne] = this.joueurActuel.getSymbole();
			return true;
		}
		return false;
	}
	
	public void changerJoueur() {
		if(this.joueurActuel.equals(this.j1)) {
			this.joueurActuel = this.j2;
		}
		else {
			this.joueurActuel = this.j1;
		}
	}
	
	public void afficherGrilleCmd() {
		System.out.println("-------------");
		for(int i = 0; i<this.grille.length;i++) {
			System.out.print("| ");
			for(int j = 0; j<this.grille[i].length;j++) {
				System.out.print(this.grille[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println("-------------");
		
	}
	
	public char[][] getGrille() {
		return grille;
	}
	
	private boolean verifSymbole(char c1, char c2, char c3) {
		return ((c1 != ' ') && (c1==c2) && (c2==c3));
	}
	
	public boolean verifGrilleNulle() {
		for(int i = 0; i<this.grille.length;i++) {
			for(int j = 0; j<this.grille[i].length;j++) {
				if(this.grille[i][j] == ' ') {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/* 
	 * Vérification pour voir si quelqu'un a gagné.
	 */
	
	public boolean verifGagnant() {
		if(this.verifLigneGagnante() || this.verifColonneGagnante() || this.verifDiagonaleGagnante()) {
			return true;
		}
		
		return false;
	}
	
	private boolean verifLigneGagnante() {
		for(int j = 0; j<this.grille[0].length;j++) {
			if(verifSymbole(this.grille[0][j], this.grille[1][j], this.grille[2][j])) {
				return true;
			}
		}
		
		return false;
	}

	private boolean verifColonneGagnante() {
		for(int i = 0; i<this.grille.length;i++) {
			if(verifSymbole(this.grille[i][0], this.grille[i][1], this.grille[i][2])) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean verifDiagonaleGagnante() {
		if(verifSymbole(this.grille[0][0], this.grille[1][1], this.grille[2][2]) || verifSymbole(this.grille[0][2], this.grille[1][1], this.grille[2][0])) {
			return true;
		}
		
		return false;
	}

	public Joueur getJoueurActuel() {
		return joueurActuel;
	}
}
