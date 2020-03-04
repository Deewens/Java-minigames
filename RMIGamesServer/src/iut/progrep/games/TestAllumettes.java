package iut.progrep.games;

import java.util.Scanner;

import iut.progrep.games.pojo.JoueurAllumettes;

public class TestAllumettes {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		JoueurAllumettes j1 = new JoueurAllumettes("Adrien");
		JoueurAllumettes j2 = new JoueurAllumettes("Sofiane");
		
		Allumettes jeux = new Allumettes(j1, j2);
		
		/*TicTacToe jeu = new TicTacToe(j1, j2);
		
		
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
		} while(true);*/
		
			
			
			System.out.println("La partie commence avec " + jeux.nballu + " allumettes");

		
			do {
				System.out.println(jeux.getJoueurActuel().getPseudo() + " joue.");
			System.out.print("Combien d'allumettes voulez vous enlevez : ");
			int allu = sc.nextInt();
			
			
			while(allu <1 || allu>2){
			System.out.println("Vous ne pouvez retirer qu'1 ou 2 allumettes.");
			} 
			
			while(allu>jeux.nballu) {
				System.out.println("Vous ne pouve pas retirer plus d'allumettes qu'il n'en reste.");
			}
			
		if(jeux.ajouterAllu(allu)) {
			jeux.jeuAllu(allu);
			System.out.println( " Vous avez  " + jeux.getJoueurActuel().getJoue() + " allumettes");
			jeux.nbAllu(allu);
			System.out.println( " Il reste  " + jeux.nballu + " allumettes.\n");
			//System.out.println(jeux.j1.getPseudo() + ": " + jeux.j1.joue);
			//System.out.println(jeux.j2.getPseudo() + ": " + jeux.j2.joue);
			jeux.changerJoueur();
			
		}
		
		
		
		} while( jeux.verifGagnant() == false);
			jeux.estGagnant();
			System.out.println("Le joueur " + jeux.estGagnant().getPseudo() + " a gagn� ! Bravo !");

		

	}
}
