package iut.progrep.games.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import iut.progrep.games.Matches;
import iut.progrep.games.TicTacToeImpl;

public class Server {
	public static void main(String[] args) {
		try {
			int port = 8000;
			
			LocateRegistry.createRegistry(port); // Active le service de nommage
			
			// rmi://localhost:port/nomService
			Naming.rebind("rmi://localhost:" + port + "/tictactoe", new TicTacToeImpl()); // Naming rebind: exporte un service
			System.out.println("Le serveur est prêt !");
		} catch(Exception e) {
			System.out.println("Echec de l'intialisation du serveur : " + e);
		}
	}
}
