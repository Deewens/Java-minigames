package iut.progrep.games;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.rmi.TicTacToeInterface;

public class TicTacToeImpl extends UnicastRemoteObject implements TicTacToeInterface {
	private TicTacToe jeu;
	private Joueur j1;
	private Joueur j2;
	private boolean j1Connexion;
	private boolean j2Connexion;
	
	public TicTacToeImpl() throws RemoteException {
		super();
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
				this.j1Connexion = true;
				
			}
			else {
				this.j2 = j;
				this.j2Connexion = true;
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

	@Override
	public void lancerPartie() throws RemoteException {
		System.out.println(this.j1Connexion);
		if(this.j1Connexion == true && this.j2Connexion == true) {
			this.jeu = new TicTacToe(j1, j2);
		}
		else {
			System.out.println("Il n'y a pas assez de joueurs pour commencer la partie.");
		}
	}

	@Override
	public boolean ajouterSymbole(int ligne, int colonne) throws RemoteException {
		return this.jeu.ajouterSymbole(ligne, colonne);
	}

	@Override
	public void changerJoueur() throws RemoteException {
		this.jeu.changerJoueur();
		
	}

	@Override
	public void afficherGrille() throws RemoteException {
		this.jeu.afficherGrille();
		
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
	public void setJoueurActuel(Joueur joueurActuel) throws RemoteException {
		this.jeu.setJoueurActuel(joueurActuel);
	}

	@Override
	public char getSymbole() throws RemoteException {
		return this.jeu.getSymbole();
	}
}
