package iut.progrep.games.pojo;

public class JoueurTicTacToe extends Joueur {
	private char symbole;

	public JoueurTicTacToe(String pseudo) {
		super(pseudo);
	}
	
	public JoueurTicTacToe(String pseudo, char symbole) {
		super(pseudo);
		this.symbole = symbole;
	}

	public char getSymbole() {
		return this.symbole;
	}
	
	public void setSymbole(char symbole) {
		this.symbole = symbole;
	}
}
