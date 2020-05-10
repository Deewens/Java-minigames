package iut.progrep.games.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.pojo.JoueurTicTacToe;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public interface TicTacToeInterface extends Remote {
	public boolean rejoindrePartie(JoueurTicTacToe j) throws RemoteException; // Ajoute un joueur dans la liste des joueurs de la partie et lui donne un symbole
	public void quitterPartie(JoueurTicTacToe j) throws RemoteException; // Enlève le joueur qui quitte de la liste des joueurs de la partie
	public void lancerPartie() throws RemoteException; // Lance la partie à condition que deux joueurs soient présent

	public boolean insererSymbole(int ligne, int colonne) throws RemoteException; // Met le symbole "X" ou "O" dans la case d'un array à deux dimensions
	public void changerJoueur() throws RemoteException; // Permet de changer le tour du joueur
	public void afficherGrilleCmd() throws RemoteException; // Permet d'afficher une représentation de la grille en ligne de commande
	public char[][] getGrille() throws RemoteException; // Permet de récuperer l'array à deux dimensions correspondant à la grille
	public boolean verifGrilleNulle() throws RemoteException; // Vérifie si la partie est nulle
	public boolean verifGagnant() throws RemoteException; // Vérifie s'il y a un gagnant
	public JoueurTicTacToe getJoueurActuel() throws RemoteException; // Récupère le joueur qui a le tour
	public boolean isPartieLance() throws RemoteException; // Vérifie sil a partie est lancé
	public boolean isClick() throws RemoteException; // Est à true si un joueur a clické dans une case ET s'il peut insérer un symbole dans l'array
	public void setClick(boolean click) throws RemoteException; // Le contrôlleur met true ou false en fonction de si le joueur a clické dans la case ou non
}
