package iut.progrep.games;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import iut.progrep.games.rmi.TicTacToeInterface;

public class TicTacToe extends UnicastRemoteObject implements TicTacToeInterface {
	public TicTacToe() throws RemoteException {
		
	}
}
