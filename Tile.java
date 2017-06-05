import java.util.ArrayList;

public class Tile{
	private Terrain terrain;
	private ArrayList<Entity> items;
	
	public Tile(Terrain t, ArrayList<Entity> i) {
		terrain = t;
		items = i;
	}
	
	public void addItem(Entity e) {
		items.add(e);
	}
	
	public Entity removeItem(Entity e) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i) == e) return items.remove(i);
		}
		return null;
	}

	/**
	 * @return the terrain
	 */
	public Terrain getTerrain() {
		return terrain;
	}

	/**
	 * @param terrain the terrain to set
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
	 * @param items the items to set
	 */
	public void setItems(ArrayList<Entity> items) {
		this.items = items;
	}
	
	
}
