import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
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
	private ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();
	
	private final int TOWN_SPAWN_CHANCE = 70;
	private final int TOWN_DENSITY_CONST = 15;
	private final int FOREST_SPAWN_CHANCE = 30;
	private final int FOREST_DENSITY_CONST = 10;
	
	public final Player DEF_PLAYER = new Player(this, '@', Color.WHITE, "player", null, 0, 0, false, 100, 100, 1, 1, 1, 0, 8, 8, 8, 8);
	public final Terrain TREE = new Terrain(this, 'T', new Color(0, 142, 25), "tree", null, 0, 0, false, 0);
	public final Terrain BUSH = new Terrain(this, '#', new Color(0, 200, 0), "bush", null, 0, 0, true, 1);
//	public final NPC ZOMBIE = new NPC(this, 'Z', new Color(85, 160, 144), "zombie", null, -10, -10, false, 50, 0, 50, 1, 1, 1, 0, true);
	public final Terrain WALL = new Terrain(this, 'W', new Color(100, 56, 0), "wall", null, 0, 0, false, 0);
	public final Terrain FLOOR = new Terrain(this, '-', new Color(155, 108, 0), "floor", null, 0, 0, true, 0);
	public final Door DOOR = new Door(this, new Color(114, 114, 114), "door", null, 0, 0, false, 0, "east", "key: 0", 2);
	public final Terrain H_ROAD = new Terrain(this, ExtendedASCII.getASCII(205), new Color(153, 153, 153), "horizontal road", null, 0, 0, true, 0);
	public final Terrain V_ROAD = new Terrain(this, ExtendedASCII.getASCII(186), new Color(153, 153, 153), "vertical road", null, 0, 0, true, 0);
	public final Terrain CROSS_ROAD = new Terrain(this, ExtendedASCII.getASCII(206), new Color(153, 153, 153), "vertical road", null, 0, 0, true, 0);
	public final Terrain TOP_LEFT_CORNER = new Terrain(this, ExtendedASCII.getASCII(201), new Color(153, 153, 153), "vertical road", null, 0, 0, true, 0);
	public final Terrain TOP_RIGHT_CORNER = new Terrain(this, ExtendedASCII.getASCII(187), new Color(153, 153, 153), "vertical road", null, 0, 0, true, 0);
	public final Terrain BOT_LEFT_CORNER = new Terrain(this, ExtendedASCII.getASCII(200), new Color(153, 153, 153), "vertical road", null, 0, 0, true, 0);
	public final Terrain BOT_RIGHT_CORNER = new Terrain(this, ExtendedASCII.getASCII(188), new Color(153, 153, 153), "vertical road", null, 0, 0, true, 0);
	public final Terrain T_INTERSECTION_UP = new Terrain(this, ExtendedASCII.getASCII(202), new Color(153, 153, 153), "vertical road", null, 0, 0, true, 0);
	public final Terrain T_INTERSECTION_DOWN = new Terrain(this, ExtendedASCII.getASCII(203), new Color(153, 153, 153), "vertical road", null, 0, 0, true, 0);
	public final Terrain T_INTERSECTION_LEFT = new Terrain(this, ExtendedASCII.getASCII(185), new Color(153, 153, 153), "vertical road", null, 0, 0, true, 0);
	public final Terrain T_INTERSECTION_RIGHT = new Terrain(this, ExtendedASCII.getASCII(204), new Color(153, 153, 153), "vertical road", null, 0, 0, true, 0);
	public final Item GARBAGE = new Item(this, 'G', new Color(180, 180, 180), "garbage", null, 0, 0, true, 5, 5, 100, 100, 0);
	
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
	//Weapon usp45 = new Weapon(this, '/', Color.GREY, "USP 45", null, x, y, 28, 12, 100, 100, 0.25, 80, 9, 28, {".45 ACP"}, 12);
	//Item 45ACP = new Item(this, '=', Color.GRAY, ".45 ACP", null, x, y, true, 0.15, 0.04, 10, 10, 1};
	
	public Game(int panelCols, int panelRows, GameDisplay frame) {
		this.frame = frame;
		Point camera = new Point();
		camera.x = 0;
		camera.y = 0;	
		map = new WorldMap(this, panelCols, panelRows, camera, panelCols * panelRows);
		genForest(0, 0, panelCols, panelRows);
		constructStore(100, 100, "south");
		constructStore(150, 100, "west");
		constructStore(100, 0, "east");
		constructStore(150, 0, "north");
		constructHouse(-40, -40, "north");
		constructHouse(-60, -80, "south");
		paveHRoad(-16, 0, -20);
		paveVRoad(0, -20, -40);
		getMap().getTile(new Point(-12, -12)).addEntity(GARBAGE);
		/*player = new Player(this, 'P', Color.RED, "Player", null, camera.x, camera.y, false,
				DEF_HEALTH, DEF_MAX_HEALTH, DEF_HEALTH_MOD, DEF_ARMOR_MOD,DEF_ATTACK_MOD, DEF_D_HEALTH, 
				DEF_STREANGTH, DEF_DEXTERITY, DEF_INTELLIGENCE, DEF_PERCEPTION);*/
		player = new Player(this, 0, 0);
		dummyPlayer = new Player(player);
		//dynamic = new ArrayList<Dynamic>();
		dynamic.add(player);
		
		ArrayList<Entity> items = new ArrayList<Entity>();
		items.add(player);
		map.setTile(new Point(0, 0), new Tile(this.BUSH, items));
		//map.setTile(new Point(-10, -10), new Tile(this.BUSH, new NPC(this, 'Z', new Color(85, 160, 144), "zombie", null, -10, -10, false, 50, 0, 50, 1, 1, 1, 0, true)));
		
		progress = new ArrayList<String>();
		//spawnHorde(-40, -40, -10, -10);
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
		constructRoom(-30, -30, 13, 21, "east");
	
		ArrayList<Entity> items = new ArrayList<Entity>();
		items.add(player);
		map.setTile(new Point(0, 0), new Tile(this.BUSH, items));
		//map.setTile(new Point(-10, -10), new Tile(this.BUSH, new NPC(this, 'Z', new Color(85, 160, 144), "zombie", null, -10, -10, false, 50, 0, 50, 1, 1, 1, 0, true)));
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
		if(player.getX()%1000 < 50 || player.getY()%1000 < 50 || player.getX()%1000 > 950 || player.getY()%1000 > 950) {
			int bX = Math.round((float)player.getX() / 1000);
			int bY = Math.round((float)player.getY() / 1000);
			
			Integer[] seed = new Integer[6];
			int rngSeed = (int)Math.round(Math.random() * 100);
			if(rngSeed < TOWN_SPAWN_CHANCE) {
				seed[0] = ((rngSeed - TOWN_SPAWN_CHANCE) / TOWN_DENSITY_CONST) + 1;
			}
			if(rngSeed < FOREST_SPAWN_CHANCE) {
				seed[5] = ((rngSeed - FOREST_SPAWN_CHANCE) / FOREST_DENSITY_CONST) + 1;
			}
			
			loadBlock(bX, bY, seed);
		}
	}
	/* 
	 * higher numbers indicate higher densities
	 * seed[0] = towns
	 * seed[1] = TBD
	 * seed[2] = TBD
	 * seed[3] = TBD
	 * seed[4] = TBD
	 * seed[5] = forests
	 */
	public void loadBlock(int x, int y, Integer[] blockStructures) {
		
	}
	
	public static double dist(int x1, int y1, int x2, int y2) {
		return (double)(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
	}
	
	public void genForest(int x1, int y1, int x2, int y2) {
		genForest(x1, y1, x2, y2, 0.3, 10);
	}

	public void genForest(int x1, int y1, int x2, int y2, double forestDensity, int forestFuzziness) {
		for (int x = (int) (x1 - Math.random() * forestFuzziness); x <= x2 + Math.random() * forestFuzziness; x++) {
			for (int y = (int) (y1 - Math.random() * forestFuzziness); y <= y2 + Math.random() * forestFuzziness; y++) {
				if (Math.random() < forestDensity) {
					map.setTile(new Point(x, y), new Tile(new Terrain(this, 'T', new Color(0, 142, 25), "tree", null, x, y, false, 0)));
					
				} else {
					if (Math.random() < forestDensity * 0.66)
						map.setTile(new Point(x, y), new Tile(new Terrain(this, '#', new Color(0, 200, 0), "bush", null, x, y, true, 1)));

				}
			}
		}
	}
	
	public void spawnHorde(int x1, int y1, int x2, int y2) {
		for(int x  = x1; x <= x2; x++) {
			for(int y = y1; y <= y2; y++) {
				if(Math.random() < 0.05) {
					Tile tile = map.getTile(new Point(x, y));
					if(tile == null) {
						map.setTile(new Point(x, y), new Tile(null, (Entity)(new NPC(this, 'Z', new Color(85, 160, 144), "zombie", null, x, y, false, 50, 0, 50, 1, 1, 1, 0, true))));
					} else {
						map.getTile(new Point(x, y)).addEntity((Entity)(new NPC(this, 'Z', new Color(85, 160, 144), "zombie", null, x, y, false, 50, 0, 50, 1, 1, 1, 0, true)));
					}
				}
			}
		}
	}
	
	public void kill(Dynamic c) {
		for(int i  = 0; i < dynamic.size(); i++) {
			if(dynamic.get(i) == c) dynamic.set(i, null);
		}
	}
	
	public void intersectionIcon() {
		// display appropriate icon for intersection
	}
	
	/*
	 * LEFT TO RIGHT
	 */
	public void paveHRoad(int x, int y, int length) {
		for(int i = 0; i < length; i++) {
			if(map.getTile(new Point(x + i, y)).getTerrain().getName().equalsIgnoreCase("V_ROAD")) {
				map.setTile(new Point(x + i, y), new Tile(CROSS_ROAD));
			} else {
				map.setTile(new Point(x + i, y), new Tile(H_ROAD));
			}
		}
	}
	
	/*
	 * TOP TO BOTTOM
	 */
	public void paveVRoad(int x, int y, int length) {
		for(int i = 0; i < length; i++) {
			if(map.getTile(new Point(x, y - i)).getTerrain().getName().equalsIgnoreCase("H_ROAD")) {
				map.setTile(new Point(x, y - i), new Tile(CROSS_ROAD));
			}
			map.setTile(new Point(x, y - i), new Tile(V_ROAD));
		}
	} 
	
	public void constructRoom(int x, int y, int width, int length, String direction) {
		for (int col = x; col <= x + width; col++) {
			for (int row = y; row <= y + length; row++) {
				if ((col == x || col == x + width) || (row == y || row == y + length)) {
					map.setTile(new Point(col, row), new Tile(new Terrain(WALL)));
				} else {
					map.setTile(new Point(col, row), new Tile(new Terrain(FLOOR)));
				}
			}
		}
		Door newDoor = new Door(DOOR);
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
			map.setTile(new Point(x + width/2, y + length), tile);
			break;
		case "east":
			map.setTile(new Point(x + width, y + length/2), tile);
			break;
		case "west":
			map.setTile(new Point(x , y + length/2), tile);
			break;
		case "south":
			default:
			map.setTile(new Point(x + width/2, y), tile);
			break;
			
		}
	}
	
	public void constructHouse(int x, int y, String direction) {
		constructRoom(x, y, 23, 23, direction);
		double rand = Math.random();
		double rand2 = Math.random();
		if (rand <= 0.125) { 
			constructRoom(x, y, 9, 9, "north");
			if (rand2 <= 0.167) constructRoom(x, y+16, 7, 7, "south");
			else if (rand2 <= 0.333) constructRoom(x, y+16, 7, 7, "east");
			else if (rand2 <= 0.5) constructRoom(x+16, y, 7, 7, "north");
			else if (rand2 <= 0.667) constructRoom(x+16, y, 7, 7, "west");
			else if (rand2 <= 0.833) constructRoom(x+16, y+16, 7, 7, "south");
			else constructRoom(x+16, y+16, 7, 7, "west");
		}
		else if (rand <= 0.25) {
			constructRoom(x, y, 9, 9, "east");
			if (rand2 <= 0.167) constructRoom(x, y+16, 7, 7, "south");
			else if (rand2 <= 0.333) constructRoom(x, y+16, 7, 7, "east");
			else if (rand2 <= 0.5) constructRoom(x+16, y, 7, 7, "north");
			else if (rand2 <= 0.667) constructRoom(x+16, y, 7, 7, "west");
			else if (rand2 <= 0.833) constructRoom(x+16, y+16, 7, 7, "south");
			else constructRoom(x+16, y+16, 7, 7, "west");
		}
		else if (rand <= 0.375) {
			constructRoom(x+14, y, 9, 9, "north");
			if (rand2 <= 0.167) constructRoom(x, y+16, 7, 7, "south");
			else if (rand2 <= 0.333) constructRoom(x, y+16, 7, 7, "east");
			else if (rand2 <= 0.5) constructRoom(x, y, 7, 7, "north");
			else if (rand2 <= 0.667) constructRoom(x, y, 7, 7, "east");
			else if (rand2 <= 0.833) constructRoom(x+16, y+16, 7, 7, "south");
			else constructRoom(x+16, y+16, 7, 7, "west");
		}
		else if (rand <= 0.5) {
			constructRoom(x+14, y, 9, 9, "west");
			if (rand2 <= 0.167) constructRoom(x, y+16, 7, 7, "south");
			else if (rand2 <= 0.333) constructRoom(x, y+16, 7, 7, "east");
			else if (rand2 <= 0.5) constructRoom(x, y, 7, 7, "north");
			else if (rand2 <= 0.667) constructRoom(x, y, 7, 7, "east");
			else if (rand2 <= 0.833) constructRoom(x+16, y+16, 7, 7, "south");
			else constructRoom(x+16, y+16, 7, 7, "west");
		}
		else if (rand <= 0.625) {
			constructRoom(x, y+14, 9, 9, "south");
			if (rand2 <= 0.167) constructRoom(x, y, 7, 7, "north");
			else if (rand2 <= 0.333) constructRoom(x, y, 7, 7, "east");
			else if (rand2 <= 0.5) constructRoom(x+16, y, 7, 7, "north");
			else if (rand2 <= 0.667) constructRoom(x+16, y, 7, 7, "west");
			else if (rand2 <= 0.833) constructRoom(x+16, y+16, 7, 7, "south");
			else constructRoom(x+16, y+16, 7, 7, "west");
		}
		else if (rand <= 0.75) {
			constructRoom(x, y+14, 9, 9, "east");
			constructRoom(x, y+14, 9, 9, "south");
			if (rand2 <= 0.167) constructRoom(x, y, 7, 7, "north");
			else if (rand2 <= 0.333) constructRoom(x, y, 7, 7, "east");
			else if (rand2 <= 0.5) constructRoom(x+16, y, 7, 7, "north");
			else if (rand2 <= 0.667) constructRoom(x+16, y, 7, 7, "west");
			else if (rand2 <= 0.833) constructRoom(x+16, y+16, 7, 7, "south");
			else constructRoom(x+16, y+16, 7, 7, "west");
		}
		else if (rand <= 0.875) {
			constructRoom(x+14, y+14, 9, 9, "south");
			if (rand2 <= 0.167) constructRoom(x, y+16, 7, 7, "south");
			else if (rand2 <= 0.333) constructRoom(x, y+16, 7, 7, "east");
			else if (rand2 <= 0.5) constructRoom(x+16, y, 7, 7, "north");
			else if (rand2 <= 0.667) constructRoom(x+16, y, 7, 7, "west");
			else if (rand2 <= 0.833) constructRoom(x, y, 7, 7, "north");
			else constructRoom(x, y, 7, 7, "east");
		}
		else {
			constructRoom(x+14, y+14, 9, 9, "west");
			if (rand2 <= 0.167) constructRoom(x, y+16, 7, 7, "south");
			else if (rand2 <= 0.333) constructRoom(x, y+16, 7, 7, "east");
			else if (rand2 <= 0.5) constructRoom(x+16, y, 7, 7, "north");
			else if (rand2 <= 0.667) constructRoom(x+16, y, 7, 7, "west");
			else if (rand2 <= 0.833) constructRoom(x, y, 7, 7, "north");
			else constructRoom(x, y, 7, 7, "east");
		}
	}
	
	public void constructStore(int x, int y, String direction) {
		switch (direction.toLowerCase()) {
		case "north":
			constructRoom(x, y, 39, 25, "north");
			constructRoom(x-15, y+4, 15, 17, "east");
			constructRoom(x+39, y+4, 15, 17, "west");
			constructRoom(x+10, y-8, 18, 8, "north");
			break;
		case "south":
			constructRoom(x, y, 39, 25, "south");
			constructRoom(x-15, y+4, 15, 17, "east");
			constructRoom(x+39, y+4, 15, 17, "west");
			constructRoom(x+11, y+25, 18, 8, "south");
			break;
		case "east":
			constructRoom(x, y, 25, 39, "east");
			constructRoom(x+4, y+39, 17, 15, "south");
			constructRoom(x+4, y-15, 17, 15, "north");
			constructRoom(x-8, y+11, 8, 18, "east");
			break;
		case "west":
			constructRoom(x, y, 25, 39, "west");
			constructRoom(x+4, y+39, 17, 15, "south");
			constructRoom(x+4, y-15, 17, 15, "north");
			constructRoom(x+25, y+10, 8, 18, "west");
			break;
		}
	}
	
	/*
	 * Constructs a town within the given rectangle
	 */
	public void constructTown(Rectangle bounds) {
		if(bounds.height > bounds.width) {
			paveRoad(bounds.x + bounds.width/2 - 3, bounds.y, bounds.height, true, ((double)bounds.width) / bounds.height);
		} else {
			paveRoad(bounds.y + bounds.height/2 - 3, bounds.x, bounds.width, false, ((double)bounds.height) / bounds.width);
		}
		
		for(int x = bounds.x; x < bounds.x + bounds.width; x++) {
			for(int y = bounds.y; y > bounds.y - bounds.height; y--) {
				
			}
		}
	}
	
	/*
	 * Creates and populates a road with upper left corner at (x, y), extending for length units
	 * Pass true for a vertical road, false for a horizontal one
	 * Will recursively create smaller branch roads for the town creator to put buildings next to
	 * Intended specifically for use in towns, so only makes width 5 roads
	 */
	public void paveRoad(int x, int y, int length, boolean vertical, double ratio) {
		if(vertical) {
			for(int i = 0; i < 5; i++) {
				paveVRoad(x + i, y, length);
			}
			for(int dX = x; dX < length; dX += 30 + Math.random() * 10) {
				int newLength = (int)Math.round((double)length * ratio);
				paveRoad(x + dX, y + newLength/2 + 2, newLength, false, ratio);
			}
		} else {
			for(int i = 0; i < 5; i ++) {
				paveHRoad(x, y + i, length);
			}
			for(int dY = y; dY < length; dY += 30 + Math.random() * 10) {
				int newLength = (int)Math.round((double)length * ratio);
				paveRoad(x + length/2 + 2, y + dY, newLength, false, ratio);
			}
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

	public void setProgress(ArrayList<String> progress) {
		this.progress = progress;
	}

}

