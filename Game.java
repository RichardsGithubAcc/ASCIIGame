import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;
import javax.swing.JFrame;

public class Game {

	private ArrayList<Dynamic> dynamic = new ArrayList<Dynamic>();
	private WorldMap map;
	private HashMap<Integer, WorldMap> maps;
	private int activeMap;

	private Player player;
	private Player dummyPlayer;
	private ArrayList<String> progress;
	private GameDisplay frame;
	private ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();

	private final int TOWN_SPAWN_CHANCE = 70;
	private final int TOWN_DENSITY_CONST = 5;
	private final int FOREST_SPAWN_CHANCE = 30;
	private final int FOREST_DENSITY_CONST = 1;

	public final Player DEF_PLAYER = new Player(this, '@', Color.WHITE, "player", null, 0, 0, false, 100, 100, 1, 1, 1,
			0, 8, 8, 8, 8);
	public final Terrain TREE = new Terrain(this, 'T', new Color(0, 142, 25), "tree", null, 0, 0, false, 0);
	public final Terrain BUSH = new Terrain(this, '#', new Color(0, 200, 0), "bush", null, 0, 0, true, 1);
	// public final NPC ZOMBIE = new NPC(this, 'Z', new Color(85, 160, 144),
	// "zombie", null, -10, -10, false, 50, 0, 50, 1, 1, 1, 0, true);
	public final Terrain WALL = new Terrain(this, 'W', new Color(100, 56, 0), "wall", null, 0, 0, false, 0);
	public final Terrain FLOOR = new Terrain(this, '-', new Color(155, 108, 0), "floor", null, 0, 0, true, 0);
	public final Door DOOR = new Door(this, new Color(114, 114, 114), "door", null, 0, 0, false, 0, "east", "key: 0",
			2);
	public final Terrain H_ROAD = new Terrain(this, ExtendedASCII.getASCII(205), new Color(153, 153, 153),
			"horizontal road", null, 0, 0, true, 0);
	public final Terrain V_ROAD = new Terrain(this, ExtendedASCII.getASCII(186), new Color(153, 153, 153),
			"vertical road", null, 0, 0, true, 0);
	public final Terrain CROSS_ROAD = new Terrain(this, ExtendedASCII.getASCII(206), new Color(153, 153, 153),
			"vertical road", null, 0, 0, true, 0);
	public final Terrain TOP_LEFT_CORNER = new Terrain(this, ExtendedASCII.getASCII(201), new Color(153, 153, 153),
			"vertical road", null, 0, 0, true, 0);
	public final Terrain TOP_RIGHT_CORNER = new Terrain(this, ExtendedASCII.getASCII(187), new Color(153, 153, 153),
			"vertical road", null, 0, 0, true, 0);
	public final Terrain BOT_LEFT_CORNER = new Terrain(this, ExtendedASCII.getASCII(200), new Color(153, 153, 153),
			"vertical road", null, 0, 0, true, 0);
	public final Terrain BOT_RIGHT_CORNER = new Terrain(this, ExtendedASCII.getASCII(188), new Color(153, 153, 153),
			"vertical road", null, 0, 0, true, 0);
	public final Terrain T_INTERSECTION_UP = new Terrain(this, ExtendedASCII.getASCII(202), new Color(153, 153, 153),
			"vertical road", null, 0, 0, true, 0);
	public final Terrain T_INTERSECTION_DOWN = new Terrain(this, ExtendedASCII.getASCII(203), new Color(153, 153, 153),
			"vertical road", null, 0, 0, true, 0);
	public final Terrain T_INTERSECTION_LEFT = new Terrain(this, ExtendedASCII.getASCII(185), new Color(153, 153, 153),
			"vertical road", null, 0, 0, true, 0);
	public final Terrain T_INTERSECTION_RIGHT = new Terrain(this, ExtendedASCII.getASCII(204), new Color(153, 153, 153),
			"vertical road", null, 0, 0, true, 0);
	public final Item GARBAGE = new Item(this, 'G', new Color(180, 180, 180), "garbage", null, 0, 0, true, 5, 1, 10, 10,
			0);

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
	// Weapon usp45 = new Weapon(this, '/', Color.GREY, "USP 45", null, x, y,
	// 28, 12, 100, 100, 0.25, 80, 9, 28, {".45 ACP"}, 12);
	// Item 45ACP = new Item(this, '=', Color.GRAY, ".45 ACP", null, x, y, true,
	// 0.15, 0.04, 10, 10, 1};

	public Game(int panelCols, int panelRows, GameDisplay frame) {
		this.frame = frame;
		Point camera = new Point();
		camera.x = 0;
		camera.y = 0;
		maps = new HashMap<Integer, WorldMap>();
		map = new WorldMap(this, panelCols, panelRows, camera, panelCols * panelRows);
		maps.put(0, map);
		maps.put(-1, new WorldMap(this, panelCols, panelRows, camera, panelCols * panelRows));
		// genForest(0, 0, panelCols, panelRows);
		// constructStore(100, 100, "south");
		// constructStore(150, 100, "west");
		// constructStore(100, 0, "east");
		// constructStore(150, 0, "north");
		// constructHouse(-40, -40, "north");
		// constructHouse(-60, -80, "south");
		//constructTown(new Rectangle(-100, 0, 200, 500));
		// constructStore(0, 0, "west");
		// paveRoad(0, 0, 200, true, 0.66);
		// paveHRoad(-16, 0, -20);
		// paveVRoad(0, -20, -40);
		Integer[] upperLeft = { 3, 0, 0, 0, 0, 3 };
		Integer[] lowerLeft = { 1, 0, 0, 0, 0, 2 };
		Integer[] upperRight = { 0, 0, 0, 0, 0, 1 };
		Integer[] lowerRight = { 2, 0, 0, 0, 0, 0 };
		loadBlock(-1, 0, upperLeft);
		loadBlock(0, 0, upperRight);
		loadBlock(0, -1, lowerRight);
		loadBlock(-1, -1, lowerLeft);

		/*
		 * player = new Player(this, 'P', Color.RED, "Player", null, camera.x,
		 * camera.y, false, DEF_HEALTH, DEF_MAX_HEALTH, DEF_HEALTH_MOD,
		 * DEF_ARMOR_MOD,DEF_ATTACK_MOD, DEF_D_HEALTH, DEF_STREANGTH,
		 * DEF_DEXTERITY, DEF_INTELLIGENCE, DEF_PERCEPTION);
		 */
		player = new Player(this, 0, 0);
		dummyPlayer = new Player(player);
		// dynamic = new ArrayList<Dynamic>();
		dynamic.add(player);
		map.addDynamic(player);

		// ArrayList<Entity> items = new ArrayList<Entity>();
		// items.add(player);
		// map.setTile(new Point(0, 0), new Tile(this.BUSH, items));
		map.setTile(new Point(0, 0), new Tile(this.BUSH, player));
		map.getTile(new Point(0, 0)).setItem(GARBAGE);
		// map.setTile(new Point(-10, -10), new Tile(this.BUSH, new NPC(this,
		// 'Z', new Color(85, 160, 144), "zombie", null, -10, -10, false, 50, 0,
		// 50, 1, 1, 1, 0, true)));

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
	 * @param player
	 *            the player to set
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

		// ArrayList<Entity> items = new ArrayList<Entity>();
		// items.add(player);
		// map.setTile(new Point(0, 0), new Tile(this.BUSH, items));
		map.setTile(new Point(0, 0), new Tile(this.BUSH, player));
		// map.setTile(new Point(-10, -10), new Tile(this.BUSH, new NPC(this,
		// 'Z', new Color(85, 160, 144), "zombie", null, -10, -10, false, 50, 0,
		// 50, 1, 1, 1, 0, true)));
		spawnHorde(-40, -40, -10, -10);
	}

	public void update() {
		if (player.getHealth() <= 0) {
			frame.gameOver();
		}
		map.setCamera(new Point(player.getX(), player.getY()));
		dynamic = map.getDynamic();
		if (dynamic != null) {
			for (Dynamic d : dynamic) {
				if (d != null)
					d.update();
			}
		}
		boolean left = player.getX() % 1000 < 100;
		boolean down = player.getY() % 1000 < 100;
		boolean right = player.getX() % 1000 > 900;
		boolean up = player.getY() % 1000 > 900;
		if (left || down || right || up) {
			int bX = (int) ((float) player.getX() / 1000);
			int bY = (int) ((float) player.getY() / 1000) + 1;
			if (left)
				bX -= 1;
			if (right)
				bX += 1;
			if (up)
				bY += 1;
			if (down)
				bY -= 1;
			Integer[] seed = new Integer[6];
			int rngSeed = (int) Math.round(Math.random() * 100);
			if (rngSeed < TOWN_SPAWN_CHANCE) {
				seed[0] = ((rngSeed - TOWN_SPAWN_CHANCE) / TOWN_DENSITY_CONST) + 1;
			}
			if (rngSeed < FOREST_SPAWN_CHANCE) {
				seed[5] = ((rngSeed - FOREST_SPAWN_CHANCE) / FOREST_DENSITY_CONST) + 1;
			}

			boolean load = true;
			for (int i = 0; i < blocks.size(); i++) {
				if (blocks.get(i).x == bX && blocks.get(i).y == bY)
					load = false;
			}
			if (load) {
				loadBlock(bX, bY, seed);
			}
		}
	}

	/*
	 * higher numbers indicate higher densities seed[0] = towns - number
	 * indicates width/height seed[1] = TBD seed[2] = TBD seed[3] = TBD seed[4]
	 * = TBD seed[5] = forests - number is the forest density constant, number *
	 * 10 +- Math.random = forest fuzziness constant;
	 * x and y is lower left corner
	 */
	public void loadBlock(int x1, int y1, Integer[] seed) {
		for(Rectangle r : blocks) {
			if(r.x == x1 && r.y == y1) return;
		}
		int x = x1 * 1000;
		int y = y1 * 1000;
		if (seed == null)
			return;
		blocks.add(new Rectangle(x1, y1, 1000, 1000));
		if (seed[5] != null && seed[5] > 0) {
			System.out.println(seed[5]);
			genForest(x, y, x + 1000, y + 1000, seed[5], seed[5] * 30 + ((int) Math.random() * 20 - 10));
		}

		if (seed[0] != null && seed[0] > 0) {
			int adj = (int) (Math.random() * 50 - 25);
			int width = 300 + 100 * seed[0] + adj;
			int height = 300 + 100 * seed[0];
			int bX = (int) (Math.random() * 1000 - width) + x;
			int bY = (int) (Math.random() * 1000 - height) + y;
			map.setEmpty(new Rectangle(bX - 2, bY + 2 + height, width + 4, height + 4));
			System.out.println("town construction beginning at (" + bX + ", " + bY + ")");
			constructTown(new Rectangle(bX, bY + height, width, height));
		}
		String[] tags = {""};
		Terrain rose = new Terrain(this, 'f', new Color(255, 0, 0), "rose", tags,  0, 0, true, 0);
		Terrain bluebell = new Terrain(this, 'f', new Color(0, 0, 255), "bluebell", tags,  0, 0, true, 0);
		Terrain dandelion = new Terrain(this, 'f', new Color(255, 255, 0), "dandelion", tags,  0, 0, true, 0);
		Terrain bush = new Terrain(this, '#', new Color(0, 200, 0), "bush", null, x, y, true, 1);
		for(int nx = x; nx < x + 1000; nx++) {
			Terrain t = null;
			for(int ny = y; ny < y + 1000; ny++) {
				switch((int)(Math.random() * 4)) {
				case 0: t = rose;
					t.setX(nx);
					t.setY(ny);
					break;
				case 1: t = bluebell;
					t.setX(nx);
					t.setY(ny);
					break;
				case 2: t = dandelion;
					t.setX(nx);
					t.setY(ny);
					break;
				case 3: t = bush;
					t.setX(nx);
					t.setY(ny);
					break;
				}
				if(map.getTile(new Point(nx, ny)).getTerrain().getName().equals("sparse") && Math.random() * 100 < 0.1) {
					map.setTile(new Point(nx, ny), new Tile(new Terrain(t)));
				}
			}
		}
		
		System.out.println("Block loaded: (" + x1 + ", " + y1 + ")");
	}

	public static double dist(int x1, int y1, int x2, int y2) {
		return (double) (Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
	}
	/*
	 * 0-49 ranged
	 * 0-24 guns
	 * 25-49 magic?
	 * 50-99 melee
	 */
	public Item spawnItem(int id) {
		switch(id) {
		case(0): String[] tags = {"RANGED"};
			String[] ammo = {"Argent-Based Plasma"};
			return new Weapon(this, 'B', new Color(95, 255, 5), "BFG 9000", tags, 0, 0, 4, 2, 9001, 9001, 6, 100, 20, 666, ammo, 3);
		case(50): Color c = (Math.random() < 0.5) ? Color.BLUE : Color.RED;
			String[] tags1 = {"IGNORE_ARMOR"};
			return new Item(this, '/', c, "Lightsaber", tags1, 0, 0, true, 0.1, 0.1, 999, 999, 999);
		case(122): Color c1 = new Color(170, 126, 3);
			String[] tags2 = {"PIERCING"};
			return new Item(this, '-', c1, "wooden bolt", null, 0.016, 0.01, 10, 10, 1);
		case(100): String[] tag = {"IGNORE_ARMOR"};
			Item r = new Item(this, '=', new Color(88, 255, 50), "Argent-Based Plasma", tag, 0, 0, true, 0.5, 0.2, 3, 3, 1);
			return r;
		default: return  null;
		}
		//return null;
	}

	public void genForest(int x1, int y1, int x2, int y2) {
		genForest(x1, y1, x2, y2, 30, 10);
	}

	public void genForest(int x1, int y1, int x2, int y2, double forestDensity, int forestFuzziness) {
		for (int x = (int) (x1 - Math.random() * forestFuzziness); x <= x2 + Math.random() * forestFuzziness; x++) {
			for (int y = (int) (y1 - Math.random() * forestFuzziness); y <= y2 + Math.random() * forestFuzziness; y++) {
				if (Math.random() * 100 < forestDensity) {
					map.setTile(new Point(x, y),
							new Tile(new Terrain(this, 'T', new Color(0, 142, 25), "tree", null, x, y, false, 0)));

				} else {
					if (Math.random() * 100 < forestDensity * 0.66)
						map.setTile(new Point(x, y),
								new Tile(new Terrain(this, '#', new Color(0, 200, 0), "bush", null, x, y, true, 1)));

				}
			}
		}
	}

	public void spawnHorde(int x1, int y1, int x2, int y2) {
		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				if (Math.random() < 0.05) {
					Tile tile = map.getTile(new Point(x, y));
					NPC zombie = new NPC(this, 'Z', new Color(85, 160, 144), "zombie", null, x, y, false, 50, 0, 50, 1, 1, 1, 0, true);
					map.addDynamic(zombie);
					if (tile == null) {
						map.setTile(new Point(x, y), new Tile(null, zombie));
					} else {
						map.getTile(new Point(x, y)).setCreature(zombie);
					}
				}
			}
		}
	}

	public void kill(int x, int y, Dynamic c) {
		for (int i = 0; i < dynamic.size(); i++) {
			if (dynamic.get(i) == c) {
				dynamic.set(i, null);
				map.getTile(new Point(x, y)).setCreature(null);
			}
		}
	}

	public void intersectionIcon() {
		// display appropriate icon for intersection
	}

	/*
	 * LEFT TO RIGHT
	 */
	public void paveHRoad(int x, int y, int length) {
		for (int i = 0; i < length; i++) {
			if (map.getTile(new Point(x + i, y)).getTerrain().getName().equals("vertical road")) {
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
		for (int i = 0; i < length; i++) {
			if (map.getTile(new Point(x, y - i)).getTerrain().getName().equals("horizontal road")) {
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
			map.setTile(new Point(x + width / 2, y + length), tile);
			break;
		case "east":
			map.setTile(new Point(x + width, y + length / 2), tile);
			break;
		case "west":
			map.setTile(new Point(x, y + length / 2), tile);
			break;
		case "south":
		default:
			map.setTile(new Point(x + width / 2, y), tile);
			break;

		}
	}

	public void constructHouse(int x, int y, String direction) {
		constructRoom(x, y, 23, 23, direction);
		double rand = Math.random();
		double rand2 = Math.random();
		if (rand <= 0.125) {
			constructRoom(x, y, 9, 9, "north");
			if (rand2 <= 0.167)
				constructRoom(x, y + 16, 7, 7, "south");
			else if (rand2 <= 0.333)
				constructRoom(x, y + 16, 7, 7, "east");
			else if (rand2 <= 0.5)
				constructRoom(x + 16, y, 7, 7, "north");
			else if (rand2 <= 0.667)
				constructRoom(x + 16, y, 7, 7, "west");
			else if (rand2 <= 0.833)
				constructRoom(x + 16, y + 16, 7, 7, "south");
			else
				constructRoom(x + 16, y + 16, 7, 7, "west");
		} else if (rand <= 0.25) {
			constructRoom(x, y, 9, 9, "east");
			if (rand2 <= 0.167)
				constructRoom(x, y + 16, 7, 7, "south");
			else if (rand2 <= 0.333)
				constructRoom(x, y + 16, 7, 7, "east");
			else if (rand2 <= 0.5)
				constructRoom(x + 16, y, 7, 7, "north");
			else if (rand2 <= 0.667)
				constructRoom(x + 16, y, 7, 7, "west");
			else if (rand2 <= 0.833)
				constructRoom(x + 16, y + 16, 7, 7, "south");
			else
				constructRoom(x + 16, y + 16, 7, 7, "west");
		} else if (rand <= 0.375) {
			constructRoom(x + 14, y, 9, 9, "north");
			if (rand2 <= 0.167)
				constructRoom(x, y + 16, 7, 7, "south");
			else if (rand2 <= 0.333)
				constructRoom(x, y + 16, 7, 7, "east");
			else if (rand2 <= 0.5)
				constructRoom(x, y, 7, 7, "north");
			else if (rand2 <= 0.667)
				constructRoom(x, y, 7, 7, "east");
			else if (rand2 <= 0.833)
				constructRoom(x + 16, y + 16, 7, 7, "south");
			else
				constructRoom(x + 16, y + 16, 7, 7, "west");
		} else if (rand <= 0.5) {
			constructRoom(x + 14, y, 9, 9, "west");
			if (rand2 <= 0.167)
				constructRoom(x, y + 16, 7, 7, "south");
			else if (rand2 <= 0.333)
				constructRoom(x, y + 16, 7, 7, "east");
			else if (rand2 <= 0.5)
				constructRoom(x, y, 7, 7, "north");
			else if (rand2 <= 0.667)
				constructRoom(x, y, 7, 7, "east");
			else if (rand2 <= 0.833)
				constructRoom(x + 16, y + 16, 7, 7, "south");
			else
				constructRoom(x + 16, y + 16, 7, 7, "west");
		} else if (rand <= 0.625) {
			constructRoom(x, y + 14, 9, 9, "south");
			if (rand2 <= 0.167)
				constructRoom(x, y, 7, 7, "north");
			else if (rand2 <= 0.333)
				constructRoom(x, y, 7, 7, "east");
			else if (rand2 <= 0.5)
				constructRoom(x + 16, y, 7, 7, "north");
			else if (rand2 <= 0.667)
				constructRoom(x + 16, y, 7, 7, "west");
			else if (rand2 <= 0.833)
				constructRoom(x + 16, y + 16, 7, 7, "south");
			else
				constructRoom(x + 16, y + 16, 7, 7, "west");
		} else if (rand <= 0.75) {
			constructRoom(x, y + 14, 9, 9, "east");
			constructRoom(x, y + 14, 9, 9, "south");
			if (rand2 <= 0.167)
				constructRoom(x, y, 7, 7, "north");
			else if (rand2 <= 0.333)
				constructRoom(x, y, 7, 7, "east");
			else if (rand2 <= 0.5)
				constructRoom(x + 16, y, 7, 7, "north");
			else if (rand2 <= 0.667)
				constructRoom(x + 16, y, 7, 7, "west");
			else if (rand2 <= 0.833)
				constructRoom(x + 16, y + 16, 7, 7, "south");
			else
				constructRoom(x + 16, y + 16, 7, 7, "west");
		} else if (rand <= 0.875) {
			constructRoom(x + 14, y + 14, 9, 9, "south");
			if (rand2 <= 0.167)
				constructRoom(x, y + 16, 7, 7, "south");
			else if (rand2 <= 0.333)
				constructRoom(x, y + 16, 7, 7, "east");
			else if (rand2 <= 0.5)
				constructRoom(x + 16, y, 7, 7, "north");
			else if (rand2 <= 0.667)
				constructRoom(x + 16, y, 7, 7, "west");
			else if (rand2 <= 0.833)
				constructRoom(x, y, 7, 7, "north");
			else
				constructRoom(x, y, 7, 7, "east");
		} else {
			constructRoom(x + 14, y + 14, 9, 9, "west");
			if (rand2 <= 0.167)
				constructRoom(x, y + 16, 7, 7, "south");
			else if (rand2 <= 0.333)
				constructRoom(x, y + 16, 7, 7, "east");
			else if (rand2 <= 0.5)
				constructRoom(x + 16, y, 7, 7, "north");
			else if (rand2 <= 0.667)
				constructRoom(x + 16, y, 7, 7, "west");
			else if (rand2 <= 0.833)
				constructRoom(x, y, 7, 7, "north");
			else
				constructRoom(x, y, 7, 7, "east");
		}
	}

	public void constructStore(int x1, int y1, String direction) {
		int x = x1 + 8;
		int y = y1 + 15;
		switch (direction.toLowerCase()) {
		case "north":
			constructRoom(x + 7, y - 7, 39, 25, "north");
			constructRoom(x - 15 + 7, y + 4 - 7, 15, 17, "east");
			constructRoom(x + 39 + 7, y + 4 - 7, 15, 17, "west");
			constructRoom(x + 10 + 7, y - 8 - 7, 18, 8, "north");
			break;
		case "south":
			constructRoom(x + 7, y - 15, 39, 25, "south");
			constructRoom(x - 15 + 7, y + 4 - 15, 15, 17, "east");
			constructRoom(x + 39 + 7, y + 4 - 15, 15, 17, "west");
			constructRoom(x + 11 + 7, y + 25 - 15, 18, 8, "south");
			break;
		case "east":
			constructRoom(x, y, 25, 39, "east");
			constructRoom(x + 4, y + 39, 17, 15, "south");
			constructRoom(x + 4, y - 15, 17, 15, "north");
			constructRoom(x - 4, y + 11, 8, 18, "east");
			break;
		case "west":
			constructRoom(x - 8, y, 25, 39, "west");
			constructRoom(x + 4 - 8, y + 39, 17, 15, "south");
			constructRoom(x + 4 - 8, y - 15, 17, 15, "north");
			constructRoom(x + 25 - 8, y + 10, 8, 18, "west");
			break;
		}
	}

	/*
	 * Constructs a town within the given rectangle
	 */
	public void constructTown(Rectangle bounds) {
		if (bounds.height > bounds.width) {
//			paveRoad(bounds.x + bounds.width / 2 - 3, bounds.y, bounds.height, true,
//					((double) bounds.width) / (double) bounds.height);
			paveRoad(bounds);
		} else {
//			paveRoad(bounds.y + bounds.height / 2 - 3, bounds.x, bounds.width, false,
//					((double) bounds.height) / (double) bounds.width);
			paveRoad(bounds);
		}
		/*
		 * building distribution(rough) 70% houses(23x23) 30% stores(69x33)
		 */
		System.out.println("Placing buildings");
		boolean stop = false;
		boolean placed = false;
		while (!stop) {
			int building = (int) (Math.random() * 100);
			//System.out.println(building);
			placed = false;
			for (int x = bounds.x; x < bounds.x + bounds.width; x++) {
				for (int y = bounds.y; y > bounds.y - bounds.height; y--) {
					boolean east = !(map.getTile(new Point(x + 1, y)).getTerrain().getName().equals("sparse"));
					boolean west = !(map.getTile(new Point(x - 1, y)).getTerrain().getName().equals("sparse"));
					boolean north = !(map.getTile(new Point(x, y + 1)).getTerrain().getName().equals("sparse"));
					boolean south = !(map.getTile(new Point(x, y - 1)).getTerrain().getName().equals("sparse"));
					String name = map.getTile(new Point(x, y + 1)).getTerrain().getName();
//					 System.out.println("north: " + north + " south: " + south
//					 + " east: " + east + " west: " + west + "(" + x + ", " +
//					 y + ")");
					if (north && !(east && west && south) && (name.endsWith("road")) && !placed) {
						 if(building < 30) {
							 boolean empty = map.isEmpty(new Rectangle(x - 36, y - 1, 73, 33));
							 if(empty) {
								 constructStore(x - 34, y - 34, "north");
								 placed = true;
							 }
						 } else {
						 boolean empty = map.isEmpty(new Rectangle(x - 13, y - 1, 27, 23));
						 if(empty) {
							 constructHouse(x - 11, y - 24, "north");
							 placed = true;
						 	}
						 }
					}
					name = map.getTile(new Point(x, y - 1)).getTerrain().getName();
					if (south && !(east && north && west) && (name.endsWith("road")) && !placed) {
						if (building < 30) {
							boolean empty = map.isEmpty(new Rectangle(x - 36, y + 34, 73, 33));
							if (empty) {
								constructStore(x - 34, y, "south");
								placed = true;
							}
						} else {
							boolean empty = map.isEmpty(new Rectangle(x - 13, y + 23, 27, 23));
							if (empty) {
								constructHouse(x - 11, y, "south");
								placed = true;
							}
						}
					}
					name = map.getTile(new Point(x - 1, y)).getTerrain().getName();
					if (west && !(north && south && east) && (name.endsWith("road")) && !placed) {
						 if(building < 30) {
							 boolean empty = map.isEmpty(new Rectangle(x + 1, y + 37, 33, 75));
						 	if(empty) {
						 		constructStore(x + 1, y - 34, "west");
						 		placed = true;
						 	}
						 } else {
							 boolean empty = map.isEmpty(new Rectangle(x + 1, y + 14, 23, 29));
							 if(empty) {
								 constructHouse(x + 1, y - 11, "west");
								 placed = true;
							 }
						 }
					}
					name = map.getTile(new Point(x + 1, y)).getTerrain().getName();
					if (east && !(north && south && west) && (name.endsWith("road")) && !placed) {
						 if(building < 30) {
							 boolean empty = map.isEmpty(new Rectangle(x - 33, y + 36, 33, 73));
							 if(empty) {
								 constructStore(x - 33, y - 34, "east");
								 placed = true;
							 }
						 } else {
							 boolean empty = map.isEmpty(new Rectangle(x - 23, y + 13, 23, 27));
							 if(empty) {
								 constructHouse(x - 24, y - 11, "east");
								 placed = true;
							 }
						 }
					}
				}
			}
			//System.out.println("this could be an infinite loop (" + placed + ")");
			if(!placed) stop = true;
		}
	}

	public void paveRoad(int x, int y, int length, boolean vertical, double ratio) {
		paveOldRoad(x, y, length, vertical, ratio, 0);
	}

	/*
	 * Creates and populates a road with upper left corner at (x, y), extending
	 * for length units Pass true for a vertical road, false for a horizontal
	 * one Will recursively create smaller branch roads for the town creator to
	 * put buildings next to Intended specifically for use in towns, so only
	 * makes width 5 roads
	 */
	public void paveOldRoad(int x, int y, int length, boolean vertical, double ratio, int counter) {
		if (length < 24)
			return;
		if (counter > 2)
			return;
		System.out.println("PAVE! " + length + " " + vertical);
		if (vertical) {
			for (int i = 0; i < 5; i++) {
				paveVRoad(x + i, y, length);
			}
			for (int dY = y; dY < length; dY += 70) {
				int newLength = (int) Math.round((double) length * ratio);
				paveOldRoad(x - newLength / 2 + 2, y - dY, newLength, false, ratio, counter++);
			}
		} else if (!vertical) {
			for (int i = 0; i < 5; i++) {
				paveHRoad(x, y + i, length);
			}
			for (int dX = x; dX < length; dX += 69 + Math.random() * 10) {
				int newLength = (int) Math.round((double) length * ratio);
				paveOldRoad(x + dX, y + newLength / 2 + 2, newLength, true, ratio, counter++);
			}
		}
	}
	
	public void paveRoad(Rectangle bounds) {
		System.out.println("paving roads");
		int sx = bounds.x;
		int sy = bounds.y;
		int ex = bounds.x + bounds.width;
		int ey = bounds.y - bounds.height;
		for(int x = sx; x < ex; x += 60 + (int)(Math.random() * 30)) {
			for (int i = 0; i < 5; i++) {
				paveVRoad(x + i, sy, bounds.height);
			}
		}
		for(int y = sy; y > ey; y-= 60 + (int)(Math.random() * 30)) {
			for (int i = 0; i < 5; i++) {
				paveHRoad(sx, y - i, bounds.width);
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
	
	public int getActiveMap() {
		return activeMap;
	}

	public void setActiveMap(int activeMap) {
		this.activeMap = activeMap;
	}
	
	public void moveUp() {
		activeMap++;
		WorldMap newMap = maps.get(activeMap);
		if(newMap == null) {
			newMap = new WorldMap(this, 80, 80, new Point(player.getX(), player.getY()), 80*80);
			maps.put(activeMap, newMap);
		}
		map = newMap;
		map.setCamera(new Point(player.getX(), player.getY()));
	}
	
	public void moveDown() {
		activeMap--;
		WorldMap newMap = maps.get(activeMap);
		if(newMap == null) {
			newMap = new WorldMap(this, 80, 80, new Point(player.getX(), player.getY()), 80*80);
			maps.put(activeMap, newMap);
		}
		map = newMap;
		map.setCamera(new Point(player.getX(), player.getY()));
	}
	
	public void spawnCreature(Creature newGuy) {
		map.getTile(new Point(newGuy.getX(), newGuy.getY())).setCreature((Creature) newGuy);
		map.addDynamic((Dynamic) newGuy);
	}
}
