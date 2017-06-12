import java.awt.Color;

public class Terrain extends Entity{
	private int moveMod;
	
	public Terrain(Game ge, char i, Color c, String n, String[] tags, int x, int y, boolean p, int mM) {
		super(ge, i, c, n, tags, x, y, p);
		setMoveMod(mM);
	}
	
	public Terrain(Terrain t) {
		super(t.getGame(), t.getIcon(), t.getColor(), t.getName(), t.getTags(), t.getX(), t.getY(), t.isPassable());
		setMoveMod(t.getMoveMod());
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
