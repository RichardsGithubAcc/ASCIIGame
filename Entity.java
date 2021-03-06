import java.awt.Color;
import java.util.ArrayList;

public abstract class Entity {

	private char icon;
	private Color color;
	private String name;
	private int x;
	private int y;
	private boolean passable;
	private String[] tags;
	private Game game;
	
	public Entity() {
		
	}
	
	public Entity(Game g, char i, Color c, String n, String[] t, int x, int y, boolean p) {
		this.game = g;
		tags = t;
		icon = i; color = c; name = n; this.x = x; this.y = y; passable = p;

	}
	
	public Entity(Entity origin) {
		
		icon = origin.getIcon();
		color = origin.getColor();
		name = new String(origin.getName());
		x = origin.getX();
		y = origin.getY();
		passable = origin.isPassable();
		
		String[] originTags = origin.getTags();
		int size;
		if (originTags != null && (size = originTags.length) > 0) {
			
			tags = new String[size];
			for (int i = 0; i < size; i++) {
				tags[i] = new String(originTags[i]);
			}	
		}
		else {
			tags = null;
		}
		
		game = origin.getGame();
	}
	
	public boolean hasTag(String str) {
		if (tags == null) {
			return false;
		}
		for(String lol : tags) {
			if(str.equals(lol)) return true;
		}
		return false;
	}

	/**
	 * @return the tags
	 */
	public String[] getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Game getGame() {
		return game;
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

	public void setXY(int x, int y) {
		this.x = x;
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
