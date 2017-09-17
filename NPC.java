import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

public class NPC extends Creature {
	private boolean hostile;
	private Point tether;
	private LinkedList<Point> path;

	public NPC(Game ge, char i, Color c, String n, String[] tags, int x, int y, boolean p, int h, int armV, int mH, double hM, double armM,
			double atkM, int dH, boolean ho) {
		super(ge, i, c, n, tags, x, y, p, h, armV, mH, hM, armM, atkM, dH);
	
		hostile = ho;
	}
	
	public NPC(Game ge, char i, Color c, String n, String[] tags, int x, int y, int h, int armor, boolean ho) {
		super(ge, i, c, n, tags, x, y, h, armor);
		hostile = ho;
	}
	
	public void moveTowards(int x, int y) {
		int dX = x - super.getX();
		int dY = y - super.getY();
		if(Math.abs(dX) > Math.abs(dY)) {
			if(Math.signum(dX) == 1) {
				super.moveRight();
			} else {
				super.moveLeft();
			}
		} else {
			if(Math.signum(dY) == 1) {
				super.moveUp();
			} else {
				super.moveDown();
			}
		}
	}
	
	public void update() {
		super.update();
		if(super.getDelay() > 0) return;
		Player lol = super.getGame().getPlayer();
		int playerX = lol.getX();
		int playerY = lol.getY();
		double d = Game.dist(super.getX(), super.getY(), playerX, playerY);
		if(hostile && d < 35) {
			if(d == 1) {
				super.attack(super.getGame().getPlayer());
			} else {
				//moveTowards(playerX, playerY);
				Point target = new Point(lol.getX(), lol.getY());
				boolean stop = false;
				for(int dX = -1; dX < 2; dX++) {
					for(int dY = -1; dY < 2; dY++) {
						if(Math.abs(dX + dY) == 1) {
							Tile t = super.getGame().getMap().getTile(new Point(playerX + dX, playerY + dY));
							if(t.getTerrain().isPassable() && t.getCreature() == null && !stop) {
								target = new Point(target.x + dX, target.y + dY);
								stop = true;
							}
						}
					}
				}
				if(path == null || ((path.size() < 10 || d < 10) && super.getDelay() == 0)) {//fix this to if the target moves too much
					AStar foo = new AStar(super.getGame().getMap(), new Point(super.getX(), super.getY()), target);
					path = foo.getPath();//this is pretty inefficient, find a way to not constantly re-search the path
				}
				//if(path.size() > 0)path.removeFirst();
				if(path != null && path.size() > 0) {//&& super.getGame().getMap().getTile(path.getFirst()).getCreature() == null) {
					moveTowards(path.getFirst().x, path.getFirst().y);
					path.removeFirst();
				}
			}
		} else {
			if(tether != null && Game.dist(super.getX(), super.getY(), tether.x, tether.y) > 10) {
				moveTowards(tether.x, tether.y);
			} else {
				int r = (int)(Math.random() * 9);
				switch (r) {
				case 1:super.moveDown();
					break;
				case 2: super.moveLeft();
					break;
				case 3: super.moveRight();
					break;
				case 4: super.moveUp();
					break;
				default: break;
				}
			}
		}
	}
	
	public void hit(int d, Item w, Creature c) {
		super.hit(d, w, c);
		hostile = true;
	}

	/**
	 * @return the hostile
	 */
	public boolean isHostile() {
		return hostile;
	}

	/**
	 * @param hostile the hostile to set
	 */
	public void setHostile(boolean hostile) {
		this.hostile = hostile;
	}

	/**
	 * @return the tether
	 */
	public Point getTether() {
		return tether;
	}

	/**
	 * @param tether the tether to set
	 */
	public void setTether(Point tether) {
		this.tether = tether;
	}

	public LinkedList<Point> getPath() {
		return path;
	}

	public void setPath(LinkedList<Point> path) {
		this.path = path;
	}
	
	
}
