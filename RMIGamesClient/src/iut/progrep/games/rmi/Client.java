package iut.progrep.games.rmi;

import java.rmi.Naming;

public class Client {
	public static void main(String[] args) {
		try {
			int port = 8000;
			
			// rmi://localhost:port/nomService

			TicTacToeInterface tictactoe = (TicTacToeInterface) Naming.lookup("rmi://localhost:" + port + "tictactoe");
			MatchesInterface matches = (MatchesInterface) Naming.lookup("rmi://localhost:" + port + "/matches");
		} catch(Exception e) {
			System.out.println("Erreur lors de l'initialisation du client : " + e);
		}

	}
}
