import java.awt.Color;

public class NPC extends Creature {
	private boolean hostile;

	public NPC(Game ge, char i, Color c, String n, int x, int y, boolean p, int h, int armV, int mH, double hM, double armM,
			double atkM, int dH, boolean ho) {
		super(ge, i, c, n, x, y, p, h, armV, mH, hM, armM, atkM, dH);
		hostile = ho;
		// TODO Auto-generated constructor stub
	}
	
	public void update() {
		if(hostile) {
			
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
