package iut.progrep.games;

import java.util.Arrays;

import iut.progrep.games.pojo.Joueur;

public class TicTacToe {
	private char[][] grille;
	private char symbole;
	private Joueur joueurActuel;
	private Joueur j1;
	private Joueur j2;
	
	
	public TicTacToe(Joueur j1, Joueur j2) {
		this.j1 = j1;
		this.j2 = j2;
		
		this.joueurActuel = j1;
		this.grille = new char[][] {{' ', ' ', ' '},{' ',' ',' '},{' ',' ',' '}};
		this.symbole = 'X';
		System.out.println("Jeu initialis�.");
		System.out.println("C'est � " + this.joueurActuel.getPseudo() + " de jou�.");
	}
	
	public boolean ajouterSymbole(int ligne, int colonne) {
		if(this.grille[ligne][colonne] == ' ') {
			this.grille[ligne][colonne] = symbole;
			return true;
		}
		return false;
	}
	
	
	public void changerJoueur() {
		if(this.symbole == 'X') {
			this.symbole = 'O';
			this.joueurActuel = j2;
		}
		else {
			this.symbole = 'X';
			this.joueurActuel = j1;
		}
	}
	
	public void afficherGrille() {
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
	 * V�rification pour voir si quelqu'un a gagn�.
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

	public void setJoueurActuel(Joueur joueurActuel) {
		this.joueurActuel = joueurActuel;
	}
	
	
}