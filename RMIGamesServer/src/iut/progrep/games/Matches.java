package iut.progrep.games;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import iut.progrep.games.rmi.MatchesInterface;

public class Matches extends UnicastRemoteObject implements MatchesInterface {
	public Matches() throws RemoteException {
		
	}
}
