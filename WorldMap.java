import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;

public class WorldMap {
	private Game game;
	private HashMap<Point, Tile> map;
	private int panelCols;
	private int panelRows;
	private Point camera;
	
	public WorldMap(Game g) {
		game = g;
		map = new HashMap<Point, Tile>();
		panelCols = 81;
		panelRows = 81;
		camera = new Point();
		camera.x = 0;
		camera.y = 0;
	}
	
	public WorldMap(Game g, int panelCols, int panelRows, Point camera, int hashSize) {
		game = g;
		map = new HashMap<Point, Tile>(hashSize);
		this.panelCols = panelCols;
		this.panelRows = panelRows;
		this.camera = new Point();
		this.camera = camera;
	}
	
	public int getPanelCol() {
		return panelCols;
	}
	
	public int getPanelRow() {
		return panelRows;
	}
	
	public Point getCamera() {
		return camera;
	}
	
	public void setCamera(Point camera) {
		this.camera = camera;
	}
	
	public void setPoint(Point point, Tile tile) {
		map.put(point, tile);
	}
	
	public Tile getPoint(Point point) {
		Tile result = map.get(point);
		if (result != null) {
			return result;
		}
		else {
			return new Tile(new Terrain(game, '.', new Color(0, 142, 25), "sparse", null, point.x, point.y, true, 0));
		}
	}
	
	public boolean isOccupied(Point location) {
		return map.get(location) != null;
		
	}
}
