import java.awt.Color;
import java.util.ArrayList;

public class Weapon extends Item implements Usable {
	private int accuracy, range;//accuracy is stored as a whole number percent
	double attackMod;
	private ArrayList<Item> inventory;
	private ArrayList<String> ammo;
	
	public Weapon(char i, Color c, String name, int x, int y, boolean p, int w, int v, int d, int dm, double dmg, int a, int r, double am, ArrayList<String> lol) {
		super(i, c, name, x, y, p, w, v, d, dm, dmg);
		accuracy = a;
		range = r;
		attackMod = am;
		this.ammo = lol;
	}
	
	/**
	 * @return the accuracy
	 */
	public int getAccuracy() {
		return accuracy;
	}

	/**
	 * @param accuracy the accuracy to set
	 */
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	/**
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * @param range the range to set
	 */
	public void setRange(int range) {
		this.range = range;
	}

	/**
	 * @return the attackMod
	 */
	public double getAttackMod() {
		return attackMod;
	}

	/**
	 * @param attackMod the attackMod to set
	 */
	public void setAttackMod(double attackMod) {
		this.attackMod = attackMod;
	}

	/**
	 * @return the inventory
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	public void load(Item e) {
		if(inventory.size() > 0) {
			if(!e.getName().equalsIgnoreCase(inventory.get(1).getName())) return;
		}
		for(String name: ammo) {
			if(e.getName().equalsIgnoreCase(name)) inventory.add(e);
			return;
		}
	}
	
	public ArrayList<Item> unload() {
		ArrayList<Item> r = new ArrayList<Item>();
		for(Item e : inventory) {
			r.add(e);
		}
		inventory.clear();
		return r;
	}

	public void use() {
		
	}
}
