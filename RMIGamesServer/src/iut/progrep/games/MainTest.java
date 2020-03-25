package iut.progrep.games;

import java.util.Scanner;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.pojo.JoueurTicTacToe;

public class MainTest {

	public static void main(String[] args) {
		/*Scanner sc = new Scanner(System.in);
		
		JoueurTicTacToe j1 = new JoueurTicTacToe('Y');
		JoueurTicTacToe j2 = new JoueurTicTacToe("Deewens", 'X');
		
		TicTacToe jeu = new TicTacToe(j1, j2);
		
		do {
			System.out.println(jeu.getJoueurActuel().getPseudo() + " joue.");
			
			boolean place;
			do {
				System.out.print("Ligne : ");
				int ligne = sc.nextInt();
				System.out.print("Colonne : ");
				int colonne = sc.nextInt();
				
				if(jeu.ajouterSymbole(ligne, colonne)) {
					System.out.println(jeu.getJoueurActuel().getPseudo() + " vient de jouer dans la case " + ligne + " - " + colonne);
					place = true;
				}
				else {
					System.out.println("Cette case est déjà remplit. Choisissez en une autre.");
					place = false;
				}
			} while(place == false);
			
			jeu.afficherGrilleCmd();
			
			if(jeu.verifGagnant()) {
				System.out.println("Le joueur " + jeu.getJoueurActuel().getPseudo() + " a gagné ! Bravo !");
				break;
			}
			else {
				if(jeu.verifGrilleNulle()) {
					System.out.println("Egalité !");
					break;
				}
				jeu.changerJoueur();
			}
		} while(true);*/
	}

}
