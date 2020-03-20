package iut.progrep.games.pojo;

import java.io.Serializable;
import java.util.Random;

public class Joueur implements Serializable {
	//private int id;
	private String pseudo;
	
	public Joueur() {
		Random rand = new Random();
		int randNumber = rand.nextInt(1000);
		this.pseudo = "Anonyme_" + randNumber;
	}
	
	public Joueur(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/*public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		return true;
	}
	
	
}
