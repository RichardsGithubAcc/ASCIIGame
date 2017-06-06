import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Game {
	public WorldMap map = new WorldMap();
	public Player player;
	private ArrayList<Dynamic> dynamic;

	public void update() {
		for (Dynamic d : dynamic) {
			d.update();
		}
	}

	public void genForest(int x1, int y1, int x2, int y2) {
		for (int x = x1; x < x2; x++) {
			for (int y = y1; y < y2; y++) {
				if (Math.random() < 0.34) {
					map.setPoint(new Point(x, y), new Tile(new Terrain(this, 'T', new Color(0, 142, 25), "tree", x, y, false,

							0), null));
				} else {
					if (Math.random() < 0.34)
						map.setPoint(new Point(x, y),
								new Tile(new Terrain(this, '#', new Color(0, 200, 0), "bush", x, y, true, 1), null));
				}
			}
		}
	}
	
}
