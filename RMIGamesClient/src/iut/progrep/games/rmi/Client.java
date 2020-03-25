package iut.progrep.games.rmi;

import java.rmi.Naming;
import java.util.Scanner;

import iut.progrep.games.pojo.Joueur;

public class Client {
	public static void main(String[] args) {
		System.setProperty("java.security.policy","file:./policies/client.policy");
		System.setProperty("java.rmi.server.codebase", "file:D:/Utilisateurs/razor/Documents/Etudes/IUT/2A/ProgRep/Projet/RMIGames/RMIGamesServer/bin");
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			int port = 8000;
			
			// rmi://localhost:port/nomService

			TicTacToeInterface tictactoe = (TicTacToeInterface) Naming.lookup("rmi://localhost:" + port + "/tictactoe");
			

			Scanner sc = new Scanner(System.in);
			sc.nextInt();
		} catch(Exception e) {
			System.out.println("Erreur lors de l'initialisation du client : " + e);
			e.printStackTrace();
		}

	}
}
