import java.util.ArrayList;

public class Tile {
	private Terrain terrain;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Tile(Terrain t, ArrayList<Entity> i) {
		terrain = t;
		entities = i;
	}
	
	public Tile(Terrain t, Entity e) {
		terrain = t;
		entities.add(e);
	}
	
	public Tile(Terrain t) {
		terrain = t;
	}

	public void addItem(Entity e) {
		entities.add(e);
	}

	public Entity removeCreature(Entity e) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) == e)
				return entities.remove(i);
		}
		return null;
	}

	public Creature getCreature() {
		if (entities != null && entities.size() > 0) {
			for (Entity e : entities) {
				if (e instanceof Creature) {
					return (Creature) e;
				}
			}
		}
		return null;
	}
	
	public void removeCreature() {
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof Creature) {
				entities.remove(i);
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
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setCreatures(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
