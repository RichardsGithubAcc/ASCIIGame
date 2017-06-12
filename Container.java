import java.awt.Color;
import java.util.ArrayList;

public class Container extends Item {
	private ArrayList<Item> inventory;
	private int volumeStored;
	private int maxVolume;
	
	public Container(Game ge, char i, Color c, String name, String[] t, int x, int y, 
			boolean p, int w, int v, int d, int dm, double dmg, int vS, int mV) {
		super(ge, i, c, name, t, x, y, p, w, v, d, dm, dmg);
		volumeStored = vS;
		maxVolume = mV;
	}
	
	public void addToInventory(Item obj) {
		if (obj.getVolume() + volumeStored <= maxVolume) {
			inventory.add(obj);
			volumeStored += obj.getVolume();
		}
	}

	public boolean isInInventory(Item obj) {
		for(int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) == obj) return true;
		}
		return false;
	}

	public Item removeFromInventory(Item obj) {
		if (isInInventory(obj)) {
			inventory.remove(obj);
			volumeStored -= obj.getVolume();
			return obj;
		}
		return null;
	}

	@Override public void use() {
		// TODO Auto-generated method stub

	}
}