import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Door extends Terrain implements Usable {
	private int orientation; // "horizontal" or "vertical"
	private String lock; // "key: #", 0 if door doesn't use lock
	private int status;
	
	public static final int IS_OPEN = 1;
	public static final int IS_CLOSED_WITHOUT_LOCK = 2;
	public static final int IS_CLOSED_WITH_LOCK = 3;
	
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	
	public Door(Game ge,  char i, Color c, String n, String[] tags, int x, int y, boolean p, int mM, int o, String l, int s) {
		super(ge, (p) ? '|' : 'O', c, n, tags, x, y, p, mM);
		orientation = o;
		lock = l;
		status = s;
	}
	
	public Door(Door d) {
		super(d.getGame(), d.getIcon(), d.getColor(), d.getName(), d.getTags(), d.getX(), d.getY(), d.isPassable(), d.getMoveMod());
		orientation = d.getOrientation(); lock = d.getLock(); status = d.status;
	}
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getOrientation() {
		return orientation;
	}
	
	public void setOrientation(int orientation) {
		this.orientation = orientation;
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
			}
			else {
				status = IS_CLOSED_WITH_LOCK ;
				super.setPassable(false);
			}
		}
		else {
			if (hasLock() && useLock) {
				status = IS_CLOSED_WITH_LOCK ;
				super.setPassable(false);
			}
			else {
				status = IS_CLOSED_WITHOUT_LOCK;
				super.setPassable(false);
			}
		}
	}
		
	public boolean open(String key) {
		if (status == IS_CLOSED_WITH_LOCK && !key.equals(lock)) {
			super.setPassable(false);
			return false;
		} else {
			status = IS_OPEN;
			super.setPassable(true);
			return true;
		}
	}

	@Override
	public void use() {	
		if(orientation == 1) {//horizontal
			Creature c = super.getGame().getMap().getPoint(new Point(super.getX() + 1, super.getY())).hasCreature();
			Creature c2 = super.getGame().getMap().getPoint(new Point(super.getX() - 1, super.getY())).hasCreature();
			ArrayList<Item> in = c.getInventory();
			for(Item i : in) {
				if(i.getTags() != null) {
					for(String tag : i.getTags()) {
						open(tag);
					}
				}
			}
			in = c2.getInventory();
			for(Item i : in) {
				if(i.getTags() != null) {
					for(String tag : i.getTags()) {
						open(tag);
					}
				}
			}
		}
		
		if(orientation == 2) {//vertical
			Creature c = super.getGame().getMap().getPoint(new Point(super.getX(), super.getY() + 1)).hasCreature();
			Creature c2 = super.getGame().getMap().getPoint(new Point(super.getX(), super.getY() - 1)).hasCreature();
			ArrayList<Item> in = c.getInventory();
			for(Item i : in) {
				if(i.getTags() != null) {
					for(String tag : i.getTags()) {
						open(tag);
					}
				}
			}
			in = c2.getInventory();
			for(Item i : in) {
				if(i.getTags() != null) {
					for(String tag : i.getTags()) {
						open(tag);
					}
				}
			}
		}
	}
 
}
