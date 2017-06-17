import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Door extends Terrain {
	private String lock; // "key: #", 0 if door doesn't use lock
	private int status;
	private String direction;

	public static final int IS_OPEN = 1;
	public static final int IS_CLOSED_WITHOUT_LOCK = 2;
	public static final int IS_CLOSED_WITH_LOCK = 3;

	public Door(Game ge, Color c, String n, String[] tags, int x, int y, boolean p, int mM, String dir, String l,
			int s) {

		super(ge, (p) ? 'O' : ((dir.equalsIgnoreCase("south") || dir.equalsIgnoreCase("north")) ? '=' : '|'), c, n,
				tags, x, y, p, mM);
		direction = dir;
		lock = l;
		status = s;
	}

	public Door(Door d) {
		super(d.getGame(), d.getIcon(), d.getColor(), d.getName(), d.getTags(), d.getX(), d.getY(), d.isPassable(),
				d.getMoveMod());
		direction = d.getDirection();
		lock = d.getLock();
		status = d.status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public boolean hasLock() {
		return (lock != null);
	}

	public void close(boolean useLock) {
		if (status == IS_OPEN) {
			if (!hasLock() || !useLock) {
				status = IS_CLOSED_WITHOUT_LOCK;
				super.setPassable(false);
			} else {
				status = IS_CLOSED_WITH_LOCK;
				super.setPassable(false);
			}
		} else {
			if (hasLock() && useLock) {
				status = IS_CLOSED_WITH_LOCK;
				super.setPassable(false);
			} else {
				status = IS_CLOSED_WITHOUT_LOCK;
				super.setPassable(false);
			}
		}
		if (direction.equalsIgnoreCase("south") || direction.equalsIgnoreCase("north")) {
			super.setIcon('=');
		} else {
			super.setIcon('|');
		}
	}

	public boolean open(String key) {
		if (status == IS_CLOSED_WITH_LOCK && !key.equals(lock)) {
			super.setPassable(false);
			return false;
		} else {
			status = IS_OPEN;
			super.setPassable(true);
			super.setIcon('O');
			return true;
		}
	}

	@Override
	public void use() {
		if(this.status == 3) {
		if (direction.equalsIgnoreCase("south") || direction.equalsIgnoreCase("north")) {// horizontal
			Creature c = super.getGame().getMap().getPoint(new Point(super.getX(), super.getY() + 1)).hasCreature();
			Creature c2 = super.getGame().getMap().getPoint(new Point(super.getX(), super.getY() - 1)).hasCreature();
			if (c != null) {
				ArrayList<Item> in = c.getInventory();
				for (Item i : in) {
					if (i.getTags() != null) {
						for (String tag : i.getTags()) {
							open(tag);
						}
					}
				}
			}
			if (c2 != null) {
				ArrayList<Item> in = c2.getInventory();
				for (Item i : in) {
					if (i.getTags() != null) {
						for (String tag : i.getTags()) {
							open(tag);
						}
					}
				}
			}
		}

		if (direction.equalsIgnoreCase("east") || direction.equalsIgnoreCase("west")) {// vertical
			Creature c = super.getGame().getMap().getPoint(new Point(super.getX() + 1, super.getY())).hasCreature();
			Creature c2 = super.getGame().getMap().getPoint(new Point(super.getX() - 1, super.getY())).hasCreature();
			if (c != null) {
				ArrayList<Item> in = c.getInventory();
				for (Item i : in) {
					if (i.getTags() != null) {
						for (String tag : i.getTags()) {
							open(tag);
						}
					}
				}
			}
			if (c2 != null) {
				ArrayList<Item> in = c2.getInventory();
				for (Item i : in) {
					if (i.getTags() != null) {
						for (String tag : i.getTags()) {
							open(tag);
						}
					}
				}
			}
		}
		} else {
			if(this.status == 1) {
				close(false);
				status = 2;
			} else {
				if(status == 2) {
					open(null);
				}
			}
		}
	}

}
