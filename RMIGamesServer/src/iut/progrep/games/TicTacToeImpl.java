package iut.progrep.games;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.rmi.TicTacToeInterface;

public class TicTacToeImpl extends UnicastRemoteObject implements TicTacToeInterface {
	private TicTacToe jeu;
	
	public TicTacToeImpl() throws RemoteException {
		super();
	}

	@Override
	public boolean rejoindrePartie(Joueur j) throws RemoteException {	
		
		
		System.out.println("Le joueur " + j.getPseudo() + " a rejoint la partie.");
		
		return true;
	}

	@Override
	public boolean quitterPartie(Joueur j) throws RemoteException {

		return true;
	}

	@Override
	public boolean lancerPartie() throws RemoteException {
		System.out.println(this.j1Connexion);
		if(this.j1Connexion == true && this.j2Connexion == true) {
			this.jeu = new TicTacToe(j1, j2);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void changerJoueur() throws RemoteException {
		this.jeu.changerJoueur();
		
	}

	@Override
	public void afficherGrilleCmd() throws RemoteException {
		this.jeu.afficherGrilleCmd();
	}
	
	public char[][] getGrille() {
		return this.jeu.getGrille();
	}

	@Override
	public boolean verifGrilleNulle() throws RemoteException {
		return this.jeu.verifGrilleNulle();
	}

	@Override
	public boolean verifGagnant() throws RemoteException {
		return this.jeu.verifGagnant();
	}

	@Override
	public Joueur getJoueurActuel() throws RemoteException {
		return this.jeu.getJoueurActuel();
	}

	@Override
	public boolean insererSymbole(int ligne, int colonne) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setJoueurActuel(Joueur joueurActuel) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}