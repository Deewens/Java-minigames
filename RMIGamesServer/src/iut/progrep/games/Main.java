package iut.progrep.games;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import iut.progrep.games.rmi.AllumettesImpl;
import iut.progrep.games.rmi.TicTacToeImpl;


public class Main {
	public static void main(String[] args) {
		System.setProperty("java.security.policy","file:./policies/serveur.policy");
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		
		try {
			int port = 8000;
			
			LocateRegistry.createRegistry(port); // Active le service de nommage
			
			Naming.rebind("rmi://localhost:" + port + "/tictactoe", new TicTacToeImpl()); // Naming rebind: exporte un service
			Naming.rebind("rmi://localhost:" + port + "/allumette", new AllumettesImpl());
			System.out.println("Le serveur est prêt !");
		} catch(Exception e) {
			System.out.println("Echec de l'intialisation du serveur : " + e);
		}
	}
}
