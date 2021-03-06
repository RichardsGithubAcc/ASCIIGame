import java.awt.Color;

public class Item extends Entity implements Usable {
	private double weight;//in kilograms
	private double volume;//in liters
	private int durability, maxDurability;
	private double damage;
	
	public Item() {
		
	}
	
	/**
	 * 
	 * @param ge game
	 * @param i icon
	 * @param c color
	 * @param name name
	 * @param t tags
	 * @param x
	 * @param y
	 * @param p passable
	 * @param w weight
	 * @param v volume
	 * @param d durability
	 * @param dm maxDurability
	 * @param dmg damage
	 */
	public Item(Game ge, char i, Color c, String name, String[] t, int x, int y, boolean p, double w, double v, int d, int dm, double dmg) {
		super(ge, i, c, name, t, x, y, p);
		weight = w;
		volume = v;
		durability = d;
		maxDurability = dm;
		damage = dmg;
	}
	
	public Item(Game ge, char i, Color c, String name, String[] t, double w, double v, int d, int dm, double dmg) {
		super(ge, i, c, name, t, 0, 0, true);
		weight = w;
		volume = v;
		durability = d;
		maxDurability = dm;
		damage = dmg;
	}
	
	public Item(Item origin) {
		super(origin);
		weight = origin.getWeight();
		volume = origin.getVolume();
		durability = origin.getDurability();
		maxDurability = origin.getMaxDurability();
		damage = origin.getDamage();
	}
	
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}



	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}



	/**
	 * @return the volume
	 */
	public double getVolume() {
		return volume;
	}



	/**
	 * @param volume the volume to set
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}



	/**
	 * @return the durability
	 */
	public int getDurability() {
		return durability;
	}



	/**
	 * @param durability the durability to set
	 */
	public void setDurability(int durability) {
		this.durability = durability;
	}



	/**
	 * @return the maxDurability
	 */
	public int getMaxDurability() {
		return maxDurability;
	}



	/**
	 * @param maxDurability the maxDurability to set
	 */
	public void setMaxDurability(int maxDurability) {
		this.maxDurability = maxDurability;
	}



	/**
	 * @return the damage
	 */
	public double getDamage() {
		return damage;
	}



	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}



	public void use() {
		
	}
}
