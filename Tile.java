import java.util.ArrayList;

public class Tile {
	private Terrain terrain;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Creature creature;
	private Item item;
	
	public Tile(Terrain t, Item i) {
		terrain = t;
		item = i;
	}
	
	public Tile(Terrain t, Creature c) {
		terrain = t;
		creature = c;
	}	
	
	public Tile(Terrain t) {
		terrain = t;
	}

	public void setItem(Item i) {
		item = i;
	}
	
	public void setCreature(Creature c) {
		creature = c;
	}

	public Item getItem() {
		return item;
	}
	
	public Creature getCreature() {
		return creature;
	}
	
	public void removeCreature() {
		creature = null;
	}

	/**
	 * @return the terrain
	 */
	public Terrain getTerrain() {
		return terrain;
	}

	/**
	 * @param terrain
	 *            the terrain to set
	 */
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
}
