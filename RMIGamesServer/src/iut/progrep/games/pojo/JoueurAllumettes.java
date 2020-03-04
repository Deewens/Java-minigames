package iut.progrep.games.pojo;

import java.io.Serializable;

public class JoueurAllumettes extends Joueur implements Serializable {
	private int joue;
	
	
	public JoueurAllumettes(String pseudo) {
		super(pseudo);
		
	}
	
	public int getJoue() {
		return joue;
	}

	public void setJoue(int joue) {
		this.joue = joue;
	}	
}
