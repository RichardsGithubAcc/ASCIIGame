import java.awt.Point;
import java.util.ArrayList;

/*
 * Implementation of a Dijkstra Map, which is something the guy who made Brogue came up with - not Dijkstra
 * Use only for close up finesse pathfinding. AStar is better over long distances or when frequent path recalculating not required
 */
public class DijkstraMap {
	private GameBoard board;
	private ArrayList<Point> targets;
	private Game game;
	private int x, y;
	
	public DijkstraMap(Game game) {
		board = new GameBoard(81, 81);
		this.game = game;
		targets = new ArrayList<Point>();
		targets.add(new Point(game.getPlayer().getX(), game.getPlayer().getY()));
		x = game.getPlayer().getX() - 40;
		y = game.getPlayer().getY() - 40;
	}
	/**
	 * 
	 * @param x - bottom left corner
	 * @param y - bottom left corner
	 * @param width
	 * @param height
	 * @param game
	 * @param targets - "goals" for the map to path to
	 */
	public DijkstraMap(int x, int y, int width, int height, Game game, ArrayList<Point> targets) {
		board = new GameBoard(width, height);
		this.targets = targets;
		this.game = game;
		this.x = x;
		this.y = y;
	}
	
	public void map() {
		boolean stop = false;
		WorldMap map = game.getMap();
		for(int x = 0; x < board.getWidth(); x++) {
			for(int y = 0; y < board.getHeight(); x++) {
				board.setPiece(x, y, board.getWidth() + board.getHeight() + 1);
			}
		}
		while(!stop) {
			for(int x = 0; x < board.getWidth(); x++) {
				for(int y = 0; y < board.getHeight(); x++) {
					//int lr = (map.getTile(new Point(x+1, y)))
					int smallest = Math.min(Math.min(board.getPiece(x+1, y), board.getPiece(x-1, y)), Math.min(board.getPiece(x, y+1), board.getPiece(x, y-1)));
				}
			}
		}
	}
}
