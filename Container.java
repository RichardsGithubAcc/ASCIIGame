import java.util.ArrayList;

public class Container extends Item implements Usable {
	private ArrayList<Item> inventory;
	private int volumeStored;
	private int maxVolume;
	
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
			return obj;
		}
		return null;
	}

	@Override public void use() {
		// TODO Auto-generated method stub

	}
}