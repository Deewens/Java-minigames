package iut.progrep.games.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.pojo.JoueurTicTacToe;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public interface TicTacToeInterface extends Remote {
	public boolean rejoindrePartie(JoueurTicTacToe j) throws RemoteException; // Ajoute un joueur dans la liste des joueurs de la partie et lui donne un symbole
	public void quitterPartie(JoueurTicTacToe j) throws RemoteException; // Enl�ve le joueur qui quitte de la liste des joueurs de la partie
	public void lancerPartie() throws RemoteException; // Lance la partie � condition que deux joueurs soient pr�sent

	public boolean insererSymbole(int ligne, int colonne) throws RemoteException; // Met le symbole "X" ou "O" dans la case d'un array � deux dimensions
	public void changerJoueur() throws RemoteException; // Permet de changer le tour du joueur
	public void afficherGrilleCmd() throws RemoteException; // Permet d'afficher une repr�sentation de la grille en ligne de commande
	public char[][] getGrille() throws RemoteException; // Permet de r�cuperer l'array � deux dimensions correspondant � la grille
	public boolean verifGrilleNulle() throws RemoteException; // V�rifie si la partie est nulle
	public boolean verifGagnant() throws RemoteException; // V�rifie s'il y a un gagnant
	public JoueurTicTacToe getJoueurActuel() throws RemoteException; // R�cup�re le joueur qui a le tour
	public boolean isPartieLance() throws RemoteException; // V�rifie sil a partie est lanc�
	public boolean isClick() throws RemoteException; // Est � true si un joueur a click� dans une case ET s'il peut ins�rer un symbole dans l'array
	public void setClick(boolean click) throws RemoteException; // Le contr�lleur met true ou false en fonction de si le joueur a click� dans la case ou non
}
