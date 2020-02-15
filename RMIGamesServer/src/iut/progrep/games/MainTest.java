package iut.progrep.games;

import java.util.Scanner;

import iut.progrep.games.pojo.Joueur;

public class MainTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Joueur j1 = new Joueur();
		Joueur j2 = new Joueur("Deewens");
		
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
					System.out.println("Cette case est d�j� remplit. Choisissez en une autre.");
					place = false;
				}
			} while(place == false);
			
			jeu.afficherGrille();
			
			if(jeu.verifGagnant()) {
				System.out.println("Le joueur " + jeu.getJoueurActuel().getPseudo() + " a gagn� ! Bravo !");
				break;
			}
			else {
				if(jeu.verifGrilleNulle()) {
					System.out.println("Egalit� !");
					break;
				}
				jeu.changerJoueur();
			}
		} while(true);
	}

}