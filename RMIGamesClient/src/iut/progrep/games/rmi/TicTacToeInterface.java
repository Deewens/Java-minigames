package iut.progrep.games.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import iut.progrep.games.pojo.Joueur;

public interface TicTacToeInterface extends Remote {
	public boolean rejoindrePartie(Joueur j) throws RemoteException;
	public boolean quitterPartie(Joueur j) throws RemoteException;
	public void lancerPartie() throws RemoteException;
	
	public boolean ajouterSymbole(int ligne, int colonne) throws RemoteException;
	public void changerJoueur() throws RemoteException;
	public void afficherGrille() throws RemoteException;
	public boolean verifGrilleNulle() throws RemoteException;
	public boolean verifGagnant() throws RemoteException;
	public Joueur getJoueurActuel() throws RemoteException;
	public void setJoueurActuel(Joueur joueurActuel) throws RemoteException;
	public char getSymbole() throws RemoteException;
}
