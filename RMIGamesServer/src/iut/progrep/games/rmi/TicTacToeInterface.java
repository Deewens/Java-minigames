package iut.progrep.games.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.pojo.JoueurTicTacToe;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public interface TicTacToeInterface extends Remote {
	public boolean rejoindrePartie(JoueurTicTacToe j) throws RemoteException;
	public void quitterPartie(JoueurTicTacToe j) throws RemoteException;
	public void lancerPartie() throws RemoteException;
	public void init() throws RemoteException;

	public boolean insererSymbole(int ligne, int colonne) throws RemoteException;
	public void changerJoueur() throws RemoteException;
	public void afficherGrilleCmd() throws RemoteException;
	public char[][] getGrille() throws RemoteException;
	public boolean verifGrilleNulle() throws RemoteException;
	public boolean verifGagnant() throws RemoteException;
	public JoueurTicTacToe getJoueurActuel() throws RemoteException;
	public boolean isPartieLance() throws RemoteException;
	public void setPartieLance(boolean partieLance) throws RemoteException;
	public boolean isClick() throws RemoteException;
	public void setClick(boolean click) throws RemoteException;
	public int getLigneGUI() throws RemoteException;
	public void setLigneGUI(int ligneGUI) throws RemoteException;
	public int getColonneGUI() throws RemoteException;
	public void setColonneGUI(int colonneGUI) throws RemoteException;
}
