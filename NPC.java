import java.awt.Color;
import java.awt.Point;

public class NPC extends Creature {
	private boolean hostile;
	private Point tether;

	public NPC(Game g,char i, Color c, String n, int x, int y, boolean p, int h, int armV, int mH, double hM, double armM,
			double atkM, int dH, boolean ho) {
		super(g, i, c, n, x, y, p, h, armV, mH, hM, armM, atkM, dH);
		hostile = ho;
		// TODO Auto-generated constructor stub
	}
	
	public void moveTowards(int x, int y) {
		int dX = x - super.getX();
		int dY = y - super.getY();
		if(Math.abs(dX) > Math.abs(dY)) {
			if(Math.signum(dX) == 1) {
				super.moveLeft();
			} else {
				super.moveRight();
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
		Game game = super.getGame();
		Player player = game.getPlayer();
		int d = game.dist(super.getX(), super.getY(), player.getX(), player.getY());
		if(hostile && d < 20) {
			int dX = player.getX() - super.getX();
			int dY = player.getY() - super.getY();
			if(d <= 1) {
				if(dX == 1 && dY == 0) {
					
				}
			}
			if(Math.abs(dX) > Math.abs(dY)) {
				if(Math.signum(dX) == 1) {
					super.moveLeft();
				} else {
					super.moveRight();
				}
			} else {
				if(Math.signum(dY) == 1) {
					super.moveUp();
				} else {
					super.moveDown();
				}
			}
		} else {
			if(tether != null && game.dist(super.getX(), super.getY(), tether.x, tether.y) > 10) {
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
	
	
}
