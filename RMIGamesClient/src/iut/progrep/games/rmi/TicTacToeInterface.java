package iut.progrep.games.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.pojo.JoueurTicTacToe;

public interface TicTacToeInterface extends Remote {
	public void rejoindrePartie(JoueurTicTacToe j) throws RemoteException;
	public void quitterPartie(JoueurTicTacToe j) throws RemoteException;
	public void lancerPartie() throws RemoteException;
	
	public boolean insererSymbole(int ligne, int colonne) throws RemoteException;
	public void changerJoueur() throws RemoteException;
	public void afficherGrilleCmd() throws RemoteException;
	public char[][] getGrille() throws RemoteException;
	public boolean verifGrilleNulle() throws RemoteException;
	public boolean verifGagnant() throws RemoteException;
	public JoueurTicTacToe getJoueurActuel() throws RemoteException;
	public boolean isPartieLance();
	public void setPartieLance(boolean partieLance);
}
