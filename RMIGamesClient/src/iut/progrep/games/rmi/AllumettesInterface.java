package iut.progrep.games.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import iut.progrep.games.pojo.Joueur;

public interface AllumettesInterface extends Remote {
	public boolean rejoindrePartie(Joueur j) throws RemoteException;
	public boolean quitterPartie(Joueur j) throws RemoteException;
}



