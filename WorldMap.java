import java.awt.Point;
import java.util.HashMap;

public class WorldMap {
	private HashMap map = new HashMap();
	
	public WorldMap() {
		
	}
	
	public WorldMap(int e) {
		map = new HashMap(e);
	}
	
	public void setPoint(Point p, Tile e) {
		map.put(p, e);
	}
	
	public Tile getPoint(Point p) {
		return (Tile)map.get(p);
	}
	
	public boolean isOccupied(Point p) {
		if(map.get(p) == null) return false;
		return true;
	}
}
