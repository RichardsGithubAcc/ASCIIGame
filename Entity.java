import java.awt.Color;

public abstract class Entity {
	public Game g;
	private char icon; 
	private Color color;
	private String name;
	private int x;
	private int y;
	private boolean passable;
	
	public Entity() {
		
	}
	
	public Entity(Game g, char i, Color c, String n, int x, int y, boolean p) {
		this.g = g;
		icon = i; color = c; name = n; x = x; y = y; passable = p;
	}

	/**
	 * @return the icon
	 */
	public char getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(char icon) {
		this.icon = icon;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the passable
	 */
	public boolean isPassable() {
		return passable;
	}

	/**
	 * @param passable the passable to set
	 */
	public void setPassable(boolean passable) {
		this.passable = passable;
	}
	
}
