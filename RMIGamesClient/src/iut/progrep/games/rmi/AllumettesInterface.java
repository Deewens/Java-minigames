package iut.progrep.games.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import iut.progrep.games.pojo.JoueurAllumettes;

public interface AllumettesInterface extends Remote {
	public boolean rejoindrePartie(JoueurAllumettes j) throws RemoteException;//l'unique joueur rejoint la partie
	public boolean quitterPartie(JoueurAllumettes j) throws RemoteException;// l'unique joueur quitte la partie lorsqu'elle est terminé
	public void lancerPartie() throws RemoteException;// des que l'unique joueur a rejoint, la partie peut commencer
	
	public void jeuAllu(int allu) throws RemoteException;//actualise le nombre d'allumette retirer par le joueur
	public void actualiserAlluRestantes(int allu) throws RemoteException;		// actualise le nombre d'allumette total par rapport a ceux retiré par le joueur et l'ordinateur
	public String estGagnant() throws RemoteException;// retourne le gagnant du jeu
	public int getCompt_ordi() throws RemoteException;// récupere le compteur de l'ordi
	public void setCompt_ordi(int compt_ordi) throws RemoteException;// permet d'actualiser le compteur de l'ordi
	public int getAlluRestantes() throws RemoteException;// recupère le nb d'allumette total
	public String getJoueurquicommence() throws RemoteException;// récupère le joueur qui joue en premier
	public boolean verifGagnant(int compt_ordi) throws RemoteException;// verification booleen si le joueur ou l'ordi est le gagnant
	

	

}



