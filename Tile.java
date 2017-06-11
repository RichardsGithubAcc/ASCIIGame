import java.util.ArrayList;

public class Tile {
	private Terrain terrain;
	private ArrayList<Entity> items = new ArrayList<Entity>();

	public Tile(Terrain t, ArrayList<Entity> i) {
		terrain = t;
		items = i;
	}
	
	public Tile(Terrain t, Entity e) {
		terrain = t;
		items.add(e);
	}
	
	public Tile(Terrain t) {
		terrain = t;
	}

	public void addItem(Entity e) {
		items.add(e);
	}

	public Entity removeItem(Entity e) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) == e)
				return items.remove(i);
		}
		return null;
	}

	public Creature hasCreature() {
		if (items != null && items.size() > 0) {
			for (Entity e : items) {
				if (e instanceof Creature) {
					return (Creature) e;
				}
			}
		}
		return null;
	}
	
	public void removeCreature() {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i) instanceof Creature) {
				items.remove(i);
			}
		}
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

	/**
	 * @return the items
	 */
	public ArrayList<Entity> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(ArrayList<Entity> items) {
		this.items = items;
	}

}
