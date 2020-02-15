package iut.progrep.games;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.rmi.TicTacToeInterface;

public class TicTacToeImpl extends UnicastRemoteObject implements TicTacToeInterface {
	private Joueur j1;
	private Joueur j2;
	private boolean j1Connexion;
	private boolean j2Connexion;
	
	public TicTacToeImpl() throws RemoteException {
		this.j1Connexion = false;
		this.j2Connexion = false;
	}

	@Override
	public boolean rejoindrePartie(Joueur j) throws RemoteException {	
		if(this.j1Connexion && this.j2Connexion) {
			System.out.println("La partie est pleine.");
			return false;
		}
		else {
			if(!this.j1Connexion) {
				this.j1 = j;
				
			}
			else {
				this.j2 = j;
			}
			
			System.out.println("Le joueur " + j.getPseudo() + " a rejoint la partie.");
		}
		
		return true;
	}

	@Override
	public boolean quitterPartie(Joueur j) throws RemoteException {
		if(j.equals(this.j1)) {
			this.j1Connexion = false;
		}
		else if(j.equals(this.j2)) {
			this.j2Connexion = false;
		}
		else {
			System.err.println("Ce joueur n'est pas dans la partie.");
			return false;
		}
		
		System.out.println("Le joueur " + j.getPseudo() + " a quitté la partie.");
		return true;
	}
}
