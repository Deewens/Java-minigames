package iut.progrep.games.rmi;

import java.awt.Graphics;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import iut.progrep.games.Allumettes;
import iut.progrep.games.pojo.Joueur;
import iut.progrep.games.pojo.JoueurAllumettes;
import iut.progrep.games.rmi.AllumettesInterface;

public class AllumettesImpl extends UnicastRemoteObject implements AllumettesInterface {
	private JoueurAllumettes j1;

	private boolean j1Connexion;

	private Allumettes jeux;
	
	public AllumettesImpl() throws RemoteException {
		this.j1Connexion = false;
	}

	@Override
	public boolean rejoindrePartie(JoueurAllumettes j) throws RemoteException {	
		//l'unique joueur rejoint la partie
		if(this.j1Connexion ) {
			System.out.println("La partie est pleine.");
			return false;
		}
		else {
			if(!this.j1Connexion) {
				this.j1 = j;
				this.j1Connexion = true;
				
			}
			
			System.out.println("Le joueur " + j.getPseudo() + " a rejoint la partie.");
		}
		
		return true;
	}

	@Override
	public boolean quitterPartie(JoueurAllumettes j) throws RemoteException {
		// l'unique joueur quitte la partie lorsqu'elle est terminé
		if(j.equals(this.j1)) {
			this.j1Connexion = false;
		}
		else {
			System.err.println("Ce joueur n'est pas dans la partie.");
			return false;
		}
		
		System.out.println("Le joueur " + j.getPseudo() + " a quitt� la partie.");
		return true;
	}

	@Override
	public void lancerPartie() throws RemoteException {
		// des que l'unique joueur a rejoint, la partie peut commencer
		System.out.println(this.j1Connexion);
		if(this.j1Connexion == true ) {
			this.jeux = new Allumettes(j1);
		}
		else {
			System.out.println("Il n'y a pas assez de joueurs pour commencer la partie.");
		}
	}
		
	

	
		/*  implémentation des methodes de l'interface*/
	

	@Override
	public void jeuAllu(int allu) throws RemoteException { 
		this.jeux.jeuAllu(allu);		
	}

	@Override
	public void actualiserAlluRestantes(int allu) throws RemoteException {
		this.jeux.actualiserAlluRestantes(allu);			
	}

	

	@Override
	public String estGagnant() throws RemoteException {
		return this.jeux.estGagnant();
	}

	
	public int getAlluRestantes() throws RemoteException{
		return this.jeux.getAlluRestantes();
		
	}



	
	public JoueurAllumettes getJ1() {
		return j1;
	}

	public void setJ1(JoueurAllumettes j1) {
		this.j1 = j1;
	}


	@Override
	public boolean verifGagnant(int compt_ordi) throws RemoteException {
		// TODO Auto-generated method stub
		return this.jeux.verifGagnant(compt_ordi);
	}


	@Override
	public String getJoueurquicommence() throws RemoteException {
		// TODO Auto-generated method stub
		return this.jeux.getJoueurquicommence();
	}

	@Override
	public int getCompt_ordi() throws RemoteException {
		// TODO Auto-generated method stub
		return this.jeux.getCompt_ordi();
	}

	@Override
	public void setCompt_ordi(int compt_ordi) throws RemoteException {
		// TODO Auto-generated method stub
		this.jeux.compt_ordi=compt_ordi;	
		}

	

}
