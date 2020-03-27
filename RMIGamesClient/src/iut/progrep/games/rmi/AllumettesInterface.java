package iut.progrep.games.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import iut.progrep.games.pojo.JoueurAllumettes;

public interface AllumettesInterface extends Remote {
	public boolean rejoindrePartie(JoueurAllumettes j) throws RemoteException;
	public boolean quitterPartie(JoueurAllumettes j) throws RemoteException;
	public void lancerPartie() throws RemoteException;
	
	public void jeuAllu(int allu) throws RemoteException;
	public void actualiserAlluRestantes(int allu) throws RemoteException;
	public String estGagnant() throws RemoteException;
	public int getCompt_ordi() throws RemoteException;
	public void setCompt_ordi(int compt_ordi) throws RemoteException;
	public int getAlluRestantes() throws RemoteException;
	public String getJoueurquicommence() throws RemoteException;
	public boolean verifGagnant(int compt_ordi) throws RemoteException;
	

	

}



