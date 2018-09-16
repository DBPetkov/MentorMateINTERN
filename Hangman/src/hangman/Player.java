package hangman;

public class Player {
	
	private int score;
	private int moves;
	private final int SCORE=0;
	private final int MOVES=10;
	
	
	public int getScore() {
		return score;
	}
	public void addScore() {
		this.score++;
	}
	
	public int getMoves() {
		return moves;
	}
	public void minusMove() {
		this.moves--;
	}
	public void resetMoves() {
		this.moves = this.MOVES;
	}
	
	public Player() {
		this.score=this.SCORE;
		this.moves=this.MOVES;
	}

}
