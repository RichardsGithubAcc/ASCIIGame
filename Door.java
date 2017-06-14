import java.awt.Color;

public class Door extends Terrain implements Usable {
	private int orientation; // "horizontal" or "vertical"
	private int lock; // "key: #", 0 if door doesn't use lock
	private int status;
	
	public static final int IS_OPEN = 1;
	public static final int IS_CLOSED_WITHOUT_LOCK = 2;
	public static final int IS_CLOSED_WITH_LOCK = 3;
	
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	
	public Door(Game ge,  char i, Color c, String n, String[] tags, int x, int y, boolean p, int mM, int o, int l, int s) {
		super(ge, i, c, n, tags, x, y, p, mM);
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
	
	public int getLock() {
		return lock;
	}
	
	public void setLock(int lock) {
		this.lock = lock;
	}
	
	public boolean hasLock() {
		return (lock != 0);
	}
	
	public void close(boolean useLock) {
		if (status == IS_OPEN) {
			if (!hasLock() || !useLock) {
				status = IS_CLOSED_WITHOUT_LOCK;
			}
			else {
				status = IS_CLOSED_WITH_LOCK ;
			}
		}
		else {
			if (hasLock() && useLock) {
				status = IS_CLOSED_WITH_LOCK ;
			}
			else {
				status = IS_CLOSED_WITHOUT_LOCK;
			}
		}
	}
		
	public boolean open(int key) {
		if (status == IS_CLOSED_WITH_LOCK && key != lock) {
			return false;
		} else {
			status = IS_OPEN;
			return true;
		}
	}

	@Override
	public void use() {		
	}
 
}
