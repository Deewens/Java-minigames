package iut.progrep.games.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import iut.progrep.games.Matches;
import iut.progrep.games.TicTacToe;

public class Server {
	public static void main(String[] args) {
		try {
			int port = 8000;
			
			LocateRegistry.createRegistry(port); // Active le service de nommage
			
			// rmi://localhost:port/nomService
			Naming.rebind("rmi://localhost:" + port + "/tictactoe", new TicTacToe()); // Naming rebind: exporte un service
			Naming.rebind("rmi://localhost:" + port + "/matches", new Matches());
			System.out.println("Le serveur est prêt !");
		} catch(Exception e) {
			System.out.println("Echec de l'intialisation du serveur : " + e);
		}
	}
}
