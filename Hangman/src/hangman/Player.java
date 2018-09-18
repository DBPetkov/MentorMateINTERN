package hangman;

public class Player {
	
	private int score;
	private int moves;
	private final int SCORE=0;
	private final int MOVES=10;
		
	public int getScore() { //get Score
		return score;
	}
	public void addScore() { //add +1 to Score
		this.score++;
	}
	
	public int getMoves() { //get Moves
		return moves;
	}
	public void minusMove() { // minus 1 to moves
		this.moves--;
	}
	public void resetMoves() { //moves reset for new word
		this.moves = this.MOVES;
	}
	
	public Player() {
		this.score=this.SCORE;
		this.moves=this.MOVES;
	}

}
