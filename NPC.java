import java.awt.Color;
import java.awt.Point;

public class NPC extends Creature {
	private boolean hostile;

	public NPC(Game ge, char i, Color c, String n, int x, int y, boolean p, int h, int armV, int mH, double hM, double armM,
			double atkM, int dH, boolean ho) {
		super(ge, i, c, n, x, y, p, h, armV, mH, hM, armM, atkM, dH);
		hostile = ho;
		// TODO Auto-generated constructor stub
	}
	
	public void update() {
		if(hostile && super.g.dist(super.getX(), super.getY(), super.g.player.getX(), super.g.player.getY()) < 20) {
			int dX = super.g.player.getX() - super.getX();
			int dY = super.g.player.getY() - super.getY();
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
	
	
}
