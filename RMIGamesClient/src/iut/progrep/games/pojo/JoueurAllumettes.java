package iut.progrep.games.pojo;

import java.io.Serializable;
// JoueurAllumettes possède les mêmes attributs que Joueur
// avec un compteur d'allumette en plus pour le jeu
public class JoueurAllumettes extends Joueur implements Serializable {
	private int compteurAllumettes;
	
	
	public JoueurAllumettes(String pseudo) {
		super(pseudo);
	}

	public JoueurAllumettes(String pseudo, int compteurAllumettes) {
		super(pseudo);
		this.compteurAllumettes = compteurAllumettes;
	}
	
	public int getCompteurAllumettes() {
		return compteurAllumettes;
	}

	public void setCompteurAllumettes(int compteurAllumettes) {
		this.compteurAllumettes = compteurAllumettes;
	}	
}
