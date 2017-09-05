import java.awt.Color;

public class Terrain extends Entity implements Usable{
	private int moveMod;
	
	/**
	 * 
	 * @param ge
	 * @param i icon
	 * @param c color
	 * @param n name
	 * @param tags 
	 * @param x
	 * @param y
	 * @param p passable
	 * @param mM moveMod
	 */
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
	
	public void use() {
		super.getGame().addProgress("That is a " + super.getName());
	}
}
