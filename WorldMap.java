import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

public class WorldMap {
	private Game game;
	private HashMap<Point, Tile> map;
	private int panelCols;
	private int panelRows;
	private Point camera;
	private ArrayList<Dynamic> dynamic = new ArrayList<Dynamic>();
	
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
	
	public int getPanelCols() {
		return panelCols;
	}
	
	public int getPanelRows() {
		return panelRows;
	}
	
	public Point getCamera() {
		return camera;
	}
	
	public void setCamera(Point camera) {
		this.camera = camera;
	}
	
	public void setTile(Point point, Tile tile) {
		map.put(point, tile);
	}
	
	public Tile getTile(Point point) {
		Tile result = map.get(point);
		if (result != null) {
			return result;
		}
		else {
			return new Tile(new Terrain(game, '.', new Color(0, 142, 25), "sparse", null, point.x, point.y, true, 0));
		}
	}
	
	public boolean isOccupied(Point location) {
		return (map.get(location) != null || !getTile(location).getTerrain().getName().equals("sparse"));	
	}
	
	public void clear() {
		map.clear();
	}
	
	/*
	 *returns true if and only if every coordinate point within the rectangle, sides included,
	 * is associated with a null reference in the map or is equal to the default sparse tile
	 */
	public boolean isEmpty(Rectangle search) {
		for(int x = 0; x < search.width; x++) {
			for(int y = 0; y < search.height; y++) {
				if(map.get(new Point(search.x + x, search.y - y)) != null ||
						!getTile(new Point(search.x + x, search.y - y)).getTerrain().getName().equals("sparse")) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void setEmpty(Rectangle area) {
		for(int x = area.x; x < area.width; x++) {
			for(int y = area.y; y > area.y - area.height; y--) {
				map.put(new Point(x, y), null);
			}
		}
	}

	public ArrayList<Dynamic> getDynamic() {
		return dynamic;
	}

	public void setDynamic(ArrayList<Dynamic> dynamic) {
		this.dynamic = dynamic;
	}
	
	public void addDynamic(Dynamic d) {
		dynamic.add(d);
	}
}
