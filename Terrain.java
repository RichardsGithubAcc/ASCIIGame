import java.awt.Color;

public class Terrain extends Entity{
	private int moveMod;
	public Terrain(Game ge, char i, Color c, String n, String[] tags, int x, int y, boolean p, int mM) {
		super(ge, i, c, n, tags, x, y, p);
		setMoveMod(mM);
	}
	
	/**
	 * @return the moveMod
	 */
	public int getMoveMod() {
		return moveMod;
	}
	/**
	 * @param moveMod the moveMod to set
	 */
	public void setMoveMod(int moveMod) {
		this.moveMod = moveMod;
	}
	
}
