package iut.progrep.games.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToeListener extends Remote {
	void grilleChange(char[][] grille) throws RemoteException;
	void grilleChange(boolean click) throws RemoteException;
}
