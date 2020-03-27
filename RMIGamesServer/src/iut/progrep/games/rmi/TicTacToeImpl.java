package iut.progrep.games.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import iut.progrep.games.pojo.JoueurTicTacToe;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class TicTacToeImpl extends UnicastRemoteObject implements TicTacToeInterface {
	private char[][] grille;
	private JoueurTicTacToe joueurActuel; // Tour du joueur
	private ArrayList<JoueurTicTacToe> joueurs;
	private boolean partieLance;
	private static final int NOMBRE_JOUEURS = 2;
	private boolean click;
	private int ligneGUI;
	private int colonneGUI;
	
	public TicTacToeImpl() throws RemoteException {
		super();
		this.partieLance = false;
		this.joueurs = new ArrayList<>(NOMBRE_JOUEURS);
		this.grille = new char[][] {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
	}
	
	@Override
	public void init() {
		// Buggué
	}

	@Override
	public boolean rejoindrePartie(JoueurTicTacToe j) throws RemoteException {
		if(this.joueurs.size() > 0) {
			if(this.joueurs.get(0).getSymbole() == j.getSymbole()) {
				return false;
			}
		}
		
		this.joueurs.add(j);
		return true;
	}

	@Override
	public void quitterPartie(JoueurTicTacToe j) throws RemoteException {
		this.joueurs.remove(j);
		this.partieLance = false;
	}
	
	@Override
	public synchronized void lancerPartie() throws RemoteException {
		while(joueurs.size() != NOMBRE_JOUEURS) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		this.joueurActuel = this.joueurs.get(0);
		System.out.println("TicTacToe initialisé.");
		System.out.println("C'est à " + this.joueurActuel.getPseudo() + " de joué.");
		this.partieLance = true;
		notifyAll();
	}
	
	@Override
	public boolean insererSymbole(int ligne, int colonne) throws RemoteException {
		if(this.grille[ligne][colonne] == ' ') {
			this.grille[ligne][colonne] = this.joueurActuel.getSymbole();
			return true;
		}
		return false;
	}

	@Override
	public void changerJoueur() throws RemoteException {
		if(this.joueurActuel == this.joueurs.get(0)) {
			this.joueurActuel = this.joueurs.get(1);
		}
		else {
			this.joueurActuel = this.joueurs.get(0);
		}
	}
	
	@Override
	public JoueurTicTacToe getJoueurActuel() throws RemoteException {
		return this.joueurActuel;
	}

	@Override
	public void afficherGrilleCmd() throws RemoteException {
		System.out.println("-------------");
		for(int i = 0; i<this.grille.length;i++) {
			System.out.print("| ");
			for(int j = 0; j<this.grille[i].length;j++) {
				System.out.print(this.grille[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println("-------------");
	}
	
	@Override
	public char[][] getGrille() {
		return grille;
	}

	@Override
	public boolean verifGagnant() {
		if(this.verifLigneGagnante() || this.verifColonneGagnante() || this.verifDiagonaleGagnante()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean verifGrilleNulle() {
		for(int i = 0; i<this.grille.length;i++) {
			for(int j = 0; j<this.grille[i].length;j++) {
				if(this.grille[i][j] == ' ') {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean verifSymbole(char c1, char c2, char c3) {
		return ((c1 != ' ') && (c1==c2) && (c2==c3));
	}
	
	/* 
	 * Vérification pour voir si quelqu'un a gagné.
	 */
	
	private boolean verifLigneGagnante() {
		for(int j = 0; j<this.grille[0].length;j++) {
			if(verifSymbole(this.grille[0][j], this.grille[1][j], this.grille[2][j])) {
				return true;
			}
		}
		
		return false;
	}

	private boolean verifColonneGagnante() {
		for(int i = 0; i<this.grille.length;i++) {
			if(verifSymbole(this.grille[i][0], this.grille[i][1], this.grille[i][2])) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean verifDiagonaleGagnante() {
		if(verifSymbole(this.grille[0][0], this.grille[1][1], this.grille[2][2]) || verifSymbole(this.grille[0][2], this.grille[1][1], this.grille[2][0])) {
			return true;
		}
		
		return false;
	}

	public boolean isPartieLance() throws RemoteException {
		return partieLance;
	}

	public void setPartieLance(boolean partieLance) throws RemoteException {
		this.partieLance = partieLance;
	}

	public boolean isClick() {
		return click;
	}

	public void setClick(boolean click) {
		this.click = click;
	}

	public int getLigneGUI() {
		return ligneGUI;
	}

	public void setLigneGUI(int ligneGUI) {
		this.ligneGUI = ligneGUI;
	}

	public int getColonneGUI() {
		return colonneGUI;
	}

	public void setColonneGUI(int colonneGUI) {
		this.colonneGUI = colonneGUI;
	}
}