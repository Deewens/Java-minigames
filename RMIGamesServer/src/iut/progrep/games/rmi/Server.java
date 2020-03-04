package iut.progrep.games.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import iut.progrep.games.TicTacToe;
import iut.progrep.games.TicTacToeImpl;

public class Server {
	public static void main(String[] args) {
		System.setProperty("java.security.policy","file:./policies/serveur.policy");
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		
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
