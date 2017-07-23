import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Weapon extends Item {
	private int accuracy, range;//accuracy is stored as a whole number percent
	private double attackMod;
	private Item[] inventory = new Item[1];
	private String[] ammo;
	private int capacity;
	
	public Weapon(Game ge, char i, Color c, String name, String[] tags, int x, int y, boolean p, int w, int v, int d, int dm, double dmg, int a, int r, double am, String[] lol, int ca) {
		super(ge, i, c, name, tags, x, y, p, w, v, d, dm, dmg);
		accuracy = a;
		range = r;
		attackMod = am;
		this.ammo = lol;
		capacity = ca;
	}
	
	public Weapon(Game ge, char i, Color c, String name, String[] tags, int x, int y, boolean p, int w, int v, int d, int dm, double dmg, int a, int r, double am, String[] lol) {
		super(ge, i, c, name, tags, x, y, p, w, v, d, dm, dmg);
		accuracy = a;
		range = r;
		attackMod = am;
		this.ammo = lol;
		capacity = 0;
	}
	
	public Weapon(Game ge, char i, Color c, String name, String[] tags, int x, int y, int w, int v, int d, int dm, double dmg, int a, int r, double am, String[] amm) {
		super(ge, i, c, name, tags, x, y, true, w, v, d, dm, dmg);
		accuracy = a;
		range = r;
		attackMod = am;
		this.ammo = amm;
		capacity = 0;
	}
	
	public Weapon(Game ge, char i, Color c, String name, String[] tags, int x, int y, int w, int v, int d, int dm, double dmg, int a, int r, double am, String[] amm, int ca) {
		super(ge, i, c, name, tags, x, y, true, w, v, d, dm, dmg);
		accuracy = a;
		range = r;
		attackMod = am;
		this.ammo = amm;
		capacity = ca;
	}
	
	public Weapon(Weapon origin) {
		super(origin);
		accuracy = origin.getAccuracy();
		range = origin.getRange();
		attackMod = origin.getAttackMod();
		
		int size;
		if (origin.getInventory() != null && (size = origin.getInventory().length) > 0) {
			inventory = new Item[size];
			for (int i = 0; i < size; i++) {
				inventory[i] = origin.getInventory()[i];
			}
		} else {
			inventory = null;
		}
		
		String[] originArmo = origin.getAmmo();
		ammo = new String[originArmo.length];
		if (originArmo != null) {
			for (int i = 0; i < originArmo.length; i++) {
				ammo[i] = originArmo[i];
			}
		}
	}
	
	public void fire(int x, int y, int tX, int tY, int accMod, Creature host) {
		System.out.println("bam");
		double d = Game.dist(x, y, tX, tY);
		if(d < range) return;
		double p = 60/(double)range;
		if(Math.random() * 101 < accuracy + accMod - p * d + 100) {
			Creature c = super.getGame().getMap().getPoint(new Point(tX, tY)).hasCreature();
			if(c != null) {
				System.out.println(c.getName());
				c.hit(attackMod * inventory[0].getDamage(), inventory[0], host);
			}
		}
		inventory[0].setDurability(inventory[0].getDurability() - 1);
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
	public Item[] getInventory() {
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(Item[] inventory) {
		this.inventory = inventory;
	}

	public String[] getAmmo() {
		return ammo;
	}
	
	public void setAmmo(String[] ammo) {
		this.ammo = ammo;
	}
	
	public void load(Item e) {
		if(inventory[0] != null) {
			if(!e.getName().equalsIgnoreCase(inventory[0].getName())) return;
		}
		for(String name: ammo) {
			if(e.getName().equalsIgnoreCase(name) && inventory[0] == null) {
				if(e.getDurability() > capacity) {
					inventory[0] = new Item(e);
					inventory[0].setDurability(capacity);
					e.setDurability(e.getDurability() - capacity);
				} else {
					inventory[0] = e;
				}
			}
			return;
		}
	}
	
	public ArrayList<Item> unload() {
		ArrayList<Item> r = new ArrayList<Item>();
		for(Item e : inventory) {
			r.add(e);
		}
		inventory[0] = null;
		return r;
	}

	public void use() {
		
	}
}
