import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JFrame;

public class Game {

	private ArrayList<Dynamic> dynamic = new ArrayList<Dynamic>();
	private WorldMap map;
	private Player player;
	private Player dummyPlayer;
	private ArrayList<String> progress;
	private GameDisplay frame;
	
	public final Player DEF_PLAYER = new Player(this, '@', Color.WHITE, "player", null, 0, 0, false, 100, 100, 1, 1, 1, 0, 8, 8, 8, 8);
	public final Terrain tree = new Terrain(this, 'T', new Color(0, 142, 25), "tree", null, 0, 0, false, 0);
	public final Terrain bush = new Terrain(this, '#', new Color(0, 200, 0), "bush", null, 0, 0, true, 1);
	public final NPC ZOMBIE = new NPC(this, 'Z', new Color(85, 160, 144), "zombie", null, -10, -10, false, 50, 0, 50, 1, 1, 1, 0, true);
	public final Terrain wall = new Terrain(this, 'W', new Color(100, 56, 0), "wall", null, 0, 0, false, 0);
	public final Terrain floor = new Terrain(this, '-', new Color(155, 108, 0), "floor", null, 0, 0, true, 0);
	public final Door door = new Door(this, new Color(114, 114, 114), "door", null, 0, 0, false, 0, "east", "key: 0", 2);
	
	public static final int DEF_HEALTH = 100;
	public static final int DEF_MAX_HEALTH = 100;
	public static final int DEF_ARMOR_VAL = 0;
	public static final double DEF_HEALTH_MOD = 1;
	public static final double DEF_ARMOR_MOD = 1;
	public static final double DEF_ATTACK_MOD = 1;
	public static final int DEF_D_HEALTH = 0;
	public static final int DEF_STRENGTH = 8;
	public static final int DEF_DEXTERITY = 8;
	public static final int DEF_INTELLIGENCE = 8;
	public static final int DEF_PERCEPTION = 8;

	
	public Game(int panelCols, int panelRows, GameDisplay frame) {
		this.frame = frame;
		Point camera = new Point();
		camera.x = 0;
		camera.y = 0;	
		map = new WorldMap(this, panelCols, panelRows, camera, panelCols * panelRows);
		genForest(0, 0, panelCols, panelRows);
		buildHouse(-30, -30, 13, 21, "east");
		
		/*player = new Player(this, 'P', Color.RED, "Player", null, camera.x, camera.y, false,
				DEF_HEALTH, DEF_MAX_HEALTH, DEF_HEALTH_MOD, DEF_ARMOR_MOD,DEF_ATTACK_MOD, DEF_D_HEALTH, 
				DEF_STREANGTH, DEF_DEXTERITY, DEF_INTELLIGENCE, DEF_PERCEPTION);*/
		player = new Player(this, 0, 0);
		dummyPlayer = new Player(player);
		//dynamic = new ArrayList<Dynamic>();
		dynamic.add(player);
		
		ArrayList<Entity> items = new ArrayList<Entity>();
		items.add(player);
		map.setPoint(new Point(0, 0), new Tile(this.bush, items));
		map.setPoint(new Point(-10, -10), new Tile(this.bush, new NPC(this, 'Z', new Color(85, 160, 144), "zombie", null, -10, -10, false, 50, 0, 50, 1, 1, 1, 0, true)));
		
		progress = new ArrayList<String>();
		spawnHorde(-40, -40, -10, -10);
		frame.repaint();
	}
	
	public WorldMap getMap() {
		return map;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	public void addProgress(String report) {
		progress.add(report);
	}
	
	public ArrayList<String> getProgress() {
		return progress;
	}
	
	public void updateProgress() {
		frame.repaint();
	}
	
	public void reset() {
		dynamic.clear();
		progress.clear();
		map.clear();
		Point camera = new Point();
		camera.x = 0;
		camera.y = 0;
		map.setCamera(camera);
		player.clearInventory();
		player = new Player(dummyPlayer);
		dynamic.add(player);
	
		genForest(0, 0, map.getPanelCols(), map.getPanelRows());
		buildHouse(-30, -30, 13, 21, "east");
	
		ArrayList<Entity> items = new ArrayList<Entity>();
		items.add(player);
		map.setPoint(new Point(0, 0), new Tile(this.bush, items));
		map.setPoint(new Point(-10, -10), new Tile(this.bush, new NPC(this, 'Z', new Color(85, 160, 144), "zombie", null, -10, -10, false, 50, 0, 50, 1, 1, 1, 0, true)));
		spawnHorde(-40, -40, -10, -10);
	}
	


	public void update() {
		if(player.getHealth() <= 0) {
			frame.gameOver();
		}
		map.setCamera(new Point(player.getX(), player.getY()));
		if (dynamic != null) {
			for (Dynamic d : dynamic) {
				d.update();
			}
		}
	}
	
	public static double dist(int x1, int y1, int x2, int y2) {
		return (double)(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
	}

	public void genForest(int x1, int y1, int x2, int y2) {
		for (int x = (int) (x1 - Math.random() * 10); x < x2 + Math.random() * 10; x++) {
			for (int y = (int) (y1 - Math.random() * 10); y < y2 + Math.random() * 10; y++) {
				if (Math.random() < 0.3) {
					map.setPoint(new Point(x, y), new Tile(new Terrain(this, 'T', new Color(0, 142, 25), "tree", null, x, y, false, 0)));
					
				} else {
					if (Math.random() < 0.2)
						map.setPoint(new Point(x, y), new Tile(new Terrain(this, '#', new Color(0, 200, 0), "bush", null, x, y, true, 1)));

				}
			}
		}
	}
	
	public void spawnHorde(int x1, int y1, int x2, int y2) {
		for(int x  = x1; x < x2; x++) {
			for(int y = y1; y < y2; y++) {
				if(Math.random() < 0.05) {
					Tile tile = map.getPoint(new Point(x, y));
					if(tile == null) {
						map.setPoint(new Point(x, y), new Tile(null, (Entity)(new NPC(this, 'Z', new Color(85, 160, 144), "zombie", null, x, y, false, 50, 0, 50, 1, 1, 1, 0, true))));
					} else {
						map.getPoint(new Point(x, y)).addItem((Entity)(new NPC(this, 'Z', new Color(85, 160, 144), "zombie", null, x, y, false, 50, 0, 50, 1, 1, 1, 0, true)));
					}
				}
			}
		}
	}
	
	public void kill(Dynamic c) {
		for(int i  = 0; i < dynamic.size(); i++) {
			if(dynamic.get(i) == c) dynamic.remove(i);
		}
	}
	public void buildHouse(int x, int y, int width, int length, String direction) {
		
		for (int col = x; col < x + width; col++) {
			for (int row = y; row < y + length; row++) {
				if ((col == x || col == x + width - 1) || (row == y || row == y + length -1)) {
					map.setPoint(new Point(col, row), new Tile(new Terrain(wall)));
				} else {
					map.setPoint(new Point(col, row), new Tile(new Terrain(floor)));
				}
			}
		}
		Door newDoor = new Door(door);
		newDoor.setDirection(direction);
		if (direction.equalsIgnoreCase("north") || direction.equalsIgnoreCase("south")) {
			newDoor.setIcon('_');
		} else {
			newDoor.setIcon('|');
		}
		
		if (Math.random() < 0) {
			newDoor.setStatus(Door.IS_OPEN);
			newDoor.setIcon('O');
			if (Math.random() < 0) {
				String key = "key: " + Math.random() * 100000000;
				newDoor.setLock(key);
			} else {
				newDoor.setLock("key: 0");
			}
			
		} else if (Math.random() < 1.1) {
			newDoor.setStatus(Door.IS_CLOSED_WITHOUT_LOCK);
			newDoor.setLock("key: 0");
		} else {
			newDoor.setStatus(Door.IS_CLOSED_WITH_LOCK);
			String key = "key: " + Math.random() * 100000000;
			newDoor.setLock(key);
		}
		
		Tile tile = new Tile(newDoor);
		switch (direction.toLowerCase()) {
		case "north":
			map.setPoint(new Point(x + width/2, y + length -1), tile);
			break;
		case "east":
			map.setPoint(new Point(x + width -1, y + length/2), tile);
			break;
		case "west":
			map.setPoint(new Point(x , y + length/2), tile);
			break;
		case "south":
			default:
			map.setPoint(new Point(x + width/2, y), tile);
			break;
			
		}
	}
	
	public ArrayList<Dynamic> getDynamic() {
		return dynamic;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(GameDisplay frame) {
		this.frame = frame;
	}

}

