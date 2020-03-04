package iut.progrep.games.pojo;

import java.io.Serializable;

public class JoueurAllumettes extends Joueur implements Serializable {
	private int joue;
	
	
	public JoueurAllumettes() {
		super();
	}

	public JoueurAllumettes(int joue) {
		super();
		this.joue = joue;
	}
	
	public int getJoue() {
		return joue;
	}

	public void setJoue(int joue) {
		this.joue = joue;
	}	
}
