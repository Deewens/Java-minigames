package iut.progrep.games.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.pojo.JoueurAllumettes;

public interface AllumettesInterface extends Remote {
	public boolean rejoindrePartie(JoueurAllumettes j) throws RemoteException;
	public boolean quitterPartie(JoueurAllumettes j) throws RemoteException;
}



