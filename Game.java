import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Point;

public class Game {

	private ArrayList<Dynamic> dynamic;
	private WorldMap map;
	private Player player;
	public final Terrain tree = new Terrain(this, 'T', new Color(0, 142, 25), "tree", null, 0, 0, false, 0);
	public final Terrain bush = new Terrain(this, '#', new Color(0, 200, 0), "bush", null, 0, 0, true, 1);
	public static final int DEF_HEALTH = 10;
	public static final int DEF_MAX_HEALTH = 200;
	public static final int DEF_ARMOR_VAL = 10;
	public static final double DEF_HEALTH_MOD = 10;
	public static final double DEF_ARMOR_MOD = 10;
	public static final double DEF_ATTACK_MOD = 10;
	public static final int DEF_D_HEALTH = 10;
	public static final int DEF_STREANGTH = 10;
	public static final int DEF_DEXTERITY = 10;
	public static final int DEF_INTELLIGENCE = 10;
	public static final int DEF_PERCEPTION = 10;

	
	public Game(int panelCol, int panelRow) {
		Point camera = new Point();
		camera.x = 0;
		camera.y = 0;
		map = new WorldMap(this, panelCol, panelRow, camera, panelCol * panelRow);
		genForest(0, 0, panelCol, panelRow);
		player = new Player(this, 'P', Color.RED, "Player", null, camera.x, camera.y, false,
				DEF_HEALTH, DEF_ARMOR_VAL, DEF_MAX_HEALTH, DEF_HEALTH_MOD, DEF_ARMOR_MOD,DEF_ATTACK_MOD, DEF_D_HEALTH, 
				DEF_STREANGTH, DEF_DEXTERITY, DEF_INTELLIGENCE, DEF_PERCEPTION);
	}
	
	public WorldMap getMap() {
		return map;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void update() {
		map.setCamera(new Point(player.getX(), player.getY()));
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
					map.setPoint(new Point(x, y), new Tile(new Terrain(tree), null));
					
				} else {
					if (Math.random() < 0.34)
						map.setPoint(new Point(x, y), new Tile(new Terrain(bush), null));

				}
			}
		}
	}
	
	

}

