
public class GameBoard {
	private int[][] board;
	private int width, height;
	
	/*
	 * Constructor for a width and height
	 */
	public GameBoard(int width, int height) {
		board = new int[height][width];
		this.width = width;
		this.height = height;
	}
	
	/*
	 * Constructor for a 2d array of ints
	 */
	public GameBoard(int[][] initialSetup) {
		board = new int[initialSetup.length][initialSetup[0].length];
		for(int row = 0; row < initialSetup.length; row++) {
			for(int column = 0; column < initialSetup[row].length; column++) {
				board[row][column] = initialSetup[row][column];
			}
		}
		this.width = initialSetup.length;
		this.height = initialSetup[0].length;
	}
	
	/*
	 * Prints the contents of the matrix in a sensible way
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = "";
		for(int row = 0; row < board.length; row++) {
			for(int column = 0; column < board[row].length; column++) {
				result += board[row][column] + " ";
			}
			result += "\n";
		}
		return result;
	}
	
	/*
	 * returns the value at a given set of coordinates
	 */
	public int getPiece(int x, int y) {
		if(isValid(x, y)) 
			return board[y][x];
		else return 0;
	}
	
	/*
	 * returns the width of the matrix
	 */
	public int getWidth() {
		return width;
	}
	
	/*
	 * Gets the height of the matrix
	 */
	public int getHeight() {
		return height;
	}
	
	/*
	 * Sets the value at the given coordinates to the given integer
	 * Returns: the value replaced
	 */
	public int setPiece(int x, int y, int piece) {
		int temp = board[y][x];
		board[y][x] = piece;
		return temp;
	}
	
	/*
	 * Sets the value of the given coordinates to 0
	 * Returns: the value replaced
	 */
	public int setPiece(int x, int y) {
		int temp = board[y][x];
		board[y][x] = 0;
		return temp;
	}
	
	/*
	 * Sets the value at the given coordinates to 0
	 */
	public void remove(int x, int y) {
		setPiece(x, y, 0);
	}
	
	/*
	 * Clears the entire board
	 */
	public void clear() {
		for(int row = 0; row < board.length; row++) {
			for(int column = 0; column < board[row].length; column++) {
				board[row][column] = 0;
			}
		}
	}
	
	/*
	 * Determines whether a given set of coordinates is a valied position
	 */
	public boolean isValid(int x, int y) {
		try{
			if(board[y][x] != 0) {
				//do nothing
			}
			return true;
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	/*
	 * Determines whether a particular set of indices refers to a nonzero integer
	 */
	public boolean isOccupied(int x, int y) {
		if(isValid(x, y) && board[y][x] != 0)
			return true;
		else return false;
	}
	
	/*
	 * Determines whether a particular set of indices refers to a specified integer
	 */
	public boolean isOccupied(int x, int y, int piece) {
		if(isValid(x, y) && board[y][x] == piece)
			return true;
		else return false;
	}
}
