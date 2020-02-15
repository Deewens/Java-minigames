package iut.progrep.games;

import java.rmi.Remote;
import java.rmi.RemoteException;

import iut.progrep.games.pojo.Joueur;

public interface TicTacToeInterface extends Remote {
	public boolean rejoindrePartie(Joueur j) throws RemoteException;
	public boolean quitterPartie(Joueur j) throws RemoteException;
}
