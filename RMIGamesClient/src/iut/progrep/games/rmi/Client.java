package iut.progrep.games.rmi;

import java.rmi.Naming;

import iut.progrep.games.TicTacToeInterface;
import iut.progrep.games.pojo.Joueur;

public class Client {
	public static void main(String[] args) {
		try {
			int port = 8000;
			
			// rmi://localhost:port/nomService
			
			SecurityManager security = System.getSecurityManager();

			TicTacToeInterface tictactoe = (TicTacToeInterface) Naming.lookup("rmi://localhost:" + port + "/tictactoe");
			
			Joueur j1 = new Joueur("Deewens");
			tictactoe.rejoindrePartie(j1);
		} catch(Exception e) {
			System.out.println("Erreur lors de l'initialisation du client : " + e);
		}

	}
}
