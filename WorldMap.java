import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;

public class WorldMap {
	private Game game;
	private HashMap<Point, Tile> map;
	private int panelCol;
	private int panelRow;
	private Point camera;
	
	public WorldMap(Game g) {
		game = g;
		map = new HashMap<Point, Tile>();
		panelCol = 80;
		panelRow = 30;
		camera = new Point();
		camera.x = 40;
		camera.y = 15;
	}
	
	public WorldMap(Game g, int panelCol, int panelRow, Point camera, int hashSize) {
		game = g;
		map = new HashMap<Point, Tile>(hashSize);
		this.panelCol = panelCol;
		this.panelRow = panelRow;
		this.camera = new Point();
		this.camera = camera;
	}
	
	public int getPanelCol() {
		return panelCol;
	}
	
	public int getPanelRow() {
		return panelRow;
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
		return (Tile)map.get(point);
	}
	
	public boolean isOccupied(Point location) {
		if(map.get(location) == null) return false;
		return true;
	}
	
	public void initialize() {
		int startX = camera.x - panelCol/2;
		int startY = camera.y - panelRow/2;
		
		for (int i = 0; i < panelCol; i++) {
			for (int j = 0; j < panelRow; j++) {
				Point point = new Point();
				point.x = startX + i;
				point.y = startY + j;
				double r = Math.random();
				if (r < 0.34) {
					setPoint(point, new Tile(new Terrain(game, 'T', new Color(0, 142, 25), "tree", point.x, point.y, false, 0), null));

				} 
				else if (r < 0.67) {
					setPoint(point, new Tile(new Terrain(game, '#', new Color(0, 200, 0), "bush", point.x, point.y, true, 1), null));
				}
			}
		}
		

				
				
				
	}
	
	public void increment() {
		
	}
	
}
