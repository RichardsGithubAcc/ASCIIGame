import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Point;

public class Game {

	private ArrayList<Dynamic> dynamic;
	public final Terrain tree = new Terrain(this, 'T', new Color(0, 142, 25), "tree", null, 0, 0, false, 0);
	public final Terrain bush = new Terrain(this, '#', new Color(0, 200, 0), "bush", null, 0, 0, true, 1);
	private WorldMap map;
	private Player player;


	public Game(int panelCol, int panelRow) {
		Point camera = new Point();
		camera.x = panelCol/2;
		camera.y = panelRow/2;
		map = new WorldMap(this, panelCol, panelRow, camera, panelCol * panelRow);
		map.initialize();
		player = new Player(this, 'P', Color.RED, "Player", null, 0, 0, false, 20, 30, 200, 15.5, 16, 16, 5, 0, 0, 0, 0);
	}
	
	public WorldMap getMap() {
		return map;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void update() {
		for (Dynamic d : dynamic) {
			d.update();
		}
	}
	
	public static double dist(int x1, int y1, int x2, int y2) {
		return (double)Math.round(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
	}

	public void genForest(int x1, int y1, int x2, int y2) {
		for (int x = x1; x < x2; x++) {
			for (int y = y1; y < y2; y++) {
				if (Math.random() < 0.34) {
					map.setPoint(new Point(x, y), new Tile(new Terrain(this, 'T', new Color(0, 142, 25), "tree", null, x, y, false,
							0), null));
				} else {
					if (Math.random() < 0.34)
						map.setPoint(new Point(x, y),
								new Tile(new Terrain(this, '#', new Color(0, 200, 0), "bush", null, x, y, true, 1), null));
				}
			}
		}
	}
	
	

}

